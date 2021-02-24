package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.MostrarRespuestasDao;
import com.cysout.sousystems.surveymodule.entity.MostrarRespuestas;
import com.cysout.sousystems.surveymodule.repository.MostrarRespuestasRepository;

public class MostrarRespuestasRepositoryImpl implements MostrarRespuestasRepository {
    private MostrarRespuestasDao mostrarRespuestasDao;

    public MostrarRespuestasRepositoryImpl(Application application){
        AppDatabase db = AppDatabase.getDataBase(application);
        this.mostrarRespuestasDao = db.mostrarRespuestasDao();
    }

    @Override
    public Long insert(MostrarRespuestas item) {
        return this.mostrarRespuestasDao.insert(item);
    }

    @Override
    public Long[] insertList(List<MostrarRespuestas> list) {
        return this.mostrarRespuestasDao.insertList(list);
    }

    @Override
    public void update(MostrarRespuestas item) {
        this.mostrarRespuestasDao.update(item);
    }

    @Override
    public LiveData<List<MostrarRespuestas>> loadAll() {
        return this.mostrarRespuestasDao.loadAll();
    }

    @Override
    public List<MostrarRespuestas> loadAllSync() {
        return this.mostrarRespuestasDao.loadAllSync();
    }

    @Override
    public LiveData<List<MostrarRespuestas>> loadByMostrarSiSeleccionaId(Long id) {
        return this.mostrarRespuestasDao.loadByMostrarSiSeleccionaId(id);
    }

    @Override
    public List<MostrarRespuestas> loadByMostrarSiSeleccionaIdSync(Long id) {
        return this.mostrarRespuestasDao.loadByMostrarSiSeleccionaIdSync(id);
    }

    @Override
    public void deleteAll() {
        this.mostrarRespuestasDao.deleteAll();
    }
}
