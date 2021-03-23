package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.AnswerDao;
import com.cysout.sousystems.surveymodule.entity.Answer;
import com.cysout.sousystems.surveymodule.repository.AnswerRepository;
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
public class AnswerRepositoryImpl implements AnswerRepository {
    private AnswerDao answerDao;

    public AnswerRepositoryImpl(Application application){
        AppDatabase db = AppDatabase.getDataBase(application);
        this.answerDao = db.answerDao();
    }
    @Override
    public Long insert(Answer item) {
        return this.answerDao.insert(item);
    }

    @Override
    public Long[] insertList(List<Answer> list) {
        return this.answerDao.insertList(list);
    }

    @Override
    public void update(Answer item) {
        this.answerDao.update(item);
    }

    @Override
    public LiveData<List<Answer>> loadAll() {
        return this.answerDao.loadAll();
    }

    @Override
    public List<Answer> loadAllSync() {
        return this.answerDao.loadAllSync();
    }

    @Override
    public void deleteAll() {
        this.answerDao.deleteAll();
    }
}
