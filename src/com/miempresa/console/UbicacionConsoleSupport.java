package com.miempresa.console;

import com.miempresa.util.InputUtils;
import com.miempresa.modelo.Ubicacion;

import java.util.function.Predicate;

public final class UbicacionConsoleSupport {
    private UbicacionConsoleSupport() {}

    public static Ubicacion createUbicacion(final InputUtils in) {
        Ubicacion u = new Ubicacion();
        u.setCalle(in.readNonEmpty("Calle"));
        u.setNumero(in.readNonEmpty("Número"));


        u.setCp(in.readMatching(
                "CP (5 dígitos)",
                new Predicate<String>() { @Override public boolean test(String s) {
                    return s != null && s.matches("\\d{5}");
                }},
                "Debe ser un código postal de 5 dígitos.",
                true
        ));

        u.setCiudad(in.readNonEmpty("Ciudad"));
        u.setProvincia(in.readNonEmpty("Provincia"));
        return u;
    }

    public static Ubicacion editUbicacion(final InputUtils in, final Ubicacion actual) {
        Ubicacion u = new Ubicacion();

        u.setCalle(in.readNonEmpty("Calle (actual: " + actual.getCalle() + ")"));
        u.setNumero(in.readNonEmpty("Número (actual: " + actual.getNumero() + ")"));

        u.setCp(in.readMatching(
                "CP (5 dígitos) (actual: " + actual.getCp() + ")",
                new Predicate<String>() { @Override public boolean test(String s) {
                    return s != null && s.matches("\\d{5}");
                }},
                "Debe ser un código postal de 5 dígitos.",
                true
        ));

        u.setCiudad(in.readNonEmpty("Ciudad (actual: " + actual.getCiudad() + ")"));
        u.setProvincia(in.readNonEmpty("Provincia (actual: " + actual.getProvincia() + ")"));
        return u;
    }
}