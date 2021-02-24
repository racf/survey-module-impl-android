package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Respuesta;

public interface RespuestaRepository {
    Long insert(Respuesta item);
    Long[] insertList(List<Respuesta> list);
    void update(Respuesta item);
    LiveData<List<Respuesta>> loadAll();
    List<Respuesta> loadAllSync();
    Respuesta loadRespuestaSync(Long id);
    LiveData<List<Respuesta>> loadByRespuestaId(Long id);
    List<Respuesta> loadByRespuestaIdSync(Long id);
    LiveData<List<Respuesta>> loadByPreguntaId(Long id);
    List<Respuesta> loadByPreguntaIdSync(Long id);
    void deleteAll();
}
