package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.CatEncuestaTipo;

public interface CatEncuestaTipoRepository {
    Long insert(CatEncuestaTipo catEncuestaTipo);
    Long[] insertList(List<CatEncuestaTipo> list);
    void update(CatEncuestaTipo catEncuestaTipo);
    void delete(CatEncuestaTipo catEncuestaTipo);
    void deleteAllRows();
    LiveData<List<CatEncuestaTipo>> findAllLiveData();
    List<CatEncuestaTipo> findAllList();
}
