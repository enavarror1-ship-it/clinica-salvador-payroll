package com.clinica.salvador;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.clinica.salvador.model.CommissionEmployee;
import com.clinica.salvador.service.PayrollResult;
import com.clinica.salvador.service.PayrollService;
import com.clinica.salvador.util.Config;

/**
 * Pruebas unitarias para CommissionEmployee.
 * Validamos cálculo de salario con base + comisiones.
 */
public class CommissionEmployeeTest {

    @Test
    void commissionEmployee_grossIncludesBaseAndCommission() {
        // Creamos un empleado con salario base $2.000.000, ventas 30.000.000 y comisión 10%
        CommissionEmployee emp = new CommissionEmployee(
                "C1", "Laura Gómez", LocalDate.now(), true,
                new BigDecimal("2000000"),
                new BigDecimal("30000000"),
                new BigDecimal("0.10")
        );

        PayrollService service = new PayrollService(List.of(emp), Config.DEDUCTION_RATE, Config.ARL_RATE);
        PayrollResult r = service.processAll().get(0);

        // Verificación: bruto = base + (ventas * comisión) + bono 3% por ventas > 20M + food allowance (por ser permanente)
        BigDecimal gross = new BigDecimal("2000000")
                .add(new BigDecimal("30000000").multiply(new BigDecimal("0.10")))
                .add(new BigDecimal("30000000").multiply(new BigDecimal("0.03")))
                .add(Config.FOOD_ALLOWANCE);
                
                System.out.println("Salario bruto esperado: $" + gross);
System.out.println("Salario bruto calculado por el sistema: $" + r.getGross());

        // Aserción: bruto calculado por el servicio debe coincidir con gross esperado
        assertEquals(0, gross.compareTo(r.getGross()));
    }
}

