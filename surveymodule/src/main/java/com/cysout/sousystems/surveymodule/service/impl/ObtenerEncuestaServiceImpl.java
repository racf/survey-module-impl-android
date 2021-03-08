package com.cysout.sousystems.surveymodule.service.impl;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.cysout.sousystems.surveymodule.entity.Answer;
import com.cysout.sousystems.surveymodule.entity.Question;
import com.cysout.sousystems.surveymodule.entity.Questionnaire;
import com.cysout.sousystems.surveymodule.entity.ShowSelect;
import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyQuestionnaires;
import com.google.gson.Gson;
import java.util.List;

import com.cysout.sousystems.surveymodule.entity.ShowQuestionnaires;
import com.cysout.sousystems.surveymodule.entity.ShowQuestions;
import com.cysout.sousystems.surveymodule.entity.ShowAnswers;
import com.cysout.sousystems.surveymodule.entity.relation.RelationShowSelect;
import com.cysout.sousystems.surveymodule.repository.QuestionnaireRepository;
import com.cysout.sousystems.surveymodule.repository.SurveyRepository;
import com.cysout.sousystems.surveymodule.repository.ShowQuestionnairesRepository;
import com.cysout.sousystems.surveymodule.repository.ShowQuestionsRepository;
import com.cysout.sousystems.surveymodule.repository.ShowAnswersRepository;
import com.cysout.sousystems.surveymodule.repository.ShowSelectRepository;
import com.cysout.sousystems.surveymodule.repository.QuestionRepository;
import com.cysout.sousystems.surveymodule.repository.AnswerRepository;
import com.cysout.sousystems.surveymodule.repository.impl.QuestionnaireRepositoryImpl;
import com.cysout.sousystems.surveymodule.repository.impl.SurveyRepositoryImpl;
import com.cysout.sousystems.surveymodule.repository.impl.ShowQuestionnairesRepositoryImpl;
import com.cysout.sousystems.surveymodule.repository.impl.ShowQuestionsRepositoryImpl;
import com.cysout.sousystems.surveymodule.repository.impl.ShowAnswersRepositoryImpl;
import com.cysout.sousystems.surveymodule.repository.impl.ShowSelectRepositoryImpl;
import com.cysout.sousystems.surveymodule.repository.impl.QuestionRepositoryImpl;
import com.cysout.sousystems.surveymodule.repository.impl.AnswerRepositoryImpl;
import com.cysout.sousystems.surveymodule.service.ObtenerEncuestaService;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
import com.cysout.sousystems.surveymodule.utils.Utils;

public class ObtenerEncuestaServiceImpl  extends AndroidViewModel implements ObtenerEncuestaService {
    Gson gson = new Gson();
    private SurveyRepository surveyRepository;
    private QuestionnaireRepository questionnaireRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private ShowSelectRepository showSelectRepository;
    private ShowQuestionnairesRepository showQuestionnairesRepository;
    private ShowQuestionsRepository showQuestionsRepository;
    private ShowAnswersRepository showAnswersRepository;

    public ObtenerEncuestaServiceImpl(@NonNull Application application) {
        super(application);
        this.surveyRepository = new SurveyRepositoryImpl(application);
        this.questionnaireRepository = new QuestionnaireRepositoryImpl(application);
        this.questionRepository = new QuestionRepositoryImpl(application);
        this.answerRepository = new AnswerRepositoryImpl(application);
        this.showSelectRepository = new ShowSelectRepositoryImpl(application);
        this.showQuestionnairesRepository = new ShowQuestionnairesRepositoryImpl(application);
        this.showQuestionsRepository = new ShowQuestionsRepositoryImpl(application);
        this.showAnswersRepository = new ShowAnswersRepositoryImpl(application);
    }

    @Override
    public Boolean guardarEncuesta(String jsonEncuesta) {
        Boolean estatus = false;
        Survey survey = gson.fromJson(jsonEncuesta, Survey.class);
        //Log.i(CustomConstants.TAG_LOG, encuestaRepository.findEncuestaById(encuesta.getsurveyId()).toString());
        if(surveyRepository.loadEncuestaByIdSync(survey.getSurveyId()) == null){
            //encuestaRepository.delete(encuesta);
            Long surveyId = this.surveyRepository.insert(survey);
            //Log.i(CustomConstants.TAG_LOG, "Encuesta ID: "+surveyId);
            //Log.i(CustomConstants.TAG_LOG, "Encuesta: "+encuesta.toString());
            //Obtenemos todos los cuestionarios de una determinada encuesta
            for( Questionnaire questionnaire : survey.getQuestionnaires() ){
                questionnaire.setSurveyId(surveyId);
                Long cuestionarioId = questionnaireRepository.insert(questionnaire);
                //Log.i(CustomConstants.TAG_LOG, "Cuestionario ID: "+cuestionarioId);
                //Log.i(CustomConstants.TAG_LOG, "Cuestionario: "+cuestionario.toString());
                //Obtenemos las preguntas de los cuestionarios
                for (Question question : questionnaire.getQuestions() ){
                    question.setQuestionnaireId(cuestionarioId);
                    Long preguntaId = questionRepository.insert(question);
                    //Log.i(CustomConstants.TAG_LOG, "Pregunta ID: "+preguntaId);
                    //Log.i(CustomConstants.TAG_LOG, "Pregunta: "+pregunta.toString());
                    //Obtenemos las respuestas de las preguntas
                    List<Answer> answers = question.getAnswers();
                    //Si la pregunta tiene respuestas insertamos
                    if ( answers.size() > 0 ){
                        for (Answer answer : answers) {
                            answer.setQuestionId(preguntaId);
                            Long respuestaId = answerRepository.insert(answer);
                            //Log.i(CustomConstants.TAG_LOG, "Respuesta ID: "+respuestaId);
                            //Log.i(CustomConstants.TAG_LOG, "Respuesta: "+respuesta.toString());
                            ShowSelect showSelect = answer.getShowSelect();
                            //Si mostrar si selecciona no esta vacío
                            if(!Utils.isEmpty(showSelect)){
                                List<ShowQuestionnaires> mostrarCuestionarios = showSelect.getQuestionnaires();
                                List<ShowQuestions> mostrarPreguntas = showSelect.getQuestions();
                                List<ShowAnswers> mostrarRespuestas = showSelect.getAnswers();
                                if( Utils.isEmpty(mostrarCuestionarios) && Utils.isEmpty(mostrarPreguntas) && Utils.isEmpty(mostrarRespuestas)){
                                    Log.i(CustomConstants.TAG_LOG, "MostrarSiSeleccion: SIN info");
                                }else{
                                    showSelect.setAnswerId(respuestaId);
                                    Long mostrarSiSeleccionaId = showSelectRepository.insert(showSelect);
                                    //Log.i(CustomConstants.TAG_LOG, "MostrarSiSeleccionaId ID: "+mostrarSiSeleccionaId);
                                    //Si no esta vacio se realiza la inserción en cada objeto
                                    if(!Utils.isEmpty(mostrarCuestionarios)){
                                        for (ShowQuestionnaires mCuestionario : mostrarCuestionarios ) {
                                            mCuestionario.setShowSelectId(mostrarSiSeleccionaId);
                                            Long mostrarCuestionarioId = showQuestionnairesRepository.insert(mCuestionario);
                                            mCuestionario.setShowQuestionnairesId(mostrarCuestionarioId);
                                           // Log.i(CustomConstants.TAG_LOG, "mCuestionario: "+mCuestionario.toString());
                                        }
                                    }
                                    if(!Utils.isEmpty(mostrarPreguntas)){
                                        for (ShowQuestions mPregunta : mostrarPreguntas ) {
                                            mPregunta.setShowSelectId(mostrarSiSeleccionaId);
                                            Long mostrarPreguntaId = showQuestionsRepository.insert(mPregunta);
                                            mPregunta.setShowQuestionsId(mostrarPreguntaId);
                                            //Log.i(CustomConstants.TAG_LOG, "mPregunta: "+mPregunta.toString());
                                        }
                                    }
                                    if(!Utils.isEmpty(mostrarRespuestas)){
                                        for (ShowAnswers mRespuesta : mostrarRespuestas ) {
                                            mRespuesta.setShowSelectId(mostrarSiSeleccionaId);
                                            Long mostrarRespuestaId = showAnswersRepository.insert(mRespuesta);
                                            mRespuesta.setShowAnswersId(mostrarRespuestaId);
                                           // Log.i(CustomConstants.TAG_LOG, "mRespuesta: "+mRespuesta.toString());
                                        }
                                    }
                                }
                            }
                        }
                    }else {
                        Log.i(CustomConstants.TAG_LOG, "NO tiene respuestas");
                    }
                }
            }
            estatus = true;
        }
        return estatus;
    }

    @Override
    public Survey obtenerEncuesta() {
        Survey survey = null;
        SurveyQuestionnaires surveyQuestionnaires = surveyRepository.loadCuestionarioRespuestasSync();
        if( !Utils.isEmpty(surveyQuestionnaires) ) {
            survey = surveyQuestionnaires.getSurvey();
            survey.setQuestionnaires(surveyQuestionnaires.getQuestionnaires());
            //Recorremos cada uno de los cuestionarios para obtener las preguntas
            for ( Questionnaire questionnaire : survey.getQuestionnaires() ) {
                questionnaire.setQuestions(questionRepository.loadByCuestionarioIdSync(questionnaire.getQuestionnaireId()));
                for ( Question question : questionnaire.getQuestions() ) {
                   if( !question.getType().equalsIgnoreCase(CustomConstants.TEXT) ) {
                       List<Answer> answers = answerRepository.loadByPreguntaIdSync(question.getQuestionId());
                       question.setAnswers(answers);
                       //Validamos que el arreglo de objetos contenga informacion
                       if( !Utils.isEmpty( question.getAnswers() )){
                           for ( Answer answer : question.getAnswers() ) {
                               RelationShowSelect relationShowSelect = showSelectRepository.loadMosstrarSiSeleccionaByRespuestaId(answer.getAnswerId());
                               if (!Utils.isEmpty(relationShowSelect)) {
                                   ShowSelect showSelect = relationShowSelect.getShowSelect();
                                   if (!Utils.isEmpty(showSelect)) {
                                       showSelect.setQuestionnaires(relationShowSelect.getQuestionnaires());
                                       showSelect.setQuestions(relationShowSelect.getQuestions());
                                       showSelect.setAnswers(relationShowSelect.getAnswers());
                                       answer.setShowSelect(showSelect);
                                   }
                               }
                           }
                       }
                   }
                }
            }
        }
        Log.i(CustomConstants.TAG_LOG, "Cuestionario INSERTADO: "+gson.toJson(survey));
        return survey;
    }
}
