package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.ShowAnswers;

public interface MostrarRespuestasRepository {
    Long insert(ShowAnswers item);
    Long[] insertList(List<ShowAnswers> list);
    void update(ShowAnswers item);
    LiveData<List<ShowAnswers>> loadAll();
    List<ShowAnswers> loadAllSync();
    LiveData<List<ShowAnswers>> loadByMostrarSiSeleccionaId(Long id);
    List<ShowAnswers> loadByMostrarSiSeleccionaIdSync(Long id);
    void deleteAll();
}
