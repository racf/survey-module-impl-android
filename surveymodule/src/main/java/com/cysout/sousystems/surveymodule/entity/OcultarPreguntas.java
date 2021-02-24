package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "ocultarPreguntas",
        foreignKeys = {
                @ForeignKey(entity = OcultarSiSelecciona.class,
                        parentColumns = "ocultarPreguntasId",
                        childColumns = "ocultarSiSeleccionaId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = {"ocultarSiSeleccionaId"})
        })
public class OcultarPreguntas implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long ocultarPreguntasId;
    private Long ocultarSiSeleccionaId;
    private Long cuestionarioId;
    private Long preguntaId;

    public OcultarPreguntas(){

    }

    @Ignore
    public OcultarPreguntas(Long ocultarPreguntasId, Long ocultarSiSeleccionaId, Long cuestionarioId, Long preguntaId) {
        this.ocultarPreguntasId = ocultarPreguntasId;
        this.ocultarSiSeleccionaId = ocultarSiSeleccionaId;
        this.cuestionarioId = cuestionarioId;
        this.preguntaId = preguntaId;
    }

    public Long getOcultarPreguntasId() {
        return ocultarPreguntasId;
    }

    public void setOcultarPreguntasId(Long ocultarPreguntasId) {
        this.ocultarPreguntasId = ocultarPreguntasId;
    }

    public Long getOcultarSiSeleccionaId() {
        return ocultarSiSeleccionaId;
    }

    public void setOcultarSiSeleccionaId(Long ocultarSiSeleccionaId) {
        this.ocultarSiSeleccionaId = ocultarSiSeleccionaId;
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
}
