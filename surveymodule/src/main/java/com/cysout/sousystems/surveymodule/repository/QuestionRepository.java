package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Question;
/**
 * Developed by cysout.com and sousystems.com.mx
 * Contact info@cysout.com or contacto@sousystems.com.mx
**/
public interface QuestionRepository {
    Long insert(Question item);
    Long[] insertList(List<Question> list);
    void update(Question item);
    LiveData<List<Question>> loadAll();
    List<Question> loadAllSync();
    Question loadPreguntaSync(Long id);
    LiveData<List<Question>> loadByPreguntaId(Long id);
    List<Question> loadByPreguntaIdSync(Long id);
    LiveData<List<Question>> loadByCuestionarioId(Long id);
    List<Question> loadByCuestionarioIdSync(Long id);
    void deleteAll();
}
