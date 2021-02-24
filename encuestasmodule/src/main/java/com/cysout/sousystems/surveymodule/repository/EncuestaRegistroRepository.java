package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.EncuestaRegistro;
import com.cysout.sousystems.surveymodule.entity.relation.EncuestaRegistroRespuestas;

public interface EncuestaRegistroRepository {
    Long insert(EncuestaRegistro item);
    Long[] insertList(List<EncuestaRegistro> list);
    void update(EncuestaRegistro item);
    void deleteAll();
    LiveData<List<EncuestaRegistro>> loadAll();
    List<EncuestaRegistro> loadAllSync();
    LiveData<List<EncuestaRegistro>> loadByEncuestaId(Long encuestaId);
    List<EncuestaRegistro> loadByEncuestaIdSync(Long encuestaId);
    EncuestaRegistro encuestaRegistro(Long encuestaId);
    EncuestaRegistro encuestaRegistroByEncuestaIdAndCatEncuestaEstatusId(Long encuestaId, Integer catEncuestaEstatusId);
    LiveData<List<EncuestaRegistro>> loadByEncuestaIdAndCatEncuestaEstatusId(Long encuestaId, Integer catEncuestaEstatusId);
    List<EncuestaRegistro> loadByEncuestaIdAndCatEncuestaEstatusIdSync(Long encuestaId, Integer catEncuestaEstatusId);
    void updateEncuestaRegistroByEnctRegtroId(Integer catEncuestaEstatusId, Long encuestaRegistroId);
    void updateEncuestaRegistroByEnctRegtroId(Integer catEncuestaEstatusId, String fechaFinal, Long encuestaRegistroId);
    LiveData<EncuestaRegistroRespuestas> loadRegistroRespByEnctRegtroId(Long encuestaRegistroId);
    EncuestaRegistroRespuestas loadRegistroRespByEnctRegtroIdSync(Long encuestaRegistroId);
    LiveData<List<EncuestaRegistroRespuestas>> loadRegistrosRespByEnctRegtroId(Long encuestaRegistroId);
    List<EncuestaRegistroRespuestas> loadRegistrosRespByEnctRegtroIdSync(Long encuestaRegistroId);
    LiveData<List<EncuestaRegistroRespuestas>> loadRegistrosRespuestasByEstatus(Integer catEncuestaEstatusId);
    List<EncuestaRegistroRespuestas> loadRegistrosRespuestasByEstatusSync(Integer catEncuestaEstatusId);
}
