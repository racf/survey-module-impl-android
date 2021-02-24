package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "encuestaRespuesta",
        foreignKeys = {
            @ForeignKey(entity = EncuestaRegistro.class,
                    parentColumns = "encuestaRegistroId",
                    childColumns = "encuestaRegistroId")
        },
        indices = {
            @Index(value = {"encuestaRegistroId"})
    })
public class EncuestaRespuesta implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long encuestaRespuestaId;
    private Long encuestaRegistroId;
    private Long cuestionarioId;
    private Long preguntaId;
    private String tipo;
    private String respuesta;

    public EncuestaRespuesta() {

    }

    @Ignore
    public EncuestaRespuesta(Long encuestaRespuestaId, Long encuestaRegistroId, Long cuestionarioId, Long preguntaId, String tipo, String respuesta) {
        this.encuestaRespuestaId = encuestaRespuestaId;
        this.encuestaRegistroId = encuestaRegistroId;
        this.cuestionarioId = cuestionarioId;
        this.preguntaId = preguntaId;
        this.tipo = tipo;
        this.respuesta = respuesta;
    }

    public Long getEncuestaRespuestaId() {
        return encuestaRespuestaId;
    }

    public void setEncuestaRespuestaId(Long encuestaRespuestaId) {
        this.encuestaRespuestaId = encuestaRespuestaId;
    }

    public Long getEncuestaRegistroId() {
        return encuestaRegistroId;
    }

    public void setEncuestaRegistroId(Long encuestaRegistroId) {
        this.encuestaRegistroId = encuestaRegistroId;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "EncuestaRespuesta{" +
                "encuestaRespuestaId=" + encuestaRespuestaId +
                ", encuestaRegistroId=" + encuestaRegistroId +
                ", cuestionarioId=" + cuestionarioId +
                ", preguntaId=" + preguntaId +
                ", tipo='" + tipo + '\'' +
                ", respuesta='" + respuesta + '\'' +
                '}';
    }
}
