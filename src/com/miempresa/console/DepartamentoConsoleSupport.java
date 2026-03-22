package com.miempresa.console;

import com.miempresa.util.InputUtils;
import com.miempresa.modelo.Departamento;
import com.miempresa.modelo.Empresa;

import java.util.function.Predicate;

public final class DepartamentoConsoleSupport {
    public DepartamentoConsoleSupport() {}

    /** Alta: crea SIEMPRE un Departamento nuevo asociado a una Empresa existente. */
    public static Departamento createDepartamento(InputUtils in, Empresa empresa) {
        if (empresa == null || empresa.getId() <= 0) {
            throw new IllegalArgumentException("La Empresa debe existir (con id) antes de crear un Departamento.");
        }

        Departamento d = new Departamento();

        d.setId(in.readLong("ID departamento", true));

        String codigo = in.readMatching(
                "Código (2-10 mayúsculas/números)",
                new Predicate<String>() {
                    @Override public boolean test(String s) {
                        return s != null && s.matches("[A-Z0-9]{2,10}");
                    }
                },
                "Formato inválido. Usa 2-10 mayúsculas/números (p. ej., IT, RRHH1, PROD01).",
                true
        );
        d.setCodigo(codigo);

        String nombre = in.readMatching(
                "Nombre del departamento",
                new Predicate<String>() {
                    @Override public boolean test(String s) {
                        return s != null && s.matches(".{3,}");
                    }
                },
                "Debe tener al menos 3 caracteres.",
                true
        );
        d.setNombre(nombre);

        d.setDescripcion(in.readOptional("Descripción"));

        d.setEmpresaId(empresa.getId());

        return d;
    }
}


