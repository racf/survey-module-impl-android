package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.MostrarSiSelecciona;
import com.cysout.sousystems.surveymodule.entity.relation.RelacionSiSelecciona;
import com.cysout.sousystems.surveymodule.entity.relation.RelacionSiSeleccionaCuestionarios;

public interface MostrarSiSeleccionaRepository {
    Long insert(MostrarSiSelecciona item);
    Long[] insertList(List<MostrarSiSelecciona> list);
    void update(MostrarSiSelecciona item);
    LiveData<List<MostrarSiSelecciona>> loadAll();
    List<MostrarSiSelecciona> loadAllSync();
    LiveData<List<MostrarSiSelecciona>> loadByRespuestaId(Long id);
    List<MostrarSiSelecciona> loadByRespuestaIdSync(Long id);
    void deleteAll();
    RelacionSiSelecciona loadMosstrarSiSeleccionaByRespuestaId(Long id);
    LiveData<List<RelacionSiSeleccionaCuestionarios>> loadMostrarCuestionarios();
    List<RelacionSiSeleccionaCuestionarios> loadMostrarCuestionariosSync();
}
