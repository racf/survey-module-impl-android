package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "surveyAnswer",
        foreignKeys = {
            @ForeignKey(entity = SurveyRecord.class,
                    parentColumns = "surveyRecordId",
                    childColumns = "surveyRecordId")
        },
        indices = {
            @Index(value = {"surveyRecordId"})
    })
public class SurveyAnswer implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long encuestaRespuestaId;
    private Long surveyRecordId;
    private Long cuestionarioId;
    private Long preguntaId;
    private String tipo;
    private String respuesta;

    public SurveyAnswer() {

    }

    @Ignore
    public SurveyAnswer(Long encuestaRespuestaId, Long surveyRecordId, Long cuestionarioId, Long preguntaId, String tipo, String respuesta) {
        this.encuestaRespuestaId = encuestaRespuestaId;
        this.surveyRecordId = surveyRecordId;
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

    public Long getSurveyRecordId() {
        return surveyRecordId;
    }

    public void setSurveyRecordId(Long surveyRecordId) {
        this.surveyRecordId = surveyRecordId;
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
                ", surveyRecordId=" + surveyRecordId +
                ", cuestionarioId=" + cuestionarioId +
                ", preguntaId=" + preguntaId +
                ", tipo='" + tipo + '\'' +
                ", respuesta='" + respuesta + '\'' +
                '}';
    }
}
