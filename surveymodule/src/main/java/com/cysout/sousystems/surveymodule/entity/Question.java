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
@Entity(tableName = "question",
        foreignKeys = {
                @ForeignKey(entity = Questionnaire.class,
                        parentColumns = "questionnaireId",
                        childColumns = "questionnaireId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = {"questionnaireId"})
        })
public class Question implements Serializable {
    @PrimaryKey
    private Long questionId;
    private Integer order;
    private String title;
    private String type;
    private Boolean required;
    private String typeInput;
    private String name;
    private String description;
    private Boolean visible;
    private Long questionnaireId;
    @Ignore
    private List<Answer> answers = new ArrayList<>();
    @Ignore
    private List<String> validations = new ArrayList<>();

    public Question(){

    }

    @Ignore
    public Question(Long questionId, Integer order, String title, String type, Boolean required, String typeInput, String name, String description, Boolean visible, Long questionnaireId) {
        this.questionId = questionId;
        this.order = order;
        this.title = title;
        this.type = type;
        this.required = required;
        this.typeInput = typeInput;
        this.name = name;
        this.description = description;
        this.visible = visible;
        this.questionnaireId = questionnaireId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getTypeInput() {
        return typeInput;
    }

    public void setTypeInput(String typeInput) {
        this.typeInput = typeInput;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<String> getValidations() {
        return validations;
    }

    public void setValidations(List<String> validations) {
        this.validations = validations;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", order=" + order +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", required=" + required +
                ", typeInput='" + typeInput + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", visible=" + visible +
                ", questionnaireId=" + questionnaireId +
                '}';
    }
}
