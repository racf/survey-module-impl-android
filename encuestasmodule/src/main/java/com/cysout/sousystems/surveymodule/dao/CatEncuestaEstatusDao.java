package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.CatEncuestaEstatus;
import com.cysout.sousystems.surveymodule.entity.CatEncuestaTipo;

@Dao
public interface CatEncuestaEstatusDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(CatEncuestaEstatus item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<CatEncuestaEstatus> list);

    @Update
    void update(CatEncuestaTipo item);

    @Query("SELECT * FROM catEncuestaEstatus")
    LiveData<List<CatEncuestaEstatus>> loadAll();

    @Query("SELECT * FROM catEncuestaEstatus")
    List<CatEncuestaEstatus> loadAllSync();
}
