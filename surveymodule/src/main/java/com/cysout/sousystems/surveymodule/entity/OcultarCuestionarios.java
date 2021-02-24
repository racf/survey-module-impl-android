package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "ocultarCuestionarios",
        foreignKeys = {
                @ForeignKey(entity = OcultarSiSelecciona.class,
                        parentColumns = "ocultarCuestionariosId",
                        childColumns = "ocultarSiSeleccionaId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = {"ocultarSiSeleccionaId"})
        })
public class OcultarCuestionarios implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long ocultarCuestionariosId;
    private Long ocultarSiSeleccionaId;
    private Long cuestionarioId;

    public OcultarCuestionarios(){

    }

    @Ignore
    public OcultarCuestionarios(Long ocultarCuestionariosId, Long ocultarSiSeleccionaId, Long cuestionarioId) {
        this.ocultarCuestionariosId = ocultarCuestionariosId;
        this.ocultarSiSeleccionaId = ocultarSiSeleccionaId;
        this.cuestionarioId = cuestionarioId;
    }

    public Long getOcultarCuestionariosId() {
        return ocultarCuestionariosId;
    }

    public void setOcultarCuestionariosId(Long ocultarCuestionariosId) {
        this.ocultarCuestionariosId = ocultarCuestionariosId;
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
}
