package com.cysout.sousystems.surveymodule.entity.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Questionnaire;
import com.cysout.sousystems.surveymodule.entity.Survey;

public class EncuestaCuestionarios implements Serializable {
    @Embedded
    private Survey survey;

    @Relation(parentColumn = "surveyId", entityColumn = "surveyId", entity = Questionnaire.class)
    private List<Questionnaire> questionnaires = new ArrayList<>();

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public List<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(List<Questionnaire> questionnaires) {
        this.questionnaires = questionnaires;
    }
}
