package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.SurveyRecord;
import com.cysout.sousystems.surveymodule.entity.relation.EncuestaRegistroRespuestas;

public interface EncuestaRegistroRepository {
    Long insert(SurveyRecord item);
    Long[] insertList(List<SurveyRecord> list);
    void update(SurveyRecord item);
    void deleteAll();
    LiveData<List<SurveyRecord>> loadAll();
    List<SurveyRecord> loadAllSync();
    LiveData<List<SurveyRecord>> loadByEncuestaId(Long encuestaId);
    List<SurveyRecord> loadByEncuestaIdSync(Long encuestaId);
    SurveyRecord encuestaRegistro(Long encuestaId);
    SurveyRecord encuestaRegistroByEncuestaIdAndCatEncuestaEstatusId(Long encuestaId, Integer catEncuestaEstatusId);
    LiveData<List<SurveyRecord>> loadByEncuestaIdAndCatEncuestaEstatusId(Long encuestaId, Integer catEncuestaEstatusId);
    List<SurveyRecord> loadByEncuestaIdAndCatEncuestaEstatusIdSync(Long encuestaId, Integer catEncuestaEstatusId);
    void updateEncuestaRegistroByEnctRegtroId(Integer catEncuestaEstatusId, Long encuestaRegistroId);
    void updateEncuestaRegistroByEnctRegtroId(Integer catEncuestaEstatusId, String fechaFinal, Long encuestaRegistroId);
    LiveData<EncuestaRegistroRespuestas> loadRegistroRespByEnctRegtroId(Long encuestaRegistroId);
    EncuestaRegistroRespuestas loadRegistroRespByEnctRegtroIdSync(Long encuestaRegistroId);
    LiveData<List<EncuestaRegistroRespuestas>> loadRegistrosRespByEnctRegtroId(Long encuestaRegistroId);
    List<EncuestaRegistroRespuestas> loadRegistrosRespByEnctRegtroIdSync(Long encuestaRegistroId);
    LiveData<List<EncuestaRegistroRespuestas>> loadRegistrosRespuestasByEstatus(Integer catEncuestaEstatusId);
    List<EncuestaRegistroRespuestas> loadRegistrosRespuestasByEstatusSync(Integer catEncuestaEstatusId);
}
