package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.SurveyDao;
import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyQuestionnaires;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecords;
import com.cysout.sousystems.surveymodule.repository.SurveyRepository;
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
public class SurveyRepositoryImpl implements SurveyRepository {
    private SurveyDao surveyDao;

    public SurveyRepositoryImpl(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        this.surveyDao = db.surveyDao();
    }

    @Override
    public Long insert(Survey survey) {
        return this.surveyDao.insert(survey);
        /*final Long[] ids = {null};
        AppDatabase.databaseWriteExecutor.execute(() -> {
            ids[0] = encuestaDao.insert(encuesta);
        });
        return ids[0];*/
    }

    @Override
    public Long[] insertList(List<Survey> list) {
        /*final Long[][] ids = {{null}};
        AppDatabase.databaseWriteExecutor.execute(() -> {
            ids[0] = encuestaDao.insertList(list);
        });
        return ids[0];*/
        return this.surveyDao.insertList(list);
    }

    @Override
    public void update(Survey survey) {
        this.surveyDao.update(survey);
    }

    @Override
    public void delete(Survey survey) {

    }

    @Override
    public void deleteAllRows() {

    }

    @Override
    public LiveData<Survey> loadEncuestaById(Long encuestaId) {
        return this.surveyDao.loadEncuestaById(encuestaId);
    }

    @Override
    public Survey loadEncuestaByIdSync(Long encuestaId) {
        return this.surveyDao.loadEncuestaByIdSync(encuestaId);
    }


    @Override
    public List<Survey> findEncuestasById(Long encuestaId) {
        return this.surveyDao.loadByEncuestaIdSync(encuestaId);
    }

    @Override
    public LiveData<List<Survey>> loadAll() {
        return this.surveyDao.loadAll();
    }

    @Override
    public List<Survey> loadAllSync() {
        return this.surveyDao.loadAllSync();
    }

    @Override
    public List<SurveyQuestionnaires> loadEncuestaCuestionarios() {
        return this.surveyDao.loadCuestionariosSync();
    }

    @Override
    public SurveyQuestionnaires loadCuestionarioRespuestasSync() {
        return this.surveyDao.loadCuestionarioRespuestasSync();
    }

    @Override
    public LiveData<List<SurveyRecords>> loadAllSurveyRecords() {
        return this.surveyDao.loadAllSurveyRecords();
    }

    @Override
    public List<SurveyRecords> loadAllSurveyRecordsSync() {
        return this.surveyDao.loadAllSurveyRecordsSync();
    }

    @Override
    public LiveData<List<SurveyRecords>> loadSurveyCompleted() {
        return this.surveyDao.loadSurveyCompleted();
    }

    @Override
    public List<SurveyRecords> loadSurveyCompletedSync() {
        return this.surveyDao.loadSurveyCompletedSync();
    }

    @Override
    public LiveData<List<SurveyRecords>> loadSurveyPending() {
        return this.surveyDao.loadSurveyPending();
    }

    @Override
    public List<SurveyRecords> loadSurveyPendingSync() {
        return this.surveyDao.loadSurveyPendingSync();
    }

    @Override
    public LiveData<List<SurveyRecords>> loadSurveySent() {
        return this.surveyDao.loadSurveySent();
    }

    @Override
    public List<SurveyRecords> loadSurveySentSync() {
        return this.surveyDao.loadSurveySentSync();
    }
}
