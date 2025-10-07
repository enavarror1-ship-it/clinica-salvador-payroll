package com.clinica.salvador.model;

import java.math.BigDecimal;

import com.clinica.salvador.util.Config;

/**
 * Clase CommissionEmployee
 * Representa a un empleado que recibe un salario base más comisiones por ventas.
 * Aplica el principio de herencia (extiende Employee).
 * 
 * Principios SOLID aplicados:
 * - SRP (Single Responsibility Principle): esta clase se encarga únicamente de
 *   manejar la lógica de cálculo salarial de un empleado por comisión.
 * - OCP (Open/Closed Principle): se puede extender el sistema para crear otros
 *   tipos de empleados sin modificar esta clase.
 */
public class CommissionEmployee extends Employee {

    // Constantes
    private static final BigDecimal BONUS_THRESHOLD = new BigDecimal("20000000");
    private static final BigDecimal BONUS_PERCENT = new BigDecimal("0.03");


    /** Salario base fijo del empleado */
    private BigDecimal baseSalary;

    /** Monto total de las ventas realizadas por el empleado */
    private BigDecimal salesAmount;

    /** Porcentaje de comisión aplicada a las ventas */
    private BigDecimal commissionPercent;

    /**
     * Constructor de CommissionEmployee.
     *
     * @param id Identificador único del empleado
     * @param name Nombre del empleado
     * @param hireDate Fecha de contratación
     * @param permanent Indica si el empleado es permanente
     * @param baseSalary Salario base fijo
     * @param salesAmount Monto total de ventas
     * @param commissionPercent Porcentaje de comisión aplicada
     */
    public CommissionEmployee(String id, String name, java.time.LocalDate hireDate, boolean permanent,
    BigDecimal baseSalary, BigDecimal salesAmount, BigDecimal commissionPercent) {
        super(id, name, hireDate, permanent);
        this.baseSalary = baseSalary;
        this.salesAmount = salesAmount;
        this.commissionPercent = commissionPercent;
    }

    /**
     * Calcula el salario bruto del empleado (gross salary).
     * La fórmula incluye:
     * - Salario base
     * - Comisiones sobre ventas
     * - Bonificación adicional si supera 20,000,000 en ventas
     * - Auxilio de alimentación en caso de ser empleado permanente
     *
     * @return Salario bruto (BigDecimal)
     */
    @Override
    public BigDecimal calculateGross() {
        // Validación: las ventas no pueden ser negativas
        if(salesAmount.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Sales cannot be negative");

        // Salario bruto = baseSalary + (ventas * porcentaje de comisión)
        BigDecimal gross = baseSalary.add(salesAmount.multiply(commissionPercent));

        // Si las ventas superan 20 millones, se da una bonificación del 3%
        if(salesAmount.compareTo(new BigDecimal("20000000")) > 0) {
            gross = gross.add(salesAmount.multiply(new BigDecimal("0.03")));
        }

        // Si el empleado es permanente, se adiciona el auxilio de alimentación
        if(isPermanent()){
            gross = gross.add(Config.FOOD_ALLOWANCE);
        }
        return gross;
    }
}
