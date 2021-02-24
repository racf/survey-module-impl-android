package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Cuestionario;

public interface CuestionarioRepository {
    Long insert(Cuestionario cuestionario);
    Long[] insertList(List<Cuestionario> list);
    void update(Cuestionario cuestionario);
    void delete(Cuestionario cuestionario);
    void deleteAllRows();
    LiveData<List<Cuestionario>> findAllLiveData();
    List<Cuestionario> findAllList();
}
