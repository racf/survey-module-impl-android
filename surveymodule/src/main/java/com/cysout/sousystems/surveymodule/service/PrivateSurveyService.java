package com.cysout.sousystems.surveymodule.service;

import androidx.lifecycle.LiveData;

import com.cysout.sousystems.surveymodule.entity.Questionnaire;
import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.SurveyAnswer;
import com.cysout.sousystems.surveymodule.entity.Question;
import com.cysout.sousystems.surveymodule.entity.SurveyRecord;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecordAnswers;

public interface PrivateSurveyService {
    Long encuestaRegistro(Survey survey, Integer catEncuestaEstatusId, String fechaInicial, String fechaFinal);
    Long encuestaRespuesta(Survey survey, Questionnaire questionnaire, Question question, String respuesta, Long encuestaRegistroId);
    SurveyRecord findEncuestaregistro(Survey survey);
    LiveData<SurveyAnswer> encuestaRespuestaByRegistroIdAndPregId(Long encuestaRegistroId, Long preguntaId);
    SurveyAnswer encuestaRespuestaByRegistroIdAndPregIdSync(Long encuestaRegistroId, Long preguntaId);
    LiveData<SurveyAnswer> encuestaRespuestaByRegtroIdAndPregIdAndRespId(Long encuestaRegistroId, Long preguntaId, String respuestaId);
    SurveyAnswer encuestaRespuestaByRegtroIdAndPregIdAndRespIdSync(Long encuestaRegistroId, Long preguntaId, String respuestaId);
    SurveyRecordAnswers encuentaFinaliza(Long encuestaRegistroId, Integer catEncuestaEstatusId, String fechaFinal);
    void eliminarEncuestaRegistroByCuestionarioId(Long encuestaRegistroId, Long cuestionarioId);
    void eliminarEncuestaRegistroByPreguntaId(Long encuestaRegistroId, Long preguntaId);
    void eliminarEncuestaRegistroByPregtIdAndResp(Long encuestaRegistroId, Long preguntaId, String respuesta);
}
