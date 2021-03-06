package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "survey")
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
public class Survey implements Serializable {
    @PrimaryKey
    private Long surveyId;
    private String title;
    private String description;
    private Integer phase;
    private Long surveyType;
    private Boolean visible;
    private Long versionCode;
    private String json;
    private String dateCreated;
    private String dateModified;
    @Ignore
    private List<Questionnaire> questionnaires = new ArrayList<>();

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Long getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Long versionCode) {
        this.versionCode = versionCode;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPhase() {
        return phase;
    }

    public void setPhase(Integer phase) {
        this.phase = phase;
    }

    public Long getSurveyType() {
        return surveyType;
    }

    public void setSurveyType(Long surveyType) {
        this.surveyType = surveyType;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public List<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(List<Questionnaire> questionnaire) {
        this.questionnaires = questionnaire;
    }

    public Survey() {

    }

    @Ignore
    public Survey(Long surveyId, String title, String description, Integer phase, Long surveyType, Boolean visible) {
        this.surveyId = surveyId;
        this.title = title;
        this.description = description;
        this.phase = phase;
        this.surveyType = surveyType;
        this.visible=visible;
    }

    @Override
    public String toString() {
        return "Survey{" +
                "surveyId=" + surveyId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", phase=" + phase +
                ", surveyType=" + surveyType +
                ", visible=" + visible +
                ", versionCode=" + versionCode +
                ", json='" + json + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateModified='" + dateModified + '\'' +
                '}';
    }
}
