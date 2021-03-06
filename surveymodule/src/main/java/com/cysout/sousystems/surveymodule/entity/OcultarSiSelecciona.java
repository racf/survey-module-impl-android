package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "ocultarSiSelecciona",
        foreignKeys = {
                @ForeignKey(entity = Answer.class,
                        parentColumns = "respuestaId",
                        childColumns = "respuestaId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = {"respuestaId"})
        })

public class OcultarSiSelecciona implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long ocultarSiSeleccionaId;
    private Long respuestaId;

    public OcultarSiSelecciona(){

    }

    @Ignore
    public OcultarSiSelecciona(Long ocultarSiSeleccionaId, Long respuestaId) {
        this.ocultarSiSeleccionaId = ocultarSiSeleccionaId;
        this.respuestaId = respuestaId;
    }

    public Long getOcultarSiSeleccionaId() {
        return ocultarSiSeleccionaId;
    }

    public void setOcultarSiSeleccionaId(Long ocultarSiSeleccionaId) {
        this.ocultarSiSeleccionaId = ocultarSiSeleccionaId;
    }

    public Long getRespuestaId() {
        return respuestaId;
    }

    public void setRespuestaId(Long respuestaId) {
        this.respuestaId = respuestaId;
    }
}
