package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.MostrarRespuestas;

public interface MostrarRespuestasRepository {
    Long insert(MostrarRespuestas item);
    Long[] insertList(List<MostrarRespuestas> list);
    void update(MostrarRespuestas item);
    LiveData<List<MostrarRespuestas>> loadAll();
    List<MostrarRespuestas> loadAllSync();
    LiveData<List<MostrarRespuestas>> loadByMostrarSiSeleccionaId(Long id);
    List<MostrarRespuestas> loadByMostrarSiSeleccionaIdSync(Long id);
    void deleteAll();
}
