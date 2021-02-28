package com.cysout.sousystems.surveymodule.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecords;

/**
 *Developed by cysout.com and sousystems.com.mx
 *Contact info@cysout.com or contacto@sousystems.com.mx
**/
public interface SurveyService {
    Boolean saveSurveys(String surveys);
    LiveData<List<Survey>> loadAllSurveys();
    List<Survey> loadAllSurveysSync();
    LiveData<Survey> loadSurveyById(Long surveyId);
    Survey loadSurveyByIdSync(Long surveyId);
    LiveData<List<SurveyRecords>> loadAllSurveyRecords();
    List<SurveyRecords> loadAllSurveyRecordsSync();
    LiveData<List<SurveyRecords>> loadSurveyCompleted();
    List<SurveyRecords> loadSurveyCompletedSync();
    LiveData<List<SurveyRecords>> loadSurveyPending();
    List<SurveyRecords> loadSurveyPendingSync();
    LiveData<List<SurveyRecords>> loadSurveySent();
    List<SurveyRecords> loadSurveySentSync();
}
