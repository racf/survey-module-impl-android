package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "answerShowQuestionnaires")
public class AnswerShowQuestionnaires implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long answerShowQuestionnairesId;
    private Long questionId;
    private Long answerId;
    private Long questionnaireId;
    private Long questionnaireOriginId;

    public AnswerShowQuestionnaires() {
    }
    @Ignore
    public AnswerShowQuestionnaires(Long answerShowQuestionnairesId, Long questionId, Long answerId, Long questionnaireId, Long questionnaireOriginId) {
        this.answerShowQuestionnairesId = answerShowQuestionnairesId;
        this.questionId = questionId;
        this.answerId = answerId;
        this.questionnaireId = questionnaireId;
        this.questionnaireOriginId = questionnaireOriginId;
    }

    public Long getAnswerShowQuestionnairesId() {
        return answerShowQuestionnairesId;
    }

    public void setAnswerShowQuestionnairesId(Long answerShowQuestionnairesId) {
        this.answerShowQuestionnairesId = answerShowQuestionnairesId;
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

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Long getQuestionnaireOriginId() {
        return questionnaireOriginId;
    }

    public void setQuestionnaireOriginId(Long questionnaireOriginId) {
        this.questionnaireOriginId = questionnaireOriginId;
    }

    @Override
    public String toString() {
        return "AnswerShowQuestionnaires{" +
                "answerShowQuestionnairesId=" + answerShowQuestionnairesId +
                ", questionId=" + questionId +
                ", answerId=" + answerId +
                ", questionnaireId=" + questionnaireId +
                ", questionnaireOriginId=" + questionnaireOriginId +
                '}';
    }
}
