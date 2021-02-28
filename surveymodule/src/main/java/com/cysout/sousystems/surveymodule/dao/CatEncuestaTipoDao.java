package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.CatEncuestaTipo;

@Dao
public interface CatEncuestaTipoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(CatEncuestaTipo item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<CatEncuestaTipo> list);

    @Update
    void update(CatEncuestaTipo item);

    @Query("SELECT * FROM catEncuestaTipo")
    LiveData<List<CatEncuestaTipo>> loadAll();

    @Query("SELECT * FROM catEncuestaTipo")
    List<CatEncuestaTipo> loadAllSync();
}
