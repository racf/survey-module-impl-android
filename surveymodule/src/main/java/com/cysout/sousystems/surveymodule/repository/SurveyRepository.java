package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyQuestionnaires;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecords;

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
public interface SurveyRepository {
    Long insert(Survey survey);
    Long[] insertList(List<Survey> list);
    void update(Survey survey);
    void delete(Survey survey);
    void deleteAllRows();
    LiveData<Survey> loadEncuestaById(Long encuestaId);
    Survey loadEncuestaByIdSync(Long encuestaId);
    List<Survey> findEncuestasById(Long encuestaId);
    LiveData<List<Survey>> loadAll();
    List<Survey> loadAllSync();
    List<SurveyQuestionnaires> loadEncuestaCuestionarios();
    SurveyQuestionnaires loadCuestionarioRespuestasSync();
    LiveData<List<SurveyRecords>> loadAllSurveyRecords();
    List<SurveyRecords> loadAllSurveyRecordsSync();
    LiveData<List<SurveyRecords>> loadSurveyCompleted();
    List<SurveyRecords> loadSurveyCompletedSync();
    LiveData<List<SurveyRecords>> loadSurveyPending();
    List<SurveyRecords> loadSurveyPendingSync();
    LiveData<List<SurveyRecords>> loadSurveySent();
    List<SurveyRecords> loadSurveySentSync();
}
