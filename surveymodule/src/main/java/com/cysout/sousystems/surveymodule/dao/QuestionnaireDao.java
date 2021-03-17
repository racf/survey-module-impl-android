package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import com.cysout.sousystems.surveymodule.entity.Questionnaire;
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
public interface QuestionnaireDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(Questionnaire item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<Questionnaire> list);

    @Update
    void update(Questionnaire item);

    @Query("select * from questionnaire where questionnaireId = :questionnaireId LIMIT 1")
    Questionnaire loadCuestionarioSync(Long questionnaireId);

    @Query("SELECT * FROM questionnaire")
    LiveData<List<Questionnaire>> loadAll();

    @Query("SELECT * FROM questionnaire")
    List<Questionnaire> loadAllSync();

    @Query("SELECT * FROM questionnaire WHERE questionnaireId = :id")
    LiveData<List<Questionnaire>> loadByCuestionarioId(Long id);

    @Query("SELECT * FROM questionnaire WHERE questionnaireId = :id")
    List<Questionnaire> loadByCuestionarioIdSync(Long id);

    @Query("SELECT * FROM questionnaire WHERE surveyId = :id")
    LiveData<List<Questionnaire>> loadByEncuestaId(Long id);

    @Query("SELECT * FROM questionnaire WHERE surveyId = :id")
    List<Questionnaire> loadByEncuestaIdSync(Long id);

    @Query("DELETE FROM questionnaire")
    void deleteAll();
}
