package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.RespuestaMostrarCuestionarios;

public interface RespuestaMostrarCuestionariosRepository {
    Long insert(RespuestaMostrarCuestionarios item);
    Long[] insertList(List<RespuestaMostrarCuestionarios> list);
    LiveData<List<RespuestaMostrarCuestionarios>> loadAll();
    List<RespuestaMostrarCuestionarios> loadAllSync();
    void deleteAll();
    void deleteByPreguntaId(Long preguntaId);
    void deleteByCuestionarioOrigenId(Long cuestionarioOrigenId);
    void deleteByPreguntaIdAndRespuestaId(Long preguntaId, Long respuestaId);
}
