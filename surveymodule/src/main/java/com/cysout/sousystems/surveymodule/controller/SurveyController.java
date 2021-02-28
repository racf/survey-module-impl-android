package com.cysout.sousystems.surveymodule.controller;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.relation.EncuestaCuestionarios;
import com.cysout.sousystems.surveymodule.repository.SurveyRepository;
import com.cysout.sousystems.surveymodule.repository.impl.SurveyRepositoryImpl;

public class SurveyController extends AndroidViewModel {

    private SurveyRepository surveyRepository;
    public SurveyController(@NonNull Application application) {
        super(application);
        this.surveyRepository = new SurveyRepositoryImpl(application);
    }

    public Long insert(Survey survey){
        return this.surveyRepository.insert(survey);
    }

    public Long[] insertList(List<Survey> list){
        return this.surveyRepository.insertList(list);
    }

    public LiveData<List<Survey>> loadAll(){
        return this.surveyRepository.loadAll();
    }

    public List<Survey> loadAllSync(){
        return this.surveyRepository.loadAllSync();
    }

    public List<EncuestaCuestionarios> findEncuestaCuestionarios() {
        return this.surveyRepository.loadEncuestaCuestionarios();
    }
}
