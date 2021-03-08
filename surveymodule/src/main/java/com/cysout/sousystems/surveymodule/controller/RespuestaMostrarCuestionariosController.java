package com.cysout.sousystems.surveymodule.controller;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.AnswerShowQuestionnaires;
import com.cysout.sousystems.surveymodule.repository.RespuestaMostrarCuestionariosRepository;
import com.cysout.sousystems.surveymodule.repository.impl.RespuestaMostrarCuestionariosRepositoryImpl;
/**
 * Developed by cysout.com and sousystems.com.mx
 * Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class RespuestaMostrarCuestionariosController extends AndroidViewModel {
    private RespuestaMostrarCuestionariosRepository respuestaMostrarCuestionariosRepository;
    public RespuestaMostrarCuestionariosController(@NonNull Application application) {
        super(application);
        this.respuestaMostrarCuestionariosRepository = new RespuestaMostrarCuestionariosRepositoryImpl(application);
    }

    public Long insert(AnswerShowQuestionnaires item) {
        return this.respuestaMostrarCuestionariosRepository.insert(item);
    }

    public Long[] insertList(List<AnswerShowQuestionnaires> list) {
        return this.respuestaMostrarCuestionariosRepository.insertList(list);
    }

    public LiveData<List<AnswerShowQuestionnaires>> loadAll() {
        return this.respuestaMostrarCuestionariosRepository.loadAll();
    }


    public List<AnswerShowQuestionnaires> loadAllSync() {
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
