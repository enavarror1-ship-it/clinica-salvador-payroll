package com.clinica.salvador.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Clase que representa a un Empleado Temporal.
 * - Tiene un salario mensual fijo (monthlySalary).
 * - Su cálculo de salario bruto (gross) es exactamente ese valor.
 */
public class TemporaryEmployee extends Employee {
    // Salario mensual del empleado temporal
    private BigDecimal monthlySalary;

    /**
     * Constructor de un empleado temporal.
     *
     * @param id Identificador único del empleado
     * @param name Nombre del empleado
     * @param hireDate Fecha de contratación
     * @param permanent Si el empleado es permanente (aunque aquí normalmente es falso)
     * @param monthlySalary Salario mensual fijo del empleado
     *
     * Reglas de validación:
     * - monthlySalary no puede ser nulo.
     * - monthlySalary debe ser mayor que 0.
     */
    public TemporaryEmployee(String id, String name, LocalDate hireDate, boolean permanent, BigDecimal monthlySalary) {
        super(id, name, hireDate, permanent);

        // Validación: el salario no puede ser nulo ni <= 0
        if (monthlySalary == null || monthlySalary.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Monthly salary must be greater than 0 and not null");
        }

        this.monthlySalary = monthlySalary;
    }

    /**
     * Calcula el salario bruto del empleado temporal.
     * Para este tipo de empleado, el salario bruto es exactamente el salario mensual.
     *
     * @return Salario bruto (monthlySalary)
     */
    @Override
    public BigDecimal calculateGross() {
        return monthlySalary;
    }
}
