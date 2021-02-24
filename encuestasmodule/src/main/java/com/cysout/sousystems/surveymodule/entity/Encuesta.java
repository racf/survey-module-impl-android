package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "encuesta")
/*,
        foreignKeys = {
@ForeignKey(entity = CatEncuestaTipo.class,
        parentColumns = "catEncuestaTipoId",
        childColumns = "catEncuestaTipoId",
        onDelete = ForeignKey.CASCADE)
        },
                indices = {
@Index(value = {"catEncuestaTipoId"})
        }*/
public class Encuesta implements Serializable {
    @PrimaryKey
    private Long encuestaId;
    private String titulo;
    private String descripcion;
    private Integer etapa;
    private Long catEncuestaTipoId;
    private Boolean visible;
    private String json;
    private String fechaCreacion;
    private String fechaModificacion;
    @Ignore
    private List<Cuestionario> cuestionarios = new ArrayList<>();

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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEtapa() {
        return etapa;
    }

    public void setEtapa(Integer etapa) {
        this.etapa = etapa;
    }

    public Long getCatEncuestaTipoId() {
        return catEncuestaTipoId;
    }

    public void setCatEncuestaTipoId(Long catEncuestaTipoId) {
        this.catEncuestaTipoId = catEncuestaTipoId;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public List<Cuestionario> getCuestionarios() {
        return cuestionarios;
    }

    public void setCuestionarios(List<Cuestionario> cuestionarios) {
        this.cuestionarios = cuestionarios;
    }

    public Encuesta() {

    }

    @Ignore
    public Encuesta(Long encuestaId, String titulo, String descripcion, Integer etapa, Long catEncuestaTipoId, Boolean visible) {
        this.encuestaId = encuestaId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.etapa = etapa;
        this.catEncuestaTipoId = catEncuestaTipoId;
        this.visible=visible;
    }

    @Override
    public String toString() {
        return "Encuesta{" +
                "encuestaId=" + encuestaId +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", etapa=" + etapa +
                ", catEncuestaTipoId=" + catEncuestaTipoId +
                ", visible=" + visible +
                ", json='" + json + '\'' +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                ", fechaModificacion='" + fechaModificacion + '\'' +
                '}';
    }
}
