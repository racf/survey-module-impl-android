package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.RespuestaDao;
import com.cysout.sousystems.surveymodule.entity.Answer;
import com.cysout.sousystems.surveymodule.repository.RespuestaRepository;

public class RespuestaRepositoryImpl implements RespuestaRepository {
    private RespuestaDao respuestaDao;

    public RespuestaRepositoryImpl (Application application){
        AppDatabase db = AppDatabase.getDataBase(application);
        this.respuestaDao = db.respuestaDao();
    }
    @Override
    public Long insert(Answer item) {
        return this.respuestaDao.insert(item);
    }

    @Override
    public Long[] insertList(List<Answer> list) {
        return this.respuestaDao.insertList(list);
    }

    @Override
    public void update(Answer item) {
        this.respuestaDao.update(item);
    }

    @Override
    public LiveData<List<Answer>> loadAll() {
        return this.respuestaDao.loadAll();
    }

    @Override
    public List<Answer> loadAllSync() {
        return this.respuestaDao.loadAllSync();
    }

    @Override
    public Answer loadRespuestaSync(Long id) {
        return this.respuestaDao.loadRespuestaSync(id);
    }

    @Override
    public LiveData<List<Answer>> loadByRespuestaId(Long id) {
        return this.respuestaDao.loadByRespuestaId(id);
    }

    @Override
    public List<Answer> loadByRespuestaIdSync(Long id) {
        return this.respuestaDao.loadByRespuestaIdSync(id);
    }

    @Override
    public LiveData<List<Answer>> loadByPreguntaId(Long id) {
        return this.respuestaDao.loadByPreguntaId(id);
    }

    @Override
    public List<Answer> loadByPreguntaIdSync(Long id) {
        return this.respuestaDao.loadByPreguntaIdSync(id);
    }

    @Override
    public void deleteAll() {
        this.respuestaDao.deleteAll();
    }
}
