package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.EncuestaRegistroDao;
import com.cysout.sousystems.surveymodule.entity.SurveyRecord;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecordAnswers;
import com.cysout.sousystems.surveymodule.repository.EncuestaRegistroRepository;

/**
 *Developed by cysout.com and sousystems.com.mx
 *Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class EncuestaRegistroRepositoryImpl implements EncuestaRegistroRepository {
    private EncuestaRegistroDao encuestaRegistroDao;

    public EncuestaRegistroRepositoryImpl(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        this.encuestaRegistroDao = db.encuestaRegistroDao();
    }

    @Override
    public Long insert(SurveyRecord encuestaRegistro) {
        return this.encuestaRegistroDao.insert(encuestaRegistro);
    }

    @Override
    public Long[] insertList(List<SurveyRecord> list) {
        return this.encuestaRegistroDao.insertList(list);
    }

    @Override
    public void update(SurveyRecord encuestaRegistro) {
        this.encuestaRegistroDao.update(encuestaRegistro);
    }

    @Override
    public void deleteAll() {
        this.encuestaRegistroDao.deleteAll();
    }

    @Override
    public LiveData<List<SurveyRecord>> loadAll() {
        return this.encuestaRegistroDao.loadAll();
    }

    @Override
    public List<SurveyRecord> loadAllSync() {
        return this.encuestaRegistroDao.loadAllSync();
    }

    @Override
    public LiveData<List<SurveyRecord>> loadByEncuestaId(Long encuestaId) {
        return this.encuestaRegistroDao.loadByEncuestaId(encuestaId);
    }

    @Override
    public List<SurveyRecord> loadByEncuestaIdSync(Long encuestaId) {
        return this.encuestaRegistroDao.loadByEncuestaIdSync(encuestaId);
    }

    @Override
    public SurveyRecord encuestaRegistro(Long encuestaId) {
        return this.encuestaRegistroDao.encuestaRegistro(encuestaId);
    }

    @Override
    public SurveyRecord encuestaRegistroByEncuestaIdAndCatEncuestaEstatusId(Long encuestaId, Integer catEncuestaEstatusId) {
        return this.encuestaRegistroDao.encuestaRegistroByEncuestaIdAndCatEncuestaEstatusId(encuestaId, catEncuestaEstatusId);
    }

    @Override
    public LiveData<List<SurveyRecord>> loadByEncuestaIdAndCatEncuestaEstatusId(Long encuestaId, Integer catEncuestaEstatusId) {
        return this.encuestaRegistroDao.loadByEncuestaIdAndCatEncuestaEstatusId(encuestaId, catEncuestaEstatusId);
    }

    @Override
    public List<SurveyRecord> loadByEncuestaIdAndCatEncuestaEstatusIdSync(Long encuestaId, Integer catEncuestaEstatusId) {
        return this.encuestaRegistroDao.loadByEncuestaIdAndCatEncuestaEstatusIdSync(encuestaId, catEncuestaEstatusId);
    }

    @Override
    public void updateEncuestaRegistroByEnctRegtroId(Integer catEncuestaEstatusId, Long encuestaRegistroId) {
        this.encuestaRegistroDao.updateEncuestaRegistroByEnctRegtroId(catEncuestaEstatusId, encuestaRegistroId);
    }

    @Override
    public void updateEncuestaRegistroByEnctRegtroId(Integer catEncuestaEstatusId, String fechaFinal, Long encuestaRegistroId) {
        this.encuestaRegistroDao.updateEncuestaRegistroByEnctRegtroId(catEncuestaEstatusId, fechaFinal, encuestaRegistroId);
    }

    @Override
    public LiveData<SurveyRecordAnswers> loadRegistroRespByEnctRegtroId(Long encuestaRegistroId) {
        return this.encuestaRegistroDao.loadRegistroRespByEnctRegtroId(encuestaRegistroId);
    }

    @Override
    public SurveyRecordAnswers loadRegistroRespByEnctRegtroIdSync(Long encuestaRegistroId) {
        return this.encuestaRegistroDao.loadRegistroRespByEnctRegtroIdSync(encuestaRegistroId);
    }

    @Override
    public LiveData<List<SurveyRecordAnswers>> loadRegistrosRespByEnctRegtroId(Long encuestaRegistroId) {
        return this.encuestaRegistroDao.loadRegistrosRespByEnctRegtroId(encuestaRegistroId);
    }

    @Override
    public List<SurveyRecordAnswers> loadRegistrosRespByEnctRegtroIdSync(Long encuestaRegistroId) {
        return this.encuestaRegistroDao.loadRegistrosRespByEnctRegtroIdSync(encuestaRegistroId);
    }

    @Override
    public LiveData<List<SurveyRecordAnswers>> loadRegistrosRespuestasByEstatus(Integer catEncuestaEstatusId) {
        return this.encuestaRegistroDao.loadRegistrosRespuestasByEstatus(catEncuestaEstatusId);
    }

    @Override
    public List<SurveyRecordAnswers> loadRegistrosRespuestasByEstatusSync(Integer catEncuestaEstatusId) {
        return this.encuestaRegistroDao.loadRegistrosRespuestasByEstatusSync(catEncuestaEstatusId);
    }

}
