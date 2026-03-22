package com.miempresa.modelo;

import java.io.Serializable;
import java.util.Objects;

public class CategoriaLaboral extends BaseEntity implements Serializable {
    private long empresaId;
    private String convenio;
    private String grupoProfesional;
    private String nivel;       // puedes usar también 'nivelProfesional'
    private String descripcion;

    public CategoriaLaboral() { }

    public CategoriaLaboral(long id, long empresaId, String convenio, String grupoProfesional,
                            String nivel, String descripcion) {
        this.id = id;
        this.empresaId = empresaId;
        this.convenio = convenio;
        this.grupoProfesional = grupoProfesional;
        this.nivel = nivel;
        this.descripcion = descripcion;
    }


    public long getEmpresaId() { return empresaId; }
    public void setEmpresaId(long empresaId) { this.empresaId = empresaId; }

    public String getConvenio() { return convenio; }
    public void setConvenio(String convenio) { this.convenio = convenio; }

    public String getGrupoProfesional() { return grupoProfesional; }
    public void setGrupoProfesional(String grupoProfesional) { this.grupoProfesional = grupoProfesional; }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoriaLaboral)) return false;
        CategoriaLaboral that = (CategoriaLaboral) o;
        return id == that.id;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "CategoriaLaboral{" +
                "id=" + id +
                ", empresaId=" + empresaId +
                ", convenio='" + convenio + '\'' +
                ", grupoProfesional='" + grupoProfesional + '\'' +
                ", nivel='" + nivel + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
