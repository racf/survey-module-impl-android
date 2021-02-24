package com.cysout.sousystems.surveymodule.controller;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.MostrarSiSelecciona;
import com.cysout.sousystems.surveymodule.entity.relation.RelacionSiSelecciona;
import com.cysout.sousystems.surveymodule.entity.relation.RelacionSiSeleccionaCuestionarios;
import com.cysout.sousystems.surveymodule.repository.MostrarSiSeleccionaRepository;
import com.cysout.sousystems.surveymodule.repository.impl.MostrarSiSeleccionaRepositoryImpl;

public class MostrarSiSeleccionaController extends AndroidViewModel {
    private MostrarSiSeleccionaRepository mostrarSiSeleccionaRepository;


    public MostrarSiSeleccionaController(@NonNull Application application) {
        super(application);
        this.mostrarSiSeleccionaRepository = new MostrarSiSeleccionaRepositoryImpl(application);
    }

    public Long insert(MostrarSiSelecciona item) {
        return this.mostrarSiSeleccionaRepository.insert(item);
    }

    public Long[] insertList(List<MostrarSiSelecciona> list) {
        return this.mostrarSiSeleccionaRepository.insertList(list);
    }


    public void update(MostrarSiSelecciona item) {
        this.mostrarSiSeleccionaRepository.update(item);
    }


    public LiveData<List<MostrarSiSelecciona>> loadAll() {
        return this.mostrarSiSeleccionaRepository.loadAll();
    }

    public List<MostrarSiSelecciona> loadAllSync() {
        return this.mostrarSiSeleccionaRepository.loadAllSync();
    }

    public LiveData<List<MostrarSiSelecciona>> loadByRespuestaId(Long id) {
        return this.mostrarSiSeleccionaRepository.loadByRespuestaId(id);
    }

    public List<MostrarSiSelecciona> loadByRespuestaIdSync(Long id) {
        return this.mostrarSiSeleccionaRepository.loadByRespuestaIdSync(id);
    }

    public void deleteAll() {
        this.mostrarSiSeleccionaRepository.deleteAll();
    }

    public RelacionSiSelecciona loadMosstrarSiSeleccionaByRespuestaId(Long id) {
        return this.mostrarSiSeleccionaRepository.loadMosstrarSiSeleccionaByRespuestaId(id);
    }

    public LiveData<List<RelacionSiSeleccionaCuestionarios>> loadMostrarCuestionarios() {
        return this.mostrarSiSeleccionaRepository.loadMostrarCuestionarios();
    }

    public List<RelacionSiSeleccionaCuestionarios> loadMostrarCuestionariosSync() {
        return this.mostrarSiSeleccionaRepository.loadMostrarCuestionariosSync();
    }
}
