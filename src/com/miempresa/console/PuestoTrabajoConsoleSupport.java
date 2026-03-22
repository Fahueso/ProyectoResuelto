package com.miempresa.console;

import com.miempresa.util.InputUtils;
import com.miempresa.modelo.*;

public final class PuestoTrabajoConsoleSupport {
    private PuestoTrabajoConsoleSupport() {}

    /**
     * Crea un Puesto.
     * Dependencias (Sede, Departamento, CategoriaLaboral) deben llegar resueltas
     * por la capa de servicios/factorías superiores.
     */
    public static PuestoTrabajo createPuesto(final InputUtils in,
                                             final Sede sede,
                                             final Departamento departamento,
                                             final CategoriaLaboral categoriaRef) {
        if (sede == null || departamento == null || categoriaRef == null) {
            throw new IllegalArgumentException("Sede, Departamento y Categoría de referencia deben existir.");
        }

        PuestoTrabajo p = new PuestoTrabajo();

        Long id = in.readLong("ID puesto", true);
        p.setId(id != null ? id : 0L);

        p.setSede(sede);
        p.setDepartamento(departamento);
        p.setCategoriaReferencia(categoriaRef);

        p.setNombre(in.readNonEmpty("Nombre del puesto"));
        p.setDescripcionFunciones(in.readOptional("Descripción de funciones"));
        p.setJornada(in.readOptional("Jornada (p. ej., completa, parcial)"));
        p.setModalidad(in.readOptional("Modalidad (p. ej., presencial, híbrido, remoto)"));
        p.setActivo(in.readBoolean("¿Activo?", true));

        return p;
    }

    public static PuestoTrabajo editPuesto(final InputUtils in, final PuestoTrabajo actual) {
        PuestoTrabajo p = new PuestoTrabajo();

        p.setId(actual.getId());
        p.setSede(actual.getSede());
        p.setDepartamento(actual.getDepartamento());
        p.setCategoriaReferencia(actual.getCategoriaReferencia());

        p.setNombre(in.readNonEmpty("Nombre del puesto (actual: " + actual.getNombre() + ")"));
        p.setDescripcionFunciones(in.readOptional("Descripción de funciones (actual: " + actual.getDescripcionFunciones() + ")"));
        p.setJornada(in.readOptional("Jornada (actual: " + actual.getJornada() + ")"));
        p.setModalidad(in.readOptional("Modalidad (actual: " + actual.getModalidad() + ")"));
        p.setActivo(in.readBoolean("¿Activo?", actual.isActivo()));

        return p;
    }
}