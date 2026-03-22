package com.miempresa.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Departamento extends BaseEntity implements Serializable {
    private long empresaId;
    private String codigo;
    private String nombre;
    private String descripcion;

    public Departamento() { }

    public Departamento(long id, long empresaId, String codigo, String nombre, String descripcion) {
        this.id = id;
        this.empresaId = empresaId;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public long getEmpresaId() { return empresaId; }
    public void setEmpresaId(long empresaId) { this.empresaId = empresaId; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departamento)) return false;
        Departamento that = (Departamento) o;
        return id == that.id;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", empresaId=" + empresaId +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}