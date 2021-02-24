package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "respuesta",
        foreignKeys = {
                @ForeignKey(entity = Pregunta.class,
                        parentColumns = "preguntaId",
                        childColumns = "preguntaId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = {"preguntaId"})
        })
public class Respuesta implements Serializable {
    @PrimaryKey
    private Long respuestaId;
    private String texto;
    private Boolean visible = true;
    private Boolean finalizarSiSelecciona;
    private Long preguntaId;
    @Ignore
    private MostrarSiSelecciona mostrarSiSelecciona = new MostrarSiSelecciona();

    public Respuesta(){

    }

    @Ignore
    public Respuesta(Long respuestaId, String texto, Boolean visible, Boolean finalizarSiSelecciona, Long preguntaId) {
        this.respuestaId = respuestaId;
        this.texto = texto;
        this.visible = visible;
        this.finalizarSiSelecciona = finalizarSiSelecciona;
        this.preguntaId = preguntaId;
    }

    public Long getRespuestaId() {
        return respuestaId;
    }

    public void setRespuestaId(Long respuestaId) {
        this.respuestaId = respuestaId;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getFinalizarSiSelecciona() {
        return finalizarSiSelecciona;
    }

    public void setFinalizarSiSelecciona(Boolean finalizarSiSelecciona) {
        this.finalizarSiSelecciona = finalizarSiSelecciona;
    }

    public Long getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(Long preguntaId) {
        this.preguntaId = preguntaId;
    }

    public MostrarSiSelecciona getMostrarSiSelecciona() {
        return mostrarSiSelecciona;
    }

    public void setMostrarSiSelecciona(MostrarSiSelecciona mostrarSiSelecciona) {
        this.mostrarSiSelecciona = mostrarSiSelecciona;
    }

    @Override
    public String toString() {
        return "Respuesta{" +
                "respuestaId=" + respuestaId +
                ", texto='" + texto + '\'' +
                ", visible=" + visible +
                ", finalizarSiSelecciona=" + finalizarSiSelecciona +
                ", preguntaId=" + preguntaId +
                '}';
    }
}
