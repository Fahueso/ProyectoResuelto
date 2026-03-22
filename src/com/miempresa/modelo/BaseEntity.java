package com.miempresa.modelo;

import java.io.Serializable;
import java.util.Objects;

public class BaseEntity implements Serializable {
    protected long id;
    private static final long serialVersionUID = 1L;

    public BaseEntity() { }

    public BaseEntity(long id) {
        this.id = id;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        BaseEntity that = (BaseEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "BaseEntity{id=" + id + '}';
    }
}