package com.clinica.salvador.util;

import java.math.BigDecimal;

/**
 * Clase Config
 *
 * Clase de utilidades que centraliza las constantes de configuración
 * del sistema de nómina. Está declarada como final para evitar herencia
 * y tiene un constructor privado para impedir su instanciación.
 *
 * Principios SOLID aplicados:
 * - SRP (Single Responsibility Principle): su única responsabilidad es
 *   almacenar configuraciones globales de la aplicación.
 * - OCP (Open/Closed Principle): se pueden agregar nuevas constantes
 *   sin modificar la lógica del sistema.
 */
public final class Config {

    /** Constructor privado para evitar instanciación */
    private Config(){}

    /** Porcentaje de deducción general (ej. seguridad social) */
    public static final BigDecimal DEDUCTION_RATE = new BigDecimal("0.04"); // 4%
    
    /** Tasa de ARL (riesgos laborales). Puede ser configurable */
    public static final BigDecimal ARL_RATE = BigDecimal.ZERO; 
    
    /** Auxilio de alimentación fijo para empleados permanentes */
    public static final BigDecimal FOOD_ALLOWANCE = new BigDecimal("1000000"); // Ejemplo
}

