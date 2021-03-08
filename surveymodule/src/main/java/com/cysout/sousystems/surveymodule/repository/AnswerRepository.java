package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Answer;

public interface AnswerRepository {
    Long insert(Answer item);
    Long[] insertList(List<Answer> list);
    void update(Answer item);
    LiveData<List<Answer>> loadAll();
    List<Answer> loadAllSync();
    Answer loadRespuestaSync(Long id);
    LiveData<List<Answer>> loadByRespuestaId(Long id);
    List<Answer> loadByRespuestaIdSync(Long id);
    LiveData<List<Answer>> loadByPreguntaId(Long id);
    List<Answer> loadByPreguntaIdSync(Long id);
    void deleteAll();
}
