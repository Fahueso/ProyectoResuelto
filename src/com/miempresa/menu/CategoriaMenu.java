package com.miempresa.menu;

import com.miempresa.modelo.CategoriaLaboral;
import com.miempresa.service.CategoriaService;
import com.miempresa.util.InputUtils;

import java.util.List;
import java.util.Scanner;

/**
 * Menú de Categorías Laborales.
 * - CRUD completo
 * - Sin while(true)
 * - Sin lambdas/streams
 */
public class CategoriaMenu {

    private final InputUtils in;
    private final CategoriaService service;

    public CategoriaMenu(InputUtils in, CategoriaService service) {
        this.in = in;
        this.service = service;
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ CATEGORÍAS ===");
            System.out.println("1. Crear");
            System.out.println("2. Listar");
            System.out.println("3. Buscar por ID");
            System.out.println("0. Volver");

            opcion = in.readInt("Opcion:", true);

            switch (opcion) {
                case 1: crear(); break;
                case 2: listar(); break;
                case 3: buscar(); break;
                case 0: System.out.println("Volviendo al menú principal..."); break;
                default: System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }





    private void crear() {
        try {
            CategoriaLaboral c = service.crear();
            System.out.println("Creado: " + c);
        } catch (Exception ex) {
            System.out.println("Error al crear: " + ex.getMessage());
        }
    }

    private void listar() {
        List<CategoriaLaboral> lista = service.listar();
        if (lista.isEmpty()) { System.out.println("(Sin categorías)"); return; }
        for (int i = 0; i < lista.size(); i++) { System.out.println(lista.get(i)); }
    }

    private void buscar() {
        long id = in.readLong("ID:", true);
        if (id < 0) { System.out.println("ID inválido."); return; }
        CategoriaLaboral c = service.buscar(id);
        System.out.println(c == null ? "No encontrado." : c);
    }

//    private void editar() {
//        System.out.print("ID a editar: ");
//        long id = leerLong();
//        if (id < 0) { System.out.println("ID inválido."); return; }
//        try {
//            CategoriaLaboral c = service.editar(id);
//            System.out.println("Actualizado: " + c);
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