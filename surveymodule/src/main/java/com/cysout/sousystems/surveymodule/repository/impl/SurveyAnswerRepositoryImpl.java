package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.SurveyAnswerDao;
import com.cysout.sousystems.surveymodule.entity.SurveyAnswer;
import com.cysout.sousystems.surveymodule.repository.SurveyAnswerRepository;
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
public class SurveyAnswerRepositoryImpl implements SurveyAnswerRepository {
    private SurveyAnswerDao surveyAnswerDao;

    public SurveyAnswerRepositoryImpl(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        this.surveyAnswerDao = db.surveyAnswerDao();
    }

    @Override
    public Long insert(SurveyAnswer surveyAnswer) {
        return this.surveyAnswerDao.insert(surveyAnswer);
    }

    @Override
    public Long[] insertList(List<SurveyAnswer> list) {
        return this.surveyAnswerDao.insertList(list);
    }

    @Override
    public void update(SurveyAnswer surveyAnswer) {
        this.surveyAnswerDao.update(surveyAnswer);
    }

    @Override
    public void deleteAll() {
        this.surveyAnswerDao.deleteAll();
    }

    @Override
    public LiveData<List<SurveyAnswer>> loadAll() {
        return this.surveyAnswerDao.loadAll();
    }

    @Override
    public List<SurveyAnswer> loadAllSync() {
        return this.surveyAnswerDao.loadAllSync();
    }

    @Override
    public SurveyAnswer encuestaRespuestaByRegistroIdAndPregIdSync(Long encuestaRegistroId, Long preguntaId) {
        return this.surveyAnswerDao.surveyAnswerByRegistroIdAndPregIdSync(encuestaRegistroId, preguntaId);
    }

    @Override
    public LiveData<SurveyAnswer> encuestaRespuestaByRegistroIdAndPregId(Long encuestaRegistroId, Long preguntaId) {
        return this.surveyAnswerDao.surveyAnswerByRegistroIdAndPregId(encuestaRegistroId, preguntaId);
    }

    @Override
    public LiveData<SurveyAnswer> encuestaRespuestaByRegtroIdAndPregIdAndRespId(Long encuestaRegistroId, Long preguntaId, String respuestaId) {
        return this.surveyAnswerDao.surveyAnswerByRegtroIdAndPregIdAndRespId(encuestaRegistroId, preguntaId, respuestaId);
    }

    @Override
    public SurveyAnswer encuestaRespuestaByRegtroIdAndPregIdAndRespIdSync(Long encuestaRegistroId, Long preguntaId, String respuestaId) {
        return this.surveyAnswerDao.surveyAnswerByRegtroIdAndPregIdAndRespIdSync(encuestaRegistroId, preguntaId, respuestaId);
    }


    @Override
    public List<SurveyAnswer> loadByEncuestaRegistroIdSync(Long encuestaRegistroId) {
        return this.surveyAnswerDao.loadBysurveyRecordIdSync(encuestaRegistroId);
    }

    @Override
    public LiveData<List<SurveyAnswer>> loadByEncuestaRegistroId(Long encuestaRegistroId) {
        return this.surveyAnswerDao.loadBysurveyRecordId(encuestaRegistroId);
    }

    @Override
    public LiveData<List<SurveyAnswer>> loadByCuestionarioId(Long cuestionarioId) {
        return this.surveyAnswerDao.loadByCuestionarioId(cuestionarioId);
    }

    @Override
    public List<SurveyAnswer> loadByCuestionarioIdSync(Long cuestionarioId) {
        return this.surveyAnswerDao.loadByCuestionarioIdSync(cuestionarioId);
    }

    @Override
    public LiveData<SurveyAnswer> encuestaRespuestaByRegistroIdAndCuestId(Long encuestaRegistroId, Long cuestionarioId) {
        return this.surveyAnswerDao.surveyAnswerByRegistroIdAndCuestId(encuestaRegistroId, cuestionarioId);
    }

    @Override
    public SurveyAnswer encuestaRespuestaByRegistroIdAndCuestIdSync(Long encuestaRegistroId, Long cuestionarioId) {
        return this.surveyAnswerDao.surveyAnswerByRegistroIdAndCuestIdSync(encuestaRegistroId, cuestionarioId);
    }

    @Override
    public void deleteByEnctRegtIdAndCuestId(Long encuestaRegistroId, Long cuestionarioId) {
        this.surveyAnswerDao.deleteByEnctRegtIdAndCuestId(encuestaRegistroId, cuestionarioId);
    }

    @Override
    public void deleteByEnctRegtIdAndPreguntaId(Long encuestaRegistroId, Long preguntaId) {
        this.surveyAnswerDao.deleteByEnctRegtIdAndPreguntaId(encuestaRegistroId, preguntaId);
    }

    @Override
    public void deleteByEnctRegtIdAndPregtIdAndResp(Long encuestaRegistroId, Long preguntaId, String respuesta) {
        this.surveyAnswerDao.deleteByEnctRegtIdAndPregtIdAndResp(encuestaRegistroId, preguntaId, respuesta);
    }

}
