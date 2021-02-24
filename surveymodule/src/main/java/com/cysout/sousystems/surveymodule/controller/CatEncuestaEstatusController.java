package com.cysout.sousystems.surveymodule.controller;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.CatEncuestaEstatus;
import com.cysout.sousystems.surveymodule.repository.CatEncuestaEstatusRepository;
import com.cysout.sousystems.surveymodule.repository.impl.CatEncuestaEstatusRepositoryImpl;

public class CatEncuestaEstatusController extends AndroidViewModel {
    private CatEncuestaEstatusRepository catEncuestaEstatusRepository;
    public CatEncuestaEstatusController(@NonNull Application application) {
        super(application);
        this.catEncuestaEstatusRepository = new CatEncuestaEstatusRepositoryImpl(application);
    }
    public Long insert(CatEncuestaEstatus catEncuestaEstatus) {
        return this.catEncuestaEstatusRepository.insert(catEncuestaEstatus);
    }
    public Long[] insertList(List<CatEncuestaEstatus> list) {
        return this.catEncuestaEstatusRepository.insertList(list);
    }
    public LiveData<List<CatEncuestaEstatus>> findAllLiveData() {
        return this.findAllLiveData();
    }

    public List<CatEncuestaEstatus> findAllList() {
        return this.catEncuestaEstatusRepository.findAllList();
    }
}
