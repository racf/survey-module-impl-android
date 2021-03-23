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
@Entity(tableName = "showSelect",
        foreignKeys = {
                @ForeignKey(entity = Answer.class,
                        parentColumns = "answerId",
                        childColumns = "answerId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = {"answerId"})
        })
public class ShowSelect implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long showSelectId;
    private Long answerId;
    @Ignore
    private List<ShowQuestionnaires> questionnaires = new ArrayList<>();
    @Ignore
    private List<ShowQuestions> questions = new ArrayList<>();
    @Ignore
    private List<ShowAnswers> answers = new ArrayList<>();

    public ShowSelect(){

    }

    @Ignore
    public ShowSelect(Long showSelectId, Long answerId) {
        this.showSelectId = showSelectId;
        this.answerId = answerId;
    }

    public Long getShowSelectId() {
        return showSelectId;
    }

    public void setShowSelectId(Long showSelectId) {
        this.showSelectId = showSelectId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public List<ShowQuestionnaires> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(List<ShowQuestionnaires> questionnaires) {
        this.questionnaires = questionnaires;
    }

    public List<ShowQuestions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ShowQuestions> questions) {
        this.questions = questions;
    }

    public List<ShowAnswers> getAnswers() {
        return answers;
    }

    public void setAnswers(List<ShowAnswers> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "ShowSelect{" +
                "showSelectId=" + showSelectId +
                ", answerId=" + answerId +
                '}';
    }
}
