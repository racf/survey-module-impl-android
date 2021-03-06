package com.cysout.sousystems.surveymodule.repository.impl;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.MostrarSiSeleccionaDao;
import com.cysout.sousystems.surveymodule.entity.ShowSelect;
import com.cysout.sousystems.surveymodule.entity.relation.RelacionSiSelecciona;
import com.cysout.sousystems.surveymodule.entity.relation.RelacionSiSeleccionaCuestionarios;
import com.cysout.sousystems.surveymodule.repository.MostrarSiSeleccionaRepository;

public class MostrarSiSeleccionaRepositoryImpl implements MostrarSiSeleccionaRepository {
    private MostrarSiSeleccionaDao mostrarSiSeleccionaDao;

    public MostrarSiSeleccionaRepositoryImpl(Application application){
        AppDatabase db = AppDatabase.getDataBase(application);
        this.mostrarSiSeleccionaDao = db.mostrarSiSeleccionaDao();
    }
    @Override
    public Long insert(ShowSelect item) {
        return this.mostrarSiSeleccionaDao.insert(item);
    }

    @Override
    public Long[] insertList(List<ShowSelect> list) {
        return this.mostrarSiSeleccionaDao.insertList(list);
    }

    @Override
    public void update(ShowSelect item) {
        this.mostrarSiSeleccionaDao.update(item);
    }

    @Override
    public LiveData<List<ShowSelect>> loadAll() {
        return this.mostrarSiSeleccionaDao.loadAll();
    }

    @Override
    public List<ShowSelect> loadAllSync() {
        return this.mostrarSiSeleccionaDao.loadAllSync();
    }

    @Override
    public LiveData<List<ShowSelect>> loadByRespuestaId(Long id) {
        return this.mostrarSiSeleccionaDao.loadByRespuestaId(id);
    }

    @Override
    public List<ShowSelect> loadByRespuestaIdSync(Long id) {
        return this.mostrarSiSeleccionaDao.loadByRespuestaIdSync(id);
    }

    @Override
    public void deleteAll() {
        this.mostrarSiSeleccionaDao.deleteAll();
    }

    @Override
    public RelacionSiSelecciona loadMosstrarSiSeleccionaByRespuestaId(Long id) {
        return this.mostrarSiSeleccionaDao.loadMosstrarSiSeleccionaByRespuestaId(id);
    }

    @Override
    public LiveData<List<RelacionSiSeleccionaCuestionarios>> loadMostrarCuestionarios() {
        return this.mostrarSiSeleccionaDao.loadMostrarCuestionarios();
    }

    @Override
    public List<RelacionSiSeleccionaCuestionarios> loadMostrarCuestionariosSync() {
        return this.mostrarSiSeleccionaDao.loadMostrarCuestionariosSync();
    }

}
