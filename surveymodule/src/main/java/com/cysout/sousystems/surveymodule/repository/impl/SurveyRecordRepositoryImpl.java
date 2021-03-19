package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.SurveyRecordDao;
import com.cysout.sousystems.surveymodule.entity.SurveyRecord;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecordAnswers;
import com.cysout.sousystems.surveymodule.repository.SurveyRecordRepository;

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
public class SurveyRecordRepositoryImpl implements SurveyRecordRepository {
    private SurveyRecordDao surveyRecordDao;

    public SurveyRecordRepositoryImpl(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        this.surveyRecordDao = db.surveyRecordDao();
    }

    @Override
    public Long insert(SurveyRecord encuestaRegistro) {
        return this.surveyRecordDao.insert(encuestaRegistro);
    }

    @Override
    public Long[] insertList(List<SurveyRecord> list) {
        return this.surveyRecordDao.insertList(list);
    }

    @Override
    public void update(SurveyRecord encuestaRegistro) {
        this.surveyRecordDao.update(encuestaRegistro);
    }

    @Override
    public void deleteAll() {
        this.surveyRecordDao.deleteAll();
    }

    @Override
    public LiveData<List<SurveyRecord>> loadAll() {
        return this.surveyRecordDao.loadAll();
    }

    @Override
    public List<SurveyRecord> loadAllSync() {
        return this.surveyRecordDao.loadAllSync();
    }

    @Override
    public LiveData<List<SurveyRecord>> loadByEncuestaId(Long encuestaId) {
        return this.surveyRecordDao.loadByEncuestaId(encuestaId);
    }

    @Override
    public List<SurveyRecord> loadByEncuestaIdSync(Long encuestaId) {
        return this.surveyRecordDao.loadByEncuestaIdSync(encuestaId);
    }

    @Override
    public SurveyRecord encuestaRegistro(Long encuestaId) {
        return this.surveyRecordDao.encuestaRegistro(encuestaId);
    }

    @Override
    public SurveyRecord encuestaRegistroByEncuestaIdAndCatEncuestaEstatusId(Long encuestaId, Integer catEncuestaEstatusId) {
        return this.surveyRecordDao.encuestaRegistroByEncuestaIdAndCatEncuestaEstatusId(encuestaId, catEncuestaEstatusId);
    }

    @Override
    public LiveData<List<SurveyRecord>> loadByEncuestaIdAndCatEncuestaEstatusId(Long encuestaId, Integer catEncuestaEstatusId) {
        return this.surveyRecordDao.loadByEncuestaIdAndCatEncuestaEstatusId(encuestaId, catEncuestaEstatusId);
    }

    @Override
    public List<SurveyRecord> loadByEncuestaIdAndCatEncuestaEstatusIdSync(Long encuestaId, Integer catEncuestaEstatusId) {
        return this.surveyRecordDao.loadByEncuestaIdAndCatEncuestaEstatusIdSync(encuestaId, catEncuestaEstatusId);
    }

    @Override
    public void updateEncuestaRegistroByEnctRegtroId(Integer catEncuestaEstatusId, Long encuestaRegistroId) {
        this.surveyRecordDao.updateEncuestaRegistroByEnctRegtroId(catEncuestaEstatusId, encuestaRegistroId);
    }

    @Override
    public void updateEncuestaRegistroByEnctRegtroId(Integer catEncuestaEstatusId, String fechaFinal, Long encuestaRegistroId) {
        this.surveyRecordDao.updateEncuestaRegistroByEnctRegtroId(catEncuestaEstatusId, fechaFinal, encuestaRegistroId);
    }

    @Override
    public LiveData<SurveyRecordAnswers> loadRegistroRespByEnctRegtroId(Long encuestaRegistroId) {
        return this.surveyRecordDao.loadRegistroRespByEnctRegtroId(encuestaRegistroId);
    }

    @Override
    public SurveyRecordAnswers loadRegistroRespByEnctRegtroIdSync(Long encuestaRegistroId) {
        return this.surveyRecordDao.loadRegistroRespByEnctRegtroIdSync(encuestaRegistroId);
    }

    @Override
    public LiveData<List<SurveyRecordAnswers>> loadRegistrosRespByEnctRegtroId(Long encuestaRegistroId) {
        return this.surveyRecordDao.loadRegistrosRespByEnctRegtroId(encuestaRegistroId);
    }

    @Override
    public List<SurveyRecordAnswers> loadRegistrosRespByEnctRegtroIdSync(Long encuestaRegistroId) {
        return this.surveyRecordDao.loadRegistrosRespByEnctRegtroIdSync(encuestaRegistroId);
    }

    @Override
    public LiveData<List<SurveyRecordAnswers>> loadRegistrosRespuestasByEstatus(Integer catEncuestaEstatusId) {
        return this.surveyRecordDao.loadRegistrosRespuestasByEstatus(catEncuestaEstatusId);
    }

    @Override
    public List<SurveyRecordAnswers> loadRegistrosRespuestasByEstatusSync(Integer catEncuestaEstatusId) {
        return this.surveyRecordDao.loadRegistrosRespuestasByEstatusSync(catEncuestaEstatusId);
    }

}
