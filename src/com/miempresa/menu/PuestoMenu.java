package com.miempresa.menu;

import com.miempresa.modelo.PuestoTrabajo;
import com.miempresa.service.PuestoService;
import com.miempresa.util.InputUtils;

import java.util.List;
import java.util.Scanner;

/**
 * Menú de Puestos de Trabajo.
 * - CRUD completo
 * - El servicio resuelve dependencias (Sede, Departamento, Categoría)
 */
public class PuestoMenu {

    private final InputUtils in;
    private final PuestoService service;

    public PuestoMenu(InputUtils in, PuestoService service) {
        this.in = in;
        this.service = service;
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ PUESTOS ===");
            System.out.println("1. Crear");
            System.out.println("2. Listar");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Editar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            opcion = in.readInt("Opción:", true);

            switch (opcion) {
                case 1: crear(); break;
                case 2: listar(); break;
                case 3: buscar(); break;
                //case 4: editar(); break;
                //case 5: eliminar(); break;
                case 0: System.out.println("Volviendo al menú principal..."); break;
                default: System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }




    private void crear() {
        try {
            PuestoTrabajo p = service.crear();
            System.out.println("Creado: " + p);
        } catch (Exception ex) {
            System.out.println("Error al crear: " + ex.getMessage());
        }
    }

    private void listar() {
        List<PuestoTrabajo> lista = service.listar();
        if (lista.isEmpty()) { System.out.println("(Sin puestos)"); return; }
        for (int i = 0; i < lista.size(); i++) { System.out.println(lista.get(i)); }
    }

    private void buscar() {
        long id = in.readLong("ID:",true);
        if (id < 0) { System.out.println("ID inválido."); return; }
        PuestoTrabajo p = service.buscar(id);
        System.out.println(p == null ? "No encontrado." : p);
    }

//    private void editar() {
//        System.out.print("ID a editar: ");
//        long id = leerLong();
//        if (id < 0) { System.out.println("ID inválido."); return; }
//        try {
//            PuestoTrabajo p = service.editar(id);
//            System.out.println("Actualizado: " + p);
//        } catch (Exception ex) {
//            System.out.println("No se pudo editar: " + ex.getMessage());
//        }
//    }

//    private void eliminar() {
//        System.out.print("ID a eliminar: ");
//        long id = leerLong();
//        if (id < 0) { System.out.println("ID inválido."); return; }
//        boolean ok = service.eliminar(id);
//        System.out.println(ok ? "Eliminado." : "No existía.");
//    }
}