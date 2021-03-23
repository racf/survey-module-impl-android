package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
* Copyright 2021 CysOut Solutions and SouSystems
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
**/
@Entity(tableName = "questionnaire",
        foreignKeys = {
            @ForeignKey(entity = Survey.class,
                    parentColumns = "surveyId",
                    childColumns = "surveyId",
                    onDelete = ForeignKey.CASCADE)
        },
        indices = {
            @Index(value = {"surveyId"})
        })
public class Questionnaire implements Serializable {
    @PrimaryKey
    private Long questionnaireId;
    private Integer order;
    private String title;
    private String name;
    private Boolean visible;
    private Long surveyId;
    @Ignore
    private List<Question> questions = new ArrayList<>();

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Questionnaire() {

    }

    @Ignore
    public Questionnaire(Long questionnaireId, Integer order, String title, String name, Boolean visible, Long surveyId) {
        this.questionnaireId = questionnaireId;
        this.order = order;
        this.title = title;
        this.name = name;
        this.visible = visible;
        this.surveyId = surveyId;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "questionnaireId=" + questionnaireId +
                ", order=" + order +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", visible=" + visible +
                ", surveyId=" + surveyId +
                '}';
    }
}
