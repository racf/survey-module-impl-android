package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.MostrarPreguntasDao;
import com.cysout.sousystems.surveymodule.entity.ShowQuestions;
import com.cysout.sousystems.surveymodule.repository.MostrarPreguntasRepository;

public class MostrarPreguntasRepositoryImpl implements MostrarPreguntasRepository {
    private MostrarPreguntasDao mostrarPreguntasDao;

    public MostrarPreguntasRepositoryImpl(Application application){
        AppDatabase db = AppDatabase.getDataBase(application);
        this.mostrarPreguntasDao = db.mostrarPreguntasDao();
    }

    @Override
    public Long insert(ShowQuestions item) {
        return this.mostrarPreguntasDao.insert(item);
    }

    @Override
    public Long[] insertList(List<ShowQuestions> list) {
        return this.mostrarPreguntasDao.insertList(list);
    }

    @Override
    public void update(ShowQuestions item) {
        this.mostrarPreguntasDao.update(item);
    }

    @Override
    public LiveData<List<ShowQuestions>> loadAll() {
        return this.mostrarPreguntasDao.loadAll();
    }

    @Override
    public List<ShowQuestions> loadAllSync() {
        return this.mostrarPreguntasDao.loadAllSync();
    }

    @Override
    public LiveData<List<ShowQuestions>> loadByMostrarSiSeleccionaId(Long id) {
        return this.mostrarPreguntasDao.loadByMostrarSiSeleccionaId(id);
    }

    @Override
    public List<ShowQuestions> loadByMostrarSiSeleccionaIdSync(Long id) {
        return this.mostrarPreguntasDao.loadByMostrarSiSeleccionaIdSync(id);
    }

    @Override
    public void deleteAll() {
        this.mostrarPreguntasDao.deleteAll();
    }
}
