package com.miempresa.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Sede extends Ubicacion implements Serializable {
    private long id;           // identidad
    private long empresaId;
    private String tipo;
    private String emailContacto;

    public Sede() { }

    public Sede(long id, long empresaId, String tipo, String emailContacto,
                String calle, String numero, String cp, String ciudad, String provincia) {
        super(calle, numero, cp, ciudad, provincia);
        this.id = id;
        this.empresaId = empresaId;
        this.tipo = tipo;
        this.emailContacto = emailContacto;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getEmpresaId() { return empresaId; }
    public void setEmpresaId(long empresaId) { this.empresaId = empresaId; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getEmailContacto() { return emailContacto; }
    public void setEmailContacto(String emailContacto) { this.emailContacto = emailContacto; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sede)) return false;
        Sede sede = (Sede) o;
        return id == sede.id;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Sede{" +
                "id=" + id +
                ", empresaId=" + empresaId +
                ", tipo='" + tipo + '\'' +
                ", emailContacto='" + emailContacto + '\'' +
                ", calle='" + calle + '\'' +
                ", numero='" + numero + '\'' +
                ", cp='" + cp + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}