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
@Entity(tableName = "showQuestionnaires",
        foreignKeys = {
                @ForeignKey(entity = ShowSelect.class,
                        parentColumns = "showSelectId",
                        childColumns = "showSelectId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = {"showSelectId"})
        })
public class ShowQuestionnaires implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long showQuestionnairesId;
    private Long showSelectId;
    private Long questionnaireId;

    public ShowQuestionnaires(){

    }

    @Ignore
    public ShowQuestionnaires(Long showQuestionnairesId, Long showSelectId, Long questionnaireId) {
        this.showQuestionnairesId = showQuestionnairesId;
        this.showSelectId = showSelectId;
        this.questionnaireId = questionnaireId;
    }

    public Long getShowQuestionnairesId() {
        return showQuestionnairesId;
    }

    public void setShowQuestionnairesId(Long showQuestionnairesId) {
        this.showQuestionnairesId = showQuestionnairesId;
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

    @Override
    public String toString() {
        return "ShowQuestionnaires{" +
                "showQuestionnairesId=" + showQuestionnairesId +
                ", showSelectId=" + showSelectId +
                ", questionnaireId=" + questionnaireId +
                '}';
    }
}
