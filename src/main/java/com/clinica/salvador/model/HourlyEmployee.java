package com.clinica.salvador.model;

import java.math.BigDecimal;

/**
 * Clase HourlyEmployee
 * 
 * Representa a un empleado cuyo salario depende de las horas trabajadas
 * y una tarifa horaria. Si el empleado trabaja horas extra (más de 40),
 * esas horas se pagan al 150%.
 * 
 * Principios SOLID aplicados:
 * - SRP (Single Responsibility Principle): la clase se encarga únicamente
 *   de calcular el salario de empleados por horas.
 * - OCP (Open/Closed Principle): se puede extender el sistema con nuevos
 *   tipos de empleados sin modificar esta clase.
 * - LSP (Liskov Substitution Principle): al extender Employee, puede ser
 *   sustituida en cualquier parte donde se use un empleado genérico.
 */
public class HourlyEmployee extends Employee {
    
    /** Tarifa horaria del empleado */
    private BigDecimal hourlyRate;
    
    /** Cantidad de horas trabajadas en el período */
    private BigDecimal hoursWorked;
    
    /** Meses de servicio en la empresa (para beneficios futuros) */
    private int monthsOfService;
    
    /** Indica si el empleado acepta fondo de ahorros */
    private boolean acceptSavingsFund;

    /**
     * Constructor de HourlyEmployee.
     *
     * @param id Identificador único del empleado
     * @param name Nombre del empleado
     * @param hireDate Fecha de contratación
     * @param permanent True si es permanente, False si es temporal
     * @param hourlyRate Tarifa horaria
     * @param hoursWorked Número de horas trabajadas
     * @param monthsOfService Meses de servicio
     * @param acceptSavingsFund Indica si participa en fondo de ahorros
     */
    public HourlyEmployee(String id, String name, java.time.LocalDate hireDate, boolean permanent,
    BigDecimal hourlyRate, BigDecimal hoursWorked, int monthsOfService, boolean acceptSavingsFund) {
        super(id, name, hireDate, permanent);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.monthsOfService = monthsOfService;
        this.acceptSavingsFund = acceptSavingsFund;
    }

    /**
     * Calcula el salario bruto de un empleado por horas.
     * Fórmula:
     * - Hasta 40 horas se pagan a tarifa normal.
     * - Horas adicionales (>40) se pagan con recargo del 50%.
     *
     * @return Salario bruto (BigDecimal)
     * @throws IllegalArgumentException si las horas trabajadas son negativas
     */
    @Override
    public BigDecimal calculateGross() {
        // Validación: las horas trabajadas no pueden ser negativas
        if (hoursWorked.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Las horas trabajadas no pueden ser negativas");
        }

        // Horas normales (máximo 40)
        BigDecimal baseHours = hoursWorked.min(new BigDecimal("40"));

        // Horas extras (mayores a 40)
        BigDecimal overtimeHours = hoursWorked.subtract(new BigDecimal("40")).max(BigDecimal.ZERO);

        // Salario bruto = horas normales * tarifa + horas extra * tarifa * 1.5
        BigDecimal gross = baseHours.multiply(hourlyRate)
                .add(overtimeHours.multiply(hourlyRate).multiply(new BigDecimal("1.5")));
                
        // Este empleado no recibe bonificaciones adicionales 

        return gross;
    }
}
