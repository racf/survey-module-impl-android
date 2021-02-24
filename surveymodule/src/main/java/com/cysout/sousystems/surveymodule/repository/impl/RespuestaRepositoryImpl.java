package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.RespuestaDao;
import com.cysout.sousystems.surveymodule.entity.Respuesta;
import com.cysout.sousystems.surveymodule.repository.RespuestaRepository;

public class RespuestaRepositoryImpl implements RespuestaRepository {
    private RespuestaDao respuestaDao;

    public RespuestaRepositoryImpl (Application application){
        AppDatabase db = AppDatabase.getDataBase(application);
        this.respuestaDao = db.respuestaDao();
    }
    @Override
    public Long insert(Respuesta item) {
        return this.respuestaDao.insert(item);
    }

    @Override
    public Long[] insertList(List<Respuesta> list) {
        return this.respuestaDao.insertList(list);
    }

    @Override
    public void update(Respuesta item) {
        this.respuestaDao.update(item);
    }

    @Override
    public LiveData<List<Respuesta>> loadAll() {
        return this.respuestaDao.loadAll();
    }

    @Override
    public List<Respuesta> loadAllSync() {
        return this.respuestaDao.loadAllSync();
    }

    @Override
    public Respuesta loadRespuestaSync(Long id) {
        return this.respuestaDao.loadRespuestaSync(id);
    }

    @Override
    public LiveData<List<Respuesta>> loadByRespuestaId(Long id) {
        return this.respuestaDao.loadByRespuestaId(id);
    }

    @Override
    public List<Respuesta> loadByRespuestaIdSync(Long id) {
        return this.respuestaDao.loadByRespuestaIdSync(id);
    }

    @Override
    public LiveData<List<Respuesta>> loadByPreguntaId(Long id) {
        return this.respuestaDao.loadByPreguntaId(id);
    }

    @Override
    public List<Respuesta> loadByPreguntaIdSync(Long id) {
        return this.respuestaDao.loadByPreguntaIdSync(id);
    }

    @Override
    public void deleteAll() {
        this.respuestaDao.deleteAll();
    }
}
