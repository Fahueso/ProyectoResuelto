package com.miempresa.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Empleado extends Persona implements Serializable {
    private PuestoTrabajo puestoActual;       // composición
    private CategoriaLaboral categoriaReal;   // composición
    private String emailCorporativo;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;              // puede ser null si está activo
    private String estadoLaboral;

    public Empleado() { }

    public Empleado(String nombre,
                    String apellidos,
                    String dni,
                    PuestoTrabajo puestoActual,
                    CategoriaLaboral categoriaReal,
                    String emailCorporativo,
                    LocalDate fechaAlta,
                    LocalDate fechaBaja,
                    String estadoLaboral) {
        super(nombre, apellidos, dni);
        this.puestoActual = puestoActual;
        this.categoriaReal = categoriaReal;
        this.emailCorporativo = emailCorporativo;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.estadoLaboral = estadoLaboral;
    }

    public PuestoTrabajo getPuestoActual() { return puestoActual; }
    public void setPuestoActual(PuestoTrabajo puestoActual) { this.puestoActual = puestoActual; }

    public CategoriaLaboral getCategoriaReal() { return categoriaReal; }
    public void setCategoriaReal(CategoriaLaboral categoriaReal) { this.categoriaReal = categoriaReal; }

    public String getEmailCorporativo() { return emailCorporativo; }
    public void setEmailCorporativo(String emailCorporativo) { this.emailCorporativo = emailCorporativo; }

    public LocalDate getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(LocalDate fechaAlta) { this.fechaAlta = fechaAlta; }

    public LocalDate getFechaBaja() { return fechaBaja; }
    public void setFechaBaja(LocalDate fechaBaja) { this.fechaBaja = fechaBaja; }

    public String getEstadoLaboral() { return estadoLaboral; }
    public void setEstadoLaboral(String estadoLaboral) { this.estadoLaboral = estadoLaboral; }

    // equals/hashCode heredan la identidad por DNI desde Persona

    @Override
    public String toString() {
        return "Empleado{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", puestoActual=" + puestoActual +
                ", categoriaReal=" + categoriaReal +
                ", emailCorporativo='" + emailCorporativo + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", fechaBaja=" + fechaBaja +
                ", estadoLaboral='" + estadoLaboral + '\'' +
                '}';
    }
}