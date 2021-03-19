package com.cysout.sousystems.surveymodule.controller;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyQuestionnaires;
import com.cysout.sousystems.surveymodule.repository.SurveyRepository;
import com.cysout.sousystems.surveymodule.repository.impl.SurveyRepositoryImpl;
/**
* Copyright 2021 CysOut Solutions and SouSystems
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
**/
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

    public List<SurveyQuestionnaires> findEncuestaCuestionarios() {
        return this.surveyRepository.loadEncuestaCuestionarios();
    }
}
