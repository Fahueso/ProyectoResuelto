package com.miempresa.menu;

import com.miempresa.modelo.Departamento;
import com.miempresa.service.DepartamentoService;
import com.miempresa.util.InputUtils;

import java.util.List;
import java.util.Scanner;

/**
 * Menú de Departamentos (Sprint 5)
 * - SIN while(true): usa do...while con opción 0 para volver.
 * - SIN lambdas ni streams: imprime con for clásico.
 * - El menú NO usa ConsoleSupport: delega en DepartamentoService.
 */
public class DepartamentoMenu {

    private final InputUtils in;
    private final DepartamentoService service;

    public DepartamentoMenu(InputUtils in, DepartamentoService service) {
        this.in = in;
        this.service = service;
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ DEPARTAMENTOS ===");
            System.out.println("1. Crear");
            System.out.println("2. Listar");
            System.out.println("3. Buscar por ID");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            opcion =  in.readInt("Opción", true);

            switch (opcion) {
                case 1:
                    crear();
                    break;

                case 2:
                    listar();
                    break;

                case 3:
                    buscar();
                    break;
               case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }


    // ================= Operaciones =================

    private void crear() {
        try {
            // No pedimos empresaId: el servicio conoce la Empresa única inyectada en App
            Departamento creado = service.crear();
            System.out.println("Creado: " + creado);
        } catch (Exception e) {
            System.out.println("Error al crear: " + e.getMessage());
        }
    }

    private void listar() {
        List<Departamento> lista = service.listar();
        if (lista.isEmpty()) {
            System.out.println("(Sin departamentos)");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
    }

    private void buscar() {
        Long id = in.readLong("ID", true);
        if (id < 0) {
            System.out.println("ID inválido.");
            return;
        }
        Departamento d = service.buscar(id);
        System.out.println(d == null ? "No encontrado." : d);
    }

}