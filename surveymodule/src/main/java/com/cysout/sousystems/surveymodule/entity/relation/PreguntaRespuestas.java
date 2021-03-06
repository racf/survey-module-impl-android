package com.cysout.sousystems.surveymodule.entity.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Answer;
import com.cysout.sousystems.surveymodule.entity.Question;

public class PreguntaRespuestas implements Serializable {
    @Embedded
    private Question question;

    @Relation(parentColumn = "questionId", entityColumn = "questionId", entity = Answer.class)
    List<Answer> answers = new ArrayList<>();

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
