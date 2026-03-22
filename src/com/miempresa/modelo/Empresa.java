package com.miempresa.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Empresa extends BaseEntity implements Serializable {
    private String razonSocial;
    private String nombreComercial;
    private String formaJuridica;
    private String cif; // identidad natural
    private String direccionFiscal;
    private String telefono;
    private String email;

    public Empresa() { }

    public Empresa(long id, String razonSocial, String nombreComercial, String formaJuridica,
                   String cif, String direccionFiscal, String telefono, String email) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.nombreComercial = nombreComercial;
        this.formaJuridica = formaJuridica;
        this.cif = cif;
        this.direccionFiscal = direccionFiscal;
        this.telefono = telefono;
        this.email = email;
    }


    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }

    public String getNombreComercial() { return nombreComercial; }
    public void setNombreComercial(String nombreComercial) { this.nombreComercial = nombreComercial; }

    public String getFormaJuridica() { return formaJuridica; }
    public void setFormaJuridica(String formaJuridica) { this.formaJuridica = formaJuridica; }

    public String getCif() { return cif; }
    public void setCif(String cif) { this.cif = cif; }

    public String getDireccionFiscal() { return direccionFiscal; }
    public void setDireccionFiscal(String direccionFiscal) { this.direccionFiscal = direccionFiscal; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empresa)) return false;
        Empresa empresa = (Empresa) o;
        return Objects.equals(cif, empresa.cif);
    }

    @Override
    public int hashCode() { return Objects.hash(cif); }

    @Override
    public String toString() {
        return "Empresa{" +
                "id=" + id +
                ", razonSocial='" + razonSocial + '\'' +
                ", nombreComercial='" + nombreComercial + '\'' +
                ", formaJuridica='" + formaJuridica + '\'' +
                ", cif='" + cif + '\'' +
                ", direccionFiscal='" + direccionFiscal + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}