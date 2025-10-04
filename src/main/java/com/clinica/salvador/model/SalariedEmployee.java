package com.clinica.salvador.model;

import com.clinica.salvador.util.Config;
import java.math.BigDecimal;

/**
 * Clase SalariedEmployee
 *
 * Representa a un empleado asalariado con un salario fijo mensual.
 * Puede recibir beneficios adicionales dependiendo de su antigüedad 
 * y si es empleado permanente.
 *
 * Principios SOLID aplicados:
 * - SRP (Single Responsibility Principle): se encarga solo del cálculo 
 *   de salario bruto de empleados asalariados.
 * - OCP (Open/Closed Principle): está abierta a extensión sin necesidad
 *   de modificar la clase base Employee.
 * - LSP (Liskov Substitution Principle): puede usarse donde se requiera
 *   un objeto de tipo Employee.
 */
public class SalariedEmployee extends Employee {

    /** Salario mensual fijo del empleado */
    private BigDecimal monthlySalary;

    /**
     * Constructor de SalariedEmployee.
     *
     * @param id Identificador del empleado
     * @param name Nombre del empleado
     * @param hireDate Fecha de contratación
     * @param permanent True si es permanente, False si es temporal
     * @param monthlySalary Salario mensual
     */
    public SalariedEmployee(String id, String name, java.time.LocalDate hireDate, boolean permanent, BigDecimal monthlySalary) {
        super(id, name, hireDate, permanent);
        this.monthlySalary = monthlySalary;
    }

    /**
     * Calcula el salario bruto de un empleado asalariado.
     * Fórmula:
     * - Salario mensual base
     * - Bono del 10% si tiene más de 5 años de servicio
     * - Auxilio de alimentación si es empleado permanente
     *
     * @return Salario bruto (BigDecimal)
     */
    @Override
    public BigDecimal calculateGross() {
        BigDecimal gross = monthlySalary;

        // Bono del 10% por antigüedad (> 5 años de servicio)
        if (yearsOfService() > 5) {
            gross = gross.add(monthlySalary.multiply(new BigDecimal("0.10"))); // bono 10%
        }

        // Si el empleado es permanente, se adiciona auxilio de alimentación
        if(isPermanent()){
            gross = gross.add(Config.FOOD_ALLOWANCE);
        }
        return gross;
    }
}
