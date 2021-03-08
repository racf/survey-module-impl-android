package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.AnswerShowQuestionnaires;

/**
 * Developed by cysout.com and sousystems.com.mx
 * Contact info@cysout.com or contacto@sousystems.com.mx
**/
@Dao
public interface AnswerShowQuestionnairesDao {
    @Insert()
    Long insert(AnswerShowQuestionnaires item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<AnswerShowQuestionnaires> list);

    @Update
    void update(AnswerShowQuestionnaires item);

    @Query("SELECT * FROM answerShowQuestionnaires")
    LiveData<List<AnswerShowQuestionnaires>> loadAll();

    @Query("SELECT * FROM answerShowQuestionnaires")
    List<AnswerShowQuestionnaires> loadAllSync();

    @Query("DELETE FROM answerShowQuestionnaires")
    void deleteAll();

    @Query("DELETE FROM answerShowQuestionnaires WHERE questionId=:questionId")
    void deleteByPreguntaId(Long questionId);

    @Query("DELETE FROM answerShowQuestionnaires WHERE questionnaireOriginId=:questionnaireOriginId")
    void deleteByCuestionarioOrigenId(Long questionnaireOriginId);

    @Query("DELETE FROM answerShowQuestionnaires WHERE questionId=:questionId AND answerId=:answerId")
    void deleteByPreguntaIdAndRespuestaId(Long questionId, Long answerId);
}
