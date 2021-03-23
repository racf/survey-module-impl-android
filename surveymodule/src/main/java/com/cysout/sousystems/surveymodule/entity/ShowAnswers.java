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
@Entity(tableName = "showAnswers",
        foreignKeys = {
                @ForeignKey(entity = ShowSelect.class,
                        parentColumns = "showSelectId",
                        childColumns = "showSelectId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = {"showSelectId"})
        })
public class ShowAnswers implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long showAnswersId;
    private Long showSelectId;
    private Long questionnaireId;
    private Long questionId;
    private Long answerId;

    public ShowAnswers(){

    }

    @Ignore
    public ShowAnswers(Long showAnswersId, Long showSelectId, Long questionnaireId, Long questionId, Long answerId) {
        this.showAnswersId = showAnswersId;
        this.showSelectId = showSelectId;
        this.questionnaireId = questionnaireId;
        this.questionId = questionId;
        this.answerId = answerId;
    }

    public Long getShowAnswersId() {
        return showAnswersId;
    }

    public void setShowAnswersId(Long showAnswersId) {
        this.showAnswersId = showAnswersId;
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

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    @Override
    public String toString() {
        return "ShowAnswers{" +
                "showAnswersId=" + showAnswersId +
                ", showSelectId=" + showSelectId +
                ", questionnaireId=" + questionnaireId +
                ", questionId=" + questionId +
                ", answerId=" + answerId +
                '}';
    }
}
