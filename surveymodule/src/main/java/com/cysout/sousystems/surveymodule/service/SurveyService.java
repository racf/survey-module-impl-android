package com.cysout.sousystems.surveymodule.service;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecords;

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
public interface SurveyService {
    Boolean saveSurveys(String surveys);
    LiveData<List<Survey>> loadAllSurveys();
    List<Survey> loadAllSurveysSync();
    LiveData<Survey> loadSurveyById(Long surveyId);
    Survey loadSurveyByIdSync(Long surveyId);
    LiveData<List<SurveyRecords>> loadAllSurveyRecords();
    List<SurveyRecords> loadAllSurveyRecordsSync();
    LiveData<List<SurveyRecords>> loadSurveyFinished();
    List<SurveyRecords> loadSurveyFinishedSync();
    LiveData<List<SurveyRecords>> loadSurveyPending();
    List<SurveyRecords> loadSurveyPendingSync();
    LiveData<List<SurveyRecords>> loadSurveyUploaded();
    List<SurveyRecords> loadSurveyUploadedSync();
}
