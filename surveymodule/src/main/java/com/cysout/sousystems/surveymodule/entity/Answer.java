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
@Entity(tableName = "answer",
        foreignKeys = {
                @ForeignKey(entity = Question.class,
                        parentColumns = "questionId",
                        childColumns = "questionId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = {"questionId"})
        })
public class Answer implements Serializable {
    @PrimaryKey
    private Long answerId;
    private String text;
    private Boolean visible = true;
    private Boolean finishSelect;
    private Long questionId;
    @Ignore
    private ShowSelect showSelect = new ShowSelect();

    public Answer(){

    }

    @Ignore
    public Answer(Long answerId, String text, Boolean visible, Boolean finishSelect, Long questionId) {
        this.answerId = answerId;
        this.text = text;
        this.visible = visible;
        this.finishSelect = finishSelect;
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getFinishSelect() {
        return finishSelect;
    }

    public void setFinishSelect(Boolean finishSelect) {
        this.finishSelect = finishSelect;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public ShowSelect getShowSelect() {
        return showSelect;
    }

    public void setShowSelect(ShowSelect showSelect) {
        this.showSelect = showSelect;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", text='" + text + '\'' +
                ", visible=" + visible +
                ", finishSelect=" + finishSelect +
                ", questionId=" + questionId +
                ", showSelect=" + showSelect +
                '}';
    }
}
