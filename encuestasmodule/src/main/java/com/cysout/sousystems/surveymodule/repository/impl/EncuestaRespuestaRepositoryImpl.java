package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.EncuestaRespuestaDao;
import com.cysout.sousystems.surveymodule.entity.EncuestaRespuesta;
import com.cysout.sousystems.surveymodule.repository.EncuestaRespuestaRepository;

public class EncuestaRespuestaRepositoryImpl implements EncuestaRespuestaRepository {
    private EncuestaRespuestaDao encuestaRespuestaDao;

    public EncuestaRespuestaRepositoryImpl(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        this.encuestaRespuestaDao = db.encuestaRespuestaDao();
    }

    @Override
    public Long insert(EncuestaRespuesta encuestaRespuesta) {
        return this.encuestaRespuestaDao.insert(encuestaRespuesta);
    }

    @Override
    public Long[] insertList(List<EncuestaRespuesta> list) {
        return this.encuestaRespuestaDao.insertList(list);
    }

    @Override
    public void update(EncuestaRespuesta encuestaRespuesta) {
        this.encuestaRespuestaDao.update(encuestaRespuesta);
    }

    @Override
    public void deleteAll() {
        this.encuestaRespuestaDao.deleteAll();
    }

    @Override
    public LiveData<List<EncuestaRespuesta>> loadAll() {
        return this.encuestaRespuestaDao.loadAll();
    }

    @Override
    public List<EncuestaRespuesta> loadAllSync() {
        return this.encuestaRespuestaDao.loadAllSync();
    }

    @Override
    public EncuestaRespuesta encuestaRespuestaByRegistroIdAndPregIdSync(Long encuestaRegistroId, Long preguntaId) {
        return this.encuestaRespuestaDao.encuestaRespuestaByRegistroIdAndPregIdSync(encuestaRegistroId, preguntaId);
    }

    @Override
    public LiveData<EncuestaRespuesta> encuestaRespuestaByRegistroIdAndPregId(Long encuestaRegistroId, Long preguntaId) {
        return this.encuestaRespuestaDao.encuestaRespuestaByRegistroIdAndPregId(encuestaRegistroId, preguntaId);
    }

    @Override
    public LiveData<EncuestaRespuesta> encuestaRespuestaByRegtroIdAndPregIdAndRespId(Long encuestaRegistroId, Long preguntaId, String respuestaId) {
        return this.encuestaRespuestaDao.encuestaRespuestaByRegtroIdAndPregIdAndRespId(encuestaRegistroId, preguntaId, respuestaId);
    }

    @Override
    public EncuestaRespuesta encuestaRespuestaByRegtroIdAndPregIdAndRespIdSync(Long encuestaRegistroId, Long preguntaId, String respuestaId) {
        return this.encuestaRespuestaDao.encuestaRespuestaByRegtroIdAndPregIdAndRespIdSync(encuestaRegistroId, preguntaId, respuestaId);
    }


    @Override
    public List<EncuestaRespuesta> loadByEncuestaRegistroIdSync(Long encuestaRegistroId) {
        return this.encuestaRespuestaDao.loadByEncuestaRegistroIdSync(encuestaRegistroId);
    }

    @Override
    public LiveData<List<EncuestaRespuesta>> loadByEncuestaRegistroId(Long encuestaRegistroId) {
        return this.encuestaRespuestaDao.loadByEncuestaRegistroId(encuestaRegistroId);
    }

    @Override
    public LiveData<List<EncuestaRespuesta>> loadByCuestionarioId(Long cuestionarioId) {
        return this.encuestaRespuestaDao.loadByCuestionarioId(cuestionarioId);
    }

    @Override
    public List<EncuestaRespuesta> loadByCuestionarioIdSync(Long cuestionarioId) {
        return this.encuestaRespuestaDao.loadByCuestionarioIdSync(cuestionarioId);
    }

    @Override
    public LiveData<EncuestaRespuesta> encuestaRespuestaByRegistroIdAndCuestId(Long encuestaRegistroId, Long cuestionarioId) {
        return this.encuestaRespuestaDao.encuestaRespuestaByRegistroIdAndCuestId(encuestaRegistroId, cuestionarioId);
    }

    @Override
    public EncuestaRespuesta encuestaRespuestaByRegistroIdAndCuestIdSync(Long encuestaRegistroId, Long cuestionarioId) {
        return this.encuestaRespuestaDao.encuestaRespuestaByRegistroIdAndCuestIdSync(encuestaRegistroId, cuestionarioId);
    }

    @Override
    public void deleteByEnctRegtIdAndCuestId(Long encuestaRegistroId, Long cuestionarioId) {
        this.encuestaRespuestaDao.deleteByEnctRegtIdAndCuestId(encuestaRegistroId, cuestionarioId);
    }

    @Override
    public void deleteByEnctRegtIdAndPreguntaId(Long encuestaRegistroId, Long preguntaId) {
        this.encuestaRespuestaDao.deleteByEnctRegtIdAndPreguntaId(encuestaRegistroId, preguntaId);
    }

    @Override
    public void deleteByEnctRegtIdAndPregtIdAndResp(Long encuestaRegistroId, Long preguntaId, String respuesta) {
        this.encuestaRespuestaDao.deleteByEnctRegtIdAndPregtIdAndResp(encuestaRegistroId, preguntaId, respuesta);
    }

}
