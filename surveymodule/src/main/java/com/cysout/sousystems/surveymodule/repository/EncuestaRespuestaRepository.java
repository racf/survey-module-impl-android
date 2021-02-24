package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.EncuestaRespuesta;

public interface EncuestaRespuestaRepository {
    Long insert(EncuestaRespuesta item);
    Long[] insertList(List<EncuestaRespuesta> list);
    void update(EncuestaRespuesta item);
    void deleteAll();
    LiveData<List<EncuestaRespuesta>> loadAll();
    List<EncuestaRespuesta> loadAllSync();
    EncuestaRespuesta encuestaRespuestaByRegistroIdAndPregIdSync(Long encuestaRegistroId, Long preguntaId);
    LiveData<EncuestaRespuesta> encuestaRespuestaByRegistroIdAndPregId(Long encuestaRegistroId, Long preguntaId);
    LiveData<EncuestaRespuesta> encuestaRespuestaByRegtroIdAndPregIdAndRespId(Long encuestaRegistroId, Long preguntaId, String respuestaId);
    EncuestaRespuesta encuestaRespuestaByRegtroIdAndPregIdAndRespIdSync(Long encuestaRegistroId, Long preguntaId, String respuestaId);
    List<EncuestaRespuesta> loadByEncuestaRegistroIdSync(Long encuestaRegistroId);
    LiveData<List<EncuestaRespuesta>> loadByEncuestaRegistroId(Long encuestaRegistroId);
    LiveData<List<EncuestaRespuesta>> loadByCuestionarioId(Long cuestionarioId);
    List<EncuestaRespuesta> loadByCuestionarioIdSync(Long cuestionarioId);
    LiveData<EncuestaRespuesta> encuestaRespuestaByRegistroIdAndCuestId(Long encuestaRegistroId, Long cuestionarioId);
    EncuestaRespuesta encuestaRespuestaByRegistroIdAndCuestIdSync(Long encuestaRegistroId, Long cuestionarioId);
    void deleteByEnctRegtIdAndCuestId(Long encuestaRegistroId, Long cuestionarioId);
    void deleteByEnctRegtIdAndPreguntaId(Long encuestaRegistroId, Long preguntaId);
    void deleteByEnctRegtIdAndPregtIdAndResp(Long encuestaRegistroId, Long preguntaId, String respuesta);
}
