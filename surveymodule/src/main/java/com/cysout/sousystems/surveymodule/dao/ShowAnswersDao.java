package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.ShowAnswers;
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
public interface ShowAnswersDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(ShowAnswers item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<ShowAnswers> list);

    @Update
    void update(ShowAnswers item);

    @Query("SELECT * FROM showAnswers")
    LiveData<List<ShowAnswers>> loadAll();

    @Query("SELECT * FROM showAnswers")
    List<ShowAnswers> loadAllSync();

    @Query("SELECT * FROM showAnswers WHERE showSelectId = :id")
    LiveData<List<ShowAnswers>> loadByShowSelectId(Long id);

    @Query("SELECT * FROM showAnswers WHERE showSelectId = :id")
    List<ShowAnswers> loadByShowSelectIdSync(Long id);

    @Query("DELETE FROM showAnswers")
    void deleteAll();
}
