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

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId AND questionId=:questionId LIMIT 1")
    LiveData<SurveyAnswer> surveyAnswerByRegistroIdAndPregId(Long surveyRecordId, Long questionId);

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId AND questionId=:questionId LIMIT 1")
    SurveyAnswer surveyAnswerByRegistroIdAndPregIdSync(Long surveyRecordId, Long questionId);

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId AND questionId=:questionId AND answer=:answerId LIMIT 1")
    LiveData<SurveyAnswer> surveyAnswerByRegtroIdAndPregIdAndRespId(Long surveyRecordId, Long questionId, String answerId);

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId AND questionId=:questionId AND answer=:answerId LIMIT 1")
    SurveyAnswer surveyAnswerByRegtroIdAndPregIdAndRespIdSync(Long surveyRecordId, Long questionId, String answerId);

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId")
    List<SurveyAnswer> loadBysurveyRecordIdSync(Long surveyRecordId);

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId")
    LiveData<List<SurveyAnswer>> loadBysurveyRecordId(Long surveyRecordId);

    @Query("SELECT * FROM surveyAnswer WHERE questionnaireId=:questionnaireId")
    LiveData<List<SurveyAnswer>> loadByCuestionarioId(Long questionnaireId);

    @Query("SELECT * FROM surveyAnswer WHERE questionnaireId=:questionnaireId")
    List<SurveyAnswer> loadByCuestionarioIdSync(Long questionnaireId);

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId AND questionnaireId=:questionnaireId LIMIT 1")
    LiveData<SurveyAnswer> surveyAnswerByRegistroIdAndCuestId(Long surveyRecordId, Long questionnaireId);

    @Query("SELECT * FROM surveyAnswer WHERE surveyRecordId =:surveyRecordId AND questionnaireId=:questionnaireId LIMIT 1")
    SurveyAnswer surveyAnswerByRegistroIdAndCuestIdSync(Long surveyRecordId, Long questionnaireId);

    @Query("DELETE FROM surveyAnswer WHERE surveyRecordId=:surveyRecordId AND questionnaireId=:questionnaireId")
    void deleteByEnctRegtIdAndCuestId(Long surveyRecordId, Long questionnaireId);

    @Query("DELETE FROM surveyAnswer WHERE surveyRecordId=:surveyRecordId AND questionId=:questionId")
    void deleteByEnctRegtIdAndPreguntaId(Long surveyRecordId, Long questionId);

    @Query("DELETE FROM surveyAnswer WHERE surveyRecordId=:surveyRecordId AND questionId=:questionId AND answer=:answerId")
    void deleteByEnctRegtIdAndPregtIdAndResp(Long surveyRecordId, Long questionId, String answerId);
}
