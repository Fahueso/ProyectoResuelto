package com.miempresa.modelo;

import java.io.Serializable;
import java.util.Objects;

public class PuestoTrabajo extends BaseEntity implements Serializable {
    private Sede sede;                       // composición
    private Departamento departamento;       // composición
    private CategoriaLaboral categoriaReferencia; // composición
    private String nombre;
    private String descripcionFunciones;
    private String jornada;
    private String modalidad;
    private boolean activo;

    public PuestoTrabajo() { }

    public PuestoTrabajo(long id,
                         Sede sede,
                         Departamento departamento,
                         CategoriaLaboral categoriaReferencia,
                         String nombre,
                         String descripcionFunciones,
                         String jornada,
                         String modalidad,
                         boolean activo) {
        this.id = id;
        this.sede = sede;
        this.departamento = departamento;
        this.categoriaReferencia = categoriaReferencia;
        this.nombre = nombre;
        this.descripcionFunciones = descripcionFunciones;
        this.jornada = jornada;
        this.modalidad = modalidad;
        this.activo = activo;
    }



    public Sede getSede() { return sede; }
    public void setSede(Sede sede) { this.sede = sede; }

    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }

    public CategoriaLaboral getCategoriaReferencia() { return categoriaReferencia; }
    public void setCategoriaReferencia(CategoriaLaboral categoriaReferencia) { this.categoriaReferencia = categoriaReferencia; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcionFunciones() { return descripcionFunciones; }
    public void setDescripcionFunciones(String descripcionFunciones) { this.descripcionFunciones = descripcionFunciones; }

    public String getJornada() { return jornada; }
    public void setJornada(String jornada) { this.jornada = jornada; }

    public String getModalidad() { return modalidad; }
    public void setModalidad(String modalidad) { this.modalidad = modalidad; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PuestoTrabajo)) return false;
        PuestoTrabajo that = (PuestoTrabajo) o;
        return id == that.id;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "PuestoTrabajo{" +
                "id=" + id +
                ", sede=" + sede +
                ", departamento=" + departamento +
                ", categoriaReferencia=" + categoriaReferencia +
                ", nombre='" + nombre + '\'' +
                ", descripcionFunciones='" + descripcionFunciones + '\'' +
                ", jornada='" + jornada + '\'' +
                ", modalidad='" + modalidad + '\'' +
                ", activo=" + activo +
                '}';
    }
}