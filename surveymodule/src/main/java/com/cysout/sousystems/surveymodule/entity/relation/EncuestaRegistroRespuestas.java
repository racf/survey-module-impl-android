package com.cysout.sousystems.surveymodule.entity.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.cysout.sousystems.surveymodule.entity.SurveyAnswer;
import com.cysout.sousystems.surveymodule.entity.SurveyRecord;

/**
 *Developed by cysout.com and sousystems.com.mx
 *Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class EncuestaRegistroRespuestas implements Serializable {
    @Embedded
    private SurveyRecord surveyRecord;

    @Relation(parentColumn = "surveyRecordId", entityColumn = "surveyRecordId", entity = SurveyAnswer.class)
    private List<SurveyAnswer> surveyAnswers = new ArrayList<>();

    public SurveyRecord getSurveyRecord() {
        return surveyRecord;
    }

    public void setSurveyRecord(SurveyRecord surveyRecord) {
        this.surveyRecord = surveyRecord;
    }

    public List<SurveyAnswer> getSurveyAnswers() {
        return surveyAnswers;
    }

    public void setSurveyAnswers(List<SurveyAnswer> surveyAnswers) {
        this.surveyAnswers = surveyAnswers;
    }
}
