package com.miempresa.service;

import com.miempresa.console.SedeConsoleSupport;
import com.miempresa.modelo.Empresa;
import com.miempresa.modelo.Sede;
import com.miempresa.util.InputUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio de Sede.
 * - ArrayList en memoria.
 * - Usa SedeConsoleSupport.createSede(input, empresaUnica) y editSede(...).
 */
public class SedeService {

    private final List<Sede> store = new ArrayList<Sede>();
    private final InputUtils input;
    private final Empresa empresa; // única

    public SedeService(InputUtils input, Empresa empresaUnica) {
        if (empresaUnica == null || empresaUnica.getId() <= 0) {
            throw new IllegalArgumentException("Empresa inválida (id > 0).");
        }
        this.input = input;
        this.empresa = empresaUnica;
    }

    public Sede crear() {
        Sede s = SedeConsoleSupport.createSede(input, empresa);
        if (existeId(s.getId())) throw new IllegalArgumentException("Ya existe una sede con ese ID.");
        // asegurar empresa
        try { s.setEmpresaId(empresa.getId()); } catch (Exception ignore) {}
        store.add(s);
        return s;
    }

    public List<Sede> listar() {
        return new ArrayList<Sede>(store);
    }

    public void cargarDesde(List<Sede> datos) {
        if (datos == null) return;
        store.clear();
        store.addAll(datos);
    }

    public Sede buscar(long id) {
        for (int i = 0; i < store.size(); i++) {
            Sede s = store.get(i);
            if (s.getId() == id) return s;
        }
        return null;
    }

//    public Sede editar(long id) {
//        int pos = indexOfId(id);
//        if (pos == -1) throw new IllegalArgumentException("No existe la sede con ID " + id);
//        Sede actual = store.get(pos);
//        Sede editada = SedeConsoleSupport.editSede(input, actual);
//        try { editada.setId(id); } catch (Exception ignore) {}
//        try { editada.setEmpresaId(empresa.getId()); } catch (Exception ignore) {}
//        store.set(pos, editada);
//        return editada;
//    }

//    public boolean eliminar(long id) {
//        int pos = indexOfId(id);
//        if (pos == -1) return false;
//        // Regla futura: no eliminar si tiene puestos (se comprobará cuando exista PuestoService)
//        store.remove(pos);
//        return true;
//    }

    // ---- helpers ----
    private boolean existeId(long id) {
        for (int i = 0; i < store.size(); i++) if (store.get(i).getId() == id) return true;
        return false;
    }
//    private int indexOfId(long id) {
//        for (int i = 0; i < store.size(); i++) if (store.get(i).getId() == id) return i;
//        return -1;
//    }
}