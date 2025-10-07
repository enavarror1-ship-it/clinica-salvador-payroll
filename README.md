# Clínica Salvador(sistema de nomina) - Actividad de la Unidad 3 - Igenieria de Software

Este repositorio contiene una implementación  para la actividad de la Unidad 3 de Ingeniería de Software: un sistema de cálculo de nómina y manejo de empleados, aplicando principios SOLID, código limpio, refactorización, pruebas unitarias y CI

## Estructura
- `src/main/java` - código fuente
- `src/test/java` - pruebas unitarias (JUnit 5)
- `.github/workflows/maven.yml` - Workflow de integración continua (CI) para ejecutar `mvn test` automáticamente

## Cómo ejecutar tests
```bash
mvn test
```

## Supuestos
- Las tasas de deducción (seguridad social y pensión) se representan como 4% por defecto.
- Bono de alimentación se añade como ejemplo para empleados permanentes.
- ARL y reglas específicas son configurables en la clase `Config`.

## Buenas prácticas incluidas
- Principios SOLID (clases por responsabilidad, uso de interfaces, inyección de dependencias simple).
- Código limpio y comentarios explicativos.
- Pruebas unitarias que cubren casos principales.

## Resultados de pruebas
Se ejecutaron las pruebas unitarias con:

```bash
mvn test

---

## Evidencia de ejecución (CI)

Este proyecto cuenta con integración continua configurada en GitHub Actions,  
donde se ejecutan automáticamente las pruebas unitarias con Maven cada vez que se hace un push.

![Build Status](docs/actions-run.png)

Puedes ver la ejecución directamente aquí:  
[Ver en GitHub Actions](https://github.com/enavarror1-ship-it/clinica-salvador-payroll/actions)

---

## Repositorio del proyecto

Puedes acceder al código completo aquí:  
[https://github.com/enavarror1-ship-it/clinica-salvador-payroll](https://github.com/enavarror1-ship-it/clinica-salvador-payroll)


#Salida relevante de la ejecución
[INFO] Results:
[INFO]
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] BUILD SUCCESS

---

## Metodología de Desarrollo

Para el desarrollo del proyecto **Clínica Salvador — Sistema de Nómina**, aplicamos una **metodología ágil tipo Scrum**, adaptada al entorno académico.  
El trabajo se organizó por roles y entregas diarias, priorizando la colaboración, la calidad del código y el cumplimiento de los principios **SOLID**.

### Roles del equipo
- **Yeison Durán:** documentación, metodología y soporte conceptual.  
- **Elizabeth Navarro & Kleymar Amador:** desarrollo del código fuente y refactorización.  
- **Junior Fruto:** pruebas unitarias y verificación de cálculos.  
- **CIPA Invictus:** integración continua y manejo del repositorio GitHub.  
- **CIPA Invictus:** exposición del proyecto.

### Fases del trabajo
1. **Planificación:** definición de tipos de empleados, reglas de negocio y estructura POO.  
2. **Desarrollo:** implementación de clases, aplicación de principios SOLID y buenas prácticas.  
3. **Pruebas:** validación de cálculos mediante **JUnit 5**.  
4. **Refactorización:** mejora del código, comentarios y documentación.  
5. **Entrega:** control de versiones en **GitHub** y exposición en video CIPA.

### Herramientas utilizadas
- **Java (JDK 25)** — lenguaje principal.  
- **Maven** — gestión de dependencias.  
- **JUnit 5** — pruebas unitarias.  
- **Git y GitHub** — control de versiones.  
- **GitHub Actions** — integración continua (CI).  
- **VS Code** — entorno de desarrollo.

---
