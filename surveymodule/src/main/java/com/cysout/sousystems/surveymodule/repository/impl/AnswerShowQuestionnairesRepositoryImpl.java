package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.AnswerShowQuestionnairesDao;
import com.cysout.sousystems.surveymodule.entity.AnswerShowQuestionnaires;
import com.cysout.sousystems.surveymodule.repository.AnswerShowQuestionnairesRepository;
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
public class AnswerShowQuestionnairesRepositoryImpl implements AnswerShowQuestionnairesRepository {
    private AnswerShowQuestionnairesDao answerShowQuestionnairesDao;

    public AnswerShowQuestionnairesRepositoryImpl(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        this.answerShowQuestionnairesDao = db.answerShowQuestionnairesDao();
    }
    @Override
    public Long insert(AnswerShowQuestionnaires item) {
        return this.answerShowQuestionnairesDao.insert(item);
    }

    @Override
    public Long[] insertList(List<AnswerShowQuestionnaires> list) {
        return this.answerShowQuestionnairesDao.insertList(list);
    }

    @Override
    public LiveData<List<AnswerShowQuestionnaires>> loadAll() {
        return this.answerShowQuestionnairesDao.loadAll();
    }

    @Override
    public List<AnswerShowQuestionnaires> loadAllSync() {
        return this.answerShowQuestionnairesDao.loadAllSync();
    }

    @Override
    public void deleteAll() {
        this.answerShowQuestionnairesDao.deleteAll();
    }

    @Override
    public void deleteByPreguntaId(Long preguntaId) {
        this.answerShowQuestionnairesDao.deleteByPreguntaId(preguntaId);
    }

    @Override
    public void deleteByCuestionarioOrigenId(Long cuestionarioOrigenId) {
        this.answerShowQuestionnairesDao.deleteByCuestionarioOrigenId(cuestionarioOrigenId);
    }

    @Override
    public void deleteByPreguntaIdAndRespuestaId(Long preguntaId, Long respuestaId) {
        this.answerShowQuestionnairesDao.deleteByPreguntaIdAndRespuestaId(preguntaId,respuestaId);
    }
}
