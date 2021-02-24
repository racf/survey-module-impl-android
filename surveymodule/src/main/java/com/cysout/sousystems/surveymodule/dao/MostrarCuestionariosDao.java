package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.MostrarCuestionarios;

@Dao
public interface MostrarCuestionariosDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(MostrarCuestionarios item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<MostrarCuestionarios> list);

    @Update
    void update(MostrarCuestionarios item);

    @Query("SELECT * FROM mostrarCuestionarios")
    LiveData<List<MostrarCuestionarios>> loadAll();

    @Query("SELECT * FROM mostrarCuestionarios")
    List<MostrarCuestionarios> loadAllSync();

    @Query("SELECT * FROM mostrarcuestionarios WHERE mostrarSiSeleccionaId = :id")
    LiveData<List<MostrarCuestionarios>> loadByMostrarSiSeleccionaId(Long id);

    @Query("SELECT * FROM mostrarCuestionarios WHERE mostrarSiSeleccionaId = :id")
    List<MostrarCuestionarios> loadByMostrarSiSeleccionaIdSync(Long id);

    @Query("DELETE FROM mostrarCuestionarios")
    void deleteAll();
}
