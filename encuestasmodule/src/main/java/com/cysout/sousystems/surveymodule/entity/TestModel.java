package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.List;

public class TestModel implements Serializable {
    @Embedded
    public CatEncuestaTipo catEncuestaTipo;

    @Relation(parentColumn = "catEncuestaTipoId", entityColumn = "catEncuestaTipoId", entity = Encuesta.class)
    private List<Encuesta> encuestas;

    public CatEncuestaTipo getCatEncuestaTipo() {
        return catEncuestaTipo;
    }

    public void setCatEncuestaTipo(CatEncuestaTipo catEncuestaTipo) {
        this.catEncuestaTipo = catEncuestaTipo;
    }

    public List<Encuesta> getEncuestas() {
        return encuestas;
    }

    public void setEncuestas(List<Encuesta> encuestas) {
        this.encuestas = encuestas;
    }
}
