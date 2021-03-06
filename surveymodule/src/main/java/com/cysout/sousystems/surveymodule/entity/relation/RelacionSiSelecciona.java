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

public class RelacionSiSelecciona implements Serializable {
    @Embedded
    ShowSelect showSelect = new ShowSelect();
    @Relation(parentColumn = "showSelectId", entityColumn = "showSelectId", entity = ShowQuestionnaires.class)
    List<ShowQuestionnaires> cuestionarios = new ArrayList<>();
    @Relation(parentColumn = "showSelectId", entityColumn = "showSelectId", entity = ShowQuestions.class)
    List<ShowQuestions> preguntas = new ArrayList<>();
    @Relation(parentColumn = "showSelectId", entityColumn = "showSelectId", entity = ShowAnswers.class)
    List<ShowAnswers> respuestas = new ArrayList<>();

    public ShowSelect getShowSelect() {
        return showSelect;
    }

    public void setShowSelect(ShowSelect showSelect) {
        this.showSelect = showSelect;
    }

    public List<ShowQuestionnaires> getCuestionarios() {
        return cuestionarios;
    }

    public void setCuestionarios(List<ShowQuestionnaires> cuestionarios) {
        this.cuestionarios = cuestionarios;
    }

    public List<ShowQuestions> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<ShowQuestions> preguntas) {
        this.preguntas = preguntas;
    }

    public List<ShowAnswers> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<ShowAnswers> respuestas) {
        this.respuestas = respuestas;
    }
}
