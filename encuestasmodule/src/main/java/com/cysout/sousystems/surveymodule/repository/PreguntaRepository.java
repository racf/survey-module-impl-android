package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Pregunta;

public interface PreguntaRepository {
    Long insert(Pregunta item);
    Long[] insertList(List<Pregunta> list);
    void update(Pregunta item);
    LiveData<List<Pregunta>> loadAll();
    List<Pregunta> loadAllSync();
    Pregunta loadPreguntaSync(Long id);
    LiveData<List<Pregunta>> loadByPreguntaId(Long id);
    List<Pregunta> loadByPreguntaIdSync(Long id);
    LiveData<List<Pregunta>> loadByCuestionarioId(Long id);
    List<Pregunta> loadByCuestionarioIdSync(Long id);
    void deleteAll();
}
