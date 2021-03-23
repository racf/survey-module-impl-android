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
    public SurveyAnswer surveyAnswerSync(Long surveyRecordId, Long questionId) {
        return this.surveyAnswerDao.surveyAnswerSync(surveyRecordId, questionId);
    }

    @Override
    public LiveData<SurveyAnswer> surveyAnswer(Long surveyRecordId, Long questionId) {
        return this.surveyAnswerDao.surveyAnswer(surveyRecordId, questionId);
    }

    @Override
    public LiveData<SurveyAnswer> surveyAnswer(Long surveyRecordId, Long questionId, String answerId) {
        return this.surveyAnswerDao.surveyAnswer(surveyRecordId, questionId, answerId);
    }

    @Override
    public SurveyAnswer surveyAnswerSync(Long surveyRecordId, Long questionId, String answerId) {
        return this.surveyAnswerDao.surveyAnswerSync(surveyRecordId, questionId, answerId);
    }


    @Override
    public List<SurveyAnswer> loadBySurveyRecordIdSync(Long surveyRecordId) {
        return this.surveyAnswerDao.loadBySurveyRecordIdSync(surveyRecordId);
    }

    @Override
    public LiveData<List<SurveyAnswer>> loadBySurveyRecordId(Long surveyRecordId) {
        return this.surveyAnswerDao.loadBySurveyRecordId(surveyRecordId);
    }

    @Override
    public LiveData<List<SurveyAnswer>> loadByQuestionnaireId(Long questionnaireId) {
        return this.surveyAnswerDao.loadByQuestionnaireId(questionnaireId);
    }

    @Override
    public List<SurveyAnswer> loadByQuestionnaireIdSync(Long questionnaireId) {
        return this.surveyAnswerDao.loadByQuestionnaireIdSync(questionnaireId);
    }

    @Override
    public LiveData<SurveyAnswer> surveyAnswerByRecordIdAndQuestionnaireId(Long surveyRecordId, Long questionnaireId) {
        return this.surveyAnswerDao.surveyAnswerByRecordIdAndQuestionnaireId(surveyRecordId, questionnaireId);
    }

    @Override
    public SurveyAnswer surveyAnswerByRecordIdAndQuestionnaireIdSync(Long surveyRecordId, Long questionnaireId) {
        return this.surveyAnswerDao.surveyAnswerByRecordIdAndQuestionnaireIdSync(surveyRecordId, questionnaireId);
    }

    @Override
    public void deleteByRecordIdQuestionnaireId(Long surveyRecordId, Long questionnaireId) {
        this.surveyAnswerDao.deleteByRecordIdQuestionnaireId(surveyRecordId, questionnaireId);
    }

    @Override
    public void deleteSurveyRecordByQuestionId(Long surveyRecordId, Long questionId) {
        this.surveyAnswerDao.deleteSurveyRecordByQuestionId(surveyRecordId, questionId);
    }

    @Override
    public void deleteSurveyRecordByQuestionIdAndAnswer(Long surveyRecordId, Long questionId, String answer) {
        this.surveyAnswerDao.deleteSurveyRecordByQuestionIdAndAnswer(surveyRecordId, questionId, answer);
    }

}
