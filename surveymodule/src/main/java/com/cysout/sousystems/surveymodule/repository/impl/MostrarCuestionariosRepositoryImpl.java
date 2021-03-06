package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.MostrarCuestionariosDao;
import com.cysout.sousystems.surveymodule.entity.ShowQuestionnaires;
import com.cysout.sousystems.surveymodule.repository.MostrarCuestionariosRepository;

public class MostrarCuestionariosRepositoryImpl implements MostrarCuestionariosRepository {
    private MostrarCuestionariosDao mostrarCuestionariosDao;

    public MostrarCuestionariosRepositoryImpl(Application application){
        AppDatabase db = AppDatabase.getDataBase(application);
        this.mostrarCuestionariosDao = db.mostrarCuestionariosDao();
    }
    @Override
    public Long insert(ShowQuestionnaires item) {
        return this.mostrarCuestionariosDao.insert(item);
    }

    @Override
    public Long[] insertList(List<ShowQuestionnaires> list) {
        return this.mostrarCuestionariosDao.insertList(list);
    }

    @Override
    public void update(ShowQuestionnaires item) {
        this.mostrarCuestionariosDao.update(item);
    }

    @Override
    public LiveData<List<ShowQuestionnaires>> loadAll() {
        return this.mostrarCuestionariosDao.loadAll();
    }

    @Override
    public List<ShowQuestionnaires> loadAllSync() {
        return this.mostrarCuestionariosDao.loadAllSync();
    }

    @Override
    public LiveData<List<ShowQuestionnaires>> loadByMostrarSiSeleccionaId(Long id) {
        return this.mostrarCuestionariosDao.loadByMostrarSiSeleccionaId(id);
    }

    @Override
    public List<ShowQuestionnaires> loadByMostrarSiSeleccionaIdSync(Long id) {
        return this.mostrarCuestionariosDao.loadByMostrarSiSeleccionaIdSync(id);
    }

    @Override
    public void deleteAll() {
        this.mostrarCuestionariosDao.deleteAll();
    }
}
