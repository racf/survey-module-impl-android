package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.EncuestaRespuestaDao;
import com.cysout.sousystems.surveymodule.entity.SurveyAnswer;
import com.cysout.sousystems.surveymodule.repository.EncuestaRespuestaRepository;

public class EncuestaRespuestaRepositoryImpl implements EncuestaRespuestaRepository {
    private EncuestaRespuestaDao encuestaRespuestaDao;

    public EncuestaRespuestaRepositoryImpl(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        this.encuestaRespuestaDao = db.encuestaRespuestaDao();
    }

    @Override
    public Long insert(SurveyAnswer surveyAnswer) {
        return this.encuestaRespuestaDao.insert(surveyAnswer);
    }

    @Override
    public Long[] insertList(List<SurveyAnswer> list) {
        return this.encuestaRespuestaDao.insertList(list);
    }

    @Override
    public void update(SurveyAnswer surveyAnswer) {
        this.encuestaRespuestaDao.update(surveyAnswer);
    }

    @Override
    public void deleteAll() {
        this.encuestaRespuestaDao.deleteAll();
    }

    @Override
    public LiveData<List<SurveyAnswer>> loadAll() {
        return this.encuestaRespuestaDao.loadAll();
    }

    @Override
    public List<SurveyAnswer> loadAllSync() {
        return this.encuestaRespuestaDao.loadAllSync();
    }

    @Override
    public SurveyAnswer encuestaRespuestaByRegistroIdAndPregIdSync(Long encuestaRegistroId, Long preguntaId) {
        return this.encuestaRespuestaDao.surveyAnswerByRegistroIdAndPregIdSync(encuestaRegistroId, preguntaId);
    }

    @Override
    public LiveData<SurveyAnswer> encuestaRespuestaByRegistroIdAndPregId(Long encuestaRegistroId, Long preguntaId) {
        return this.encuestaRespuestaDao.surveyAnswerByRegistroIdAndPregId(encuestaRegistroId, preguntaId);
    }

    @Override
    public LiveData<SurveyAnswer> encuestaRespuestaByRegtroIdAndPregIdAndRespId(Long encuestaRegistroId, Long preguntaId, String respuestaId) {
        return this.encuestaRespuestaDao.surveyAnswerByRegtroIdAndPregIdAndRespId(encuestaRegistroId, preguntaId, respuestaId);
    }

    @Override
    public SurveyAnswer encuestaRespuestaByRegtroIdAndPregIdAndRespIdSync(Long encuestaRegistroId, Long preguntaId, String respuestaId) {
        return this.encuestaRespuestaDao.surveyAnswerByRegtroIdAndPregIdAndRespIdSync(encuestaRegistroId, preguntaId, respuestaId);
    }


    @Override
    public List<SurveyAnswer> loadByEncuestaRegistroIdSync(Long encuestaRegistroId) {
        return this.encuestaRespuestaDao.loadBysurveyRecordIdSync(encuestaRegistroId);
    }

    @Override
    public LiveData<List<SurveyAnswer>> loadByEncuestaRegistroId(Long encuestaRegistroId) {
        return this.encuestaRespuestaDao.loadBysurveyRecordId(encuestaRegistroId);
    }

    @Override
    public LiveData<List<SurveyAnswer>> loadByCuestionarioId(Long cuestionarioId) {
        return this.encuestaRespuestaDao.loadByCuestionarioId(cuestionarioId);
    }

    @Override
    public List<SurveyAnswer> loadByCuestionarioIdSync(Long cuestionarioId) {
        return this.encuestaRespuestaDao.loadByCuestionarioIdSync(cuestionarioId);
    }

    @Override
    public LiveData<SurveyAnswer> encuestaRespuestaByRegistroIdAndCuestId(Long encuestaRegistroId, Long cuestionarioId) {
        return this.encuestaRespuestaDao.surveyAnswerByRegistroIdAndCuestId(encuestaRegistroId, cuestionarioId);
    }

    @Override
    public SurveyAnswer encuestaRespuestaByRegistroIdAndCuestIdSync(Long encuestaRegistroId, Long cuestionarioId) {
        return this.encuestaRespuestaDao.surveyAnswerByRegistroIdAndCuestIdSync(encuestaRegistroId, cuestionarioId);
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
