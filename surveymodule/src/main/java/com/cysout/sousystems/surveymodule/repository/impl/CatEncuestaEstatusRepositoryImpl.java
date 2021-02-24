package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.CatEncuestaEstatusDao;
import com.cysout.sousystems.surveymodule.entity.CatEncuestaEstatus;
import com.cysout.sousystems.surveymodule.repository.CatEncuestaEstatusRepository;

public class CatEncuestaEstatusRepositoryImpl implements CatEncuestaEstatusRepository {
    private CatEncuestaEstatusDao catEncuestaEstatusDao;

    public CatEncuestaEstatusRepositoryImpl(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        this.catEncuestaEstatusDao = db.catEncuestaEstatusDao();
    }

    @Override
    public Long insert(CatEncuestaEstatus catEncuestaEstatus) {
        return this.catEncuestaEstatusDao.insert(catEncuestaEstatus);
    }

    @Override
    public Long[] insertList(List<CatEncuestaEstatus> list) {
        return this.catEncuestaEstatusDao.insertList(list);
    }

    @Override
    public void update(CatEncuestaEstatus catEncuestaEstatus) {

    }

    @Override
    public void delete(CatEncuestaEstatus catEncuestaEstatus) {

    }

    @Override
    public void deleteAllRows() {

    }

    @Override
    public LiveData<List<CatEncuestaEstatus>> findAllLiveData() {
        return this.catEncuestaEstatusDao.loadAll();
    }

    @Override
    public List<CatEncuestaEstatus> findAllList() {
        return this.catEncuestaEstatusDao.loadAllSync();
    }
}
