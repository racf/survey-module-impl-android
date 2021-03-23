package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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
@Dao
public interface SurveyAnswerDao {
    @Insert()
    Long insert(SurveyAnswer item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<SurveyAnswer> list);

    @Update
    void update(SurveyAnswer item);

    @Query("DELETE FROM surveyAnswer")
    void deleteAll();

    @Query("SELECT * FROM surveyAnswer")
    LiveData<List<SurveyAnswer>> loadAll();

    @Query("SELECT * FROM surveyAnswer")
    List<SurveyAnswer> loadAllSync();

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId AND questionId=:questionId LIMIT 1")
    LiveData<SurveyAnswer> surveyAnswer(Long surveyRecordId, Long questionId);

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId AND questionId=:questionId LIMIT 1")
    SurveyAnswer surveyAnswerSync(Long surveyRecordId, Long questionId);

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId AND questionId=:questionId AND answer=:answerId LIMIT 1")
    LiveData<SurveyAnswer> surveyAnswer(Long surveyRecordId, Long questionId, String answerId);

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId AND questionId=:questionId AND answer=:answerId LIMIT 1")
    SurveyAnswer surveyAnswerSync(Long surveyRecordId, Long questionId, String answerId);

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId")
    List<SurveyAnswer> loadBySurveyRecordIdSync(Long surveyRecordId);

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId")
    LiveData<List<SurveyAnswer>> loadBySurveyRecordId(Long surveyRecordId);

    @Query("SELECT * FROM surveyAnswer WHERE questionnaireId=:questionnaireId")
    LiveData<List<SurveyAnswer>> loadByQuestionnaireId(Long questionnaireId);

    @Query("SELECT * FROM surveyAnswer WHERE questionnaireId=:questionnaireId")
    List<SurveyAnswer> loadByQuestionnaireIdSync(Long questionnaireId);

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId AND questionnaireId=:questionnaireId LIMIT 1")
    LiveData<SurveyAnswer> surveyAnswerByRecordIdAndQuestionnaireId(Long surveyRecordId, Long questionnaireId);

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId AND questionnaireId=:questionnaireId LIMIT 1")
    SurveyAnswer surveyAnswerByRecordIdAndQuestionnaireIdSync(Long surveyRecordId, Long questionnaireId);

    @Query("DELETE FROM surveyAnswer WHERE surveyRecordId=:surveyRecordId AND questionnaireId=:questionnaireId")
    void deleteByRecordIdQuestionnaireId(Long surveyRecordId, Long questionnaireId);

    @Query("DELETE FROM surveyAnswer WHERE surveyRecordId=:surveyRecordId AND questionId=:questionId")
    void deleteSurveyRecordByQuestionId(Long surveyRecordId, Long questionId);

    @Query("DELETE FROM surveyAnswer WHERE surveyRecordId=:surveyRecordId AND questionId=:questionId AND answer=:answer")
    void deleteSurveyRecordByQuestionIdAndAnswer(Long surveyRecordId, Long questionId, String answer);

    @Query("DELETE FROM surveyAnswer WHERE surveyRecordId=:surveyRecordId AND questionnaireId=:questionnaireId AND questionId=:questionId AND answer=:answer")
    void delete(Long surveyRecordId, Long questionnaireId, Long questionId, String answer);
}
