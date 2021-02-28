package com.cysout.sousystems.surveymodule.controller;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.CatEncuestaTipo;
import com.cysout.sousystems.surveymodule.repository.CatEncuestaTipoRepository;
import com.cysout.sousystems.surveymodule.repository.impl.CatEncuestaTipoRepositoryImpl;

public class CatEncuestaTipoController extends AndroidViewModel {
    private CatEncuestaTipoRepository catEncuestaTipoRepository;
    public CatEncuestaTipoController(@NonNull Application application) {
        super(application);
        this.catEncuestaTipoRepository = new CatEncuestaTipoRepositoryImpl(application);
    }

    public Long insert(CatEncuestaTipo catEncuestaTipo){
        return this.catEncuestaTipoRepository.insert(catEncuestaTipo);
    }

    public Long[] insertList(List<CatEncuestaTipo> list){
        return this.catEncuestaTipoRepository.insertList(list);
    }

    public LiveData<List<CatEncuestaTipo>> findAllLiveData(){
        return this.catEncuestaTipoRepository.findAllLiveData();
    }

    public List<CatEncuestaTipo> findAllList(){
        return this.catEncuestaTipoRepository.findAllList();
    }
}
