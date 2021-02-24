package com.cysout.sousystems.surveymodule.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Encuesta;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecords;

public interface SurveyService {
    Boolean saveSurveys(String surveys);
    LiveData<List<Encuesta>> loadAllSurveys();
    List<Encuesta> loadAllSurveysSync();
    LiveData<Encuesta> loadSurveyById(Long surveyId);
    Encuesta loadSurveyByIdSync(Long surveyId);
    LiveData<List<SurveyRecords>> loadAllSurveyRecords();
    List<SurveyRecords> loadAllSurveyRecordsSync();
    LiveData<List<SurveyRecords>> loadSurveyCompleted();
    List<SurveyRecords> loadSurveyCompletedSync();
    LiveData<List<SurveyRecords>> loadSurveyPending();
    List<SurveyRecords> loadSurveyPendingSync();
    LiveData<List<SurveyRecords>> loadSurveySent();
    List<SurveyRecords> loadSurveySentSync();
}
