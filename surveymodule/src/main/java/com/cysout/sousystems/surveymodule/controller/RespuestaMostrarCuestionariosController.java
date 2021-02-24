package com.cysout.sousystems.surveymodule.controller;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.RespuestaMostrarCuestionarios;
import com.cysout.sousystems.surveymodule.repository.RespuestaMostrarCuestionariosRepository;
import com.cysout.sousystems.surveymodule.repository.impl.RespuestaMostrarCuestionariosRepositoryImpl;

public class RespuestaMostrarCuestionariosController extends AndroidViewModel {
    private RespuestaMostrarCuestionariosRepository respuestaMostrarCuestionariosRepository;
    public RespuestaMostrarCuestionariosController(@NonNull Application application) {
        super(application);
        this.respuestaMostrarCuestionariosRepository = new RespuestaMostrarCuestionariosRepositoryImpl(application);
    }

    public Long insert(RespuestaMostrarCuestionarios item) {
        return this.respuestaMostrarCuestionariosRepository.insert(item);
    }

    public Long[] insertList(List<RespuestaMostrarCuestionarios> list) {
        return this.respuestaMostrarCuestionariosRepository.insertList(list);
    }

    public LiveData<List<RespuestaMostrarCuestionarios>> loadAll() {
        return this.respuestaMostrarCuestionariosRepository.loadAll();
    }


    public List<RespuestaMostrarCuestionarios> loadAllSync() {
        return this.respuestaMostrarCuestionariosRepository.loadAllSync();
    }

    public void deleteAll() {
        this.respuestaMostrarCuestionariosRepository.deleteAll();
    }

    public void deleteByPreguntaId(Long preguntaId) {
        this.respuestaMostrarCuestionariosRepository.deleteByPreguntaId(preguntaId);
    }

    public void deleteByCuestionarioOrigenId(Long cuestionarioOrigenId) {
        this.respuestaMostrarCuestionariosRepository.deleteByCuestionarioOrigenId(cuestionarioOrigenId);
    }

    public void deleteByPreguntaIdAndRespuestaId(Long preguntaId, Long respuestaId) {
        this.respuestaMostrarCuestionariosRepository.deleteByPreguntaIdAndRespuestaId(preguntaId,respuestaId);
    }
}
