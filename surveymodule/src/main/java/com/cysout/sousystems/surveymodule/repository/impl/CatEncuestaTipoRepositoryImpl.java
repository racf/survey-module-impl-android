package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.CatEncuestaTipoDao;
import com.cysout.sousystems.surveymodule.entity.CatEncuestaTipo;
import com.cysout.sousystems.surveymodule.repository.CatEncuestaTipoRepository;

public class CatEncuestaTipoRepositoryImpl implements CatEncuestaTipoRepository {
    private CatEncuestaTipoDao catEncuestaTipoDao;

    public CatEncuestaTipoRepositoryImpl(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        this.catEncuestaTipoDao = db.catEncuestaTipoDao();
    }

    @Override
    public Long insert(final CatEncuestaTipo catEncuestaTipo) {
        /*final Long[] ids = {};
        AppDatabase.databaseWriteExecutor.execute(() -> {
             ids[0] = catEncuestaTipoDao.insert(catEncuestaTipo);
        });
        return ids[0];*/
        return this.catEncuestaTipoDao.insert(catEncuestaTipo);
    }

    @Override
    public Long[] insertList(List<CatEncuestaTipo> list) {
        /*final Long[][] ids = {{null}};
        AppDatabase.databaseWriteExecutor.execute(() -> {
            ids[0] = catEncuestaTipoDao.insertList(list);
        });
        return ids[0];*/
        return this.catEncuestaTipoDao.insertList(list);
    }

    @Override
    public void update(CatEncuestaTipo catEncuestaTipo) {

    }

    @Override
    public void delete(CatEncuestaTipo catEncuestaTipo) {

    }

    @Override
    public void deleteAllRows() {

    }

    @Override
    public LiveData<List<CatEncuestaTipo>> findAllLiveData() {
        return this.catEncuestaTipoDao.loadAll();
    }

    @Override
    public List<CatEncuestaTipo> findAllList() {
        return this.catEncuestaTipoDao.loadAllSync();
    }

}
