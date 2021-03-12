package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.AnswerDao;
import com.cysout.sousystems.surveymodule.entity.Answer;
import com.cysout.sousystems.surveymodule.repository.AnswerRepository;
/**
 * Developed by cysout.com and sousystems.com.mx
 * Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class AnswerRepositoryImpl implements AnswerRepository {
    private AnswerDao answerDao;

    public AnswerRepositoryImpl(Application application){
        AppDatabase db = AppDatabase.getDataBase(application);
        this.answerDao = db.answerDao();
    }
    @Override
    public Long insert(Answer item) {
        return this.answerDao.insert(item);
    }

    @Override
    public Long[] insertList(List<Answer> list) {
        return this.answerDao.insertList(list);
    }

    @Override
    public void update(Answer item) {
        this.answerDao.update(item);
    }

    @Override
    public LiveData<List<Answer>> loadAll() {
        return this.answerDao.loadAll();
    }

    @Override
    public List<Answer> loadAllSync() {
        return this.answerDao.loadAllSync();
    }

    @Override
    public Answer loadRespuestaSync(Long id) {
        return this.answerDao.loadRespuestaSync(id);
    }

    @Override
    public LiveData<List<Answer>> loadByRespuestaId(Long id) {
        return this.answerDao.loadByRespuestaId(id);
    }

    @Override
    public List<Answer> loadByRespuestaIdSync(Long id) {
        return this.answerDao.loadByRespuestaIdSync(id);
    }

    @Override
    public LiveData<List<Answer>> loadByPreguntaId(Long id) {
        return this.answerDao.loadByPreguntaId(id);
    }

    @Override
    public List<Answer> loadByPreguntaIdSync(Long id) {
        return this.answerDao.loadByPreguntaIdSync(id);
    }

    @Override
    public void deleteAll() {
        this.answerDao.deleteAll();
    }
}
