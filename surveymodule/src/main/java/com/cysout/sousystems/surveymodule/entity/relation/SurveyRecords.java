package com.cysout.sousystems.surveymodule.entity.relation;

import androidx.room.Embedded;

import java.io.Serializable;

import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.SurveyRecord;

/**
* Copyright 2021 CysOut Solutions and SouSystems
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
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
