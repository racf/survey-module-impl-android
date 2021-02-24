package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Encuesta;
import com.cysout.sousystems.surveymodule.entity.relation.EncuestaCuestionarios;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecords;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;

@Dao
public interface EncuestaDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(Encuesta item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<Encuesta> list);

    @Update
    void update(Encuesta item);

    @Query("SELECT * FROM encuesta")
    LiveData<List<Encuesta>> loadAll();

    @Query("SELECT * FROM encuesta")
    List<Encuesta> loadAllSync();

    @Transaction
    @Query("SELECT * FROM encuesta")
    LiveData<List<EncuestaCuestionarios>> loadCuestionarios();
    @Transaction
    @Query("SELECT * FROM encuesta")
    List<EncuestaCuestionarios> loadCuestionariosSync();
    @Transaction
    @Query("SELECT * FROM encuesta LIMIT 1")
    EncuestaCuestionarios loadCuestionarioRespuestasSync();

    @Query("SELECT * FROM encuesta WHERE encuestaId = :encuestaId LIMIT 1")
    LiveData<Encuesta> loadEncuestaById(Long encuestaId);

    @Query("SELECT * FROM encuesta WHERE encuestaId = :encuestaId LIMIT 1")
    Encuesta loadEncuestaByIdSync(Long encuestaId);

    @Query("SELECT * FROM encuesta WHERE encuestaId=:encuestaId")
    LiveData<List<Encuesta>> loadByEncuestaId(Long encuestaId);

    @Query("SELECT * FROM encuesta WHERE encuestaId=:encuestaId")
    List<Encuesta> loadByEncuestaIdSync(Long encuestaId);

    @Query("DELETE FROM cuestionario")
    void deleteAll();

    @Transaction
    @Query("SELECT encuesta.*, er.* FROM encuesta INNER JOIN encuestaRegistro er ON encuesta.encuestaId = er.encuestaIdFK ")
    LiveData<List<SurveyRecords>> loadAllSurveyRecords();

    @Transaction
    @Query("SELECT encuesta.*, er.* FROM encuesta INNER JOIN encuestaRegistro er ON encuesta.encuestaId = er.encuestaIdFK ")
    List<SurveyRecords> loadAllSurveyRecordsSync();

    @Transaction
    @Query("SELECT encuesta.*, er.* FROM encuesta INNER JOIN encuestaRegistro er ON encuesta.encuestaId = er.encuestaIdFK " +
            "WHERE er.catEncuestaEstatusId = "+ CustomConstants.TERMINADA)
    LiveData<List<SurveyRecords>> loadSurveyCompleted();

    @Transaction
    @Query("SELECT encuesta.*, er.* FROM encuesta INNER JOIN encuestaRegistro er ON encuesta.encuestaId = er.encuestaIdFK " +
            "WHERE er.catEncuestaEstatusId = "+ CustomConstants.TERMINADA)
    List<SurveyRecords> loadSurveyCompletedSync();

    @Transaction
    @Query("SELECT encuesta.*, er.* FROM encuesta INNER JOIN encuestaRegistro er ON encuesta.encuestaId = er.encuestaIdFK " +
            "WHERE er.catEncuestaEstatusId = "+ CustomConstants.PENDIENTE)
    LiveData<List<SurveyRecords>> loadSurveyPending();

    @Transaction
    @Query("SELECT encuesta.*, er.* FROM encuesta INNER JOIN encuestaRegistro er ON encuesta.encuestaId = er.encuestaIdFK " +
            "WHERE er.catEncuestaEstatusId = "+ CustomConstants.PENDIENTE)
    List<SurveyRecords> loadSurveyPendingSync();

    @Transaction
    @Query("SELECT encuesta.*, er.* FROM encuesta INNER JOIN encuestaRegistro er ON encuesta.encuestaId = er.encuestaIdFK " +
            "WHERE er.catEncuestaEstatusId = "+ CustomConstants.ENVIADA)
    LiveData<List<SurveyRecords>> loadSurveySent();

    @Transaction
    @Query("SELECT encuesta.*, er.* FROM encuesta INNER JOIN encuestaRegistro er ON encuesta.encuestaId = er.encuestaIdFK " +
            "WHERE er.catEncuestaEstatusId = "+ CustomConstants.ENVIADA)
    List<SurveyRecords> loadSurveySentSync();


}
