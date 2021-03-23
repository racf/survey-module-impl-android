package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.ShowAnswersDao;
import com.cysout.sousystems.surveymodule.entity.ShowAnswers;
import com.cysout.sousystems.surveymodule.repository.ShowAnswersRepository;
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
public class ShowAnswersRepositoryImpl implements ShowAnswersRepository {
    private ShowAnswersDao showAnswersDao;

    public ShowAnswersRepositoryImpl(Application application){
        AppDatabase db = AppDatabase.getDataBase(application);
        this.showAnswersDao = db.showAnswersDao();
    }

    @Override
    public Long insert(ShowAnswers item) {
        return this.showAnswersDao.insert(item);
    }

    @Override
    public Long[] insertList(List<ShowAnswers> list) {
        return this.showAnswersDao.insertList(list);
    }

    @Override
    public void update(ShowAnswers item) {
        this.showAnswersDao.update(item);
    }

    @Override
    public LiveData<List<ShowAnswers>> loadAll() {
        return this.showAnswersDao.loadAll();
    }

    @Override
    public List<ShowAnswers> loadAllSync() {
        return this.showAnswersDao.loadAllSync();
    }

    @Override
    public LiveData<List<ShowAnswers>> loadByShowSelectId(Long id) {
        return this.showAnswersDao.loadByShowSelectId(id);
    }

    @Override
    public List<ShowAnswers> loadByShowSelectIdSync(Long id) {
        return this.showAnswersDao.loadByShowSelectIdSync(id);
    }

    @Override
    public void deleteAll() {
        this.showAnswersDao.deleteAll();
    }
}
