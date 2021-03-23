package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.ShowSelect;
import com.cysout.sousystems.surveymodule.entity.relation.RelationSelectQuestionnaires;
import com.cysout.sousystems.surveymodule.entity.relation.RelationShowSelect;
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
public interface ShowSelectDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(ShowSelect item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<ShowSelect> list);

    @Update
    void update(ShowSelect item);

    @Query("SELECT * FROM showSelect")
    LiveData<List<ShowSelect>> loadAll();

    @Query("SELECT * FROM showSelect")
    List<ShowSelect> loadAllSync();

    @Query("SELECT * FROM showSelect WHERE answerId = :id")
    LiveData<List<ShowSelect>> loadByAnswerId(Long id);

    @Query("SELECT * FROM showSelect WHERE answerId = :id")
    List<ShowSelect> loadByAnswerIdSync(Long id);

    @Query("DELETE FROM showSelect")
    void deleteAll();

    @Transaction
    @Query("SELECT * FROM showSelect WHERE answerId=:id LIMIT 1")
    RelationShowSelect loadShowSelectByAnswerId(Long id);

    @Transaction
    @Query("SELECT * FROM showSelect")
    LiveData<List<RelationSelectQuestionnaires>> loadShowQuestionnaires();

    @Transaction
    @Query("SELECT * FROM showSelect")
    List<RelationSelectQuestionnaires> loadShowQuestionnairesSync();
}
