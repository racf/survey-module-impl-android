package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import com.cysout.sousystems.surveymodule.entity.Cuestionario;

@Dao
public interface CuestionarioDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(Cuestionario item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<Cuestionario> list);

    @Update
    void update(Cuestionario item);

    @Query("select * from cuestionario where cuestionarioId = :cuestionarioId LIMIT 1")
    Cuestionario loadCuestionarioSync(Long cuestionarioId);

    @Query("SELECT * FROM cuestionario")
    LiveData<List<Cuestionario>> loadAll();

    @Query("SELECT * FROM cuestionario")
    List<Cuestionario> loadAllSync();

    @Query("SELECT * FROM cuestionario WHERE cuestionarioId = :id")
    LiveData<List<Cuestionario>> loadByCuestionarioId(Long id);

    @Query("SELECT * FROM cuestionario WHERE cuestionarioId = :id")
    List<Cuestionario> loadByCuestionarioIdSync(Long id);

    @Query("SELECT * FROM cuestionario WHERE surveyId = :id")
    LiveData<List<Cuestionario>> loadByEncuestaId(Long id);

    @Query("SELECT * FROM cuestionario WHERE surveyId = :id")
    List<Cuestionario> loadByEncuestaIdSync(Long id);

    @Query("DELETE FROM cuestionario")
    void deleteAll();
}
