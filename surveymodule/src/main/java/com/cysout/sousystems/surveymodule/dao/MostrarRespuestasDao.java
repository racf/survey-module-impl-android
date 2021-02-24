package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.MostrarRespuestas;

@Dao
public interface MostrarRespuestasDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(MostrarRespuestas item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<MostrarRespuestas> list);

    @Update
    void update(MostrarRespuestas item);

    @Query("SELECT * FROM mostrarRespuestas")
    LiveData<List<MostrarRespuestas>> loadAll();

    @Query("SELECT * FROM mostrarRespuestas")
    List<MostrarRespuestas> loadAllSync();

    @Query("SELECT * FROM mostrarRespuestas WHERE mostrarSiSeleccionaId = :id")
    LiveData<List<MostrarRespuestas>> loadByMostrarSiSeleccionaId(Long id);

    @Query("SELECT * FROM mostrarRespuestas WHERE mostrarSiSeleccionaId = :id")
    List<MostrarRespuestas> loadByMostrarSiSeleccionaIdSync(Long id);

    @Query("DELETE FROM mostrarRespuestas")
    void deleteAll();
}
