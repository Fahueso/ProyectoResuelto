package com.miempresa.service;

import com.miempresa.console.DepartamentoConsoleSupport;
import com.miempresa.modelo.Departamento;
import com.miempresa.modelo.Empresa;
import com.miempresa.util.InputUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio de Departamento (Sprint 5)
 * - Almacén en ArrayList (memoria).
 * - Usa DepartamentoConsoleSupport.createDepartamento(InputUtils, Empresa).
 * - La Empresa es única y se inyecta desde App.
 */
public class DepartamentoService {

    private final List<Departamento> store = new ArrayList<Departamento>();
    private final InputUtils input;
    private final Empresa empresa; // Empresa única del sistema

    public DepartamentoService(InputUtils input, Empresa empresa) {
        this.input = input;
        this.empresa = empresa;
    }

    /** Alta: crea un Departamento asociado SIEMPRE a la empresa única. */
    public Departamento crear() {
        // Llamamos a tu helper estático con parámetros:
        Departamento d = DepartamentoConsoleSupport.createDepartamento(input, empresa);

        if (existeId(d.getId())) {
            throw new IllegalArgumentException("Ya existe un departamento con ese ID.");
        }

        store.add(d);
        return d;
    }

    /** Lista (devuelve copia). */
    public List<Departamento> listar() {
        return new ArrayList<Departamento>(store);
    }

    public void cargarDesde(List<Departamento> datos) {
        if (datos == null) return;
        store.clear();
        store.addAll(datos);
    }

    /** Búsqueda por ID (o null si no existe). */
    public Departamento buscar(long id) {
        for (int i = 0; i < store.size(); i++) {
            Departamento d = store.get(i);
            if (d.getId() == id) return d;
        }
        return null;
    }


    // -------- Helpers --------
    private boolean existeId(long id) {
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getId() == id) return true;
        }
        return false;
    }



    /**
     * Edición: mientras tu helper no tenga un método específico de edición,
     * podemos reusar createDepartamento(...) y preservar id/empresaId.
     * Cuando dispongas de editDepartamento(InputUtils, Departamento), sustitúyelo.
     */
//    public Departamento editar(long id) {
//        int pos = indexOfId(id);
//        if (pos == -1) {
//            throw new IllegalArgumentException("No existe el departamento con ID " + id);
//        }
//
//        Departamento actual = store.get(pos);
//
//        // Reutilizamos createDepartamento(...) para volver a pedir datos
//        Departamento editado = DepartamentoConsoleSupport.createDepartamento(input, empresa);
//
//        // Mantener identidad y empresa
//        try { editado.setId(id); } catch (Exception ignore) { }
//        try { editado.setEmpresaId(empresa.getId()); } catch (Exception ignore) { }
//
//        store.set(pos, editado);
//        return editado;
//    }

    /** Eliminación por ID. */
//    public boolean eliminar(long id) {
//        int pos = indexOfId(id);
//        if (pos == -1) return false;
//        store.remove(pos);
//        return true;
//    }

//    private int indexOfId(long id) {
//        for (int i = 0; i < store.size(); i++) {
//            if (store.get(i).getId() == id) return i;
//        }
//        return -1;
//    }
}