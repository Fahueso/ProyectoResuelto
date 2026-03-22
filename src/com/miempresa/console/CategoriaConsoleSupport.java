package com.miempresa.console;

import com.miempresa.util.InputUtils;
import com.miempresa.modelo.CategoriaLaboral;
import com.miempresa.modelo.Empresa;

public final class CategoriaConsoleSupport {
    private CategoriaConsoleSupport() {}

    public static CategoriaLaboral createCategoria(final InputUtils in, final Empresa empresa) {
        if (empresa == null || empresa.getId() <= 0) {
            throw new IllegalArgumentException("La Empresa debe existir (id > 0).");
        }

        CategoriaLaboral c = new CategoriaLaboral();

        Long id = in.readLong("ID categoría", true);
        c.setId(id != null ? id : 0L);
        c.setEmpresaId(empresa.getId());

        c.setConvenio(in.readOptional("Convenio"));
        c.setGrupoProfesional(in.readOptional("Grupo profesional"));
        c.setNivel(in.readOptional("Nivel (opcional)"));
        c.setDescripcion(in.readOptional("Descripción"));
        return c;
    }

    public static CategoriaLaboral editCategoria(final InputUtils in, final CategoriaLaboral actual) {
        CategoriaLaboral c = new CategoriaLaboral();
        c.setId(actual.getId());
        c.setEmpresaId(actual.getEmpresaId());

        c.setConvenio(in.readOptional("Convenio (actual: " + actual.getConvenio() + ")"));
        c.setGrupoProfesional(in.readOptional("Grupo profesional (actual: " + actual.getGrupoProfesional() + ")"));
        c.setNivel(in.readOptional("Nivel (actual: " + actual.getNivel() + ")"));
        c.setDescripcion(in.readOptional("Descripción (actual: " + actual.getDescripcion() + ")"));
        return c;
    }
}