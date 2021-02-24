package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Pregunta;
import com.cysout.sousystems.surveymodule.entity.relation.PreguntaRespuestas;

@Dao
public interface PreguntaDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(Pregunta item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<Pregunta> list);

    @Update
    void update(Pregunta item);

    @Query("SELECT * FROM pregunta")
    LiveData<List<Pregunta>> loadAll();

    @Query("SELECT * FROM pregunta")
    List<Pregunta> loadAllSync();

    @Query("SELECT * FROM pregunta WHERE preguntaId = :id LIMIT 1")
    Pregunta loadPreguntaSync(Long id);

    @Transaction
    @Query("SELECT * FROM pregunta WHERE cuestionarioId=:id")
    PreguntaRespuestas loadPreguntaRespuestasByCuestionarioId(Long id);

    @Query("SELECT * FROM pregunta WHERE preguntaId = :id")
    LiveData<List<Pregunta>> loadByPreguntaId(Long id);

    @Query("SELECT * FROM pregunta WHERE preguntaId = :id")
    List<Pregunta> loadByPreguntaIdSync(Long id);

    @Query("SELECT * FROM pregunta WHERE cuestionarioId = :id")
    LiveData<List<Pregunta>> loadByCuestionarioId(Long id);

    @Query("SELECT * FROM pregunta WHERE cuestionarioId = :id")
    List<Pregunta> loadByCuestionarioIdSync(Long id);

    @Query("DELETE FROM pregunta")
    void deleteAll();
}
