package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "mostrarSiSelecciona",
        foreignKeys = {
                @ForeignKey(entity = Respuesta.class,
                        parentColumns = "respuestaId",
                        childColumns = "respuestaId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = {"respuestaId"})
        })
public class MostrarSiSelecciona implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long mostrarSiSeleccionaId;
    private Long respuestaId;
    @Ignore
    private List<MostrarCuestionarios> cuestionarios = new ArrayList<>();
    @Ignore
    private List<MostrarPreguntas> preguntas = new ArrayList<>();
    @Ignore
    private List<MostrarRespuestas> respuestas = new ArrayList<>();

    public MostrarSiSelecciona(){

    }

    @Ignore
    public MostrarSiSelecciona(Long mostrarSiSeleccionaId, Long respuestaId) {
        this.mostrarSiSeleccionaId = mostrarSiSeleccionaId;
        this.respuestaId = respuestaId;
    }

    public Long getMostrarSiSeleccionaId() {
        return mostrarSiSeleccionaId;
    }

    public void setMostrarSiSeleccionaId(Long mostrarSiSeleccionaId) {
        this.mostrarSiSeleccionaId = mostrarSiSeleccionaId;
    }

    public Long getRespuestaId() {
        return respuestaId;
    }

    public void setRespuestaId(Long respuestaId) {
        this.respuestaId = respuestaId;
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

    @Override
    public String toString() {
        return "MostrarSiSelecciona{" +
                "mostrarSiSeleccionaId=" + mostrarSiSeleccionaId +
                ", respuestaId=" + respuestaId +
                '}';
    }
}
