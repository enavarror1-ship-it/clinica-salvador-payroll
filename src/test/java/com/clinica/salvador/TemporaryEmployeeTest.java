package com.clinica.salvador;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.clinica.salvador.model.TemporaryEmployee;
import com.clinica.salvador.service.PayrollResult;
import com.clinica.salvador.service.PayrollService;
import com.clinica.salvador.util.Config;

/**
 * Pruebas unitarias para la clase TemporaryEmployee.
 * 
 * Aquí validamos que el cálculo de salario para empleados temporales
 * se realice correctamente y que se manejen los casos de error.
 */
public class TemporaryEmployeeTest {

    @Test
    void temporaryEmployee_grossEqualsMonthlySalary_andNetCalculated() {
        // Creamos un empleado temporal con un salario mensual fijo
        TemporaryEmployee temp = new TemporaryEmployee(
                "T1",                  // ID del empleado
                "María Pérez",         // Nombre del empleado
                LocalDate.now(),       // Fecha de contratación (hoy)
                false,                 // No es empleado permanente
                new BigDecimal("1200000") // Salario mensual
        );

        // Procesamos la nómina usando el PayrollService
        PayrollService service = new PayrollService(List.of(temp), Config.DEDUCTION_RATE, Config.ARL_RATE);
        List<PayrollResult> results = service.processAll();

        // Verificamos que solo haya un resultado
        assertEquals(1, results.size());

        // Obtenemos el resultado de la nómina del empleado temporal
        PayrollResult r = results.get(0);

        // Verificación 1: El salario bruto debe ser exactamente igual al mensual definido
        assertEquals(0, new BigDecimal("1200000").compareTo(r.getGross()));

        // Verificación 2: El salario neto debe ser bruto - deducciones (4% según Config)
        BigDecimal expectedNet = r.getGross().subtract(r.getGross().multiply(Config.DEDUCTION_RATE));
        assertEquals(0, expectedNet.compareTo(r.getNet()));
        System.out.println("Empleado temporal: María Pérez");
    System.out.println("Salario mensual fijo: $1.200.000");
    System.out.println("Salario bruto calculado: $" + r.getGross());

    System.out.println(); // Espacio visual

    System.out.println("Deducción aplicada (4%): $" + r.getGross().multiply(Config.DEDUCTION_RATE));
    System.out.println("Salario neto esperado: $" + expectedNet);
    System.out.println("Salario neto calculado: $" + r.getNet());

    }

    @Test
    void temporaryEmployee_nullSalary_throws() {
        // Verifica que si intentamos crear un empleado con salario null,
        // al calcular el salario bruto se lance IllegalArgumentException.
        assertThrows(IllegalArgumentException.class, () -> 
            new TemporaryEmployee("T2", "Error", LocalDate.now(), false, null).calculateGross()
        );
    }
}
