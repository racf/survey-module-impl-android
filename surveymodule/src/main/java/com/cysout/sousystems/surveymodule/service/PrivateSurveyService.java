package com.cysout.sousystems.surveymodule.service;

import androidx.lifecycle.LiveData;

import com.cysout.sousystems.surveymodule.entity.Questionnaire;
import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.SurveyAnswer;
import com.cysout.sousystems.surveymodule.entity.Question;
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
public interface PrivateSurveyService {
    Long surveyRecord(Survey survey, Integer surveyStatus, String startDate, String endDate);
    Long surveyAnswer(Survey survey, Questionnaire questionnaire, Question question, String answer, Long surveyRecordId);
    SurveyRecord findSurveyRecord(Survey survey);
    LiveData<SurveyAnswer> surveyAnswer(Long surveyRecordId, Long questionId);
    SurveyAnswer surveyAnswerSync(Long surveyRecordId, Long questionId);
    LiveData<SurveyAnswer> surveyAnswer(Long surveyRecordId, Long questionId, String answerId);
    SurveyAnswer surveyAnswerSync(Long surveyRecordId, Long questionId, String answerId);
    SurveyRecordAnswers surveyFinished(Long surveyRecordId, Integer surveyStatus, String endDate);
    void deleteSurveyRecordByQuestionnaireId(Long surveyRecordId, Long questionnaireId);
    void deleteSurveyRecordByQuestionId(Long surveyRecordId, Long questionId);
    void deleteSurveyRecordByQuestionIdAndAnswer(Long surveyRecordId, Long questionId, String answer);
}
