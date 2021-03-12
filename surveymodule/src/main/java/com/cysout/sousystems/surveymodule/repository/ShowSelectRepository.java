package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.ShowSelect;
import com.cysout.sousystems.surveymodule.entity.relation.RelationShowSelect;
import com.cysout.sousystems.surveymodule.entity.relation.RelationSelectQuestionnaires;
/**
 * Developed by cysout.com and sousystems.com.mx
 * Contact info@cysout.com or contacto@sousystems.com.mx
**/
public interface ShowSelectRepository {
    Long insert(ShowSelect item);
    Long[] insertList(List<ShowSelect> list);
    void update(ShowSelect item);
    LiveData<List<ShowSelect>> loadAll();
    List<ShowSelect> loadAllSync();
    LiveData<List<ShowSelect>> loadByRespuestaId(Long id);
    List<ShowSelect> loadByRespuestaIdSync(Long id);
    void deleteAll();
    RelationShowSelect loadMosstrarSiSeleccionaByRespuestaId(Long id);
    LiveData<List<RelationSelectQuestionnaires>> loadMostrarCuestionarios();
    List<RelationSelectQuestionnaires> loadMostrarCuestionariosSync();
}
