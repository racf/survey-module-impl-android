package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.CatEncuestaEstatus;

public interface CatEncuestaEstatusRepository {
    Long insert(CatEncuestaEstatus catEncuestaEstatus);
    Long[] insertList(List<CatEncuestaEstatus> list);
    void update(CatEncuestaEstatus catEncuestaEstatus);
    void delete(CatEncuestaEstatus catEncuestaEstatus);
    void deleteAllRows();
    LiveData<List<CatEncuestaEstatus>> findAllLiveData();
    List<CatEncuestaEstatus> findAllList();
}
