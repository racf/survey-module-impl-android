package com.cysout.sousystems.surveymodule.service.impl;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cysout.sousystems.surveymodule.utils.CustomConstants;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import com.cysout.sousystems.surveymodule.entity.Encuesta;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecords;
import com.cysout.sousystems.surveymodule.repository.EncuestaRegistroRepository;
import com.cysout.sousystems.surveymodule.repository.EncuestaRepository;
import com.cysout.sousystems.surveymodule.repository.impl.EncuestaRegistroRepositoryImpl;
import com.cysout.sousystems.surveymodule.repository.impl.EncuestaRepositoryImpl;
import com.cysout.sousystems.surveymodule.service.SurveyService;
import com.cysout.sousystems.surveymodule.utils.Utils;


/**
 *Developed by cysout.com and sousystems.com.mx
 *Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class SurveyServiceImpl extends AndroidViewModel implements SurveyService {
    Gson gson = new Gson();
    private EncuestaRepository encuestaRepository;
    private EncuestaRegistroRepository encuestaRegistroRepository;

    public SurveyServiceImpl(@NonNull Application application) {
        super(application);
        this.encuestaRepository = new EncuestaRepositoryImpl(application);
        this.encuestaRegistroRepository = new EncuestaRegistroRepositoryImpl(application);
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
            ArrayList<Encuesta> surveyArray = Utils.convertJsonToObjectSurveys(surveys);
            //Recorremos cada una de las encuestas
            for ( Encuesta survey : surveyArray ) {
                String jsonStringSurvey = gson.toJson(survey);
                Encuesta encuesta = gson.fromJson(jsonStringSurvey, Encuesta.class);
                encuesta.setJson(jsonStringSurvey);
                Encuesta surveyReturn = encuestaRepository.loadEncuestaByIdSync(encuesta.getEncuestaId());
                //Validamos si se encuentra la encuesta en la DB local
                if( surveyReturn != null){
                    //Si se encuentra, validamos que el versionCode sea diferente para actualizarlo
                    if(surveyReturn.getVersionCode() != survey.getVersionCode()){
                        encuesta.setFechaCreacion(surveyReturn.getFechaCreacion());
                        encuesta.setFechaModificacion(Utils.dateTime());
                        this.encuestaRepository.update(encuesta);
                    }
                }else {
                    encuesta.setFechaCreacion(Utils.dateTime());
                    this.encuestaRepository.insert(encuesta);
                }
                status.set(true);
            }

        });
        return status.get();
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


