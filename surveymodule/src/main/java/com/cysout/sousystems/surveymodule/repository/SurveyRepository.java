package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyQuestionnaires;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecords;

/**
 * Developed by cysout.com and sousystems.com.mx
 * Contact info@cysout.com or contacto@sousystems.com.mx
**/
public interface SurveyRepository {
    Long insert(Survey survey);
    Long[] insertList(List<Survey> list);
    void update(Survey survey);
    void delete(Survey survey);
    void deleteAllRows();
    LiveData<Survey> loadEncuestaById(Long encuestaId);
    Survey loadEncuestaByIdSync(Long encuestaId);
    List<Survey> findEncuestasById(Long encuestaId);
    LiveData<List<Survey>> loadAll();
    List<Survey> loadAllSync();
    List<SurveyQuestionnaires> loadEncuestaCuestionarios();
    SurveyQuestionnaires loadCuestionarioRespuestasSync();
    LiveData<List<SurveyRecords>> loadAllSurveyRecords();
    List<SurveyRecords> loadAllSurveyRecordsSync();
    LiveData<List<SurveyRecords>> loadSurveyCompleted();
    List<SurveyRecords> loadSurveyCompletedSync();
    LiveData<List<SurveyRecords>> loadSurveyPending();
    List<SurveyRecords> loadSurveyPendingSync();
    LiveData<List<SurveyRecords>> loadSurveySent();
    List<SurveyRecords> loadSurveySentSync();
}
