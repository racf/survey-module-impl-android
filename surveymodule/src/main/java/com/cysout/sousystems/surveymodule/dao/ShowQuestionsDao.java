package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.ShowQuestions;
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
public interface ShowQuestionsDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(ShowQuestions item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<ShowQuestions> list);

    @Update
    void update(ShowQuestions item);

    @Query("SELECT * FROM showQuestions")
    LiveData<List<ShowQuestions>> loadAll();

    @Query("SELECT * FROM showQuestions")
    List<ShowQuestions> loadAllSync();

    @Query("SELECT * FROM showQuestions WHERE showSelectId = :id")
    LiveData<List<ShowQuestions>> loadByMostrarSiSeleccionaId(Long id);

    @Query("SELECT * FROM showQuestions WHERE showSelectId = :id")
    List<ShowQuestions> loadByMostrarSiSeleccionaIdSync(Long id);

    @Query("DELETE FROM showQuestions")
    void deleteAll();
}
