package com.miempresa.service;

import com.miempresa.console.CategoriaConsoleSupport;
import com.miempresa.modelo.CategoriaLaboral;
import com.miempresa.modelo.Empresa;
import com.miempresa.util.InputUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio de Categoría Laboral.
 * - ArrayList en memoria.
 * - Usa CategoriaConsoleSupport.createCategoria(input, empresaUnica) y editCategoria(...).
 */
public class CategoriaService {

    private final List<CategoriaLaboral> store = new ArrayList<CategoriaLaboral>();
    private final InputUtils input;
    private final Empresa empresa; // única

    public CategoriaService(InputUtils input, Empresa empresaUnica) {
        if (empresaUnica == null || empresaUnica.getId() <= 0) {
            throw new IllegalArgumentException("Empresa inválida (id > 0).");
        }
        this.input = input;
        this.empresa = empresaUnica;
    }

    public CategoriaLaboral crear() {
        CategoriaLaboral c = CategoriaConsoleSupport.createCategoria(input, empresa);
        if (existeId(c.getId())) throw new IllegalArgumentException("Ya existe una categoría con ese ID.");
        try { c.setEmpresaId(empresa.getId()); } catch (Exception ignore) {}
        store.add(c);
        return c;
    }

    public List<CategoriaLaboral> listar() {
        return new ArrayList<CategoriaLaboral>(store);
    }

    public void cargarDesde(List<CategoriaLaboral> datos) {
        if (datos == null) return;
        store.clear();
        store.addAll(datos);
    }

    public CategoriaLaboral buscar(long id) {
        for (int i = 0; i < store.size(); i++) {
            CategoriaLaboral c = store.get(i);
            if (c.getId() == id) return c;
        }
        return null;
    }

//    public CategoriaLaboral editar(long id) {
//        int pos = indexOfId(id);
//        if (pos == -1) throw new IllegalArgumentException("No existe la categoría con ID " + id);
//        CategoriaLaboral actual = store.get(pos);
//        CategoriaLaboral editada = CategoriaConsoleSupport.editCategoria(input, actual);
//        try { editada.setId(id); } catch (Exception ignore) {}
//        try { editada.setEmpresaId(empresa.getId()); } catch (Exception ignore) {}
//        store.set(pos, editada);
//        return editada;
//    }

//    public boolean eliminar(long id) {
//        int pos = indexOfId(id);
//        if (pos == -1) return false;
//        // Regla futura: no eliminar si está en uso (puestos/empleados)
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