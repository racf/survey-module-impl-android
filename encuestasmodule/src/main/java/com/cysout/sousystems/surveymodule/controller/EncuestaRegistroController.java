package com.cysout.sousystems.surveymodule.controller;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.EncuestaRegistro;
import com.cysout.sousystems.surveymodule.entity.relation.EncuestaRegistroRespuestas;
import com.cysout.sousystems.surveymodule.repository.EncuestaRegistroRepository;
import com.cysout.sousystems.surveymodule.repository.impl.EncuestaRegistroRepositoryImpl;

public class EncuestaRegistroController extends AndroidViewModel {
    private EncuestaRegistroRepository encuestaRegistroRepository;

    public EncuestaRegistroController(@NonNull Application application) {
        super(application);
        this.encuestaRegistroRepository = new EncuestaRegistroRepositoryImpl(application);
    }
    public Long insert(EncuestaRegistro encuestaRegistro) {
        return this.encuestaRegistroRepository.insert(encuestaRegistro);
    }

    public Long[] insertList(List<EncuestaRegistro> list) {
        return this.encuestaRegistroRepository.insertList(list);
    }

    public void update(EncuestaRegistro encuestaRegistro) {
        this.encuestaRegistroRepository.update(encuestaRegistro);
    }

    public void deleteAll() {
        this.encuestaRegistroRepository.deleteAll();
    }

    public LiveData<List<EncuestaRegistro>> loadAll() {
        return this.encuestaRegistroRepository.loadAll();
    }

    public List<EncuestaRegistro> loadAllSync() {
        return this.encuestaRegistroRepository.loadAllSync();
    }

    public LiveData<List<EncuestaRegistro>> loadByEncuestaId(Long encuestaId) {
        return this.encuestaRegistroRepository.loadByEncuestaId(encuestaId);
    }

    public List<EncuestaRegistro> loadByEncuestaIdSync(Long encuestaId) {
        return this.encuestaRegistroRepository.loadByEncuestaIdSync(encuestaId);
    }

    public EncuestaRegistro encuestaRegistro(Long encuestaId) {
        return this.encuestaRegistroRepository.encuestaRegistro(encuestaId);
    }

    public EncuestaRegistro encuestaRegistroByEncuestaIdAndCatEncuestaEstatusId(Long encuestaId, Integer catEncuestaEstatusId) {
        return this.encuestaRegistroRepository.encuestaRegistroByEncuestaIdAndCatEncuestaEstatusId(encuestaId, catEncuestaEstatusId);
    }

    public LiveData<List<EncuestaRegistro>> loadByEncuestaIdAndCatEncuestaEstatusId(Long encuestaId, Integer catEncuestaEstatusId) {
        return this.encuestaRegistroRepository.loadByEncuestaIdAndCatEncuestaEstatusId(encuestaId, catEncuestaEstatusId);
    }

    public List<EncuestaRegistro> loadByEncuestaIdAndCatEncuestaEstatusIdSync(Long encuestaId, Integer catEncuestaEstatusId) {
        return this.encuestaRegistroRepository.loadByEncuestaIdAndCatEncuestaEstatusIdSync(encuestaId, catEncuestaEstatusId);
    }

    public LiveData<List<EncuestaRegistroRespuestas>> loadRegistrosRespuestasByEstatus(Integer catEncuestaEstatusId) {
        return this.encuestaRegistroRepository.loadRegistrosRespuestasByEstatus(catEncuestaEstatusId);
    }

    public List<EncuestaRegistroRespuestas> loadRegistrosRespuestasByEstatusSync(Integer catEncuestaEstatusId) {
        return this.encuestaRegistroRepository.loadRegistrosRespuestasByEstatusSync(catEncuestaEstatusId);
    }
}
