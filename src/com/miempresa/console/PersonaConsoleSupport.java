package com.miempresa.console;

import com.miempresa.util.InputUtils;
import com.miempresa.modelo.Persona;

import java.util.function.Predicate;

public final class PersonaConsoleSupport {
    private PersonaConsoleSupport() {}

    public static Persona createPersona(final InputUtils in) {
        Persona p = new Persona();

        p.setNombre(in.readMatching(
                "Nombre",
                new Predicate<String>() { @Override public boolean test(String s) {
                    return s != null && s.trim().length() >= 2;
                }},
                "Debe tener al menos 2 caracteres.",
                true
        ));

        p.setApellidos(in.readMatching(
                "Apellidos",
                new Predicate<String>() { @Override public boolean test(String s) {
                    return s != null && s.trim().length() >= 2;
                }},
                "Debe tener al menos 2 caracteres.",
                true
        ));

        p.setDni(in.readMatching(
                "DNI (8 dígitos + letra)",
                new Predicate<String>() { @Override public boolean test(String s) {
                    return s != null && s.matches("\\d{8}[A-Za-z]");
                }},
                "Formato inválido. Ejemplo: 12345678A",
                true
        ));

        return p;
    }

    public static Persona editPersona(final InputUtils in, final Persona actual) {
        Persona p = new Persona();

        p.setNombre(in.readMatching(
                "Nombre [" + actual.getNombre() + "]",
                new Predicate<String>() { @Override public boolean test(String s) {
                    return s != null && s.trim().length() >= 2;
                }},
                "Debe tener al menos 2 caracteres.",
                true
        ));

        p.setApellidos(in.readMatching(
                "Apellidos [" + actual.getApellidos() + "]",
                new Predicate<String>() { @Override public boolean test(String s) {
                    return s != null && s.trim().length() >= 2;
                }},
                "Debe tener al menos 2 caracteres.",
                true
        ));

        p.setDni(in.readMatching(
                "DNI (8 dígitos + letra) [" + actual.getDni() + "]",
                new Predicate<String>() { @Override public boolean test(String s) {
                    return s != null && s.matches("\\d{8}[A-Za-z]");
                }},
                "Formato inválido. Ejemplo: 12345678A",
                true
        ));

        return p;
    }
}
