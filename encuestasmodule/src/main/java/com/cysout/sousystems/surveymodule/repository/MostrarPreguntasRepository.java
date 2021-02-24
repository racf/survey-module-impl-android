package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.MostrarPreguntas;

public interface MostrarPreguntasRepository {
    Long insert(MostrarPreguntas item);
    Long[] insertList(List<MostrarPreguntas> list);
    void update(MostrarPreguntas item);
    LiveData<List<MostrarPreguntas>> loadAll();
    List<MostrarPreguntas> loadAllSync();
    LiveData<List<MostrarPreguntas>> loadByMostrarSiSeleccionaId(Long id);
    List<MostrarPreguntas> loadByMostrarSiSeleccionaIdSync(Long id);
    void deleteAll();
}
