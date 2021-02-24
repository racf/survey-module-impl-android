package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "catEncuestaTipo")
public class CatEncuestaTipo implements Serializable {
    @PrimaryKey
    private Integer catEncuestaTipoId;
    private String nombre;
    private String descripcion;

    public CatEncuestaTipo() {

    }

    @Ignore
    public CatEncuestaTipo(Integer catEncuestaTipoId, String nombre, String descripcion) {
        this.catEncuestaTipoId = catEncuestaTipoId;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getCatEncuestaTipoId() {
        return catEncuestaTipoId;
    }

    public void setCatEncuestaTipoId(Integer catEncuestaTipoId) {
        this.catEncuestaTipoId = catEncuestaTipoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
