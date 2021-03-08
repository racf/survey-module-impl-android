package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.AnswerShowQuestionnairesDao;
import com.cysout.sousystems.surveymodule.entity.AnswerShowQuestionnaires;
import com.cysout.sousystems.surveymodule.repository.RespuestaMostrarCuestionariosRepository;

public class RespuestaMostrarCuestionariosRepositoryImpl implements RespuestaMostrarCuestionariosRepository {
    private AnswerShowQuestionnairesDao answerShowQuestionnairesDao;

    public RespuestaMostrarCuestionariosRepositoryImpl(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        this.answerShowQuestionnairesDao = db.answerShowQuestionnairesDao();
    }
    @Override
    public Long insert(AnswerShowQuestionnaires item) {
        return this.answerShowQuestionnairesDao.insert(item);
    }

    @Override
    public Long[] insertList(List<AnswerShowQuestionnaires> list) {
        return this.answerShowQuestionnairesDao.insertList(list);
    }

    @Override
    public LiveData<List<AnswerShowQuestionnaires>> loadAll() {
        return this.answerShowQuestionnairesDao.loadAll();
    }

    @Override
    public List<AnswerShowQuestionnaires> loadAllSync() {
        return this.answerShowQuestionnairesDao.loadAllSync();
    }

    @Override
    public void deleteAll() {
        this.answerShowQuestionnairesDao.deleteAll();
    }

    @Override
    public void deleteByPreguntaId(Long preguntaId) {
        this.answerShowQuestionnairesDao.deleteByPreguntaId(preguntaId);
    }

    @Override
    public void deleteByCuestionarioOrigenId(Long cuestionarioOrigenId) {
        this.answerShowQuestionnairesDao.deleteByCuestionarioOrigenId(cuestionarioOrigenId);
    }

    @Override
    public void deleteByPreguntaIdAndRespuestaId(Long preguntaId, Long respuestaId) {
        this.answerShowQuestionnairesDao.deleteByPreguntaIdAndRespuestaId(preguntaId,respuestaId);
    }
}
