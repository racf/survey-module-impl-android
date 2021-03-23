package com.cysout.sousystems.surveymodule.service.impl;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
import com.cysout.sousystems.surveymodule.view.QuestionaryActivity;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecords;
import com.cysout.sousystems.surveymodule.repository.SurveyRecordRepository;
import com.cysout.sousystems.surveymodule.repository.SurveyRepository;
import com.cysout.sousystems.surveymodule.repository.impl.SurveyRecordRepositoryImpl;
import com.cysout.sousystems.surveymodule.repository.impl.SurveyRepositoryImpl;
import com.cysout.sousystems.surveymodule.service.SurveyService;
import com.cysout.sousystems.surveymodule.utils.Utils;


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
public class SurveyServiceImpl extends AndroidViewModel implements SurveyService {
    Gson gson = new Gson();
    private SurveyRepository surveyRepository;
    private SurveyRecordRepository surveyRecordRepository;

    public SurveyServiceImpl(@NonNull Application application) {
        super(application);
        this.surveyRepository = new SurveyRepositoryImpl(application);
        this.surveyRecordRepository = new SurveyRecordRepositoryImpl(application);
    }

    /**
     * Metodo para guardar la información del json de encuestas
     * @param surveys json de encuestas
     * @return true si todo es correcto
     */
    @Override
    public Boolean saveSurveys(String surveys) {
        //final ExecutorService exService = Executors.newSingleThreadExecutor();
        //return true;
        AtomicReference<Boolean> status = new AtomicReference<>(false);
        Executors.newSingleThreadExecutor().execute(() -> {
            //Convertimos el arreglo de encuesta a objeto
            ArrayList<Survey> surveyArray = Utils.convertJsonToObjectSurveys(surveys);
            //Recorremos cada una de las encuestas
            for ( Survey survey : surveyArray ) {
                String jsonStringSurvey = gson.toJson(survey);
                Survey encuesta = gson.fromJson(jsonStringSurvey, Survey.class);
                encuesta.setJson(jsonStringSurvey);
                Survey surveyReturn = surveyRepository.loadSurveyByIdSync(encuesta.getSurveyId());
                //Validamos si se encuentra la encuesta en la DB local
                if( surveyReturn != null){
                    //Si se encuentra, validamos que el versionCode sea diferente para actualizarlo
                    if(surveyReturn.getVersionCode() != survey.getVersionCode()){
                        encuesta.setDateCreated(surveyReturn.getDateCreated());
                        encuesta.setDateModified(Utils.dateTime());
                        this.surveyRepository.update(encuesta);
                    }
                }else {
                    encuesta.setDateCreated(Utils.dateTime());
                    this.surveyRepository.insert(encuesta);
                }
                status.set(true);
            }

        });
        return status.get();
    }

    @Override
    public LiveData<List<Survey>> loadAllSurveys() {
        return this.surveyRepository.loadAll();
    }

    @Override
    public List<Survey> loadAllSurveysSync() {
        return this.surveyRepository.loadAllSync();
    }

    @Override
    public LiveData<Survey> loadSurveyById(Long surveyId) {
        return this.surveyRepository.loadSurveyById(surveyId);
    }

    @Override
    public Survey loadSurveyByIdSync(Long surveyId) {
        return this.surveyRepository.loadSurveyByIdSync(surveyId);
    }

    @Override
    public LiveData<List<SurveyRecords>> loadAllSurveyRecords() {
        return this.surveyRepository.loadAllSurveyRecords();
    }

    @Override
    public List<SurveyRecords> loadAllSurveyRecordsSync() {
        return this.surveyRepository.loadAllSurveyRecordsSync();
    }

    @Override
    public LiveData<List<SurveyRecords>> loadSurveyFinished() {
        return this.surveyRepository.loadSurveyCompleted();
    }

    @Override
    public List<SurveyRecords> loadSurveyFinishedSync() {
        return this.surveyRepository.loadSurveyCompletedSync();
    }

    @Override
    public LiveData<List<SurveyRecords>> loadSurveyPending() {
        return this.surveyRepository.loadSurveyPending();
    }

    @Override
    public List<SurveyRecords> loadSurveyPendingSync() {
        return this.surveyRepository.loadSurveyPendingSync();
    }

    @Override
    public LiveData<List<SurveyRecords>> loadSurveyUploaded() {
        return this.surveyRepository.loadSurveySent();
    }

    @Override
    public List<SurveyRecords> loadSurveyUploadedSync() {
        return this.surveyRepository.loadSurveySentSync();
    }

    @Override
    public void startNewSurvey(Context context, Activity activity, Survey survey) {
        Intent intent = new Intent(context, QuestionaryActivity.class);
        intent.putExtra(CustomConstants.SURVEY_KEY, survey);
        activity.startActivityForResult(intent, CustomConstants.QUESTIONNAIRES_REQUEST);
    }
}


