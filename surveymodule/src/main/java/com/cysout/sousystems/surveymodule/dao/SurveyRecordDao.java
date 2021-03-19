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

    @Query("SELECT * FROM surveyRecord WHERE surveyIdFK=:encuestaId")
    LiveData<List<SurveyRecord>> loadByEncuestaId(Long encuestaId);

    @Query("SELECT * FROM surveyRecord WHERE surveyIdFK=:encuestaId LIMIT 1")
    List<SurveyRecord> loadByEncuestaIdSync(Long encuestaId);

    @Query("SELECT * FROM surveyRecord WHERE surveyIdFK=:encuestaId")
    SurveyRecord encuestaRegistro(Long encuestaId);

    @Query("SELECT * FROM surveyRecord WHERE surveyIdFK=:encuestaId AND surveyStatus=:catEncuestaEstatusId LIMIT 1")
    SurveyRecord encuestaRegistroByEncuestaIdAndCatEncuestaEstatusId(Long encuestaId, Integer catEncuestaEstatusId);

    @Query("SELECT * FROM surveyRecord WHERE surveyIdFK=:encuestaId AND surveyStatus=:catEncuestaEstatusId")
    LiveData<List<SurveyRecord>> loadByEncuestaIdAndCatEncuestaEstatusId(Long encuestaId, Integer catEncuestaEstatusId);

    @Query("SELECT * FROM surveyRecord WHERE surveyIdFK=:encuestaId AND surveyStatus=:catEncuestaEstatusId")
    List<SurveyRecord> loadByEncuestaIdAndCatEncuestaEstatusIdSync(Long encuestaId, Integer catEncuestaEstatusId);

    @Query("UPDATE surveyRecord SET surveyStatus =:catEncuestaEstatusId WHERE surveyRecordId=:surveyRecordId")
    void updateEncuestaRegistroByEnctRegtroId(Integer catEncuestaEstatusId, Long surveyRecordId);

    @Query("UPDATE surveyRecord SET surveyStatus =:catEncuestaEstatusId, endDate=:fechaFinal WHERE surveyRecordId=:surveyRecordId")
    void updateEncuestaRegistroByEnctRegtroId(Integer catEncuestaEstatusId, String fechaFinal, Long surveyRecordId);

    @Transaction
    @Query("SELECT * FROM surveyRecord WHERE surveyRecordId=:surveyRecordId")
    LiveData<SurveyRecordAnswers> loadRegistroRespByEnctRegtroId(Long surveyRecordId);

    @Transaction
    @Query("SELECT * FROM surveyRecord WHERE surveyRecordId=:surveyRecordId")
    SurveyRecordAnswers loadRegistroRespByEnctRegtroIdSync(Long surveyRecordId);

    @Transaction
    @Query("SELECT * FROM surveyRecord WHERE surveyRecordId=:surveyRecordId")
    LiveData<List<SurveyRecordAnswers>> loadRegistrosRespByEnctRegtroId(Long surveyRecordId);

    @Transaction
    @Query("SELECT * FROM surveyRecord WHERE surveyRecordId=:surveyRecordId")
    List<SurveyRecordAnswers> loadRegistrosRespByEnctRegtroIdSync(Long surveyRecordId);

    @Transaction
    @Query("SELECT * FROM surveyRecord WHERE surveyStatus=:catEncuestaEstatusId")
    LiveData<List<SurveyRecordAnswers>> loadRegistrosRespuestasByEstatus(Integer catEncuestaEstatusId);

    @Transaction
    @Query("SELECT * FROM surveyRecord WHERE surveyStatus=:catEncuestaEstatusId")
    List<SurveyRecordAnswers> loadRegistrosRespuestasByEstatusSync(Integer catEncuestaEstatusId);

}
