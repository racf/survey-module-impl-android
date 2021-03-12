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

@Entity(tableName = "answer",
        foreignKeys = {
                @ForeignKey(entity = Question.class,
                        parentColumns = "questionId",
                        childColumns = "questionId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = {"questionId"})
        })
public class Answer implements Serializable {
    @PrimaryKey
    private Long answerId;
    private String text;
    private Boolean visible = true;
    private Boolean finishSelect;
    private Long questionId;
    @Ignore
    private ShowSelect showSelect = new ShowSelect();

    public Answer(){

    }

    @Ignore
    public Answer(Long answerId, String text, Boolean visible, Boolean finishSelect, Long questionId) {
        this.answerId = answerId;
        this.text = text;
        this.visible = visible;
        this.finishSelect = finishSelect;
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getFinishSelect() {
        return finishSelect;
    }

    public void setFinishSelect(Boolean finishSelect) {
        this.finishSelect = finishSelect;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public ShowSelect getShowSelect() {
        return showSelect;
    }

    public void setShowSelect(ShowSelect showSelect) {
        this.showSelect = showSelect;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", text='" + text + '\'' +
                ", visible=" + visible +
                ", finishSelect=" + finishSelect +
                ", questionId=" + questionId +
                ", showSelect=" + showSelect +
                '}';
    }
}
