package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "pregunta",
        foreignKeys = {
                @ForeignKey(entity = Cuestionario.class,
                        parentColumns = "cuestionarioId",
                        childColumns = "cuestionarioId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = {"cuestionarioId"})
        })
public class Pregunta implements Serializable {
    @PrimaryKey
    private Long preguntaId;
    private Integer orden;
    private String titulo;
    private String tipo;
    private Boolean requerido;
    private String tipoInput;
    private String nombre;
    private String descripcion;
    private Boolean visible;
    private Long cuestionarioId;
    @Ignore
    private List<Respuesta> respuestas = new ArrayList<>();

    public Pregunta(){

    }

    @Ignore
    public Pregunta(Long preguntaId, Integer orden, String titulo, String tipo, Boolean requerido, String tipoInput, String nombre, String descripcion, Boolean visible, Long cuestionarioId) {
        this.preguntaId = preguntaId;
        this.orden = orden;
        this.titulo = titulo;
        this.tipo = tipo;
        this.requerido = requerido;
        this.tipoInput = tipoInput;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.visible = visible;
        this.cuestionarioId = cuestionarioId;
    }

    public Long getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(Long preguntaId) {
        this.preguntaId = preguntaId;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getRequerido() {
        return requerido;
    }

    public void setRequerido(Boolean requerido) {
        this.requerido = requerido;
    }

    public String getTipoInput() {
        return tipoInput;
    }

    public void setTipoInput(String tipoInput) {
        this.tipoInput = tipoInput;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Long getCuestionarioId() {
        return cuestionarioId;
    }

    public void setCuestionarioId(Long cuestionarioId) {
        this.cuestionarioId = cuestionarioId;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    @Override
    public String toString() {
        return "Pregunta{" +
                "preguntaId=" + preguntaId +
                ", orden=" + orden +
                ", titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", requerido=" + requerido +
                ", tipoInput='" + tipoInput + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", visible=" + visible +
                ", cuestionarioId=" + cuestionarioId +
                '}';
    }
}
