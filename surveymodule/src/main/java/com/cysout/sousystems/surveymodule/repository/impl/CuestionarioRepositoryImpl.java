package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.QuestionnaireDao;
import com.cysout.sousystems.surveymodule.entity.Questionnaire;
import com.cysout.sousystems.surveymodule.repository.CuestionarioRepository;

public class CuestionarioRepositoryImpl implements CuestionarioRepository {
    private QuestionnaireDao questionnaireDao;

    public CuestionarioRepositoryImpl(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        this.questionnaireDao = db.questionnaireDao();
    }

    @Override
    public Long insert(Questionnaire questionnaire) {
        return this.questionnaireDao.insert(questionnaire);
    }

    @Override
    public Long[] insertList(List<Questionnaire> list) {
        return this.questionnaireDao.insertList(list);
    }

    @Override
    public void update(Questionnaire questionnaire) {

    }

    @Override
    public void delete(Questionnaire questionnaire) {

    }

    @Override
    public void deleteAllRows() {

    }

    @Override
    public LiveData<List<Questionnaire>> findAllLiveData() {
        return this.questionnaireDao.loadAll();
    }

    @Override
    public List<Questionnaire> findAllList() {
        return this.questionnaireDao.loadAllSync();
    }
}
