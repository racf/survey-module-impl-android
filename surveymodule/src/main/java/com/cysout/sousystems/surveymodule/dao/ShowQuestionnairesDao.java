package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.ShowQuestionnaires;

@Dao
public interface ShowQuestionnairesDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(ShowQuestionnaires item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<ShowQuestionnaires> list);

    @Update
    void update(ShowQuestionnaires item);

    @Query("SELECT * FROM showQuestionnaires")
    LiveData<List<ShowQuestionnaires>> loadAll();

    @Query("SELECT * FROM showQuestionnaires")
    List<ShowQuestionnaires> loadAllSync();

    @Query("SELECT * FROM showQuestionnaires WHERE showSelectId = :id")
    LiveData<List<ShowQuestionnaires>> loadByMostrarSiSeleccionaId(Long id);

    @Query("SELECT * FROM showQuestionnaires WHERE showSelectId = :id")
    List<ShowQuestionnaires> loadByMostrarSiSeleccionaIdSync(Long id);

    @Query("DELETE FROM showQuestionnaires")
    void deleteAll();
}
