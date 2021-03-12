package com.cysout.sousystems.surveymodule.entity;

import androidx.room.ColumnInfo;
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
@Entity(tableName = "surveyRecord", foreignKeys = {
        @ForeignKey(entity = Survey.class,
                parentColumns = "surveyId",
                childColumns = "surveyIdFK")
        },
        indices = {
                @Index(value = {"surveyIdFK"})
        })
public class SurveyRecord implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long surveyRecordId;
    @ColumnInfo(name = "surveyIdFK")
    private Long surveyId;
    private Integer surveyStatus;
    private String startDate;
    private String endDate;

    public SurveyRecord() {

    }

    @Ignore
    public SurveyRecord(Long surveyRecordId, Long surveyId, Integer surveyStatus, String startDate, String endDate) {
        this.surveyRecordId = surveyRecordId;
        this.surveyId = surveyId;
        this.surveyStatus = surveyStatus;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getSurveyRecordId() {
        return surveyRecordId;
    }

    public void setSurveyRecordId(Long surveyRecordId) {
        this.surveyRecordId = surveyRecordId;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Integer getSurveyStatus() {
        return surveyStatus;
    }

    public void setSurveyStatus(Integer surveyStatus) {
        this.surveyStatus = surveyStatus;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "SurveyRecord{" +
                "surveyRecordId=" + surveyRecordId +
                ", surveyId=" + surveyId +
                ", surveyStatus=" + surveyStatus +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
