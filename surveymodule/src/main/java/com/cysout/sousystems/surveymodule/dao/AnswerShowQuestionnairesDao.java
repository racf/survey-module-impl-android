package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.AnswerShowQuestionnaires;

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
public interface AnswerShowQuestionnairesDao {
    @Insert()
    Long insert(AnswerShowQuestionnaires item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<AnswerShowQuestionnaires> list);

    @Update
    void update(AnswerShowQuestionnaires item);

    @Query("SELECT * FROM answerShowQuestionnaires")
    LiveData<List<AnswerShowQuestionnaires>> loadAll();

    @Query("SELECT * FROM answerShowQuestionnaires")
    List<AnswerShowQuestionnaires> loadAllSync();

    @Query("DELETE FROM answerShowQuestionnaires")
    void deleteAll();

    @Query("DELETE FROM answerShowQuestionnaires WHERE questionId=:questionId")
    void deleteByQuestionId(Long questionId);

    @Query("DELETE FROM answerShowQuestionnaires WHERE questionnaireOriginId=:questionnaireOriginId")
    void deleteByQuestionnaireOriginId(Long questionnaireOriginId);

    @Query("DELETE FROM answerShowQuestionnaires WHERE questionId=:questionId AND answerId=:answerId")
    void deleteByQuestionIdAndAnswerId(Long questionId, Long answerId);
}
