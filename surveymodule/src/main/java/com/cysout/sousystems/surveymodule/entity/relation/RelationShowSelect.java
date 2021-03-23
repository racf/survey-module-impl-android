package com.cysout.sousystems.surveymodule.entity.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cysout.sousystems.surveymodule.entity.ShowQuestionnaires;
import com.cysout.sousystems.surveymodule.entity.ShowQuestions;
import com.cysout.sousystems.surveymodule.entity.ShowAnswers;
import com.cysout.sousystems.surveymodule.entity.ShowSelect;
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
public class RelationShowSelect implements Serializable {
    @Embedded
    ShowSelect showSelect = new ShowSelect();
    @Relation(parentColumn = "showSelectId", entityColumn = "showSelectId", entity = ShowQuestionnaires.class)
    List<ShowQuestionnaires> questionnaires = new ArrayList<>();
    @Relation(parentColumn = "showSelectId", entityColumn = "showSelectId", entity = ShowQuestions.class)
    List<ShowQuestions> questions = new ArrayList<>();
    @Relation(parentColumn = "showSelectId", entityColumn = "showSelectId", entity = ShowAnswers.class)
    List<ShowAnswers> answers = new ArrayList<>();

    public ShowSelect getShowSelect() {
        return showSelect;
    }

    public void setShowSelect(ShowSelect showSelect) {
        this.showSelect = showSelect;
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
}
