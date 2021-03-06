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
 * Developed by cysout.com and sousystems.com.mx
 * Contact info@cysout.com or contacto@sousystems.com.mx
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
