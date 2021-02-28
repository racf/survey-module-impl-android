package com.cysout.sousystems.surveymodule.controller;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.SurveyAnswer;
import com.cysout.sousystems.surveymodule.repository.EncuestaRespuestaRepository;
import com.cysout.sousystems.surveymodule.repository.impl.EncuestaRespuestaRepositoryImpl;

public class EncuestaRespuestaController extends AndroidViewModel {
    private EncuestaRespuestaRepository encuestaRespuestaRepository;
    public EncuestaRespuestaController(@NonNull Application application) {
        super(application);
        this.encuestaRespuestaRepository = new EncuestaRespuestaRepositoryImpl(application);
    }

    public Long insert(SurveyAnswer surveyAnswer) {
        return this.encuestaRespuestaRepository.insert(surveyAnswer);
    }

    public Long[] insertList(List<SurveyAnswer> list) {
        return this.encuestaRespuestaRepository.insertList(list);
    }

    public void update(SurveyAnswer surveyAnswer) {
        this.encuestaRespuestaRepository.update(surveyAnswer);
    }

    public void deleteAll() {
        this.encuestaRespuestaRepository.deleteAll();
    }

    public LiveData<List<SurveyAnswer>> loadAll() {
        return this.encuestaRespuestaRepository.loadAll();
    }

    public List<SurveyAnswer> loadAllSync() {
        return this.encuestaRespuestaRepository.loadAllSync();
    }

    public LiveData<SurveyAnswer> encuestaRespuestaByRegistroIdAndPregId(Long encuestaRegistroId, Long preguntaId) {
        return this.encuestaRespuestaRepository.encuestaRespuestaByRegistroIdAndPregId(encuestaRegistroId, preguntaId);
    }

    public SurveyAnswer encuestaRespuestaByRegistroIdAndPregIdSync(Long encuestaRegistroId, Long preguntaId) {
        return this.encuestaRespuestaRepository.encuestaRespuestaByRegistroIdAndPregIdSync(encuestaRegistroId, preguntaId);
    }

    public LiveData<SurveyAnswer> encuestaRespuestaByRegtroIdAndPregIdAndRespId(Long encuestaRegistroId, Long preguntaId, String respuestaId) {
        return this.encuestaRespuestaRepository.encuestaRespuestaByRegtroIdAndPregIdAndRespId(encuestaRegistroId, preguntaId, respuestaId);
    }

    public SurveyAnswer encuestaRespuestaByRegtroIdAndPregIdAndRespIdSync(Long encuestaRegistroId, Long preguntaId, String respuestaId) {
        return this.encuestaRespuestaRepository.encuestaRespuestaByRegtroIdAndPregIdAndRespIdSync(encuestaRegistroId, preguntaId, respuestaId);
    }

    public List<SurveyAnswer> loadByEncuestaRegistroIdSync(Long encuestaRegistroId) {
        return this.encuestaRespuestaRepository.loadByEncuestaRegistroIdSync(encuestaRegistroId);
    }

    public LiveData<List<SurveyAnswer>> loadByEncuestaRegistroId(Long encuestaRegistroId) {
        return this.encuestaRespuestaRepository.loadByEncuestaRegistroId(encuestaRegistroId);
    }

    public LiveData<List<SurveyAnswer>> loadByCuestionarioId(Long cuestionarioId) {
        return this.encuestaRespuestaRepository.loadByCuestionarioId(cuestionarioId);
    }

    public List<SurveyAnswer> loadByCuestionarioIdSync(Long cuestionarioId) {
        return this.encuestaRespuestaRepository.loadByCuestionarioIdSync(cuestionarioId);
    }
}
