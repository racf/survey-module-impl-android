package com.cysout.sousystems.surveymodule.entity.relation;

import androidx.room.Embedded;

import java.io.Serializable;

import com.cysout.sousystems.surveymodule.entity.Encuesta;
import com.cysout.sousystems.surveymodule.entity.EncuestaRegistro;

public class SurveyRecords implements Serializable {
    @Embedded
    Encuesta survey = new Encuesta();

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
    EncuestaRegistro record = new EncuestaRegistro();

    public Encuesta getSurvey() {
        return survey;
    }

    public void setSurvey(Encuesta survey) {
        this.survey = survey;
    }

    public EncuestaRegistro getRecord() {
        return record;
    }

    public void setRecord(EncuestaRegistro record) {
        this.record = record;
    }


}
