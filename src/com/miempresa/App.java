package com.miempresa;

import com.miempresa.menu.*;
import com.miempresa.modelo.*;

//import com.miempresa.service.SedeService;
import com.miempresa.service.*;
//import com.miempresa.service.CategoriaService;
//import com.miempresa.service.PuestoService;
//import com.miempresa.service.EmpleadoService;

import com.miempresa.util.InputUtils;

import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 * App oficial de arranque para el Sprint 5.
 * - Crea la Empresa ÚNICA del sistema en main (id = 1).
 * - Inyecta InputUtils y Empresa en los servicios que lo requieren.
 * - Construye servicios en el orden correcto para satisfacer dependencias.
 * - Registra DepartamentoMenu (plantilla oficial disponible) y deja TODOs del resto.
 * - Sin lambdas ni streams.
 */
public class App {

    public static final String fichero = "datos.dat";

    public static void main(String[] args) {

        // -------------------------------------------------------
        // Infra de consola compartida
        // -------------------------------------------------------
        Scanner scanner = new Scanner(System.in);
        InputUtils input = new InputUtils(scanner);


        Empresa empresa = new Empresa();
        empresa.setId(1L);
        empresa.setCif("B12345678");
        empresa.setRazonSocial("Mi Empresa S.L.");
        empresa.setNombreComercial("MiEmpresa");
        empresa.setFormaJuridica("S.L.");
        empresa.setDireccionFiscal("C/ Mayor, 1");
        empresa.setTelefono("600000000");
        empresa.setEmail("info@miempresa.test");

        // -------------------------------------------------------
        // Construcción de servicios (orden recomendado)
        // -------------------------------------------------------


//        // 1) Servicios base que dependen solo de Empresa + Input
        DepartamentoService departamentoService = new DepartamentoService(input, empresa);
        //TODO: Genera los servicios y descomenta
        SedeService sedeService = new SedeService(input, empresa);
        CategoriaService categoriaService = new CategoriaService(input, empresa);

        // 2) Servicios que dependen de otros servicios
        PuestoService puestoService = new PuestoService(input, sedeService, departamentoService, categoriaService);
        EmpleadoService empleadoService = new EmpleadoService(input, puestoService, categoriaService);

        //TODO: Implementa los métodos de serialización y descomenta
       System.out.println("Autocargando");
        cargar(sedeService,departamentoService,categoriaService,puestoService,empleadoService);

        // -------------------------------------------------------
        // Menús por entidad
         // -------------------------------------------------------
        DepartamentoMenu departamentoMenu = new DepartamentoMenu(input, departamentoService);
        //TODO: Genera los menús y descomenta
        SedeMenu sedeMenu = new SedeMenu(input, sedeService);
        CategoriaMenu categoriaMenu = new CategoriaMenu(input, categoriaService);
        PuestoMenu puestoMenu = new PuestoMenu(input, puestoService);
        EmpleadoMenu empleadoMenu = new EmpleadoMenu(input, empleadoService);


        MainMenu mainMenu = new MainMenu(input);

        // Registro de submenús disponibles
        mainMenu.setDepartamentoMenu(departamentoMenu);
        //TODO: Genera los menús y descomenta
        mainMenu.setSedeMenu(sedeMenu);
        mainMenu.setCategoriaMenu(categoriaMenu);
        mainMenu.setPuestoMenu(puestoMenu);
        mainMenu.setEmpleadoMenu(empleadoMenu);

        // -------------------------------------------------------
        // Arranque
        // -------------------------------------------------------
        mainMenu.mostrar();

        // -------------------------------------------------------
        // Cierre ordenado
        // -------------------------------------------------------
        scanner.close();
        //TODO: Implementa los métodos de serialización y descomenta
//        System.out.println("Autoguardado");
        guardar(sedeService,departamentoService,categoriaService,puestoService,empleadoService);
         System.out.println("Aplicación finalizada.");
    }

    private static void guardar(SedeService sedeService, DepartamentoService departamentoService, CategoriaService categoriaService, PuestoService puestoService, EmpleadoService empleadoService) {
        FileOutputStream f = null;
        try {
            f = new FileOutputStream(fichero);
            ObjectOutputStream oos = new ObjectOutputStream(f);
            oos.writeObject(sedeService.listar());
            oos.writeObject(departamentoService.listar());
            oos.writeObject(categoriaService.listar());
            oos.writeObject(puestoService.listar());
            oos.writeObject(empleadoService.listar());
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void cargar(SedeService sedeService, DepartamentoService departamentoService, CategoriaService categoriaService, PuestoService puestoService, EmpleadoService empleadoService) {
        FileInputStream f = null;
        try {
            f = new FileInputStream(fichero);
            ObjectInputStream ois = new ObjectInputStream(f);
            sedeService.cargarDesde((List<Sede>) ois.readObject());
            departamentoService.cargarDesde((List<Departamento>) ois.readObject());
            categoriaService.cargarDesde((List<CategoriaLaboral>) ois.readObject());
            puestoService.cargarDesde((List<PuestoTrabajo>) ois.readObject());
            empleadoService.cargarDesde((List<Empleado>) ois.readObject());
            ois.close();

        } catch (IOException e) {
            System.out.println("Todavía no se creó el archivo");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
