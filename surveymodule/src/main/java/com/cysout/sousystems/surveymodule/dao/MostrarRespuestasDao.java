package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.ShowAnswers;

@Dao
public interface MostrarRespuestasDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(ShowAnswers item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<ShowAnswers> list);

    @Update
    void update(ShowAnswers item);

    @Query("SELECT * FROM showAnswers")
    LiveData<List<ShowAnswers>> loadAll();

    @Query("SELECT * FROM showAnswers")
    List<ShowAnswers> loadAllSync();

    @Query("SELECT * FROM showAnswers WHERE showSelectId = :id")
    LiveData<List<ShowAnswers>> loadByMostrarSiSeleccionaId(Long id);

    @Query("SELECT * FROM showAnswers WHERE showSelectId = :id")
    List<ShowAnswers> loadByMostrarSiSeleccionaIdSync(Long id);

    @Query("DELETE FROM showAnswers")
    void deleteAll();
}
