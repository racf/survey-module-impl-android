package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "mostrarCuestionarios",
        foreignKeys = {
                @ForeignKey(entity = MostrarSiSelecciona.class,
                        parentColumns = "mostrarSiSeleccionaId",
                        childColumns = "mostrarSiSeleccionaId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = {"mostrarSiSeleccionaId"})
        })
public class MostrarCuestionarios implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long mostrarCuestionariosId;
    private Long mostrarSiSeleccionaId;
    private Long cuestionarioId;

    public MostrarCuestionarios(){

    }

    @Ignore
    public MostrarCuestionarios(Long mostrarCuestionariosId, Long mostrarSiSeleccionaId, Long cuestionarioId) {
        this.mostrarCuestionariosId = mostrarCuestionariosId;
        this.mostrarSiSeleccionaId = mostrarSiSeleccionaId;
        this.cuestionarioId = cuestionarioId;
    }

    public Long getMostrarCuestionariosId() {
        return mostrarCuestionariosId;
    }

    public void setMostrarCuestionariosId(Long mostrarCuestionariosId) {
        this.mostrarCuestionariosId = mostrarCuestionariosId;
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

    @Override
    public String toString() {
        return "MostrarCuestionarios{" +
                "mostrarCuestionariosId=" + mostrarCuestionariosId +
                ", mostrarSiSeleccionaId=" + mostrarSiSeleccionaId +
                ", cuestionarioId=" + cuestionarioId +
                '}';
    }
}
