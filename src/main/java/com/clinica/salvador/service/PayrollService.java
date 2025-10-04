package com.clinica.salvador.service;

import com.clinica.salvador.model.Employee;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase PayrollService
 *
 * Servicio encargado de procesar la nómina de los empleados.
 * Se apoya en las clases de modelo (Employee y sus subclases) para
 * calcular el salario bruto, deducciones y neto.
 *
 * Principios SOLID aplicados:
 * - SRP (Single Responsibility Principle): su única responsabilidad
 *   es gestionar el cálculo de la nómina.
 * - OCP (Open/Closed Principle): se pueden añadir nuevos tipos de empleados
 *   sin modificar esta clase, ya que depende de la abstracción Employee.
 * - DIP (Dependency Inversion Principle): depende de la abstracción (Employee),
 *   no de implementaciones concretas.
 */
public class PayrollService {

    /** Lista de empleados que se procesarán en la nómina */
    private final List<Employee> employees;

    /** Porcentaje de deducción general (ej. seguridad social) */
    private final BigDecimal deductionRate;

    /** Porcentaje de deducción adicional (ej. ARL) */
    private final BigDecimal arlRate;

    /**
     * Constructor de PayrollService.
     *
     * @param employees Lista de empleados a procesar
     * @param deductionRate Porcentaje de deducciones generales
     * @param arlRate Porcentaje de deducciones por ARL
     */
    public PayrollService(List<Employee> employees, BigDecimal deductionRate, BigDecimal arlRate) {
        this.employees = employees;
        this.deductionRate = deductionRate;
        this.arlRate = arlRate;
    }

    /**
     * Calcula el resultado de nómina de un empleado específico.
     *
     * @param e Empleado a calcular
     * @return PayrollResult con bruto, deducciones y neto
     */
    public PayrollResult calculateFor(Employee e){
        // Calcula salario bruto según el tipo de empleado
        BigDecimal gross = e.calculateGross();
        
        // Calcula deducciones aplicables (ej. seguridad social, ARL)
        BigDecimal deductions = e.calculateDeductions(gross, deductionRate, arlRate);
        
        // Calcula salario neto
        BigDecimal net = e.calculateNet(gross, deductions);
        
        // Retorna objeto inmutable con el resultado
        return new PayrollResult(e.getId(), gross, deductions, net);
    }

    /**
     * Procesa la nómina de todos los empleados registrados en la lista.
     *
     * @return Lista de PayrollResult con los cálculos de todos los empleados
     */
    public List<PayrollResult> processAll(){
        return employees.stream().map(this::calculateFor).collect(Collectors.toList());
    }
}
