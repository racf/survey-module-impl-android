package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.MostrarCuestionarios;

public interface MostrarCuestionariosRepository {
    Long insert(MostrarCuestionarios item);
    Long[] insertList(List<MostrarCuestionarios> list);
    void update(MostrarCuestionarios item);
    LiveData<List<MostrarCuestionarios>> loadAll();
    List<MostrarCuestionarios> loadAllSync();
    LiveData<List<MostrarCuestionarios>> loadByMostrarSiSeleccionaId(Long id);
    List<MostrarCuestionarios> loadByMostrarSiSeleccionaIdSync(Long id);
    void deleteAll();
}
