package com.miempresa.menu;

import com.miempresa.modelo.Empleado;
import com.miempresa.service.EmpleadoService;
import com.miempresa.util.InputUtils;

import java.util.List;
import java.util.Scanner;

/**
 * Menú de Empleados.
 * - CRUD completo
 * - Identidad por DNI (no por ID)
 */
public class EmpleadoMenu {

    private final InputUtils in;
    private final EmpleadoService service;

    public EmpleadoMenu(InputUtils in, EmpleadoService service) {
        this.in = in;
        this.service = service;
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ EMPLEADOS ===");
            System.out.println("1. Crear");
            System.out.println("2. Listar");
            System.out.println("3. Buscar por DNI");
            System.out.println("4. Editar por DNI");
            System.out.println("5. Eliminar por DNI");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            opcion = in.readInt("Opción:", true);

            switch (opcion) {
                case 1: crear(); break;
                case 2: listar(); break;
                case 3: buscarPorDni(); break;
                case 0: System.out.println("Volviendo al menú principal..."); break;
                default: System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }





    private void crear() {
        try {
            Empleado e = service.crear();
            System.out.println("Creado: " + e);
        } catch (Exception ex) {
            System.out.println("Error al crear: " + ex.getMessage());
        }
    }

    private void listar() {
        List<Empleado> lista = service.listar();
        if (lista.isEmpty()) { System.out.println("(Sin empleados)"); return; }
        for (int i = 0; i < lista.size(); i++) { System.out.println(lista.get(i)); }
    }

    private void buscarPorDni() {
        String dni = in.readNonEmpty("DNI");
        Empleado e = service.buscarPorDni(dni);
        System.out.println(e == null ? "No encontrado." : e);
    }

//    private void editarPorDni() {
//        String dni = leerTexto("DNI a editar");
//        if (dni.isEmpty()) { System.out.println("DNI inválido."); return; }
//        try {
//            Empleado e = service.editarPorDni(dni);
//            System.out.println("Actualizado: " + e);
//        } catch (Exception ex) {
//            System.out.println("No se pudo editar: " + ex.getMessage());
//        }
//    }

//    private void eliminarPorDni() {
//        String dni = leerTexto("DNI a eliminar");
//        if (dni.isEmpty()) { System.out.println("DNI inválido."); return; }
//        boolean ok = service.eliminarPorDni(dni);
//        System.out.println(ok ? "Eliminado." : "No existía.");
//    }
}