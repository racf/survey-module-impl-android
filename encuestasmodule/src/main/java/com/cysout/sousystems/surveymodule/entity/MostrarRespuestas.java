package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "mostrarRespuestas",
        foreignKeys = {
                @ForeignKey(entity = MostrarSiSelecciona.class,
                        parentColumns = "mostrarSiSeleccionaId",
                        childColumns = "mostrarSiSeleccionaId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = {"mostrarSiSeleccionaId"})
        })
public class MostrarRespuestas implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long mostrarRespuestasId;
    private Long mostrarSiSeleccionaId;
    private Long cuestionarioId;
    private Long preguntaId;
    private Long respuestaId;

    public MostrarRespuestas(){

    }

    @Ignore
    public MostrarRespuestas(Long mostrarRespuestasId, Long mostrarSiSeleccionaId, Long cuestionarioId, Long preguntaId, Long respuestaId) {
        this.mostrarRespuestasId = mostrarRespuestasId;
        this.mostrarSiSeleccionaId = mostrarSiSeleccionaId;
        this.cuestionarioId = cuestionarioId;
        this.preguntaId = preguntaId;
        this.respuestaId = respuestaId;
    }

    public Long getMostrarRespuestasId() {
        return mostrarRespuestasId;
    }

    public void setMostrarRespuestasId(Long mostrarRespuestasId) {
        this.mostrarRespuestasId = mostrarRespuestasId;
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

    public Long getRespuestaId() {
        return respuestaId;
    }

    public void setRespuestaId(Long respuestaId) {
        this.respuestaId = respuestaId;
    }

    @Override
    public String toString() {
        return "MostrarRespuestas{" +
                "mostrarRespuestasId=" + mostrarRespuestasId +
                ", mostrarSiSeleccionaId=" + mostrarSiSeleccionaId +
                ", cuestionarioId=" + cuestionarioId +
                ", preguntaId=" + preguntaId +
                ", respuestaId=" + respuestaId +
                '}';
    }
}
