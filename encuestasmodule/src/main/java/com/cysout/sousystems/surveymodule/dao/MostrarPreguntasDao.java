package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.MostrarPreguntas;

@Dao
public interface MostrarPreguntasDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(MostrarPreguntas item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<MostrarPreguntas> list);

    @Update
    void update(MostrarPreguntas item);

    @Query("SELECT * FROM mostrarPreguntas")
    LiveData<List<MostrarPreguntas>> loadAll();

    @Query("SELECT * FROM mostrarPreguntas")
    List<MostrarPreguntas> loadAllSync();

    @Query("SELECT * FROM mostrarPreguntas WHERE mostrarSiSeleccionaId = :id")
    LiveData<List<MostrarPreguntas>> loadByMostrarSiSeleccionaId(Long id);

    @Query("SELECT * FROM mostrarPreguntas WHERE mostrarSiSeleccionaId = :id")
    List<MostrarPreguntas> loadByMostrarSiSeleccionaIdSync(Long id);

    @Query("DELETE FROM mostrarPreguntas")
    void deleteAll();
}
