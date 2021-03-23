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
    public Long insert(SurveyRecord surveyRecord) {
        return this.surveyRecordDao.insert(surveyRecord);
    }

    @Override
    public Long[] insertList(List<SurveyRecord> list) {
        return this.surveyRecordDao.insertList(list);
    }

    @Override
    public void update(SurveyRecord surveyRecord) {
        this.surveyRecordDao.update(surveyRecord);
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
    public LiveData<List<SurveyRecord>> loadBySurveyId(Long surveyId) {
        return this.surveyRecordDao.loadBySurveyId(surveyId);
    }

    @Override
    public List<SurveyRecord> loadBySurveyIdSync(Long surveyId) {
        return this.surveyRecordDao.loadBySurveyIdSync(surveyId);
    }

    @Override
    public SurveyRecord surveyRecord(Long surveyId) {
        return this.surveyRecordDao.surveyRecord(surveyId);
    }

    @Override
    public SurveyRecord surveyRecord(Long surveyId, Integer surveyStatus) {
        return this.surveyRecordDao.surveyRecord(surveyId, surveyStatus);
    }

    @Override
    public LiveData<List<SurveyRecord>> loadBySurveyIdAndSurveyStatus(Long surveyId, Integer surveyStatus) {
        return this.surveyRecordDao.loadBySurveyIdAndSurveyStatus(surveyId, surveyStatus);
    }

    @Override
    public List<SurveyRecord> loadBySurveyIdAndSurveyStatusSync(Long surveyId, Integer surveyStatus) {
        return this.surveyRecordDao.loadBySurveyIdAndSurveyStatusSync(surveyId, surveyStatus);
    }

    @Override
    public void update(Integer surveyStatus, Long surveyRecordId) {
        this.surveyRecordDao.update(surveyStatus, surveyRecordId);
    }

    @Override
    public void update(Integer surveyStatus, String endDate, Long surveyRecordId) {
        this.surveyRecordDao.update(surveyStatus, endDate, surveyRecordId);
    }

    @Override
    public LiveData<SurveyRecordAnswers> surveyRecordAnswers(Long surveyRecordId) {
        return this.surveyRecordDao.surveyRecordAnswers(surveyRecordId);
    }

    @Override
    public SurveyRecordAnswers surveyRecordAnswersSync(Long surveyRecordId) {
        return this.surveyRecordDao.surveyRecordAnswersSync(surveyRecordId);
    }

    @Override
    public LiveData<List<SurveyRecordAnswers>> loadBySurveyRecordId(Long surveyRecordId) {
        return this.surveyRecordDao.loadBySurveyRecordId(surveyRecordId);
    }

    @Override
    public List<SurveyRecordAnswers> loadBySurveyRecordIdSync(Long surveyRecordId) {
        return this.surveyRecordDao.loadBySurveyRecordIdSync(surveyRecordId);
    }

    @Override
    public LiveData<List<SurveyRecordAnswers>> loadBySurveyStatus(Integer surveyStatus) {
        return this.surveyRecordDao.loadBySurveyStatus(surveyStatus);
    }

    @Override
    public List<SurveyRecordAnswers> loadBySurveyStatusSync(Integer surveyStatus) {
        return this.surveyRecordDao.loadBySurveyStatusSync(surveyStatus);
    }

}
