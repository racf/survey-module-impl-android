package com.cysout.sousystems.surveymodule.entity.relation;

import androidx.room.Embedded;

import java.io.Serializable;

import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.SurveyRecord;

/**
 *Developed by cysout.com and sousystems.com.mx
 *Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class SurveyRecords implements Serializable {
    @Embedded
    Survey survey = new Survey();

   /* @Relation(parentColumn = "encuestaId", entityColumn = "encuestaId", entity = EncuestaRegistro.class)
    List<EncuestaRegistro> records = new ArrayList<>();

    public Encuesta getSurvey() {
        return survey;
    }

    public void setSurvey(Encuesta survey) {
        this.survey = survey;
    }

    public List<EncuestaRegistro> getRecords() {
        return records;
    }

    public void setRecords(List<EncuestaRegistro> records) {
        this.records = records;
    }*/

   @Embedded
   SurveyRecord record = new SurveyRecord();

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public SurveyRecord getRecord() {
        return record;
    }

    public void setRecord(SurveyRecord record) {
        this.record = record;
    }


}
