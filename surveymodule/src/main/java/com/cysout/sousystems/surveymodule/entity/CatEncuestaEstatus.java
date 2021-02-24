package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "catEncuestaEstatus")
public class CatEncuestaEstatus implements Serializable {
    @PrimaryKey
    private Integer catEncuestaEstatusId;
    private String nombre;
    private String descripcion;

    public CatEncuestaEstatus() {

    }

    @Ignore
    public CatEncuestaEstatus(Integer catEncuestaEstatusId, String nombre, String descripcion) {
        this.catEncuestaEstatusId = catEncuestaEstatusId;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getCatEncuestaEstatusId() {
        return catEncuestaEstatusId;
    }

    public void setCatEncuestaEstatusId(Integer catEncuestaEstatusId) {
        this.catEncuestaEstatusId = catEncuestaEstatusId;
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
