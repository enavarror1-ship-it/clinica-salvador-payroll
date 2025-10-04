package com.clinica.salvador.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Clase abstracta Employee
 * 
 * Representa a un empleado genérico dentro del sistema de nómina.
 * Esta clase define los atributos y comportamientos comunes a todos
 * los empleados, mientras que las subclases deben implementar el 
 * cálculo específico del salario bruto.
 * 
 * Principios SOLID aplicados:
 * - SRP (Single Responsibility Principle): administra atributos y 
 *   métodos comunes de empleados.
 * - OCP (Open/Closed Principle): está abierta a extensión mediante 
 *   subclases (ej. empleados por comisión, por horas, asalariados).
 * - LSP (Liskov Substitution Principle): cualquier subclase puede 
 *   sustituir a Employee sin romper el sistema.
 */
public abstract class Employee {
    
    /** Identificador único del empleado */
    private String id;
    
    /** Nombre completo del empleado */
    private String name;
    
    /** Fecha de contratación del empleado */
    private LocalDate hireDate;
    
    /** Indica si el empleado es permanente */
    private boolean permanent;

    /**
     * Constructor de Employee.
     *
     * @param id Identificador del empleado
     * @param name Nombre del empleado
     * @param hireDate Fecha de contratación
     * @param permanent True si es empleado permanente, false si es temporal
     */
    public Employee(String id, String name, LocalDate hireDate, boolean permanent) {
        this.id = id;
        this.name = name;
        this.hireDate = hireDate;
        this.permanent = permanent;
    }

    // --- Métodos de acceso (Getters) ---
    public String getId(){ return id; }
    public String getName(){ return name; }
    public LocalDate getHireDate(){ return hireDate; }
    public boolean isPermanent(){ return permanent; }

    /**
     * Calcula los años de servicio del empleado a partir de la fecha de contratación.
     * 
     * @return años de servicio (int)
     */
    public int yearsOfService(){
        return LocalDate.now().getYear() - hireDate.getYear();
    }

    /**
     * Calcula las deducciones aplicadas al salario bruto.
     * Incluye deducciones obligatorias (ej. seguridad social) y opcionales como ARL.
     *
     * @param gross Salario bruto
     * @param deductionRate Porcentaje de deducciones generales
     * @param arlRate Porcentaje de deducción adicional por ARL (puede ser null)
     * @return Total de deducciones
     * @throws IllegalArgumentException si el salario bruto es null
     */
    public BigDecimal calculateDeductions(BigDecimal gross, BigDecimal deductionRate, BigDecimal arlRate){
        if(gross == null) throw new IllegalArgumentException("El salario bruto no puede ser nulo");
        
        BigDecimal total = gross.multiply(deductionRate);
        
        if(arlRate != null) {
            total = total.add(gross.multiply(arlRate));
        }
        
        return total;
    }

    /**
     * Calcula el salario neto después de deducciones.
     *
     * @param gross Salario bruto
     * @param deductions Total de deducciones
     * @return Salario neto
     * @throws IllegalArgumentException si el neto es negativo
     */
    public BigDecimal calculateNet(BigDecimal gross, BigDecimal deductions){
        BigDecimal net = gross.subtract(deductions);
        if(net.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El salario neto no puede ser negativo");
        }
        return net;
    }

    /**
     * Método abstracto que debe ser implementado por cada tipo de empleado
     * para calcular su salario bruto específico.
     *
     * @return salario bruto (BigDecimal)
     */
    public abstract BigDecimal calculateGross();
}