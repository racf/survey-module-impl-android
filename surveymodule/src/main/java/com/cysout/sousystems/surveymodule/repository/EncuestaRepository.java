package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Encuesta;
import com.cysout.sousystems.surveymodule.entity.relation.EncuestaCuestionarios;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecords;


public interface EncuestaRepository {
    Long insert(Encuesta encuesta);
    Long[] insertList(List<Encuesta> list);
    void update(Encuesta encuesta);
    void delete(Encuesta encuesta);
    void deleteAllRows();
    LiveData<Encuesta> loadEncuestaById(Long encuestaId);
    Encuesta loadEncuestaByIdSync(Long encuestaId);
    List<Encuesta> findEncuestasById(Long encuestaId);
    LiveData<List<Encuesta>> loadAll();
    List<Encuesta> loadAllSync();
    List<EncuestaCuestionarios> loadEncuestaCuestionarios();
    EncuestaCuestionarios loadCuestionarioRespuestasSync();
    LiveData<List<SurveyRecords>> loadAllSurveyRecords();
    List<SurveyRecords> loadAllSurveyRecordsSync();
    LiveData<List<SurveyRecords>> loadSurveyCompleted();
    List<SurveyRecords> loadSurveyCompletedSync();
    LiveData<List<SurveyRecords>> loadSurveyPending();
    List<SurveyRecords> loadSurveyPendingSync();
    LiveData<List<SurveyRecords>> loadSurveySent();
    List<SurveyRecords> loadSurveySentSync();
}
