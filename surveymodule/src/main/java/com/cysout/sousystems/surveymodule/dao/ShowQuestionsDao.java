package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.ShowQuestions;

@Dao
public interface ShowQuestionsDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(ShowQuestions item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<ShowQuestions> list);

    @Update
    void update(ShowQuestions item);

    @Query("SELECT * FROM showQuestions")
    LiveData<List<ShowQuestions>> loadAll();

    @Query("SELECT * FROM showQuestions")
    List<ShowQuestions> loadAllSync();

    @Query("SELECT * FROM showQuestions WHERE showSelectId = :id")
    LiveData<List<ShowQuestions>> loadByMostrarSiSeleccionaId(Long id);

    @Query("SELECT * FROM showQuestions WHERE showSelectId = :id")
    List<ShowQuestions> loadByMostrarSiSeleccionaIdSync(Long id);

    @Query("DELETE FROM showQuestions")
    void deleteAll();
}
