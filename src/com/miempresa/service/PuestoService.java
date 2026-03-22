package com.miempresa.service;

import com.miempresa.console.PuestoTrabajoConsoleSupport;
import com.miempresa.modelo.CategoriaLaboral;
import com.miempresa.modelo.Departamento;
import com.miempresa.modelo.PuestoTrabajo;
import com.miempresa.modelo.Sede;
import com.miempresa.util.InputUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio de Puesto de Trabajo.
 * - ArrayList en memoria.
 * - Usa PuestoTrabajoConsoleSupport.createPuesto(input, sede, departamento, categoriaRef) y editPuesto(...).
 * - Pide IDs de dependencias, las busca en sus servicios; si no existen, ofrece crearlas.
 */
public class PuestoService {

    private final List<PuestoTrabajo> store = new ArrayList<PuestoTrabajo>();
    private final InputUtils input;

    // Dependencias para resolver Sede/Departamento/Categoría
    private final SedeService sedeService;
    private final DepartamentoService departamentoService;
    private final CategoriaService categoriaService;

    public PuestoService(InputUtils input,
                         SedeService sedeService,
                         DepartamentoService departamentoService,
                         CategoriaService categoriaService) {
        this.input = input;
        this.sedeService = sedeService;
        this.departamentoService = departamentoService;
        this.categoriaService = categoriaService;
    }

    public PuestoTrabajo crear() {
        // Resolver Sede
        System.out.print("ID de Sede: ");
        long sedeId = leerLong();
        Sede sede = sedeService.buscar(sedeId);
        if (sede == null) {
            boolean crear = input.readBoolean("Sede no existe. ¿Crear ahora?", true);
            if (crear) sede = sedeService.crear(); else throw new IllegalArgumentException("Sede requerida.");
        }

        // Resolver Departamento
        System.out.print("ID de Departamento: ");
        long deptId = leerLong();
        Departamento dept = departamentoService.buscar(deptId);
        if (dept == null) {
            boolean crear = input.readBoolean("Departamento no existe. ¿Crear ahora?", true);
            if (crear) dept = departamentoService.crear(); else throw new IllegalArgumentException("Departamento requerido.");
        }

        // Resolver Categoría
        System.out.print("ID de Categoría de referencia: ");
        long catId = leerLong();
        CategoriaLaboral cat = categoriaService.buscar(catId);
        if (cat == null) {
            boolean crear = input.readBoolean("Categoría no existe. ¿Crear ahora?", true);
            if (crear) cat = categoriaService.crear(); else throw new IllegalArgumentException("Categoría requerida.");
        }

        PuestoTrabajo p = PuestoTrabajoConsoleSupport.createPuesto(input, sede, dept, cat);

        if (existeId(p.getId())) throw new IllegalArgumentException("Ya existe un puesto con ese ID.");

        store.add(p);
        return p;
    }

    public List<PuestoTrabajo> listar() {
        return new ArrayList<PuestoTrabajo>(store);
    }

    public void cargarDesde(List<PuestoTrabajo> datos) {
        if (datos == null) return;
        store.clear();
        store.addAll(datos);
    }

    public PuestoTrabajo buscar(long id) {
        for (int i = 0; i < store.size(); i++) {
            PuestoTrabajo p = store.get(i);
            if (p.getId() == id) return p;
        }
        return null;
    }

//    public PuestoTrabajo editar(long id) {
//        int pos = indexOfId(id);
//        if (pos == -1) throw new IllegalArgumentException("No existe el puesto con ID " + id);
//
//        PuestoTrabajo actual = store.get(pos);
//        PuestoTrabajo editado = PuestoTrabajoConsoleSupport.editPuesto(input, actual);
//        try { editado.setId(id); } catch (Exception ignore) {}
//        // Dependencias se mantienen como en el helper de edición
//        store.set(pos, editado);
//        return editado;
//    }
//
//    public boolean eliminar(long id) {
//        int pos = indexOfId(id);
//        if (pos == -1) return false;
//        // Regla futura: no eliminar si hay empleados asociados
//        store.remove(pos);
//        return true;
//    }

    // ---- helpers ----
    private long leerLong() {
        // Reutiliza el Scanner dentro de InputUtils leyendo una línea "a mano"
        // pero aquí mantenemos un lector sencillo sin try/catch complejo:
        Long v = input.readLong(" ", true); // prompt vacío, ya hemos impreso arriba
        return v != null ? v : -1;
    }
    private boolean existeId(long id) {
        for (int i = 0; i < store.size(); i++) if (store.get(i).getId() == id) return true;
        return false;
    }
//    private int indexOfId(long id) {
//        for (int i = 0; i < store.size(); i++) if (store.get(i).getId() == id) return i;
//        return -1;
//    }
}