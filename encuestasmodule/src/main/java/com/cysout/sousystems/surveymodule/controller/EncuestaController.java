package com.cysout.sousystems.surveymodule.controller;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Encuesta;
import com.cysout.sousystems.surveymodule.entity.relation.EncuestaCuestionarios;
import com.cysout.sousystems.surveymodule.repository.EncuestaRepository;
import com.cysout.sousystems.surveymodule.repository.impl.EncuestaRepositoryImpl;

public class EncuestaController extends AndroidViewModel {

    private EncuestaRepository encuestaRepository;
    public EncuestaController(@NonNull Application application) {
        super(application);
        this.encuestaRepository = new EncuestaRepositoryImpl(application);
    }

    public Long insert(Encuesta encuesta){
        return this.encuestaRepository.insert(encuesta);
    }

    public Long[] insertList(List<Encuesta> list){
        return this.encuestaRepository.insertList(list);
    }

    public LiveData<List<Encuesta>> loadAll(){
        return this.encuestaRepository.loadAll();
    }

    public List<Encuesta> loadAllSync(){
        return this.encuestaRepository.loadAllSync();
    }

    public List<EncuestaCuestionarios> findEncuestaCuestionarios() {
        return this.encuestaRepository.loadEncuestaCuestionarios();
    }
}
