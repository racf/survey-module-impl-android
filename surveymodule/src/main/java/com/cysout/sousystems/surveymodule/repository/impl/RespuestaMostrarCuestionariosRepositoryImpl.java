package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.RespuestaMostrarCuestionariosDao;
import com.cysout.sousystems.surveymodule.entity.AnswerShowQuestionnaires;
import com.cysout.sousystems.surveymodule.repository.RespuestaMostrarCuestionariosRepository;

public class RespuestaMostrarCuestionariosRepositoryImpl implements RespuestaMostrarCuestionariosRepository {
    private RespuestaMostrarCuestionariosDao respuestaMostrarCuestionariosDao;

    public RespuestaMostrarCuestionariosRepositoryImpl(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        this.respuestaMostrarCuestionariosDao = db.respuestaMostrarCuestionariosDao();
    }
    @Override
    public Long insert(AnswerShowQuestionnaires item) {
        return this.respuestaMostrarCuestionariosDao.insert(item);
    }

    @Override
    public Long[] insertList(List<AnswerShowQuestionnaires> list) {
        return this.respuestaMostrarCuestionariosDao.insertList(list);
    }

    @Override
    public LiveData<List<AnswerShowQuestionnaires>> loadAll() {
        return this.respuestaMostrarCuestionariosDao.loadAll();
    }

    @Override
    public List<AnswerShowQuestionnaires> loadAllSync() {
        return this.respuestaMostrarCuestionariosDao.loadAllSync();
    }

    @Override
    public void deleteAll() {
        this.respuestaMostrarCuestionariosDao.deleteAll();
    }

    @Override
    public void deleteByPreguntaId(Long preguntaId) {
        this.respuestaMostrarCuestionariosDao.deleteByPreguntaId(preguntaId);
    }

    @Override
    public void deleteByCuestionarioOrigenId(Long cuestionarioOrigenId) {
        this.respuestaMostrarCuestionariosDao.deleteByCuestionarioOrigenId(cuestionarioOrigenId);
    }

    @Override
    public void deleteByPreguntaIdAndRespuestaId(Long preguntaId, Long respuestaId) {
        this.respuestaMostrarCuestionariosDao.deleteByPreguntaIdAndRespuestaId(preguntaId,respuestaId);
    }
}
