package com.cysout.sousystems.surveymodule.service;

import androidx.lifecycle.LiveData;

import com.cysout.sousystems.surveymodule.entity.Questionnaire;
import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.SurveyAnswer;
import com.cysout.sousystems.surveymodule.entity.Question;
import com.cysout.sousystems.surveymodule.entity.SurveyRecord;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecordAnswers;
/**
* Copyright 2021 CysOut Solutions and SouSystems
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
**/
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
