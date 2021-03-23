package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.SurveyAnswer;
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
public interface SurveyAnswerRepository {
    Long insert(SurveyAnswer item);
    Long[] insertList(List<SurveyAnswer> list);
    void update(SurveyAnswer item);
    void deleteAll();
    LiveData<List<SurveyAnswer>> loadAll();
    List<SurveyAnswer> loadAllSync();
    SurveyAnswer surveyAnswerSync(Long surveyRecordId, Long questionId);
    LiveData<SurveyAnswer> surveyAnswer(Long surveyRecordId, Long questionId);
    LiveData<SurveyAnswer> surveyAnswer(Long surveyRecordId, Long questionId, String answerId);
    SurveyAnswer surveyAnswerSync(Long surveyRecordId, Long questionId, String answerId);
    List<SurveyAnswer> loadBySurveyRecordIdSync(Long surveyRecordId);
    LiveData<List<SurveyAnswer>> loadBySurveyRecordId(Long surveyRecordId);
    LiveData<List<SurveyAnswer>> loadByQuestionnaireId(Long questionnaireId);
    List<SurveyAnswer> loadByQuestionnaireIdSync(Long questionnaireId);
    LiveData<SurveyAnswer> surveyAnswerByRecordIdAndQuestionnaireId(Long surveyRecordId, Long questionnaireId);
    SurveyAnswer surveyAnswerByRecordIdAndQuestionnaireIdSync(Long surveyRecordId, Long questionnaireId);
    void deleteByRecordIdQuestionnaireId(Long surveyRecordId, Long questionnaireId);
    void deleteSurveyRecordByQuestionId(Long surveyRecordId, Long questionId);
    void deleteSurveyRecordByQuestionIdAndAnswer(Long surveyRecordId, Long questionId, String answer);
}
