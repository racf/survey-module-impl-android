package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.QuestionDao;
import com.cysout.sousystems.surveymodule.entity.Question;
import com.cysout.sousystems.surveymodule.repository.QuestionRepository;
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
public class QuestionRepositoryImpl implements QuestionRepository {
    private QuestionDao questionDao;

    public QuestionRepositoryImpl(Application application){
        AppDatabase db = AppDatabase.getDataBase(application);
        this.questionDao = db.questionDao();
    }
    @Override
    public Long insert(Question item) {
        return this.questionDao.insert(item);
    }

    @Override
    public Long[] insertList(List<Question> list) {
        return this.questionDao.insertList(list);
    }

    @Override
    public void update(Question item) {
        this.questionDao.update(item);
    }

    @Override
    public LiveData<List<Question>> loadAll() {
        return this.questionDao.loadAll();
    }

    @Override
    public List<Question> loadAllSync() {
        return this.questionDao.loadAllSync();
    }

    @Override
    public Question loadQuestionSync(Long id) {
        return this.questionDao.loadQuestionSync(id);
    }

    @Override
    public LiveData<List<Question>> loadByQuestionId(Long id) {
        return this.questionDao.loadByQuestionId(id);
    }

    @Override
    public List<Question> loadByQuestionIdSync(Long id) {
        return this.questionDao.loadByQuestionIdSync(id);
    }

    @Override
    public LiveData<List<Question>> loadByQuestionnaireId(Long id) {
        return this.questionDao.loadByQuestionnaireId(id);
    }

    @Override
    public List<Question> loadByQuestionnaireIdSync(Long id) {
        return this.questionDao.loadByQuestionnaireIdSync(id);
    }

    @Override
    public void deleteAll() {
        this.questionDao.deleteAll();
    }
}
