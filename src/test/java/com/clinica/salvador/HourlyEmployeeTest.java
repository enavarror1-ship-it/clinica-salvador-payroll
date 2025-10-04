package com.clinica.salvador;

import com.clinica.salvador.model.HourlyEmployee;
import com.clinica.salvador.service.PayrollResult;
import com.clinica.salvador.service.PayrollService;
import com.clinica.salvador.util.Config;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para HourlyEmployee.
 * Validamos el cálculo del salario bruto y neto de empleados por horas.
 */
public class HourlyEmployeeTest {

    @Test
    void hourlyEmployee_baseAndOvertimeCalculated() {
        // Creamos un empleado por horas con tarifa $20.000 y 45 horas trabajadas
        HourlyEmployee emp = new HourlyEmployee(
                "H1", "Carlos Ruiz", LocalDate.now(), false,
                new BigDecimal("20000"), new BigDecimal("45"), 12, false
        );

        PayrollService service = new PayrollService(List.of(emp), Config.DEDUCTION_RATE, Config.ARL_RATE);
        PayrollResult r = service.processAll().get(0);

        // Verificación: 40h normales + 5h extra con 1.5x => calculamos el bruto esperado
        BigDecimal base = new BigDecimal("40").multiply(new BigDecimal("20000"));
        BigDecimal overtime = new BigDecimal("5").multiply(new BigDecimal("20000")).multiply(new BigDecimal("1.5"));
        BigDecimal expectedGross = base.add(overtime);
        // Aserción: bruto calculado por el servicio debe coincidir con expectedGross
        assertEquals(0, expectedGross.compareTo(r.getGross()));

        // Verificación: neto = bruto - deducciones (4% según Config)
        BigDecimal expectedNet = expectedGross.subtract(expectedGross.multiply(Config.DEDUCTION_RATE));
        // Aserción: neto calculado debe coincidir con expectedNet
        assertEquals(0, expectedNet.compareTo(r.getNet()));
    }
}
