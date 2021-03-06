package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.PreguntaDao;
import com.cysout.sousystems.surveymodule.entity.Question;
import com.cysout.sousystems.surveymodule.repository.PreguntaRepository;

public class PreguntaRepositoryImpl implements PreguntaRepository {
    private PreguntaDao preguntaDao;

    public PreguntaRepositoryImpl(Application application){
        AppDatabase db = AppDatabase.getDataBase(application);
        this.preguntaDao = db.preguntaDao();
    }
    @Override
    public Long insert(Question item) {
        return this.preguntaDao.insert(item);
    }

    @Override
    public Long[] insertList(List<Question> list) {
        return this.preguntaDao.insertList(list);
    }

    @Override
    public void update(Question item) {
        this.preguntaDao.update(item);
    }

    @Override
    public LiveData<List<Question>> loadAll() {
        return this.preguntaDao.loadAll();
    }

    @Override
    public List<Question> loadAllSync() {
        return this.preguntaDao.loadAllSync();
    }

    @Override
    public Question loadPreguntaSync(Long id) {
        return this.preguntaDao.loadPreguntaSync(id);
    }

    @Override
    public LiveData<List<Question>> loadByPreguntaId(Long id) {
        return this.preguntaDao.loadByPreguntaId(id);
    }

    @Override
    public List<Question> loadByPreguntaIdSync(Long id) {
        return this.preguntaDao.loadByPreguntaIdSync(id);
    }

    @Override
    public LiveData<List<Question>> loadByCuestionarioId(Long id) {
        return this.preguntaDao.loadByCuestionarioId(id);
    }

    @Override
    public List<Question> loadByCuestionarioIdSync(Long id) {
        return this.preguntaDao.loadByCuestionarioIdSync(id);
    }

    @Override
    public void deleteAll() {
        this.preguntaDao.deleteAll();
    }
}
