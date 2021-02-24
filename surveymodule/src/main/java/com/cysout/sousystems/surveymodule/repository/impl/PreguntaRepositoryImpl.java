package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.PreguntaDao;
import com.cysout.sousystems.surveymodule.entity.Pregunta;
import com.cysout.sousystems.surveymodule.repository.PreguntaRepository;

public class PreguntaRepositoryImpl implements PreguntaRepository {
    private PreguntaDao preguntaDao;

    public PreguntaRepositoryImpl(Application application){
        AppDatabase db = AppDatabase.getDataBase(application);
        this.preguntaDao = db.preguntaDao();
    }
    @Override
    public Long insert(Pregunta item) {
        return this.preguntaDao.insert(item);
    }

    @Override
    public Long[] insertList(List<Pregunta> list) {
        return this.preguntaDao.insertList(list);
    }

    @Override
    public void update(Pregunta item) {
        this.preguntaDao.update(item);
    }

    @Override
    public LiveData<List<Pregunta>> loadAll() {
        return this.preguntaDao.loadAll();
    }

    @Override
    public List<Pregunta> loadAllSync() {
        return this.preguntaDao.loadAllSync();
    }

    @Override
    public Pregunta loadPreguntaSync(Long id) {
        return this.preguntaDao.loadPreguntaSync(id);
    }

    @Override
    public LiveData<List<Pregunta>> loadByPreguntaId(Long id) {
        return this.preguntaDao.loadByPreguntaId(id);
    }

    @Override
    public List<Pregunta> loadByPreguntaIdSync(Long id) {
        return this.preguntaDao.loadByPreguntaIdSync(id);
    }

    @Override
    public LiveData<List<Pregunta>> loadByCuestionarioId(Long id) {
        return this.preguntaDao.loadByCuestionarioId(id);
    }

    @Override
    public List<Pregunta> loadByCuestionarioIdSync(Long id) {
        return this.preguntaDao.loadByCuestionarioIdSync(id);
    }

    @Override
    public void deleteAll() {
        this.preguntaDao.deleteAll();
    }
}
