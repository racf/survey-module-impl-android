package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.MostrarCuestionariosDao;
import com.cysout.sousystems.surveymodule.entity.MostrarCuestionarios;
import com.cysout.sousystems.surveymodule.repository.MostrarCuestionariosRepository;

public class MostrarCuestionariosRepositoryImpl implements MostrarCuestionariosRepository {
    private MostrarCuestionariosDao mostrarCuestionariosDao;

    public MostrarCuestionariosRepositoryImpl(Application application){
        AppDatabase db = AppDatabase.getDataBase(application);
        this.mostrarCuestionariosDao = db.mostrarCuestionariosDao();
    }
    @Override
    public Long insert(MostrarCuestionarios item) {
        return this.mostrarCuestionariosDao.insert(item);
    }

    @Override
    public Long[] insertList(List<MostrarCuestionarios> list) {
        return this.mostrarCuestionariosDao.insertList(list);
    }

    @Override
    public void update(MostrarCuestionarios item) {
        this.mostrarCuestionariosDao.update(item);
    }

    @Override
    public LiveData<List<MostrarCuestionarios>> loadAll() {
        return this.mostrarCuestionariosDao.loadAll();
    }

    @Override
    public List<MostrarCuestionarios> loadAllSync() {
        return this.mostrarCuestionariosDao.loadAllSync();
    }

    @Override
    public LiveData<List<MostrarCuestionarios>> loadByMostrarSiSeleccionaId(Long id) {
        return this.mostrarCuestionariosDao.loadByMostrarSiSeleccionaId(id);
    }

    @Override
    public List<MostrarCuestionarios> loadByMostrarSiSeleccionaIdSync(Long id) {
        return this.mostrarCuestionariosDao.loadByMostrarSiSeleccionaIdSync(id);
    }

    @Override
    public void deleteAll() {
        this.mostrarCuestionariosDao.deleteAll();
    }
}
