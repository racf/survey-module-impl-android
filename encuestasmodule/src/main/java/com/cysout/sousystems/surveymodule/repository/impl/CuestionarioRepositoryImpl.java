package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.CuestionarioDao;
import com.cysout.sousystems.surveymodule.entity.Cuestionario;
import com.cysout.sousystems.surveymodule.repository.CuestionarioRepository;

public class CuestionarioRepositoryImpl implements CuestionarioRepository {
    private CuestionarioDao cuestionarioDao;

    public CuestionarioRepositoryImpl(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        this.cuestionarioDao = db.cuestionarioDao();
    }

    @Override
    public Long insert(Cuestionario cuestionario) {
        return this.cuestionarioDao.insert(cuestionario);
    }

    @Override
    public Long[] insertList(List<Cuestionario> list) {
        return this.cuestionarioDao.insertList(list);
    }

    @Override
    public void update(Cuestionario cuestionario) {

    }

    @Override
    public void delete(Cuestionario cuestionario) {

    }

    @Override
    public void deleteAllRows() {

    }

    @Override
    public LiveData<List<Cuestionario>> findAllLiveData() {
        return this.cuestionarioDao.loadAll();
    }

    @Override
    public List<Cuestionario> findAllList() {
        return this.cuestionarioDao.loadAllSync();
    }
}
