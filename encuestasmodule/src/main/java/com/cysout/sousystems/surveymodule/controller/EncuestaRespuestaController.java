package com.cysout.sousystems.surveymodule.controller;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.EncuestaRespuesta;
import com.cysout.sousystems.surveymodule.repository.EncuestaRespuestaRepository;
import com.cysout.sousystems.surveymodule.repository.impl.EncuestaRespuestaRepositoryImpl;

public class EncuestaRespuestaController extends AndroidViewModel {
    private EncuestaRespuestaRepository encuestaRespuestaRepository;
    public EncuestaRespuestaController(@NonNull Application application) {
        super(application);
        this.encuestaRespuestaRepository = new EncuestaRespuestaRepositoryImpl(application);
    }

    public Long insert(EncuestaRespuesta encuestaRespuesta) {
        return this.encuestaRespuestaRepository.insert(encuestaRespuesta);
    }

    public Long[] insertList(List<EncuestaRespuesta> list) {
        return this.encuestaRespuestaRepository.insertList(list);
    }

    public void update(EncuestaRespuesta encuestaRespuesta) {
        this.encuestaRespuestaRepository.update(encuestaRespuesta);
    }

    public void deleteAll() {
        this.encuestaRespuestaRepository.deleteAll();
    }

    public LiveData<List<EncuestaRespuesta>> loadAll() {
        return this.encuestaRespuestaRepository.loadAll();
    }

    public List<EncuestaRespuesta> loadAllSync() {
        return this.encuestaRespuestaRepository.loadAllSync();
    }

    public LiveData<EncuestaRespuesta> encuestaRespuestaByRegistroIdAndPregId(Long encuestaRegistroId, Long preguntaId) {
        return this.encuestaRespuestaRepository.encuestaRespuestaByRegistroIdAndPregId(encuestaRegistroId, preguntaId);
    }

    public EncuestaRespuesta encuestaRespuestaByRegistroIdAndPregIdSync(Long encuestaRegistroId, Long preguntaId) {
        return this.encuestaRespuestaRepository.encuestaRespuestaByRegistroIdAndPregIdSync(encuestaRegistroId, preguntaId);
    }

    public LiveData<EncuestaRespuesta> encuestaRespuestaByRegtroIdAndPregIdAndRespId(Long encuestaRegistroId, Long preguntaId, String respuestaId) {
        return this.encuestaRespuestaRepository.encuestaRespuestaByRegtroIdAndPregIdAndRespId(encuestaRegistroId, preguntaId, respuestaId);
    }

    public EncuestaRespuesta encuestaRespuestaByRegtroIdAndPregIdAndRespIdSync(Long encuestaRegistroId, Long preguntaId, String respuestaId) {
        return this.encuestaRespuestaRepository.encuestaRespuestaByRegtroIdAndPregIdAndRespIdSync(encuestaRegistroId, preguntaId, respuestaId);
    }

    public List<EncuestaRespuesta> loadByEncuestaRegistroIdSync(Long encuestaRegistroId) {
        return this.encuestaRespuestaRepository.loadByEncuestaRegistroIdSync(encuestaRegistroId);
    }

    public LiveData<List<EncuestaRespuesta>> loadByEncuestaRegistroId(Long encuestaRegistroId) {
        return this.encuestaRespuestaRepository.loadByEncuestaRegistroId(encuestaRegistroId);
    }

    public LiveData<List<EncuestaRespuesta>> loadByCuestionarioId(Long cuestionarioId) {
        return this.encuestaRespuestaRepository.loadByCuestionarioId(cuestionarioId);
    }

    public List<EncuestaRespuesta> loadByCuestionarioIdSync(Long cuestionarioId) {
        return this.encuestaRespuestaRepository.loadByCuestionarioIdSync(cuestionarioId);
    }
}
