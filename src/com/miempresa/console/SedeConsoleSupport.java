package com.miempresa.console;

import com.miempresa.util.InputUtils;
import com.miempresa.modelo.Sede;
import com.miempresa.modelo.Empresa;
import com.miempresa.modelo.Ubicacion;

import java.util.function.Predicate;

public final class SedeConsoleSupport {
    private SedeConsoleSupport() {}

    public static Sede createSede(final InputUtils in, final Empresa empresa) {
        if (empresa == null || empresa.getId() <= 0) {
            throw new IllegalArgumentException("La Empresa debe existir (id > 0).");
        }

        Sede s = new Sede();

        Long id = in.readLong("ID sede", true);
        s.setId(id != null ? id : 0L);
        s.setEmpresaId(empresa.getId());

        s.setTipo(in.readOptional("Tipo de sede (Central/Delegación/etc.)"));

        s.setEmailContacto(in.readMatching(
                "Email de contacto",
                new Predicate<String>() { @Override public boolean test(String v) {
                    return v != null && v.contains("@");
                }},
                "Debe contener '@'.",
                true
        ));

        Ubicacion u = UbicacionConsoleSupport.createUbicacion(in);
        s.setCalle(u.getCalle());
        s.setNumero(u.getNumero());
        s.setCp(u.getCp());
        s.setCiudad(u.getCiudad());
        s.setProvincia(u.getProvincia());
        return s;
    }

    public static Sede editSede(final InputUtils in, final Sede actual) {
        Sede s = new Sede();
        s.setId(actual.getId());
        s.setEmpresaId(actual.getEmpresaId());

        s.setTipo(in.readOptional("Tipo de sede (actual: " + actual.getTipo() + ")"));

        s.setEmailContacto(in.readMatching(
                "Email de contacto (actual: " + actual.getEmailContacto() + ")",
                new Predicate<String>() { @Override public boolean test(String v) {
                    return v != null && v.contains("@");
                }},
                "Debe contener '@'.",
                true
        ));

        Ubicacion u = UbicacionConsoleSupport.editUbicacion(in, actual);
        s.setCalle(u.getCalle());
        s.setNumero(u.getNumero());
        s.setCp(u.getCp());
        s.setCiudad(u.getCiudad());
        s.setProvincia(u.getProvincia());
        return s;
    }
}