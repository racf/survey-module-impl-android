package com.cysout.sousystems.surveymodule.service;

import androidx.lifecycle.LiveData;

import com.cysout.sousystems.surveymodule.entity.Cuestionario;
import com.cysout.sousystems.surveymodule.entity.Encuesta;
import com.cysout.sousystems.surveymodule.entity.EncuestaRegistro;
import com.cysout.sousystems.surveymodule.entity.EncuestaRespuesta;
import com.cysout.sousystems.surveymodule.entity.Pregunta;
import com.cysout.sousystems.surveymodule.entity.relation.EncuestaRegistroRespuestas;

public interface EncuestaService {
    Long encuestaRegistro(Encuesta encuesta, Integer catEncuestaEstatusId, String fechaInicial, String fechaFinal);
    Long encuestaRespuesta(Encuesta encuesta, Cuestionario cuestionario, Pregunta pregunta, String respuesta, Long encuestaRegistroId);
    EncuestaRegistro findEncuestaregistro(Encuesta encuesta);
    LiveData<EncuestaRespuesta> encuestaRespuestaByRegistroIdAndPregId(Long encuestaRegistroId, Long preguntaId);
    EncuestaRespuesta encuestaRespuestaByRegistroIdAndPregIdSync(Long encuestaRegistroId, Long preguntaId);
    LiveData<EncuestaRespuesta> encuestaRespuestaByRegtroIdAndPregIdAndRespId(Long encuestaRegistroId, Long preguntaId, String respuestaId);
    EncuestaRespuesta encuestaRespuestaByRegtroIdAndPregIdAndRespIdSync(Long encuestaRegistroId, Long preguntaId, String respuestaId);
    EncuestaRegistroRespuestas encuentaFinaliza(Long encuestaRegistroId, Integer catEncuestaEstatusId, String fechaFinal);
    void eliminarEncuestaRegistroByCuestionarioId(Long encuestaRegistroId, Long cuestionarioId);
    void eliminarEncuestaRegistroByPreguntaId(Long encuestaRegistroId, Long preguntaId);
    void eliminarEncuestaRegistroByPregtIdAndResp(Long encuestaRegistroId, Long preguntaId, String respuesta);
}
