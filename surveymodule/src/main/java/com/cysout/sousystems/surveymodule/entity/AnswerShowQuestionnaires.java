package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
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
@Entity(tableName = "answerShowQuestionnaires")
public class AnswerShowQuestionnaires implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long answerShowQuestionnairesId;
    private Long questionId;
    private Long answerId;
    private Long questionnaireId;
    private Long questionnaireOriginId;

    public AnswerShowQuestionnaires() {
    }
    @Ignore
    public AnswerShowQuestionnaires(Long answerShowQuestionnairesId, Long questionId, Long answerId, Long questionnaireId, Long questionnaireOriginId) {
        this.answerShowQuestionnairesId = answerShowQuestionnairesId;
        this.questionId = questionId;
        this.answerId = answerId;
        this.questionnaireId = questionnaireId;
        this.questionnaireOriginId = questionnaireOriginId;
    }

    public Long getAnswerShowQuestionnairesId() {
        return answerShowQuestionnairesId;
    }

    public void setAnswerShowQuestionnairesId(Long answerShowQuestionnairesId) {
        this.answerShowQuestionnairesId = answerShowQuestionnairesId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Long getQuestionnaireOriginId() {
        return questionnaireOriginId;
    }

    public void setQuestionnaireOriginId(Long questionnaireOriginId) {
        this.questionnaireOriginId = questionnaireOriginId;
    }

    @Override
    public String toString() {
        return "AnswerShowQuestionnaires{" +
                "answerShowQuestionnairesId=" + answerShowQuestionnairesId +
                ", questionId=" + questionId +
                ", answerId=" + answerId +
                ", questionnaireId=" + questionnaireId +
                ", questionnaireOriginId=" + questionnaireOriginId +
                '}';
    }
}
