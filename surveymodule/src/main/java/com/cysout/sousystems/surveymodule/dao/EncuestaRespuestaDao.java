package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.SurveyAnswer;

@Dao
public interface EncuestaRespuestaDao {
    @Insert()
    Long insert(SurveyAnswer item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<SurveyAnswer> list);

    @Update
    void update(SurveyAnswer item);

    @Query("DELETE FROM surveyAnswer")
    void deleteAll();

    @Query("SELECT * FROM surveyAnswer")
    LiveData<List<SurveyAnswer>> loadAll();

    @Query("SELECT * FROM surveyAnswer")
    List<SurveyAnswer> loadAllSync();

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId AND preguntaId=:preguntaId LIMIT 1")
    LiveData<SurveyAnswer> surveyAnswerByRegistroIdAndPregId(Long surveyRecordId, Long preguntaId);

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId AND preguntaId=:preguntaId LIMIT 1")
    SurveyAnswer surveyAnswerByRegistroIdAndPregIdSync(Long surveyRecordId, Long preguntaId);

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId AND preguntaId=:preguntaId AND respuesta=:respuestaId LIMIT 1")
    LiveData<SurveyAnswer> surveyAnswerByRegtroIdAndPregIdAndRespId(Long surveyRecordId, Long preguntaId, String respuestaId);

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId AND preguntaId=:preguntaId AND respuesta=:respuestaId LIMIT 1")
    SurveyAnswer surveyAnswerByRegtroIdAndPregIdAndRespIdSync(Long surveyRecordId, Long preguntaId, String respuestaId);

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId")
    List<SurveyAnswer> loadBysurveyRecordIdSync(Long surveyRecordId);

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId")
    LiveData<List<SurveyAnswer>> loadBysurveyRecordId(Long surveyRecordId);

    @Query("SELECT * FROM surveyAnswer WHERE cuestionarioId=:cuestionarioId")
    LiveData<List<SurveyAnswer>> loadByCuestionarioId(Long cuestionarioId);

    @Query("SELECT * FROM surveyAnswer WHERE cuestionarioId=:cuestionarioId")
    List<SurveyAnswer> loadByCuestionarioIdSync(Long cuestionarioId);

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId AND cuestionarioId=:cuestionarioId LIMIT 1")
    LiveData<SurveyAnswer> surveyAnswerByRegistroIdAndCuestId(Long surveyRecordId, Long cuestionarioId);

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId AND cuestionarioId=:cuestionarioId LIMIT 1")
    SurveyAnswer surveyAnswerByRegistroIdAndCuestIdSync(Long surveyRecordId, Long cuestionarioId);

    @Query("DELETE FROM surveyAnswer WHERE surveyRecordId=:surveyRecordId AND cuestionarioId=:cuestionarioId")
    void deleteByEnctRegtIdAndCuestId(Long surveyRecordId, Long cuestionarioId);

    @Query("DELETE FROM surveyAnswer WHERE surveyRecordId=:surveyRecordId AND preguntaId=:preguntaId")
    void deleteByEnctRegtIdAndPreguntaId(Long surveyRecordId, Long preguntaId);

    @Query("DELETE FROM surveyAnswer WHERE surveyRecordId=:surveyRecordId AND preguntaId=:preguntaId AND respuesta=:respuesta")
    void deleteByEnctRegtIdAndPregtIdAndResp(Long surveyRecordId, Long preguntaId, String respuesta);
}
