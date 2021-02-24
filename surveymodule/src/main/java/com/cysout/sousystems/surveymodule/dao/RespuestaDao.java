package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Respuesta;

@Dao
public interface RespuestaDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(Respuesta item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<Respuesta> list);

    @Update
    void update(Respuesta item);

    @Query("SELECT * FROM respuesta")
    LiveData<List<Respuesta>> loadAll();

    @Query("SELECT * FROM respuesta")
    List<Respuesta> loadAllSync();

    @Query("SELECT * FROM respuesta WHERE respuestaId = :id LIMIT 1")
    Respuesta loadRespuestaSync(Long id);

    @Query("SELECT * FROM respuesta WHERE respuestaId = :id")
    LiveData<List<Respuesta>> loadByRespuestaId(Long id);

    @Query("SELECT * FROM respuesta WHERE respuestaId = :id")
    List<Respuesta> loadByRespuestaIdSync(Long id);

    @Query("SELECT * FROM respuesta WHERE preguntaId = :id")
    LiveData<List<Respuesta>> loadByPreguntaId(Long id);

    @Query("SELECT * FROM respuesta WHERE preguntaId = :id")
    List<Respuesta> loadByPreguntaIdSync(Long id);

    @Query("DELETE FROM respuesta")
    void deleteAll();
}
