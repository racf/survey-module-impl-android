package com.cysout.sousystems.surveymodule.entity.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cysout.sousystems.surveymodule.entity.MostrarCuestionarios;
import com.cysout.sousystems.surveymodule.entity.MostrarPreguntas;
import com.cysout.sousystems.surveymodule.entity.MostrarRespuestas;
import com.cysout.sousystems.surveymodule.entity.MostrarSiSelecciona;

public class RelacionSiSelecciona implements Serializable {
    @Embedded
    MostrarSiSelecciona mostrarSiSelecciona = new MostrarSiSelecciona();
    @Relation(parentColumn = "mostrarSiSeleccionaId", entityColumn = "mostrarSiSeleccionaId", entity = MostrarCuestionarios.class)
    List<MostrarCuestionarios> cuestionarios = new ArrayList<>();
    @Relation(parentColumn = "mostrarSiSeleccionaId", entityColumn = "mostrarSiSeleccionaId", entity = MostrarPreguntas.class)
    List<MostrarPreguntas> preguntas = new ArrayList<>();
    @Relation(parentColumn = "mostrarSiSeleccionaId", entityColumn = "mostrarSiSeleccionaId", entity = MostrarRespuestas.class)
    List<MostrarRespuestas> respuestas = new ArrayList<>();

    public MostrarSiSelecciona getMostrarSiSelecciona() {
        return mostrarSiSelecciona;
    }

    public void setMostrarSiSelecciona(MostrarSiSelecciona mostrarSiSelecciona) {
        this.mostrarSiSelecciona = mostrarSiSelecciona;
    }

    public List<MostrarCuestionarios> getCuestionarios() {
        return cuestionarios;
    }

    public void setCuestionarios(List<MostrarCuestionarios> cuestionarios) {
        this.cuestionarios = cuestionarios;
    }

    public List<MostrarPreguntas> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<MostrarPreguntas> preguntas) {
        this.preguntas = preguntas;
    }

    public List<MostrarRespuestas> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<MostrarRespuestas> respuestas) {
        this.respuestas = respuestas;
    }
}
