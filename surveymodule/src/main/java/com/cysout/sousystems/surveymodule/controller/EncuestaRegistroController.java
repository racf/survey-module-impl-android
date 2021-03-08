package com.cysout.sousystems.surveymodule.controller;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.SurveyRecord;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecordAnswers;
import com.cysout.sousystems.surveymodule.repository.EncuestaRegistroRepository;
import com.cysout.sousystems.surveymodule.repository.impl.EncuestaRegistroRepositoryImpl;

public class EncuestaRegistroController extends AndroidViewModel {
    private EncuestaRegistroRepository encuestaRegistroRepository;

    public EncuestaRegistroController(@NonNull Application application) {
        super(application);
        this.encuestaRegistroRepository = new EncuestaRegistroRepositoryImpl(application);
    }
    public Long insert(SurveyRecord encuestaRegistro) {
        return this.encuestaRegistroRepository.insert(encuestaRegistro);
    }

    public Long[] insertList(List<SurveyRecord> list) {
        return this.encuestaRegistroRepository.insertList(list);
    }

    public void update(SurveyRecord encuestaRegistro) {
        this.encuestaRegistroRepository.update(encuestaRegistro);
    }

    public void deleteAll() {
        this.encuestaRegistroRepository.deleteAll();
    }

    public LiveData<List<SurveyRecord>> loadAll() {
        return this.encuestaRegistroRepository.loadAll();
    }

    public List<SurveyRecord> loadAllSync() {
        return this.encuestaRegistroRepository.loadAllSync();
    }

    public LiveData<List<SurveyRecord>> loadByEncuestaId(Long encuestaId) {
        return this.encuestaRegistroRepository.loadByEncuestaId(encuestaId);
    }

    public List<SurveyRecord> loadByEncuestaIdSync(Long encuestaId) {
        return this.encuestaRegistroRepository.loadByEncuestaIdSync(encuestaId);
    }

    public SurveyRecord encuestaRegistro(Long encuestaId) {
        return this.encuestaRegistroRepository.encuestaRegistro(encuestaId);
    }

    public SurveyRecord encuestaRegistroByEncuestaIdAndCatEncuestaEstatusId(Long encuestaId, Integer catEncuestaEstatusId) {
        return this.encuestaRegistroRepository.encuestaRegistroByEncuestaIdAndCatEncuestaEstatusId(encuestaId, catEncuestaEstatusId);
    }

    public LiveData<List<SurveyRecord>> loadByEncuestaIdAndCatEncuestaEstatusId(Long encuestaId, Integer catEncuestaEstatusId) {
        return this.encuestaRegistroRepository.loadByEncuestaIdAndCatEncuestaEstatusId(encuestaId, catEncuestaEstatusId);
    }

    public List<SurveyRecord> loadByEncuestaIdAndCatEncuestaEstatusIdSync(Long encuestaId, Integer catEncuestaEstatusId) {
        return this.encuestaRegistroRepository.loadByEncuestaIdAndCatEncuestaEstatusIdSync(encuestaId, catEncuestaEstatusId);
    }

    public LiveData<List<SurveyRecordAnswers>> loadRegistrosRespuestasByEstatus(Integer catEncuestaEstatusId) {
        return this.encuestaRegistroRepository.loadRegistrosRespuestasByEstatus(catEncuestaEstatusId);
    }

    public List<SurveyRecordAnswers> loadRegistrosRespuestasByEstatusSync(Integer catEncuestaEstatusId) {
        return this.encuestaRegistroRepository.loadRegistrosRespuestasByEstatusSync(catEncuestaEstatusId);
    }
}
