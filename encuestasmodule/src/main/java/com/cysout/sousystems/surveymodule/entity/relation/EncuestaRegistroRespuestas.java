package com.cysout.sousystems.surveymodule.entity.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cysout.sousystems.surveymodule.entity.EncuestaRegistro;
import com.cysout.sousystems.surveymodule.entity.EncuestaRespuesta;

public class EncuestaRegistroRespuestas implements Serializable {
    @Embedded
    private EncuestaRegistro encuestaRegistro;

    @Relation(parentColumn = "encuestaRegistroId", entityColumn = "encuestaRegistroId", entity = EncuestaRespuesta.class)
    private List<EncuestaRespuesta> encuestaRespuestas = new ArrayList<>();

    public EncuestaRegistro getEncuestaRegistro() {
        return encuestaRegistro;
    }

    public void setEncuestaRegistro(EncuestaRegistro encuestaRegistro) {
        this.encuestaRegistro = encuestaRegistro;
    }

    public List<EncuestaRespuesta> getEncuestaRespuestas() {
        return encuestaRespuestas;
    }

    public void setEncuestaRespuestas(List<EncuestaRespuesta> encuestaRespuestas) {
        this.encuestaRespuestas = encuestaRespuestas;
    }
}
