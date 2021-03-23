package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
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
@Entity(tableName = "showQuestions",
        foreignKeys = {
                @ForeignKey(entity = ShowSelect.class,
                        parentColumns = "showSelectId",
                        childColumns = "showSelectId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = {"showSelectId"})
        })
public class ShowQuestions implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long showQuestionsId;
    private Long showSelectId;
    private Long questionnaireId;
    private Long questionId;

    public ShowQuestions(){

    }

    @Ignore
    public ShowQuestions(Long showQuestionsId, Long showSelectId, Long questionnaireId, Long questionId) {
        this.showQuestionsId = showQuestionsId;
        this.showSelectId = showSelectId;
        this.questionnaireId = questionnaireId;
        this.questionId = questionId;
    }

    public Long getShowQuestionsId() {
        return showQuestionsId;
    }

    public void setShowQuestionsId(Long showQuestionsId) {
        this.showQuestionsId = showQuestionsId;
    }

    public Long getShowSelectId() {
        return showSelectId;
    }

    public void setShowSelectId(Long showSelectId) {
        this.showSelectId = showSelectId;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "ShowQuestions{" +
                "showQuestionsId=" + showQuestionsId +
                ", showSelectId=" + showSelectId +
                ", questionnaireId=" + questionnaireId +
                ", questionId=" + questionId +
                '}';
    }
}
