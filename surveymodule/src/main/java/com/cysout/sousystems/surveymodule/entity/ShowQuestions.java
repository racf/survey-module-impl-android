package com.cysout.sousystems.surveymodule.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
/**
 * Developed by cysout.com and sousystems.com.mx
 * Contact info@cysout.com or contacto@sousystems.com.mx
**/
@Entity(tableName = "showQuestions",
        foreignKeys = {
                @ForeignKey(entity = ShowSelect.class,
                        parentColumns = "showSelectId",
                        childColumns = "showSelectId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = {"showSelectId"})
        })
public class ShowQuestions implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long showQuestionsId;
    private Long showSelectId;
    private Long questionnaireId;
    private Long questionId;

    public ShowQuestions(){

    }

    @Ignore
    public ShowQuestions(Long showQuestionsId, Long showSelectId, Long questionnaireId, Long questionId) {
        this.showQuestionsId = showQuestionsId;
        this.showSelectId = showSelectId;
        this.questionnaireId = questionnaireId;
        this.questionId = questionId;
    }

    public Long getShowQuestionsId() {
        return showQuestionsId;
    }

    public void setShowQuestionsId(Long showQuestionsId) {
        this.showQuestionsId = showQuestionsId;
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

    @Override
    public String toString() {
        return "ShowQuestions{" +
                "showQuestionsId=" + showQuestionsId +
                ", showSelectId=" + showSelectId +
                ", questionnaireId=" + questionnaireId +
                ", questionId=" + questionId +
                '}';
    }
}
