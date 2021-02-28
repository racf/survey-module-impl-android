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
import com.cysout.sousystems.surveymodule.entity.relation.EncuestaRegistroRespuestas;


@Dao
public interface EncuestaRegistroDao {

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
    LiveData<EncuestaRegistroRespuestas> loadRegistroRespByEnctRegtroId(Long surveyRecordId);

    @Transaction
    @Query("SELECT * FROM surveyRecord WHERE surveyRecordId=:surveyRecordId")
    EncuestaRegistroRespuestas loadRegistroRespByEnctRegtroIdSync(Long surveyRecordId);

    @Transaction
    @Query("SELECT * FROM surveyRecord WHERE surveyRecordId=:surveyRecordId")
    LiveData<List<EncuestaRegistroRespuestas>> loadRegistrosRespByEnctRegtroId(Long surveyRecordId);

    @Transaction
    @Query("SELECT * FROM surveyRecord WHERE surveyRecordId=:surveyRecordId")
    List<EncuestaRegistroRespuestas> loadRegistrosRespByEnctRegtroIdSync(Long surveyRecordId);

    @Transaction
    @Query("SELECT * FROM surveyRecord WHERE surveyStatus=:catEncuestaEstatusId")
    LiveData<List<EncuestaRegistroRespuestas>> loadRegistrosRespuestasByEstatus(Integer catEncuestaEstatusId);

    @Transaction
    @Query("SELECT * FROM surveyRecord WHERE surveyStatus=:catEncuestaEstatusId")
    List<EncuestaRegistroRespuestas> loadRegistrosRespuestasByEstatusSync(Integer catEncuestaEstatusId);

}
