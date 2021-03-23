package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyQuestionnaires;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecords;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
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
public interface SurveyDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(Survey item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<Survey> list);

    @Update
    void update(Survey item);

    @Query("SELECT * FROM survey")
    LiveData<List<Survey>> loadAll();

    @Query("SELECT * FROM survey")
    List<Survey> loadAllSync();

    @Transaction
    @Query("SELECT * FROM survey")
    List<SurveyQuestionnaires> loadSurveyQuestionnairesSync();

    @Query("SELECT * FROM survey WHERE surveyId = :surveyId LIMIT 1")
    LiveData<Survey> loadSurveyById(Long surveyId);

    @Query("SELECT * FROM survey WHERE surveyId = :surveyId LIMIT 1")
    Survey loadSurveyByIdSync(Long surveyId);

    @Query("SELECT * FROM survey WHERE surveyId=:surveyId")
    LiveData<List<Survey>> loadBySurveyId(Long surveyId);

    @Query("SELECT * FROM survey WHERE surveyId=:surveyId")
    List<Survey> loadBySurveyIdSync(Long surveyId);

    @Query("DELETE FROM survey")
    void deleteAll();

    @Transaction
    @Query("SELECT survey.*, er.* FROM Survey INNER JOIN surveyRecord er ON Survey.surveyId = er.surveyIdFK ")
    LiveData<List<SurveyRecords>> loadAllSurveyRecords();

    @Transaction
    @Query("SELECT survey.*, er.* FROM Survey INNER JOIN surveyRecord er ON Survey.surveyId = er.surveyIdFK ")
    List<SurveyRecords> loadAllSurveyRecordsSync();

    @Transaction
    @Query("SELECT survey.*, er.* FROM Survey INNER JOIN surveyRecord er ON Survey.surveyId = er.surveyIdFK " +
            "WHERE er.surveyStatus = "+ CustomConstants.FINISHED)
    LiveData<List<SurveyRecords>> loadSurveyCompleted();

    @Transaction
    @Query("SELECT survey.*, er.* FROM Survey INNER JOIN surveyRecord er ON Survey.surveyId = er.surveyIdFK " +
            "WHERE er.surveyStatus = "+ CustomConstants.FINISHED)
    List<SurveyRecords> loadSurveyCompletedSync();

    @Transaction
    @Query("SELECT survey.*, er.* FROM Survey INNER JOIN surveyRecord er ON Survey.surveyId = er.surveyIdFK " +
            "WHERE er.surveyStatus = "+ CustomConstants.PENDING)
    LiveData<List<SurveyRecords>> loadSurveyPending();

    @Transaction
    @Query("SELECT survey.*, er.* FROM Survey INNER JOIN surveyRecord er ON Survey.surveyId = er.surveyIdFK " +
            "WHERE er.surveyStatus = "+ CustomConstants.PENDING)
    List<SurveyRecords> loadSurveyPendingSync();

    @Transaction
    @Query("SELECT survey.*, er.* FROM Survey INNER JOIN surveyRecord er ON Survey.surveyId = er.surveyIdFK " +
            "WHERE er.surveyStatus = "+ CustomConstants.UPLOADED)
    LiveData<List<SurveyRecords>> loadSurveySent();

    @Transaction
    @Query("SELECT survey.*, er.* FROM Survey INNER JOIN surveyRecord er ON Survey.surveyId = er.surveyIdFK " +
            "WHERE er.surveyStatus = "+ CustomConstants.UPLOADED)
    List<SurveyRecords> loadSurveySentSync();


}
