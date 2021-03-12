package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.ShowQuestionnairesDao;
import com.cysout.sousystems.surveymodule.entity.ShowQuestionnaires;
import com.cysout.sousystems.surveymodule.repository.ShowQuestionnairesRepository;
/**
 * Developed by cysout.com and sousystems.com.mx
 * Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class ShowQuestionnairesRepositoryImpl implements ShowQuestionnairesRepository {
    private ShowQuestionnairesDao showQuestionnairesDao;

    public ShowQuestionnairesRepositoryImpl(Application application){
        AppDatabase db = AppDatabase.getDataBase(application);
        this.showQuestionnairesDao = db.showQuestionnairesDao();
    }
    @Override
    public Long insert(ShowQuestionnaires item) {
        return this.showQuestionnairesDao.insert(item);
    }

    @Override
    public Long[] insertList(List<ShowQuestionnaires> list) {
        return this.showQuestionnairesDao.insertList(list);
    }

    @Override
    public void update(ShowQuestionnaires item) {
        this.showQuestionnairesDao.update(item);
    }

    @Override
    public LiveData<List<ShowQuestionnaires>> loadAll() {
        return this.showQuestionnairesDao.loadAll();
    }

    @Override
    public List<ShowQuestionnaires> loadAllSync() {
        return this.showQuestionnairesDao.loadAllSync();
    }

    @Override
    public LiveData<List<ShowQuestionnaires>> loadByMostrarSiSeleccionaId(Long id) {
        return this.showQuestionnairesDao.loadByMostrarSiSeleccionaId(id);
    }

    @Override
    public List<ShowQuestionnaires> loadByMostrarSiSeleccionaIdSync(Long id) {
        return this.showQuestionnairesDao.loadByMostrarSiSeleccionaIdSync(id);
    }

    @Override
    public void deleteAll() {
        this.showQuestionnairesDao.deleteAll();
    }
}
