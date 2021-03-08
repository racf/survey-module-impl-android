package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.ShowQuestionnaires;
/**
 * Developed by cysout.com and sousystems.com.mx
 * Contact info@cysout.com or contacto@sousystems.com.mx
**/
public interface ShowQuestionnairesRepository {
    Long insert(ShowQuestionnaires item);
    Long[] insertList(List<ShowQuestionnaires> list);
    void update(ShowQuestionnaires item);
    LiveData<List<ShowQuestionnaires>> loadAll();
    List<ShowQuestionnaires> loadAllSync();
    LiveData<List<ShowQuestionnaires>> loadByMostrarSiSeleccionaId(Long id);
    List<ShowQuestionnaires> loadByMostrarSiSeleccionaIdSync(Long id);
    void deleteAll();
}
