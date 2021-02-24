package com.cysout.sousystems.surveymodule.entity.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Pregunta;
import com.cysout.sousystems.surveymodule.entity.Respuesta;

public class PreguntaRespuestas implements Serializable {
    @Embedded
    private Pregunta pregunta;

    @Relation(parentColumn = "preguntaId", entityColumn = "preguntaId", entity = Respuesta.class)
    List<Respuesta> respuestas = new ArrayList<>();

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }
}
