package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Question;
import com.cysout.sousystems.surveymodule.entity.relation.QuestionAnswers;

@Dao
public interface PreguntaDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(Question item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<Question> list);

    @Update
    void update(Question item);

    @Query("SELECT * FROM question")
    LiveData<List<Question>> loadAll();

    @Query("SELECT * FROM question")
    List<Question> loadAllSync();

    @Query("SELECT * FROM question WHERE questionId = :id LIMIT 1")
    Question loadPreguntaSync(Long id);

    @Transaction
    @Query("SELECT * FROM question WHERE questionnaireId=:id")
    QuestionAnswers loadPreguntaRespuestasByCuestionarioId(Long id);

    @Query("SELECT * FROM question WHERE questionId = :id")
    LiveData<List<Question>> loadByPreguntaId(Long id);

    @Query("SELECT * FROM question WHERE questionId = :id")
    List<Question> loadByPreguntaIdSync(Long id);

    @Query("SELECT * FROM question WHERE questionnaireId = :id")
    LiveData<List<Question>> loadByCuestionarioId(Long id);

    @Query("SELECT * FROM question WHERE questionnaireId = :id")
    List<Question> loadByCuestionarioIdSync(Long id);

    @Query("DELETE FROM question")
    void deleteAll();
}
