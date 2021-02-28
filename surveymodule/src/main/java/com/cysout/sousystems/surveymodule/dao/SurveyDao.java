package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.relation.EncuestaCuestionarios;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecords;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;

@Dao
public interface SurveyDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(Survey item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<Survey> list);

    @Update
    void update(Survey item);

    @Query("SELECT * FROM Survey")
    LiveData<List<Survey>> loadAll();

    @Query("SELECT * FROM Survey")
    List<Survey> loadAllSync();

    @Transaction
    @Query("SELECT * FROM Survey")
    LiveData<List<EncuestaCuestionarios>> loadCuestionarios();
    @Transaction
    @Query("SELECT * FROM Survey")
    List<EncuestaCuestionarios> loadCuestionariosSync();
    @Transaction
    @Query("SELECT * FROM Survey LIMIT 1")
    EncuestaCuestionarios loadCuestionarioRespuestasSync();

    @Query("SELECT * FROM Survey WHERE surveyId = :encuestaId LIMIT 1")
    LiveData<Survey> loadEncuestaById(Long encuestaId);

    @Query("SELECT * FROM Survey WHERE surveyId = :encuestaId LIMIT 1")
    Survey loadEncuestaByIdSync(Long encuestaId);

    @Query("SELECT * FROM Survey WHERE surveyId=:encuestaId")
    LiveData<List<Survey>> loadByEncuestaId(Long encuestaId);

    @Query("SELECT * FROM Survey WHERE surveyId=:encuestaId")
    List<Survey> loadByEncuestaIdSync(Long encuestaId);

    @Query("DELETE FROM cuestionario")
    void deleteAll();

    @Transaction
    @Query("SELECT Survey.*, er.* FROM Survey INNER JOIN surveyRecord er ON Survey.surveyId = er.surveyIdFK ")
    LiveData<List<SurveyRecords>> loadAllSurveyRecords();

    @Transaction
    @Query("SELECT Survey.*, er.* FROM Survey INNER JOIN surveyRecord er ON Survey.surveyId = er.surveyIdFK ")
    List<SurveyRecords> loadAllSurveyRecordsSync();

    @Transaction
    @Query("SELECT Survey.*, er.* FROM Survey INNER JOIN surveyRecord er ON Survey.surveyId = er.surveyIdFK " +
            "WHERE er.surveyStatus = "+ CustomConstants.TERMINADA)
    LiveData<List<SurveyRecords>> loadSurveyCompleted();

    @Transaction
    @Query("SELECT Survey.*, er.* FROM Survey INNER JOIN surveyRecord er ON Survey.surveyId = er.surveyIdFK " +
            "WHERE er.surveyStatus = "+ CustomConstants.TERMINADA)
    List<SurveyRecords> loadSurveyCompletedSync();

    @Transaction
    @Query("SELECT Survey.*, er.* FROM Survey INNER JOIN surveyRecord er ON Survey.surveyId = er.surveyIdFK " +
            "WHERE er.surveyStatus = "+ CustomConstants.PENDIENTE)
    LiveData<List<SurveyRecords>> loadSurveyPending();

    @Transaction
    @Query("SELECT Survey.*, er.* FROM Survey INNER JOIN surveyRecord er ON Survey.surveyId = er.surveyIdFK " +
            "WHERE er.surveyStatus = "+ CustomConstants.PENDIENTE)
    List<SurveyRecords> loadSurveyPendingSync();

    @Transaction
    @Query("SELECT Survey.*, er.* FROM Survey INNER JOIN surveyRecord er ON Survey.surveyId = er.surveyIdFK " +
            "WHERE er.surveyStatus = "+ CustomConstants.ENVIADA)
    LiveData<List<SurveyRecords>> loadSurveySent();

    @Transaction
    @Query("SELECT Survey.*, er.* FROM Survey INNER JOIN surveyRecord er ON Survey.surveyId = er.surveyIdFK " +
            "WHERE er.surveyStatus = "+ CustomConstants.ENVIADA)
    List<SurveyRecords> loadSurveySentSync();


}
