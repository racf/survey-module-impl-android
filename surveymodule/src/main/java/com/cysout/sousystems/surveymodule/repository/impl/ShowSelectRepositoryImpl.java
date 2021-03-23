package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.ShowSelectDao;
import com.cysout.sousystems.surveymodule.entity.ShowSelect;
import com.cysout.sousystems.surveymodule.entity.relation.RelationSelectQuestionnaires;
import com.cysout.sousystems.surveymodule.entity.relation.RelationShowSelect;
import com.cysout.sousystems.surveymodule.repository.ShowSelectRepository;
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
public class ShowSelectRepositoryImpl implements ShowSelectRepository {
    private ShowSelectDao showSelectDao;

    public ShowSelectRepositoryImpl(Application application){
        AppDatabase db = AppDatabase.getDataBase(application);
        this.showSelectDao = db.showSelectDao();
    }
    @Override
    public Long insert(ShowSelect item) {
        return this.showSelectDao.insert(item);
    }

    @Override
    public Long[] insertList(List<ShowSelect> list) {
        return this.showSelectDao.insertList(list);
    }

    @Override
    public void update(ShowSelect item) {
        this.showSelectDao.update(item);
    }

    @Override
    public LiveData<List<ShowSelect>> loadAll() {
        return this.showSelectDao.loadAll();
    }

    @Override
    public List<ShowSelect> loadAllSync() {
        return this.showSelectDao.loadAllSync();
    }

    @Override
    public LiveData<List<ShowSelect>> loadByAnswerId(Long id) {
        return this.showSelectDao.loadByAnswerId(id);
    }

    @Override
    public List<ShowSelect> loadByAnswerIdSync(Long id) {
        return this.showSelectDao.loadByAnswerIdSync(id);
    }

    @Override
    public void deleteAll() {
        this.showSelectDao.deleteAll();
    }

    @Override
    public RelationShowSelect loadShowSelectByAnswerId(Long id) {
        return this.showSelectDao.loadShowSelectByAnswerId(id);
    }

    @Override
    public LiveData<List<RelationSelectQuestionnaires>> loadShowQuestionnaires() {
        return this.showSelectDao.loadShowQuestionnaires();
    }

    @Override
    public List<RelationSelectQuestionnaires> loadShowQuestionnairesSync() {
        return this.showSelectDao.loadShowQuestionnairesSync();
    }

}
