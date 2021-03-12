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
@Entity(tableName = "showAnswers",
        foreignKeys = {
                @ForeignKey(entity = ShowSelect.class,
                        parentColumns = "showSelectId",
                        childColumns = "showSelectId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = {"showSelectId"})
        })
public class ShowAnswers implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long showAnswersId;
    private Long showSelectId;
    private Long questionnaireId;
    private Long questionId;
    private Long answerId;

    public ShowAnswers(){

    }

    @Ignore
    public ShowAnswers(Long showAnswersId, Long showSelectId, Long questionnaireId, Long questionId, Long answerId) {
        this.showAnswersId = showAnswersId;
        this.showSelectId = showSelectId;
        this.questionnaireId = questionnaireId;
        this.questionId = questionId;
        this.answerId = answerId;
    }

    public Long getShowAnswersId() {
        return showAnswersId;
    }

    public void setShowAnswersId(Long showAnswersId) {
        this.showAnswersId = showAnswersId;
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

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    @Override
    public String toString() {
        return "ShowAnswers{" +
                "showAnswersId=" + showAnswersId +
                ", showSelectId=" + showSelectId +
                ", questionnaireId=" + questionnaireId +
                ", questionId=" + questionId +
                ", answerId=" + answerId +
                '}';
    }
}
