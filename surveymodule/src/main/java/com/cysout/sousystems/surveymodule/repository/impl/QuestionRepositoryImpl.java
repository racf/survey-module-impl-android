package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.QuestionDao;
import com.cysout.sousystems.surveymodule.entity.Question;
import com.cysout.sousystems.surveymodule.repository.QuestionRepository;
/**
 * Developed by cysout.com and sousystems.com.mx
 * Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class QuestionRepositoryImpl implements QuestionRepository {
    private QuestionDao questionDao;

    public QuestionRepositoryImpl(Application application){
        AppDatabase db = AppDatabase.getDataBase(application);
        this.questionDao = db.questionDao();
    }
    @Override
    public Long insert(Question item) {
        return this.questionDao.insert(item);
    }

    @Override
    public Long[] insertList(List<Question> list) {
        return this.questionDao.insertList(list);
    }

    @Override
    public void update(Question item) {
        this.questionDao.update(item);
    }

    @Override
    public LiveData<List<Question>> loadAll() {
        return this.questionDao.loadAll();
    }

    @Override
    public List<Question> loadAllSync() {
        return this.questionDao.loadAllSync();
    }

    @Override
    public Question loadPreguntaSync(Long id) {
        return this.questionDao.loadPreguntaSync(id);
    }

    @Override
    public LiveData<List<Question>> loadByPreguntaId(Long id) {
        return this.questionDao.loadByPreguntaId(id);
    }

    @Override
    public List<Question> loadByPreguntaIdSync(Long id) {
        return this.questionDao.loadByPreguntaIdSync(id);
    }

    @Override
    public LiveData<List<Question>> loadByCuestionarioId(Long id) {
        return this.questionDao.loadByCuestionarioId(id);
    }

    @Override
    public List<Question> loadByCuestionarioIdSync(Long id) {
        return this.questionDao.loadByCuestionarioIdSync(id);
    }

    @Override
    public void deleteAll() {
        this.questionDao.deleteAll();
    }
}
