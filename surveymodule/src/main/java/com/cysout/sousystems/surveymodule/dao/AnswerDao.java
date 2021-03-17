package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Answer;
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
public interface AnswerDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(Answer item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<Answer> list);

    @Update
    void update(Answer item);

    @Query("SELECT * FROM answer")
    LiveData<List<Answer>> loadAll();

    @Query("SELECT * FROM answer")
    List<Answer> loadAllSync();

    @Query("SELECT * FROM answer WHERE answerId = :id LIMIT 1")
    Answer loadRespuestaSync(Long id);

    @Query("SELECT * FROM answer WHERE answerId = :id")
    LiveData<List<Answer>> loadByRespuestaId(Long id);

    @Query("SELECT * FROM answer WHERE answerId = :id")
    List<Answer> loadByRespuestaIdSync(Long id);

    @Query("SELECT * FROM answer WHERE answerId = :id")
    LiveData<List<Answer>> loadByPreguntaId(Long id);

    @Query("SELECT * FROM answer WHERE answerId = :id")
    List<Answer> loadByPreguntaIdSync(Long id);

    @Query("DELETE FROM answer")
    void deleteAll();
}
