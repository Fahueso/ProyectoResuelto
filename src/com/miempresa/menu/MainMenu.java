package com.miempresa.menu;

import com.miempresa.util.InputUtils;

import java.util.Scanner;

/**
 * MainMenu oficial entregado por el profesorado.
 * - SIN while(true): usa do...while.
 * - SIN lambdas/streams.
 * - Lectura robusta con leerEntero().
 * - Comentarios TODO indicando lo que aún NO está implementado por el alumnado.
 */
public class MainMenu {

    private final InputUtils in;

    // --- Submenús disponibles ---
    // DepartamentoMenu: se entrega hecho como PLANTILLA OFICIAL.
    private DepartamentoMenu departamentoMenu;


    //TODO: Genera los menús y descomenta

    private SedeMenu sedeMenu;              // TODO: implementar por el alumnado
    private CategoriaMenu categoriaMenu;    // TODO: implementar por el alumnado
    private PuestoMenu puestoMenu;          // TODO: implementar por el alumnado
    private EmpleadoMenu empleadoMenu;      // TODO: implementar por el alumnado

    public MainMenu(InputUtils in) {
        this.in = in;
    }

    // --- Setters para inyectar submenús ---
    public void setDepartamentoMenu(DepartamentoMenu menu) { this.departamentoMenu = menu; }

    //TODO: Genera los menús y descomenta

    public void setSedeMenu(SedeMenu menu) { this.sedeMenu = menu; }                    // TODO
    public void setCategoriaMenu(CategoriaMenu menu) { this.categoriaMenu = menu; }     // TODO
    public void setPuestoMenu(PuestoMenu menu) { this.puestoMenu = menu; }              // TODO
    public void setEmpleadoMenu(EmpleadoMenu menu) { this.empleadoMenu = menu; }        // TODO

    /**
     * Muestra el menú principal y enruta a los submenús disponibles.
     * Las opciones que no tengan menú registrado muestran "Módulo no disponible."
     */
    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Departamentos  ");
            System.out.println("2. Sedes     ");
            System.out.println("3. Categorías ");
            System.out.println("4. Puestos   ");
            System.out.println("5. Empleados  ");
            System.out.println("0. Salir");

            opcion = in.readInt("Opcion", true);

            switch (opcion) {
                case 1:
                    departamentoMenu.mostrar();
                    break;
              case 2:
                  //TODO: Genera los menús y descomenta
                    sedeMenu.mostrar();
                    break;

                case 3:
                    categoriaMenu.mostrar();
                    break;

                case 4:
                    puestoMenu.mostrar();
                    break;

                case 5:
                    empleadoMenu.mostrar();
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }



    public DepartamentoMenu getDepartamentoMenu() {
        return departamentoMenu;
    }
    //TODO: Genera los menús y descomenta

    public SedeMenu getSedeMenu() {
        return sedeMenu;
    }

    public CategoriaMenu getCategoriaMenu() {
        return categoriaMenu;
    }

    public PuestoMenu getPuestoMenu() {
        return puestoMenu;
    }

    public EmpleadoMenu getEmpleadoMenu() {
        return empleadoMenu;
    }
}