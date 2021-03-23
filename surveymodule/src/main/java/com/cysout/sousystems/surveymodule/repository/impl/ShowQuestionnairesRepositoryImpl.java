package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.ShowQuestionnairesDao;
import com.cysout.sousystems.surveymodule.entity.ShowQuestionnaires;
import com.cysout.sousystems.surveymodule.repository.ShowQuestionnairesRepository;
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
public class ShowQuestionnairesRepositoryImpl implements ShowQuestionnairesRepository {
    private ShowQuestionnairesDao showQuestionnairesDao;

    public ShowQuestionnairesRepositoryImpl(Application application){
        AppDatabase db = AppDatabase.getDataBase(application);
        this.showQuestionnairesDao = db.showQuestionnairesDao();
    }
    @Override
    public Long insert(ShowQuestionnaires item) {
        return this.showQuestionnairesDao.insert(item);
    }

    @Override
    public Long[] insertList(List<ShowQuestionnaires> list) {
        return this.showQuestionnairesDao.insertList(list);
    }

    @Override
    public void update(ShowQuestionnaires item) {
        this.showQuestionnairesDao.update(item);
    }

    @Override
    public LiveData<List<ShowQuestionnaires>> loadAll() {
        return this.showQuestionnairesDao.loadAll();
    }

    @Override
    public List<ShowQuestionnaires> loadAllSync() {
        return this.showQuestionnairesDao.loadAllSync();
    }

    @Override
    public LiveData<List<ShowQuestionnaires>> loadByShowSelectId(Long id) {
        return this.showQuestionnairesDao.loadByShowSelectId(id);
    }

    @Override
    public List<ShowQuestionnaires> loadByShowSelectIdSync(Long id) {
        return this.showQuestionnairesDao.loadByShowSelectIdSync(id);
    }

    @Override
    public void deleteAll() {
        this.showQuestionnairesDao.deleteAll();
    }
}
