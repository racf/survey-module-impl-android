package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.ShowQuestionsDao;
import com.cysout.sousystems.surveymodule.entity.ShowQuestions;
import com.cysout.sousystems.surveymodule.repository.ShowQuestionsRepository;
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
public class ShowQuestionsRepositoryImpl implements ShowQuestionsRepository {
    private ShowQuestionsDao showQuestionsDao;

    public ShowQuestionsRepositoryImpl(Application application){
        AppDatabase db = AppDatabase.getDataBase(application);
        this.showQuestionsDao = db.showQuestionsDao();
    }

    @Override
    public Long insert(ShowQuestions item) {
        return this.showQuestionsDao.insert(item);
    }

    @Override
    public Long[] insertList(List<ShowQuestions> list) {
        return this.showQuestionsDao.insertList(list);
    }

    @Override
    public void update(ShowQuestions item) {
        this.showQuestionsDao.update(item);
    }

    @Override
    public LiveData<List<ShowQuestions>> loadAll() {
        return this.showQuestionsDao.loadAll();
    }

    @Override
    public List<ShowQuestions> loadAllSync() {
        return this.showQuestionsDao.loadAllSync();
    }

    @Override
    public LiveData<List<ShowQuestions>> loadByMostrarSiSeleccionaId(Long id) {
        return this.showQuestionsDao.loadByMostrarSiSeleccionaId(id);
    }

    @Override
    public List<ShowQuestions> loadByMostrarSiSeleccionaIdSync(Long id) {
        return this.showQuestionsDao.loadByMostrarSiSeleccionaIdSync(id);
    }

    @Override
    public void deleteAll() {
        this.showQuestionsDao.deleteAll();
    }
}
