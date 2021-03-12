package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.ShowAnswersDao;
import com.cysout.sousystems.surveymodule.entity.ShowAnswers;
import com.cysout.sousystems.surveymodule.repository.ShowAnswersRepository;
/**
 * Developed by cysout.com and sousystems.com.mx
 * Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class ShowAnswersRepositoryImpl implements ShowAnswersRepository {
    private ShowAnswersDao showAnswersDao;

    public ShowAnswersRepositoryImpl(Application application){
        AppDatabase db = AppDatabase.getDataBase(application);
        this.showAnswersDao = db.showAnswersDao();
    }

    @Override
    public Long insert(ShowAnswers item) {
        return this.showAnswersDao.insert(item);
    }

    @Override
    public Long[] insertList(List<ShowAnswers> list) {
        return this.showAnswersDao.insertList(list);
    }

    @Override
    public void update(ShowAnswers item) {
        this.showAnswersDao.update(item);
    }

    @Override
    public LiveData<List<ShowAnswers>> loadAll() {
        return this.showAnswersDao.loadAll();
    }

    @Override
    public List<ShowAnswers> loadAllSync() {
        return this.showAnswersDao.loadAllSync();
    }

    @Override
    public LiveData<List<ShowAnswers>> loadByMostrarSiSeleccionaId(Long id) {
        return this.showAnswersDao.loadByMostrarSiSeleccionaId(id);
    }

    @Override
    public List<ShowAnswers> loadByMostrarSiSeleccionaIdSync(Long id) {
        return this.showAnswersDao.loadByMostrarSiSeleccionaIdSync(id);
    }

    @Override
    public void deleteAll() {
        this.showAnswersDao.deleteAll();
    }
}
