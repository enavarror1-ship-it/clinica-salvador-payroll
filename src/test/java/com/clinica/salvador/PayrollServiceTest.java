package com.clinica.salvador;

import com.clinica.salvador.model.HourlyEmployee;
import com.clinica.salvador.service.PayrollResult;
import com.clinica.salvador.service.PayrollService;
import com.clinica.salvador.util.Config;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para PayrollService.
 * Validamos que el servicio procese varios empleados y calcule bien sus nóminas.
 */
public class PayrollServiceTest {

    @Test
    void testPayrollCalculationForMultipleEmployees() {
        // Creamos 2 empleados por horas
        HourlyEmployee emp1 = new HourlyEmployee("E001", "Ana Lopez", LocalDate.now(), false,
                new BigDecimal("20000"), new BigDecimal("40"), 12, false);
        HourlyEmployee emp2 = new HourlyEmployee("E002", "Juan Perez", LocalDate.now(), true,
                new BigDecimal("15000"), new BigDecimal("42"), 6, true);

        // Procesamos la nómina de ambos
        PayrollService service = new PayrollService(Arrays.asList(emp1, emp2), Config.DEDUCTION_RATE, Config.ARL_RATE);
        List<PayrollResult> results = service.processAll();

        // Verificamos que haya resultados para 2 empleados
        assertEquals(2, results.size());

        // Comprobamos que ningún neto sea negativo
        results.forEach(r -> assertTrue(r.getNet().compareTo(BigDecimal.ZERO) > 0));
    }
}
