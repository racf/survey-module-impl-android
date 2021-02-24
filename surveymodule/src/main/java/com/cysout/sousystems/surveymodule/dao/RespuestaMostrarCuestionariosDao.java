package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.RespuestaMostrarCuestionarios;

@Dao
public interface RespuestaMostrarCuestionariosDao {
    @Insert()
    Long insert(RespuestaMostrarCuestionarios item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<RespuestaMostrarCuestionarios> list);

    @Update
    void update(RespuestaMostrarCuestionarios item);

    @Query("SELECT * FROM respuestaMostrarCuestionarios")
    LiveData<List<RespuestaMostrarCuestionarios>> loadAll();

    @Query("SELECT * FROM respuestaMostrarCuestionarios")
    List<RespuestaMostrarCuestionarios> loadAllSync();

    @Query("DELETE FROM respuestaMostrarCuestionarios")
    void deleteAll();

    @Query("DELETE FROM respuestaMostrarCuestionarios WHERE preguntaId=:preguntaId")
    void deleteByPreguntaId(Long preguntaId);

    @Query("DELETE FROM respuestaMostrarCuestionarios WHERE cuestionarioOrigenId=:cuestionarioOrigenId")
    void deleteByCuestionarioOrigenId(Long cuestionarioOrigenId);

    @Query("DELETE FROM respuestaMostrarCuestionarios WHERE preguntaId=:preguntaId AND respuestaId=:respuestaId")
    void deleteByPreguntaIdAndRespuestaId(Long preguntaId, Long respuestaId);
}
