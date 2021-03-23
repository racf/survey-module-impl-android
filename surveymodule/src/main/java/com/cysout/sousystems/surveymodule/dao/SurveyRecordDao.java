package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

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
@Dao
public interface SurveyRecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(SurveyRecord item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<SurveyRecord> list);

    @Update
    void update(SurveyRecord item);

    @Query("DELETE FROM surveyRecord")
    void deleteAll();

    @Query("SELECT * FROM surveyRecord")
    LiveData<List<SurveyRecord>> loadAll();

    @Query("SELECT * FROM surveyRecord")
    List<SurveyRecord> loadAllSync();

    @Query("SELECT * FROM surveyRecord WHERE surveyIdFK=:surveyId")
    LiveData<List<SurveyRecord>> loadBySurveyId(Long surveyId);

    @Query("SELECT * FROM surveyRecord WHERE surveyIdFK=:surveyId LIMIT 1")
    List<SurveyRecord> loadBySurveyIdSync(Long surveyId);

    @Query("SELECT * FROM surveyRecord WHERE surveyIdFK=:surveyId")
    SurveyRecord surveyRecord(Long surveyId);

    @Query("SELECT * FROM surveyRecord WHERE surveyIdFK=:surveyId AND surveyStatus=:surveyStatus LIMIT 1")
    SurveyRecord surveyRecord(Long surveyId, Integer surveyStatus);

    @Query("SELECT * FROM surveyRecord WHERE surveyIdFK=:surveyId AND surveyStatus=:surveyStatus")
    LiveData<List<SurveyRecord>> loadBySurveyIdAndSurveyStatus(Long surveyId, Integer surveyStatus);

    @Query("SELECT * FROM surveyRecord WHERE surveyIdFK=:surveyId AND surveyStatus=:surveyStatus")
    List<SurveyRecord> loadBySurveyIdAndSurveyStatusSync(Long surveyId, Integer surveyStatus);

    @Query("UPDATE surveyRecord SET surveyStatus =:surveyStatus WHERE surveyRecordId=:surveyRecordId")
    void update(Integer surveyStatus, Long surveyRecordId);

    @Query("UPDATE surveyRecord SET surveyStatus =:surveyStatus, endDate=:endDate WHERE surveyRecordId=:surveyRecordId")
    void update(Integer surveyStatus, String endDate, Long surveyRecordId);

    @Transaction
    @Query("SELECT * FROM surveyRecord WHERE surveyRecordId=:surveyRecordId")
    LiveData<SurveyRecordAnswers> surveyRecordAnswers(Long surveyRecordId);

    @Transaction
    @Query("SELECT * FROM surveyRecord WHERE surveyRecordId=:surveyRecordId")
    SurveyRecordAnswers surveyRecordAnswersSync(Long surveyRecordId);

    @Transaction
    @Query("SELECT * FROM surveyRecord WHERE surveyRecordId=:surveyRecordId")
    LiveData<List<SurveyRecordAnswers>> loadBySurveyRecordId(Long surveyRecordId);

    @Transaction
    @Query("SELECT * FROM surveyRecord WHERE surveyRecordId=:surveyRecordId")
    List<SurveyRecordAnswers> loadBySurveyRecordIdSync(Long surveyRecordId);

    @Transaction
    @Query("SELECT * FROM surveyRecord WHERE surveyStatus=:surveyStatus")
    LiveData<List<SurveyRecordAnswers>> loadBySurveyStatus(Integer surveyStatus);

    @Transaction
    @Query("SELECT * FROM surveyRecord WHERE surveyStatus=:surveyStatus")
    List<SurveyRecordAnswers> loadBySurveyStatusSync(Integer surveyStatus);

}
