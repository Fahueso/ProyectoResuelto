package com.miempresa.console;

import com.miempresa.util.InputUtils;
import com.miempresa.modelo.*;

import java.time.LocalDate;
import java.util.function.Predicate;

public final class EmpleadoConsoleSupport {
    private EmpleadoConsoleSupport() {}

    public static Empleado createEmpleado(final InputUtils in,
                                          final PuestoTrabajo puestoActual,
                                          final CategoriaLaboral categoriaReal) {
        if (puestoActual == null || categoriaReal == null) {
            throw new IllegalArgumentException("Puesto y Categoría real deben existir.");
        }

        Persona base = PersonaConsoleSupport.createPersona(in);

        Empleado e = new Empleado();
        e.setNombre(base.getNombre());
        e.setApellidos(base.getApellidos());
        e.setDni(base.getDni());

        e.setPuestoActual(puestoActual);
        e.setCategoriaReal(categoriaReal);

        e.setEmailCorporativo(in.readMatching(
                "Email corporativo",
                new Predicate<String>() { @Override public boolean test(String s) {
                    return s != null && s.contains("@");
                }},
                "Debe contener '@'.",
                true
        ));

        e.setEstadoLaboral(in.readNonEmpty("Estado laboral (ALTA/BAJA/EXCEDENCIA/...)"));

        // Fechas con el formateador de InputUtils (dd-MM-yyyy)
        LocalDate alta = in.readDate("Fecha de alta (dd-MM-yyyy)", true);
        LocalDate baja = in.readDate("Fecha de baja (dd-MM-yyyy)", false);
        e.setFechaAlta(alta);
        e.setFechaBaja(baja);

        return e;
    }

    public static Empleado editEmpleado(final InputUtils in, final Empleado actual) {
        Persona base = PersonaConsoleSupport.editPersona(in, actual);

        Empleado e = new Empleado();
        e.setNombre(base.getNombre());
        e.setApellidos(base.getApellidos());
        e.setDni(base.getDni());

        e.setPuestoActual(actual.getPuestoActual());
        e.setCategoriaReal(actual.getCategoriaReal());

        e.setEmailCorporativo(in.readMatching(
                "Email corporativo (actual: " + actual.getEmailCorporativo() + ")",
                new java.util.function.Predicate<String>() { @Override public boolean test(String s) {
                    return s != null && s.contains("@");
                }},
                "Debe contener '@'.",
                true
        ));

        e.setEstadoLaboral(in.readNonEmpty("Estado laboral (actual: " + actual.getEstadoLaboral() + ")"));

        LocalDate alta = in.readDate("Fecha de alta (dd-MM-yyyy) (actual: " + actual.getFechaAlta() + ")", true);
        LocalDate baja = in.readDate("Fecha de baja (dd-MM-yyyy) (actual: " + actual.getFechaBaja() + ")", false);
        e.setFechaAlta(alta);
        e.setFechaBaja(baja);

        return e;
    }
}