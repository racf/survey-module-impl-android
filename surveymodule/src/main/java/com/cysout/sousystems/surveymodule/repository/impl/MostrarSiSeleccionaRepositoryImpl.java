package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.ShowSelectDao;
import com.cysout.sousystems.surveymodule.entity.ShowSelect;
import com.cysout.sousystems.surveymodule.entity.relation.RelationSelectQuestionnaires;
import com.cysout.sousystems.surveymodule.entity.relation.RelationShowSelect;
import com.cysout.sousystems.surveymodule.repository.MostrarSiSeleccionaRepository;

public class MostrarSiSeleccionaRepositoryImpl implements MostrarSiSeleccionaRepository {
    private ShowSelectDao showSelectDao;

    public MostrarSiSeleccionaRepositoryImpl(Application application){
        AppDatabase db = AppDatabase.getDataBase(application);
        this.showSelectDao = db.showSelectDao();
    }
    @Override
    public Long insert(ShowSelect item) {
        return this.showSelectDao.insert(item);
    }

    @Override
    public Long[] insertList(List<ShowSelect> list) {
        return this.showSelectDao.insertList(list);
    }

    @Override
    public void update(ShowSelect item) {
        this.showSelectDao.update(item);
    }

    @Override
    public LiveData<List<ShowSelect>> loadAll() {
        return this.showSelectDao.loadAll();
    }

    @Override
    public List<ShowSelect> loadAllSync() {
        return this.showSelectDao.loadAllSync();
    }

    @Override
    public LiveData<List<ShowSelect>> loadByRespuestaId(Long id) {
        return this.showSelectDao.loadByRespuestaId(id);
    }

    @Override
    public List<ShowSelect> loadByRespuestaIdSync(Long id) {
        return this.showSelectDao.loadByRespuestaIdSync(id);
    }

    @Override
    public void deleteAll() {
        this.showSelectDao.deleteAll();
    }

    @Override
    public RelationShowSelect loadMosstrarSiSeleccionaByRespuestaId(Long id) {
        return this.showSelectDao.loadMosstrarSiSeleccionaByRespuestaId(id);
    }

    @Override
    public LiveData<List<RelationSelectQuestionnaires>> loadMostrarCuestionarios() {
        return this.showSelectDao.loadMostrarCuestionarios();
    }

    @Override
    public List<RelationSelectQuestionnaires> loadMostrarCuestionariosSync() {
        return this.showSelectDao.loadMostrarCuestionariosSync();
    }

}
