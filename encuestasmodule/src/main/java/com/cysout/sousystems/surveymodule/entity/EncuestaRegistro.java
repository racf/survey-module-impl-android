package com.cysout.sousystems.surveymodule.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "encuestaRegistro", foreignKeys = {
        @ForeignKey(entity = Encuesta.class,
                parentColumns = "encuestaId",
                childColumns = "encuestaIdFK")
        },
        indices = {
                @Index(value = {"encuestaIdFK"})
        })
/*,
        foreignKeys = {
@ForeignKey(entity = Encuesta.class,
        parentColumns = "encuestaId",
        childColumns = "encuestaId"),
@ForeignKey(entity = CatEncuestaEstatus.class,
        parentColumns = "catEncuestaEstatusId",
        childColumns = "catEncuestaEstatusId")
        },
                indices = {
@Index(value = {"encuestaId"}),
@Index(value = {"catEncuestaEstatusId"})
    }*/

public class EncuestaRegistro implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long encuestaRegistroId;
    @ColumnInfo(name = "encuestaIdFK")
    private Long encuestaId;
    private Integer catEncuestaEstatusId;
    private String fechaInicial;
    private String fechaFinal;

    public EncuestaRegistro() {

    }

    @Ignore
    public EncuestaRegistro(Long encuestaRegistroId, Long encuestaId, Integer catEncuestaEstatusId, String fechaInicial, String fechaFinal) {
        this.encuestaRegistroId = encuestaRegistroId;
        this.encuestaId = encuestaId;
        this.catEncuestaEstatusId = catEncuestaEstatusId;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }

    public Long getEncuestaRegistroId() {
        return encuestaRegistroId;
    }

    public void setEncuestaRegistroId(Long encuestaRegistroId) {
        this.encuestaRegistroId = encuestaRegistroId;
    }

    public Long getEncuestaId() {
        return encuestaId;
    }

    public void setEncuestaId(Long encuestaId) {
        this.encuestaId = encuestaId;
    }

    public Integer getCatEncuestaEstatusId() {
        return catEncuestaEstatusId;
    }

    public void setCatEncuestaEstatusId(Integer catEncuestaEstatusId) {
        this.catEncuestaEstatusId = catEncuestaEstatusId;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    @Override
    public String toString() {
        return "EncuestaRegistro{" +
                "encuestaRegistroId=" + encuestaRegistroId +
                ", encuestaId=" + encuestaId +
                ", catEncuestaEstatusId=" + catEncuestaEstatusId +
                ", fechaInicial='" + fechaInicial + '\'' +
                ", fechaFinal='" + fechaFinal + '\'' +
                '}';
    }
}
