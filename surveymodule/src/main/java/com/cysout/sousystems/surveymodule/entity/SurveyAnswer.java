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
@Entity(tableName = "surveyAnswer",
        foreignKeys = {
            @ForeignKey(entity = SurveyRecord.class,
                    parentColumns = "surveyRecordId",
                    childColumns = "surveyRecordId")
        },
        indices = {
            @Index(value = {"surveyRecordId"})
    })
public class SurveyAnswer implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long surveyAnswerId;
    private Long surveyRecordId;
    private Long questionnaireId;
    private Long questionId;
    private String type;
    private String answer;

    public SurveyAnswer() {

    }

    @Ignore
    public SurveyAnswer(Long surveyAnswerId, Long surveyRecordId, Long questionnaireId, Long questionId, String type, String answer) {
        this.surveyAnswerId = surveyAnswerId;
        this.surveyRecordId = surveyRecordId;
        this.questionnaireId = questionnaireId;
        this.questionId = questionId;
        this.type = type;
        this.answer = answer;
    }

    public Long getSurveyAnswerId() {
        return surveyAnswerId;
    }

    public void setSurveyAnswerId(Long surveyAnswerId) {
        this.surveyAnswerId = surveyAnswerId;
    }

    public Long getSurveyRecordId() {
        return surveyRecordId;
    }

    public void setSurveyRecordId(Long surveyRecordId) {
        this.surveyRecordId = surveyRecordId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "SurveyAnswer{" +
                "surveyAnswerId=" + surveyAnswerId +
                ", surveyRecordId=" + surveyRecordId +
                ", questionnaireId=" + questionnaireId +
                ", questionId=" + questionId +
                ", type='" + type + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
