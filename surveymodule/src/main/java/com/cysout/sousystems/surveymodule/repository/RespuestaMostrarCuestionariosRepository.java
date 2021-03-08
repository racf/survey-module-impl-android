package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.AnswerShowQuestionnaires;

/**
 * Developed by cysout.com and sousystems.com.mx
 * Contact info@cysout.com or contacto@sousystems.com.mx
**/
public interface RespuestaMostrarCuestionariosRepository {
    Long insert(AnswerShowQuestionnaires item);
    Long[] insertList(List<AnswerShowQuestionnaires> list);
    LiveData<List<AnswerShowQuestionnaires>> loadAll();
    List<AnswerShowQuestionnaires> loadAllSync();
    void deleteAll();
    void deleteByPreguntaId(Long preguntaId);
    void deleteByCuestionarioOrigenId(Long cuestionarioOrigenId);
    void deleteByPreguntaIdAndRespuestaId(Long preguntaId, Long respuestaId);
}
