package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Question;
import com.cysout.sousystems.surveymodule.entity.relation.QuestionAnswers;
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
public interface QuestionDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(Question item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<Question> list);

    @Update
    void update(Question item);

    @Query("SELECT * FROM question")
    LiveData<List<Question>> loadAll();

    @Query("SELECT * FROM question")
    List<Question> loadAllSync();

    @Query("SELECT * FROM question WHERE questionId = :id LIMIT 1")
    Question loadPreguntaSync(Long id);

    @Transaction
    @Query("SELECT * FROM question WHERE questionnaireId=:id")
    QuestionAnswers loadPreguntaRespuestasByCuestionarioId(Long id);

    @Query("SELECT * FROM question WHERE questionId = :id")
    LiveData<List<Question>> loadByPreguntaId(Long id);

    @Query("SELECT * FROM question WHERE questionId = :id")
    List<Question> loadByPreguntaIdSync(Long id);

    @Query("SELECT * FROM question WHERE questionnaireId = :id")
    LiveData<List<Question>> loadByCuestionarioId(Long id);

    @Query("SELECT * FROM question WHERE questionnaireId = :id")
    List<Question> loadByCuestionarioIdSync(Long id);

    @Query("DELETE FROM question")
    void deleteAll();
}
