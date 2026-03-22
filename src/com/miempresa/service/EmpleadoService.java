package com.miempresa.service;

import com.miempresa.console.EmpleadoConsoleSupport;
import com.miempresa.modelo.CategoriaLaboral;
import com.miempresa.modelo.Empleado;
import com.miempresa.modelo.PuestoTrabajo;
import com.miempresa.util.InputUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio de Empleado.
 * - ArrayList en memoria.
 * - Usa EmpleadoConsoleSupport.createEmpleado(input, puesto, categoriaReal) y editEmpleado(...).
 * - Resuelve dependencias preguntando IDs y ofreciendo crearlas si faltan.
 * - Controla duplicado por DNI (identidad natural de Persona/Empleado).
 */
public class EmpleadoService {

    private final List<Empleado> store = new ArrayList<Empleado>();
    private final InputUtils input;

    // Dependencias para resolver el alta
    private final PuestoService puestoService;
    private final CategoriaService categoriaService;

    public EmpleadoService(InputUtils input, PuestoService puestoService, CategoriaService categoriaService) {
        this.input = input;
        this.puestoService = puestoService;
        this.categoriaService = categoriaService;
    }

    public Empleado crear() {
        // Resolver Puesto
        System.out.print("ID de Puesto: ");
        long puestoId = leerLong();
        PuestoTrabajo puesto = puestoService.buscar(puestoId);
        if (puesto == null) {
            boolean crear = input.readBoolean("Puesto no existe. ¿Crear ahora?", true);
            if (crear) puesto = puestoService.crear(); else throw new IllegalArgumentException("Puesto requerido.");
        }

        // Resolver Categoría real
        System.out.print("ID de Categoría REAL: ");
        long catId = leerLong();
        CategoriaLaboral categoria = categoriaService.buscar(catId);
        if (categoria == null) {
            boolean crear = input.readBoolean("Categoría no existe. ¿Crear ahora?", true);
            if (crear) categoria = categoriaService.crear(); else throw new IllegalArgumentException("Categoría requerida.");
        }

        Empleado e = EmpleadoConsoleSupport.createEmpleado(input, puesto, categoria);

        if (existeDni(e.getDni())) throw new IllegalArgumentException("Ya existe un empleado con ese DNI.");

        store.add(e);
        return e;
    }

    public List<Empleado> listar() {
        return new ArrayList<Empleado>(store);
    }


    public void cargarDesde(List<Empleado> datos) {
        if (datos == null) return;
        store.clear();
        store.addAll(datos);
    }

    public Empleado buscarPorDni(String dni) {
        if (dni == null) return null;
        for (int i = 0; i < store.size(); i++) {
            Empleado e = store.get(i);
            if (dni.equalsIgnoreCase(e.getDni())) return e;
        }
        return null;
    }

//    public Empleado editarPorDni(String dni) {
//        int pos = indexOfDni(dni);
//        if (pos == -1) throw new IllegalArgumentException("No existe empleado con DNI " + dni);
//
//        Empleado actual = store.get(pos);
//        Empleado editado = EmpleadoConsoleSupport.editEmpleado(input, actual);
//
//        // Control de identidad por DNI: si cambia, comprobar duplicado
//        if (!actual.getDni().equalsIgnoreCase(editado.getDni()) && existeDni(editado.getDni())) {
//            throw new IllegalArgumentException("Ya existe un empleado con DNI " + editado.getDni());
//        }
//
//        store.set(pos, editado);
//        return editado;
//    }

//    public boolean eliminarPorDni(String dni) {
//        int pos = indexOfDni(dni);
//        if (pos == -1) return false;
//        // Regla futura: no eliminar si está activo o hay restricciones de negocio
//        store.remove(pos);
//        return true;
//    }

    // ---- helpers ----
    private long leerLong() {
        Long v = input.readLong(" ", true);
        return v != null ? v : -1;
    }
    private boolean existeDni(String dni) {
        if (dni == null) return false;
        for (int i = 0; i < store.size(); i++) {
            if (dni.equalsIgnoreCase(store.get(i).getDni())) return true;
        }
        return false;
    }
    private int indexOfDni(String dni) {
        if (dni == null) return -1;
        for (int i = 0; i < store.size(); i++) {
            if (dni.equalsIgnoreCase(store.get(i).getDni())) return i;
        }
        return -1;
    }
}