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
@Entity(tableName = "showQuestionnaires",
        foreignKeys = {
                @ForeignKey(entity = ShowSelect.class,
                        parentColumns = "showSelectId",
                        childColumns = "showSelectId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = {"showSelectId"})
        })
public class ShowQuestionnaires implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long showQuestionnairesId;
    private Long showSelectId;
    private Long questionnaireId;

    public ShowQuestionnaires(){

    }

    @Ignore
    public ShowQuestionnaires(Long showQuestionnairesId, Long showSelectId, Long questionnaireId) {
        this.showQuestionnairesId = showQuestionnairesId;
        this.showSelectId = showSelectId;
        this.questionnaireId = questionnaireId;
    }

    public Long getShowQuestionnairesId() {
        return showQuestionnairesId;
    }

    public void setShowQuestionnairesId(Long showQuestionnairesId) {
        this.showQuestionnairesId = showQuestionnairesId;
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

    @Override
    public String toString() {
        return "ShowQuestionnaires{" +
                "showQuestionnairesId=" + showQuestionnairesId +
                ", showSelectId=" + showSelectId +
                ", questionnaireId=" + questionnaireId +
                '}';
    }
}
