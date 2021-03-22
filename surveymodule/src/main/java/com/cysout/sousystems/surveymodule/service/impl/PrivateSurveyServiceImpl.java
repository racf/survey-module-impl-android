package com.cysout.sousystems.surveymodule.service.impl;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.SurveyRecordDao;
import com.cysout.sousystems.surveymodule.dao.SurveyAnswerDao;
import com.cysout.sousystems.surveymodule.entity.Question;
import com.cysout.sousystems.surveymodule.entity.Questionnaire;
import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.SurveyAnswer;
import com.cysout.sousystems.surveymodule.entity.SurveyRecord;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecordAnswers;
import com.cysout.sousystems.surveymodule.service.PrivateSurveyService;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
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
public class PrivateSurveyServiceImpl extends AndroidViewModel implements PrivateSurveyService {
    private SurveyRecordDao surveyRecordDao;
    private SurveyAnswerDao surveyAnswerDao;

    public PrivateSurveyServiceImpl(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDataBase(application);
        this.surveyRecordDao = db.surveyRecordDao();
        this.surveyAnswerDao = db.surveyAnswerDao();
    }

    @Override
    public Long surveyRecord(Survey survey, Integer surveyStatus, String startDate, String endDate) {
        SurveyRecord surveyRecord = Utils.getSurveyRecord(survey, surveyStatus, startDate, endDate);
        return this.surveyRecordDao.insert(surveyRecord);
    }

    @Override
    public Long surveyAnswer(Survey survey, Questionnaire questionnaire, Question question, String answer, Long surveyRecordId) {
        Long surveyAnswerId = 0L;
        SurveyAnswer surveyAnswer = Utils.getSurveyAnswer(questionnaire, question, answer, surveyRecordId);
        Log.i(CustomConstants.TAG_LOG, "PrivateSurveyServiceImpl - surveyAnswer");
        Log.i(CustomConstants.TAG_LOG, surveyAnswer.toString());
        //Validamos las preguntas que solo pueden tener una sola respuesta (text, radiogroup and select)
        if( surveyAnswer.getType().equalsIgnoreCase(CustomConstants.TEXT) || surveyAnswer.getType().equalsIgnoreCase(CustomConstants.SELECT)
                || surveyAnswer.getType().equalsIgnoreCase(CustomConstants.RADIOGROUP)){
            SurveyAnswer surveyAnswerResult = this.surveyAnswerDao.surveyAnswerSync(surveyRecordId, question.getQuestionId());
            //Validamos si ya se ha contestado la pregunta
            //Si existe solo se actualiza la informacion
            //Caso contrarío se inserta la pregunta con su respuesta
            if( surveyAnswerResult != null ) {
                surveyAnswerResult.setAnswer(surveyAnswer.getAnswer());
                this.surveyAnswerDao.update(surveyAnswerResult);
            } else {
                surveyAnswerId = this.surveyAnswerDao.insert(surveyAnswer);
            }
        }else {
            //Eliminar registros si esque existen e insertar nuevamente
            this.surveyAnswerDao.delete(surveyRecordId, surveyAnswer.getQuestionnaireId(), surveyAnswer.getQuestionId(), answer);
            surveyAnswerId = this.surveyAnswerDao.insert(surveyAnswer);
        }
        return surveyAnswerId;
    }

    @Override
    public SurveyRecord findSurveyRecord(Survey survey) {
        SurveyRecord surveyRecord = null;
        if( survey.getSurveyType() == CustomConstants.UNICA) {
            surveyRecord = this.surveyRecordDao.surveyRecord(survey.getSurveyId());
        /*Long encuestaRegistroId = 0L;
        EncuestaRegistro encuestaRegistro = this.encuestaRegistroDao.encuestaRegistro(encuesta.getEncuestaId());
            if( encuestaRegistro == null){
                EncuestaRegistro encuestaRegistroAux = Utils.getEncuestaRegistro(encuesta, catEncuestaEstatusId);
                encuestaRegistroId = this.encuestaRegistroDao.insert(encuestaRegistroAux);;
            }*/
        }
        return surveyRecord;
    }

    @Override
    public LiveData<SurveyAnswer> surveyAnswer(Long surveyRecordId, Long answerId) {
        return this.surveyAnswerDao.surveyAnswer(surveyRecordId, answerId);
    }

    @Override
    public SurveyAnswer surveyAnswerSync(Long surveyRecordId, Long questionId) {
        return this.surveyAnswerDao.surveyAnswerSync(surveyRecordId, questionId);
    }

    @Override
    public LiveData<SurveyAnswer> surveyAnswer(Long surveyRecordId, Long questionId, String answerId) {
        return this.surveyAnswerDao.surveyAnswer(surveyRecordId, questionId, answerId);
    }

    @Override
    public SurveyAnswer surveyAnswerSync(Long surveyRecordId, Long questionId, String answerId) {
        return this.surveyAnswerDao.surveyAnswerSync(surveyRecordId, questionId, answerId);
    }

    @Override
    public SurveyRecordAnswers surveyFinished(Long surveyRecordId, Integer surveyStatus, String endDate) {
        //Actualizamos el estatus del registro de la encuesta a 1 que es una encuesta terminada
        this.surveyRecordDao.update(surveyStatus, endDate, surveyRecordId);
        SurveyRecordAnswers surveyRecordAnswers = this.surveyRecordDao.surveyRecordAnswersSync(surveyRecordId);
        return surveyRecordAnswers;
    }
    @Override
    public void deleteSurveyRecordByQuestionnaireId(Long surveyRecordId, Long questionnaireId){
        Log.i(CustomConstants.TAG_LOG, "PrivateSurveyServiceImpl - deleteSurveyRecordByQuestionnaireId");
        SurveyAnswer surveyAnswer = this.surveyAnswerDao.surveyAnswerByRecordIdAndQuestionnaireIdSync(surveyRecordId, questionnaireId);
        if( surveyAnswer != null ) {
            this.surveyAnswerDao.deleteByRecordIdQuestionnaireId(surveyRecordId, questionnaireId);
        }

    }

    @Override
    public void deleteSurveyRecordByQuestionId(Long surveyRecordId, Long questionId) {
        this.surveyAnswerDao.deleteSurveyRecordByQuestionId(surveyRecordId, questionId);
    }

    @Override
    public void deleteSurveyRecordByQuestionIdAndAnswer(Long surveyRecordId, Long questionId, String answer) {
        this.surveyAnswerDao.deleteSurveyRecordByQuestionIdAndAnswer(surveyRecordId, questionId, answer);
    }

}
