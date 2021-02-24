package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "cuestionario",
        foreignKeys = {
            @ForeignKey(entity = Encuesta.class,
                    parentColumns = "encuestaId",
                    childColumns = "encuestaId",
                    onDelete = ForeignKey.CASCADE)
        },
        indices = {
            @Index(value = {"encuestaId"})
        })
public class Cuestionario implements Serializable {
    @PrimaryKey
    private Long cuestionarioId;
    private Integer orden;
    private String titulo;
    private String nombre;
    private Boolean visible;
    private Long encuestaId;
    @Ignore
    private List<Pregunta> preguntas = new ArrayList<>();

    public Long getCuestionarioId() {
        return cuestionarioId;
    }

    public void setCuestionarioId(Long cuestionarioId) {
        this.cuestionarioId = cuestionarioId;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Long getEncuestaId() {
        return encuestaId;
    }

    public void setEncuestaId(Long encuestaId) {
        this.encuestaId = encuestaId;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public Cuestionario() {

    }

    @Ignore
    public Cuestionario(Long cuestionarioId, Integer orden, String titulo, String nombre, Boolean visible, Long encuestaId) {
        this.cuestionarioId = cuestionarioId;
        this.orden = orden;
        this.titulo = titulo;
        this.nombre = nombre;
        this.visible = visible;
        this.encuestaId = encuestaId;
    }

    @Override
    public String toString() {
        return "Cuestionario{" +
                "cuestionarioId=" + cuestionarioId +
                ", orden=" + orden +
                ", titulo='" + titulo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", visible=" + visible +
                ", encuestaId=" + encuestaId +
                '}';
    }
}
