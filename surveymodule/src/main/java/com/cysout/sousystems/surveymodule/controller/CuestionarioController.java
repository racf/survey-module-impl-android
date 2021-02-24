package com.cysout.sousystems.surveymodule.controller;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Cuestionario;
import com.cysout.sousystems.surveymodule.repository.CuestionarioRepository;
import com.cysout.sousystems.surveymodule.repository.impl.CuestionarioRepositoryImpl;

public class CuestionarioController extends AndroidViewModel {
    private CuestionarioRepository cuestionarioRepository;

    public CuestionarioController(@NonNull Application application) {
        super(application);
        this.cuestionarioRepository = new CuestionarioRepositoryImpl(application);
    }

    public Long insert(Cuestionario cuestionario) {
        return this.cuestionarioRepository.insert(cuestionario);
    }

    public Long[] insertList(List<Cuestionario> list) {
        return this.cuestionarioRepository.insertList(list);
    }

    public LiveData<List<Cuestionario>> findAllLiveData() {
        return this.cuestionarioRepository.findAllLiveData();
    }

    public List<Cuestionario> findAllList() {
        return this.cuestionarioRepository.findAllList();
    }
}
