package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

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
public interface SurveyRecordRepository {
    Long insert(SurveyRecord item);
    Long[] insertList(List<SurveyRecord> list);
    void update(SurveyRecord item);
    void deleteAll();
    LiveData<List<SurveyRecord>> loadAll();
    List<SurveyRecord> loadAllSync();
    LiveData<List<SurveyRecord>> loadBySurveyId(Long surveyId);
    List<SurveyRecord> loadBySurveyIdSync(Long surveyId);
    SurveyRecord surveyRecord(Long surveyId);
    SurveyRecord surveyRecord(Long surveyId, Integer surveyStatus);
    LiveData<List<SurveyRecord>> loadBySurveyIdAndSurveyStatus(Long surveyId, Integer surveyStatus);
    List<SurveyRecord> loadBySurveyIdAndSurveyStatusSync(Long surveyId, Integer surveyStatus);
    void update(Integer surveyStatus, Long surveyRecordId);
    void update(Integer surveyStatus, String endDate, Long surveyRecordId);
    LiveData<SurveyRecordAnswers> surveyRecordAnswers(Long surveyRecordId);
    SurveyRecordAnswers surveyRecordAnswersSync(Long surveyRecordId);
    LiveData<List<SurveyRecordAnswers>> loadBySurveyRecordId(Long surveyRecordId);
    List<SurveyRecordAnswers> loadBySurveyRecordIdSync(Long surveyRecordId);
    LiveData<List<SurveyRecordAnswers>> loadBySurveyStatus(Integer surveyStatus);
    List<SurveyRecordAnswers> loadBySurveyStatusSync(Integer surveyStatus);
}
