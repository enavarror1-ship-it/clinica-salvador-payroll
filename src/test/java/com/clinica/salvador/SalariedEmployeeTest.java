package com.clinica.salvador;

import com.clinica.salvador.model.SalariedEmployee;
import com.clinica.salvador.service.PayrollResult;
import com.clinica.salvador.service.PayrollService;
import com.clinica.salvador.util.Config;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para SalariedEmployee.
 */
public class SalariedEmployeeTest {

    @Test
    void salariedEmployee_grossIsMonthlySalary_withoutBonus() {
        // Empleado asalariado NO permanente: no aplica bono de alimentación
        SalariedEmployee emp = new SalariedEmployee(
                "S1", "Pedro Ramírez", LocalDate.now(), false,
                new BigDecimal("5000000")
        );

        PayrollService service = new PayrollService(List.of(emp), Config.DEDUCTION_RATE, Config.ARL_RATE);
        PayrollResult r = service.processAll().get(0);

        // Verificación: bruto == salario fijo (5.000.000)
        assertEquals(0, new BigDecimal("5000000").compareTo(r.getGross()));

        // Verificación: neto == bruto - deducciones (4%)
        BigDecimal expectedNet = r.getGross().subtract(r.getGross().multiply(Config.DEDUCTION_RATE));
        assertEquals(0, expectedNet.compareTo(r.getNet()));
    }

    @Test
    void salariedEmployee_grossIncludesFoodAllowance_whenPermanent() {
        // Empleado asalariado PERMANENTE: aplica bono de alimentación
        SalariedEmployee emp = new SalariedEmployee(
                "S2", "Laura Gómez", LocalDate.now(), true,
                new BigDecimal("5000000")
        );

        PayrollService service = new PayrollService(List.of(emp), Config.DEDUCTION_RATE, Config.ARL_RATE);
        PayrollResult r = service.processAll().get(0);

        // Verificación: bruto == salario fijo + food allowance
        BigDecimal expectedGross = new BigDecimal("5000000").add(Config.FOOD_ALLOWANCE);
        assertEquals(0, expectedGross.compareTo(r.getGross()));

        // Verificación: neto == bruto - deducciones (4%)
        BigDecimal expectedNet = r.getGross().subtract(r.getGross().multiply(Config.DEDUCTION_RATE));
        assertEquals(0, expectedNet.compareTo(r.getNet()));
    }
}

