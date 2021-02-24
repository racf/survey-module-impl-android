package com.cysout.sousystems.surveymodule.service.impl;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Encuesta;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecords;
import com.cysout.sousystems.surveymodule.repository.EncuestaRegistroRepository;
import com.cysout.sousystems.surveymodule.repository.EncuestaRepository;
import com.cysout.sousystems.surveymodule.repository.impl.EncuestaRegistroRepositoryImpl;
import com.cysout.sousystems.surveymodule.repository.impl.EncuestaRepositoryImpl;
import com.cysout.sousystems.surveymodule.service.SurveyService;
import com.cysout.sousystems.surveymodule.utils.Utils;

public class SurveyServiceImpl extends AndroidViewModel implements SurveyService {
    Gson gson = new Gson();
    private EncuestaRepository encuestaRepository;
    private EncuestaRegistroRepository encuestaRegistroRepository;

    public SurveyServiceImpl(@NonNull Application application) {
        super(application);
        this.encuestaRepository = new EncuestaRepositoryImpl(application);
        this.encuestaRegistroRepository = new EncuestaRegistroRepositoryImpl(application);
    }

    @Override
    public Boolean saveSurveys(String surveys) {
        Boolean status = false;
        //Convertimos el arreglo de encuesta a objeto
        ArrayList<Encuesta> surveyArray = Utils.convertJsonToObjectSurveys(surveys);
        for ( Encuesta survey : surveyArray ) {
            String jsonStringSurvey = gson.toJson(survey);
            Encuesta encuesta = gson.fromJson(jsonStringSurvey, Encuesta.class);
            encuesta.setJson(jsonStringSurvey);
            encuesta.setFechaModificacion(Utils.dateTime());
            Encuesta encuestaReturn = encuestaRepository.loadEncuestaByIdSync(encuesta.getEncuestaId());
            if( encuestaReturn != null){
                encuesta.setFechaCreacion(encuestaReturn.getFechaCreacion());
                this.encuestaRepository.update(encuesta);
                status = true;
            }else {
                encuesta.setFechaCreacion(Utils.dateTime());
                this.encuestaRepository.insert(encuesta);
                status = true;
            }
        }
        return status;
    }

    @Override
    public LiveData<List<Encuesta>> loadAllSurveys() {
        return this.encuestaRepository.loadAll();
    }

    @Override
    public List<Encuesta> loadAllSurveysSync() {
        return this.encuestaRepository.loadAllSync();
    }

    @Override
    public LiveData<Encuesta> loadSurveyById(Long surveyId) {
        return this.encuestaRepository.loadEncuestaById(surveyId);
    }

    @Override
    public Encuesta loadSurveyByIdSync(Long surveyId) {
        return this.encuestaRepository.loadEncuestaByIdSync(surveyId);
    }

    @Override
    public LiveData<List<SurveyRecords>> loadAllSurveyRecords() {
        return this.encuestaRepository.loadAllSurveyRecords();
    }

    @Override
    public List<SurveyRecords> loadAllSurveyRecordsSync() {
        return this.encuestaRepository.loadAllSurveyRecordsSync();
    }

    @Override
    public LiveData<List<SurveyRecords>> loadSurveyCompleted() {
        return this.encuestaRepository.loadSurveyCompleted();
    }

    @Override
    public List<SurveyRecords> loadSurveyCompletedSync() {
        return this.encuestaRepository.loadSurveyCompletedSync();
    }

    @Override
    public LiveData<List<SurveyRecords>> loadSurveyPending() {
        return this.encuestaRepository.loadSurveyPending();
    }

    @Override
    public List<SurveyRecords> loadSurveyPendingSync() {
        return this.encuestaRepository.loadSurveyPendingSync();
    }

    @Override
    public LiveData<List<SurveyRecords>> loadSurveySent() {
        return this.encuestaRepository.loadSurveySent();
    }

    @Override
    public List<SurveyRecords> loadSurveySentSync() {
        return this.encuestaRepository.loadSurveySentSync();
    }


}
