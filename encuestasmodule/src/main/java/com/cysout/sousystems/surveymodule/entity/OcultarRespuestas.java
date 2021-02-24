package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "ocultarRespuestas",
        foreignKeys = {
                @ForeignKey(entity = OcultarSiSelecciona.class,
                        parentColumns = "ocultarRespuestasId",
                        childColumns = "ocultarSiSeleccionaId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = {"ocultarSiSeleccionaId"})
        })
public class OcultarRespuestas implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long ocultarRespuestasId;
    private Long ocultarSiSeleccionaId;
    private Long cuestionarioId;
    private Long preguntaId;
    private Long respuestaId;

    public OcultarRespuestas(){

    }

    @Ignore
    public OcultarRespuestas(Long ocultarRespuestasId, Long ocultarSiSeleccionaId, Long cuestionarioId, Long preguntaId, Long respuestaId) {
        this.ocultarRespuestasId = ocultarRespuestasId;
        this.ocultarSiSeleccionaId = ocultarSiSeleccionaId;
        this.cuestionarioId = cuestionarioId;
        this.preguntaId = preguntaId;
        this.respuestaId = respuestaId;
    }

    public Long getOcultarRespuestasId() {
        return ocultarRespuestasId;
    }

    public void setOcultarRespuestasId(Long ocultarRespuestasId) {
        this.ocultarRespuestasId = ocultarRespuestasId;
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

    public Long getRespuestaId() {
        return respuestaId;
    }

    public void setRespuestaId(Long respuestaId) {
        this.respuestaId = respuestaId;
    }
}
