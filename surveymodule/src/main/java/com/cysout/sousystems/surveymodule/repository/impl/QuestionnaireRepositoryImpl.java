package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.QuestionnaireDao;
import com.cysout.sousystems.surveymodule.entity.Questionnaire;
import com.cysout.sousystems.surveymodule.repository.QuestionnaireRepository;
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
public class QuestionnaireRepositoryImpl implements QuestionnaireRepository {
    private QuestionnaireDao questionnaireDao;

    public QuestionnaireRepositoryImpl(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        this.questionnaireDao = db.questionnaireDao();
    }

    @Override
    public Long insert(Questionnaire questionnaire) {
        return this.questionnaireDao.insert(questionnaire);
    }

    @Override
    public Long[] insertList(List<Questionnaire> list) {
        return this.questionnaireDao.insertList(list);
    }

    @Override
    public void update(Questionnaire questionnaire) {

    }

    @Override
    public void delete(Questionnaire questionnaire) {

    }

    @Override
    public void deleteAllRows() {

    }

    @Override
    public LiveData<List<Questionnaire>> findAllLiveData() {
        return this.questionnaireDao.loadAll();
    }

    @Override
    public List<Questionnaire> findAllList() {
        return this.questionnaireDao.loadAllSync();
    }
}
