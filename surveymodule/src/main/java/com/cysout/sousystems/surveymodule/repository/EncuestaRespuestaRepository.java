package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.SurveyAnswer;

public interface EncuestaRespuestaRepository {
    Long insert(SurveyAnswer item);
    Long[] insertList(List<SurveyAnswer> list);
    void update(SurveyAnswer item);
    void deleteAll();
    LiveData<List<SurveyAnswer>> loadAll();
    List<SurveyAnswer> loadAllSync();
    SurveyAnswer encuestaRespuestaByRegistroIdAndPregIdSync(Long encuestaRegistroId, Long preguntaId);
    LiveData<SurveyAnswer> encuestaRespuestaByRegistroIdAndPregId(Long encuestaRegistroId, Long preguntaId);
    LiveData<SurveyAnswer> encuestaRespuestaByRegtroIdAndPregIdAndRespId(Long encuestaRegistroId, Long preguntaId, String respuestaId);
    SurveyAnswer encuestaRespuestaByRegtroIdAndPregIdAndRespIdSync(Long encuestaRegistroId, Long preguntaId, String respuestaId);
    List<SurveyAnswer> loadByEncuestaRegistroIdSync(Long encuestaRegistroId);
    LiveData<List<SurveyAnswer>> loadByEncuestaRegistroId(Long encuestaRegistroId);
    LiveData<List<SurveyAnswer>> loadByCuestionarioId(Long cuestionarioId);
    List<SurveyAnswer> loadByCuestionarioIdSync(Long cuestionarioId);
    LiveData<SurveyAnswer> encuestaRespuestaByRegistroIdAndCuestId(Long encuestaRegistroId, Long cuestionarioId);
    SurveyAnswer encuestaRespuestaByRegistroIdAndCuestIdSync(Long encuestaRegistroId, Long cuestionarioId);
    void deleteByEnctRegtIdAndCuestId(Long encuestaRegistroId, Long cuestionarioId);
    void deleteByEnctRegtIdAndPreguntaId(Long encuestaRegistroId, Long preguntaId);
    void deleteByEnctRegtIdAndPregtIdAndResp(Long encuestaRegistroId, Long preguntaId, String respuesta);
}
