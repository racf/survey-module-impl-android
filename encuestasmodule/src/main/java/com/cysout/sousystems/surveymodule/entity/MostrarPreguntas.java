package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "mostrarPreguntas",
        foreignKeys = {
                @ForeignKey(entity = MostrarSiSelecciona.class,
                        parentColumns = "mostrarSiSeleccionaId",
                        childColumns = "mostrarSiSeleccionaId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = {"mostrarSiSeleccionaId"})
        })
public class MostrarPreguntas implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long mostrarPreguntasId;
    private Long mostrarSiSeleccionaId;
    private Long cuestionarioId;
    private Long preguntaId;

    public MostrarPreguntas(){

    }

    @Ignore
    public MostrarPreguntas(Long mostrarPreguntasId, Long mostrarSiSeleccionaId, Long cuestionarioId, Long preguntaId) {
        this.mostrarPreguntasId = mostrarPreguntasId;
        this.mostrarSiSeleccionaId = mostrarSiSeleccionaId;
        this.cuestionarioId = cuestionarioId;
        this.preguntaId = preguntaId;
    }

    public Long getMostrarPreguntasId() {
        return mostrarPreguntasId;
    }

    public void setMostrarPreguntasId(Long mostrarPreguntasId) {
        this.mostrarPreguntasId = mostrarPreguntasId;
    }

    public Long getMostrarSiSeleccionaId() {
        return mostrarSiSeleccionaId;
    }

    public void setMostrarSiSeleccionaId(Long mostrarSiSeleccionaId) {
        this.mostrarSiSeleccionaId = mostrarSiSeleccionaId;
    }

    public Long getCuestionarioId() {
        return cuestionarioId;
    }

    public void setCuestionarioId(Long cuestionarioId) {
        this.cuestionarioId = cuestionarioId;
    }

    public Long getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(Long preguntaId) {
        this.preguntaId = preguntaId;
    }

    @Override
    public String toString() {
        return "MostrarPreguntas{" +
                "mostrarPreguntasId=" + mostrarPreguntasId +
                ", mostrarSiSeleccionaId=" + mostrarSiSeleccionaId +
                ", cuestionarioId=" + cuestionarioId +
                ", preguntaId=" + preguntaId +
                '}';
    }
}
