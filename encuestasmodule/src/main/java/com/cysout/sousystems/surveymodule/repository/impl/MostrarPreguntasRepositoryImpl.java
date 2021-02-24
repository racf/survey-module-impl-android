package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.MostrarPreguntasDao;
import com.cysout.sousystems.surveymodule.entity.MostrarPreguntas;
import com.cysout.sousystems.surveymodule.repository.MostrarPreguntasRepository;

public class MostrarPreguntasRepositoryImpl implements MostrarPreguntasRepository {
    private MostrarPreguntasDao mostrarPreguntasDao;

    public MostrarPreguntasRepositoryImpl(Application application){
        AppDatabase db = AppDatabase.getDataBase(application);
        this.mostrarPreguntasDao = db.mostrarPreguntasDao();
    }

    @Override
    public Long insert(MostrarPreguntas item) {
        return this.mostrarPreguntasDao.insert(item);
    }

    @Override
    public Long[] insertList(List<MostrarPreguntas> list) {
        return this.mostrarPreguntasDao.insertList(list);
    }

    @Override
    public void update(MostrarPreguntas item) {
        this.mostrarPreguntasDao.update(item);
    }

    @Override
    public LiveData<List<MostrarPreguntas>> loadAll() {
        return this.mostrarPreguntasDao.loadAll();
    }

    @Override
    public List<MostrarPreguntas> loadAllSync() {
        return this.mostrarPreguntasDao.loadAllSync();
    }

    @Override
    public LiveData<List<MostrarPreguntas>> loadByMostrarSiSeleccionaId(Long id) {
        return this.mostrarPreguntasDao.loadByMostrarSiSeleccionaId(id);
    }

    @Override
    public List<MostrarPreguntas> loadByMostrarSiSeleccionaIdSync(Long id) {
        return this.mostrarPreguntasDao.loadByMostrarSiSeleccionaIdSync(id);
    }

    @Override
    public void deleteAll() {
        this.mostrarPreguntasDao.deleteAll();
    }
}
