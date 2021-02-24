package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "respuestaMostrarCuestionarios")
public class RespuestaMostrarCuestionarios implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long respuestaMostrarCuestionariosId;
    private Long preguntaId;
    private Long respuestaId;
    private Long cuestionarioId;
    private Long cuestionarioOrigenId;

    public RespuestaMostrarCuestionarios() {
    }
    @Ignore
    public RespuestaMostrarCuestionarios(Long respuestaMostrarCuestionariosId, Long preguntaId, Long respuestaId, Long cuestionarioId, Long cuestionarioOrigenId) {
        this.respuestaMostrarCuestionariosId = respuestaMostrarCuestionariosId;
        this.preguntaId = preguntaId;
        this.respuestaId = respuestaId;
        this.cuestionarioId = cuestionarioId;
        this.cuestionarioOrigenId = cuestionarioOrigenId;
    }

    public Long getRespuestaMostrarCuestionariosId() {
        return respuestaMostrarCuestionariosId;
    }

    public void setRespuestaMostrarCuestionariosId(Long respuestaMostrarCuestionariosId) {
        this.respuestaMostrarCuestionariosId = respuestaMostrarCuestionariosId;
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

    public Long getCuestionarioId() {
        return cuestionarioId;
    }

    public void setCuestionarioId(Long cuestionarioId) {
        this.cuestionarioId = cuestionarioId;
    }

    public Long getCuestionarioOrigenId() {
        return cuestionarioOrigenId;
    }

    public void setCuestionarioOrigenId(Long cuestionarioOrigenId) {
        this.cuestionarioOrigenId = cuestionarioOrigenId;
    }

    @Override
    public String toString() {
        return "RespuestaMostrarCuestionarios{" +
                "respuestaMostrarCuestionariosId=" + respuestaMostrarCuestionariosId +
                ", preguntaId=" + preguntaId +
                ", respuestaId=" + respuestaId +
                ", cuestionarioId=" + cuestionarioId +
                ", cuestionarioOrigenId=" + cuestionarioOrigenId +
                '}';
    }
}
