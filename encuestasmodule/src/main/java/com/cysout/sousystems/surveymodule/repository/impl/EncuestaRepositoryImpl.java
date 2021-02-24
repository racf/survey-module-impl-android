package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.EncuestaDao;
import com.cysout.sousystems.surveymodule.entity.Encuesta;
import com.cysout.sousystems.surveymodule.entity.relation.EncuestaCuestionarios;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecords;
import com.cysout.sousystems.surveymodule.repository.EncuestaRepository;

public class EncuestaRepositoryImpl implements EncuestaRepository {
    private EncuestaDao encuestaDao;

    public EncuestaRepositoryImpl(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        this.encuestaDao = db.encuestaDao();
    }

    @Override
    public Long insert(Encuesta encuesta) {
        return this.encuestaDao.insert(encuesta);
        /*final Long[] ids = {null};
        AppDatabase.databaseWriteExecutor.execute(() -> {
            ids[0] = encuestaDao.insert(encuesta);
        });
        return ids[0];*/
    }

    @Override
    public Long[] insertList(List<Encuesta> list) {
        /*final Long[][] ids = {{null}};
        AppDatabase.databaseWriteExecutor.execute(() -> {
            ids[0] = encuestaDao.insertList(list);
        });
        return ids[0];*/
        return this.encuestaDao.insertList(list);
    }

    @Override
    public void update(Encuesta encuesta) {
        this.encuestaDao.update(encuesta);
    }

    @Override
    public void delete(Encuesta encuesta) {

    }

    @Override
    public void deleteAllRows() {

    }

    @Override
    public LiveData<Encuesta> loadEncuestaById(Long encuestaId) {
        return this.encuestaDao.loadEncuestaById(encuestaId);
    }

    @Override
    public Encuesta loadEncuestaByIdSync(Long encuestaId) {
        return this.encuestaDao.loadEncuestaByIdSync(encuestaId);
    }


    @Override
    public List<Encuesta> findEncuestasById(Long encuestaId) {
        return this.encuestaDao.loadByEncuestaIdSync(encuestaId);
    }

    @Override
    public LiveData<List<Encuesta>> loadAll() {
        return this.encuestaDao.loadAll();
    }

    @Override
    public List<Encuesta> loadAllSync() {
        return this.encuestaDao.loadAllSync();
    }

    @Override
    public List<EncuestaCuestionarios> loadEncuestaCuestionarios() {
        return this.encuestaDao.loadCuestionariosSync();
    }

    @Override
    public EncuestaCuestionarios loadCuestionarioRespuestasSync() {
        return this.encuestaDao.loadCuestionarioRespuestasSync();
    }

    @Override
    public LiveData<List<SurveyRecords>> loadAllSurveyRecords() {
        return this.encuestaDao.loadAllSurveyRecords();
    }

    @Override
    public List<SurveyRecords> loadAllSurveyRecordsSync() {
        return this.encuestaDao.loadAllSurveyRecordsSync();
    }

    @Override
    public LiveData<List<SurveyRecords>> loadSurveyCompleted() {
        return this.encuestaDao.loadSurveyCompleted();
    }

    @Override
    public List<SurveyRecords> loadSurveyCompletedSync() {
        return this.encuestaDao.loadSurveyCompletedSync();
    }

    @Override
    public LiveData<List<SurveyRecords>> loadSurveyPending() {
        return this.encuestaDao.loadSurveyPending();
    }

    @Override
    public List<SurveyRecords> loadSurveyPendingSync() {
        return this.encuestaDao.loadSurveyPendingSync();
    }

    @Override
    public LiveData<List<SurveyRecords>> loadSurveySent() {
        return this.encuestaDao.loadSurveySent();
    }

    @Override
    public List<SurveyRecords> loadSurveySentSync() {
        return this.encuestaDao.loadSurveySentSync();
    }
}
