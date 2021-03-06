package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.ShowQuestions;

public interface MostrarPreguntasRepository {
    Long insert(ShowQuestions item);
    Long[] insertList(List<ShowQuestions> list);
    void update(ShowQuestions item);
    LiveData<List<ShowQuestions>> loadAll();
    List<ShowQuestions> loadAllSync();
    LiveData<List<ShowQuestions>> loadByMostrarSiSeleccionaId(Long id);
    List<ShowQuestions> loadByMostrarSiSeleccionaIdSync(Long id);
    void deleteAll();
}
