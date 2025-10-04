package com.clinica.salvador.service;

import java.math.BigDecimal;

/**
 * Clase PayrollResult
 * 
 * Representa el resultado del cálculo de nómina de un empleado,
 * incluyendo el salario bruto, deducciones y salario neto.
 * 
 * Principios SOLID aplicados:
 * - SRP (Single Responsibility Principle): su única responsabilidad 
 *   es almacenar y representar los resultados de la nómina.
 * - OCP (Open/Closed Principle): la clase está abierta a extensión 
 *   (se pueden añadir más campos si se requiere, como bonos o prestaciones),
 *   sin modificar la lógica actual.
 */
public class PayrollResult {

    /** Identificador único del empleado */
    private final String employeeId;

    /** Salario bruto calculado */
    private final BigDecimal gross;

    /** Total de deducciones aplicadas al salario */
    private final BigDecimal deductions;

    /** Salario neto final después de deducciones */
    private final BigDecimal net;

    /**
     * Constructor de PayrollResult.
     *
     * @param employeeId Identificador del empleado
     * @param gross Salario bruto
     * @param deductions Total de deducciones
     * @param net Salario neto
     */
    public PayrollResult(String employeeId, BigDecimal gross, BigDecimal deductions, BigDecimal net) {
        this.employeeId = employeeId;
        this.gross = gross;
        this.deductions = deductions;
        this.net = net;
    }


    // --- Métodos de acceso (Getters) ---
    
    /** @return Identificador del empleado */
    public String getEmployeeId(){ return employeeId; }
    
    /** @return Salario bruto */
    public BigDecimal getGross(){ return gross; }
    
    /** @return Total de deducciones */
    public BigDecimal getDeductions(){ return deductions; }
    
    /** @return Salario neto */
    public BigDecimal getNet(){ return net; }

    /**
     * Representación en formato String del resultado de nómina.
     * 
     * @return Cadena con el detalle de id, bruto, deducciones y neto.
     */

    @Override
    public String toString(){
        return String.format("PayrollResult[id=%s, gross=%s, deductions=%s, net=%s]", employeeId, gross, deductions, net);
    }
}
