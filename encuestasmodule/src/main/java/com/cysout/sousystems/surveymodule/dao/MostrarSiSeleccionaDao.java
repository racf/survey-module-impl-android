package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.MostrarSiSelecciona;
import com.cysout.sousystems.surveymodule.entity.relation.RelacionSiSelecciona;
import com.cysout.sousystems.surveymodule.entity.relation.RelacionSiSeleccionaCuestionarios;

@Dao
public interface MostrarSiSeleccionaDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(MostrarSiSelecciona item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<MostrarSiSelecciona> list);

    @Update
    void update(MostrarSiSelecciona item);

    @Query("SELECT * FROM mostrarSiSelecciona")
    LiveData<List<MostrarSiSelecciona>> loadAll();

    @Query("SELECT * FROM mostrarSiSelecciona")
    List<MostrarSiSelecciona> loadAllSync();

    @Query("SELECT * FROM mostrarSiSelecciona WHERE respuestaId = :id")
    LiveData<List<MostrarSiSelecciona>> loadByRespuestaId(Long id);

    @Query("SELECT * FROM mostrarSiSelecciona WHERE respuestaId = :id")
    List<MostrarSiSelecciona> loadByRespuestaIdSync(Long id);

    @Query("DELETE FROM mostrarSiSelecciona")
    void deleteAll();

    @Transaction
    @Query("SELECT * FROM mostrarSiSelecciona WHERE respuestaId=:id LIMIT 1")
    RelacionSiSelecciona loadMosstrarSiSeleccionaByRespuestaId(Long id);

    @Transaction
    @Query("SELECT * FROM mostrarSiSelecciona")
    LiveData<List<RelacionSiSeleccionaCuestionarios>> loadMostrarCuestionarios();

    @Transaction
    @Query("SELECT * FROM mostrarSiSelecciona")
    List<RelacionSiSeleccionaCuestionarios> loadMostrarCuestionariosSync();
}
