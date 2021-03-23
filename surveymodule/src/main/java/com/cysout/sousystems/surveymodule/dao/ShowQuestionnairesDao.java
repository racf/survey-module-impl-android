package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.ShowQuestionnaires;
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
public interface ShowQuestionnairesDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(ShowQuestionnaires item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<ShowQuestionnaires> list);

    @Update
    void update(ShowQuestionnaires item);

    @Query("SELECT * FROM showQuestionnaires")
    LiveData<List<ShowQuestionnaires>> loadAll();

    @Query("SELECT * FROM showQuestionnaires")
    List<ShowQuestionnaires> loadAllSync();

    @Query("SELECT * FROM showQuestionnaires WHERE showSelectId = :id")
    LiveData<List<ShowQuestionnaires>> loadByShowSelectId(Long id);

    @Query("SELECT * FROM showQuestionnaires WHERE showSelectId = :id")
    List<ShowQuestionnaires> loadByShowSelectIdSync(Long id);

    @Query("DELETE FROM showQuestionnaires")
    void deleteAll();
}
