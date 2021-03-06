package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.ShowSelect;
import com.cysout.sousystems.surveymodule.entity.relation.RelacionSiSelecciona;
import com.cysout.sousystems.surveymodule.entity.relation.RelacionSiSeleccionaCuestionarios;

@Dao
public interface MostrarSiSeleccionaDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(ShowSelect item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<ShowSelect> list);

    @Update
    void update(ShowSelect item);

    @Query("SELECT * FROM showSelect")
    LiveData<List<ShowSelect>> loadAll();

    @Query("SELECT * FROM showSelect")
    List<ShowSelect> loadAllSync();

    @Query("SELECT * FROM showSelect WHERE answerId = :id")
    LiveData<List<ShowSelect>> loadByRespuestaId(Long id);

    @Query("SELECT * FROM showSelect WHERE answerId = :id")
    List<ShowSelect> loadByRespuestaIdSync(Long id);

    @Query("DELETE FROM showSelect")
    void deleteAll();

    @Transaction
    @Query("SELECT * FROM showSelect WHERE answerId=:id LIMIT 1")
    RelacionSiSelecciona loadMosstrarSiSeleccionaByRespuestaId(Long id);

    @Transaction
    @Query("SELECT * FROM showSelect")
    LiveData<List<RelacionSiSeleccionaCuestionarios>> loadMostrarCuestionarios();

    @Transaction
    @Query("SELECT * FROM showSelect")
    List<RelacionSiSeleccionaCuestionarios> loadMostrarCuestionariosSync();
}
