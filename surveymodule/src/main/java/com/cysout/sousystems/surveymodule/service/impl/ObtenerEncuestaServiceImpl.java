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
import com.google.gson.Gson;
import java.util.List;

import com.cysout.sousystems.surveymodule.entity.ShowQuestionnaires;
import com.cysout.sousystems.surveymodule.entity.ShowQuestions;
import com.cysout.sousystems.surveymodule.entity.ShowAnswers;
import com.cysout.sousystems.surveymodule.entity.relation.EncuestaCuestionarios;
import com.cysout.sousystems.surveymodule.entity.relation.RelacionSiSelecciona;
import com.cysout.sousystems.surveymodule.repository.CuestionarioRepository;
import com.cysout.sousystems.surveymodule.repository.SurveyRepository;
import com.cysout.sousystems.surveymodule.repository.MostrarCuestionariosRepository;
import com.cysout.sousystems.surveymodule.repository.MostrarPreguntasRepository;
import com.cysout.sousystems.surveymodule.repository.MostrarRespuestasRepository;
import com.cysout.sousystems.surveymodule.repository.MostrarSiSeleccionaRepository;
import com.cysout.sousystems.surveymodule.repository.PreguntaRepository;
import com.cysout.sousystems.surveymodule.repository.RespuestaRepository;
import com.cysout.sousystems.surveymodule.repository.impl.CuestionarioRepositoryImpl;
import com.cysout.sousystems.surveymodule.repository.impl.SurveyRepositoryImpl;
import com.cysout.sousystems.surveymodule.repository.impl.MostrarCuestionariosRepositoryImpl;
import com.cysout.sousystems.surveymodule.repository.impl.MostrarPreguntasRepositoryImpl;
import com.cysout.sousystems.surveymodule.repository.impl.MostrarRespuestasRepositoryImpl;
import com.cysout.sousystems.surveymodule.repository.impl.MostrarSiSeleccionaRepositoryImpl;
import com.cysout.sousystems.surveymodule.repository.impl.PreguntaRepositoryImpl;
import com.cysout.sousystems.surveymodule.repository.impl.RespuestaRepositoryImpl;
import com.cysout.sousystems.surveymodule.service.ObtenerEncuestaService;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
import com.cysout.sousystems.surveymodule.utils.Utils;

public class ObtenerEncuestaServiceImpl  extends AndroidViewModel implements ObtenerEncuestaService {
    Gson gson = new Gson();
    private SurveyRepository surveyRepository;
    private CuestionarioRepository cuestionarioRepository;
    private PreguntaRepository preguntaRepository;
    private RespuestaRepository respuestaRepository;
    private MostrarSiSeleccionaRepository mostrarSiSeleccionaRepository;
    private MostrarCuestionariosRepository mostrarCuestionariosRepository;
    private MostrarPreguntasRepository mostrarPreguntasRepository;
    private MostrarRespuestasRepository mostrarRespuestasRepository;

    public ObtenerEncuestaServiceImpl(@NonNull Application application) {
        super(application);
        this.surveyRepository = new SurveyRepositoryImpl(application);
        this.cuestionarioRepository = new CuestionarioRepositoryImpl(application);
        this.preguntaRepository = new PreguntaRepositoryImpl(application);
        this.respuestaRepository = new RespuestaRepositoryImpl(application);
        this.mostrarSiSeleccionaRepository = new MostrarSiSeleccionaRepositoryImpl(application);
        this.mostrarCuestionariosRepository = new MostrarCuestionariosRepositoryImpl(application);
        this.mostrarPreguntasRepository = new MostrarPreguntasRepositoryImpl(application);
        this.mostrarRespuestasRepository = new MostrarRespuestasRepositoryImpl(application);
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
                Long cuestionarioId = cuestionarioRepository.insert(questionnaire);
                //Log.i(CustomConstants.TAG_LOG, "Cuestionario ID: "+cuestionarioId);
                //Log.i(CustomConstants.TAG_LOG, "Cuestionario: "+cuestionario.toString());
                //Obtenemos las preguntas de los cuestionarios
                for (Question question : questionnaire.getQuestions() ){
                    question.setQuestionnaireId(cuestionarioId);
                    Long preguntaId = preguntaRepository.insert(question);
                    //Log.i(CustomConstants.TAG_LOG, "Pregunta ID: "+preguntaId);
                    //Log.i(CustomConstants.TAG_LOG, "Pregunta: "+pregunta.toString());
                    //Obtenemos las respuestas de las preguntas
                    List<Answer> answers = question.getAnswers();
                    //Si la pregunta tiene respuestas insertamos
                    if ( answers.size() > 0 ){
                        for (Answer answer : answers) {
                            answer.setQuestionId(preguntaId);
                            Long respuestaId = respuestaRepository.insert(answer);
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
                                    Long mostrarSiSeleccionaId = mostrarSiSeleccionaRepository.insert(showSelect);
                                    //Log.i(CustomConstants.TAG_LOG, "MostrarSiSeleccionaId ID: "+mostrarSiSeleccionaId);
                                    //Si no esta vacio se realiza la inserción en cada objeto
                                    if(!Utils.isEmpty(mostrarCuestionarios)){
                                        for (ShowQuestionnaires mCuestionario : mostrarCuestionarios ) {
                                            mCuestionario.setShowSelectId(mostrarSiSeleccionaId);
                                            Long mostrarCuestionarioId = mostrarCuestionariosRepository.insert(mCuestionario);
                                            mCuestionario.setShowQuestionnairesId(mostrarCuestionarioId);
                                           // Log.i(CustomConstants.TAG_LOG, "mCuestionario: "+mCuestionario.toString());
                                        }
                                    }
                                    if(!Utils.isEmpty(mostrarPreguntas)){
                                        for (ShowQuestions mPregunta : mostrarPreguntas ) {
                                            mPregunta.setShowSelectId(mostrarSiSeleccionaId);
                                            Long mostrarPreguntaId = mostrarPreguntasRepository.insert(mPregunta);
                                            mPregunta.setShowQuestionsId(mostrarPreguntaId);
                                            //Log.i(CustomConstants.TAG_LOG, "mPregunta: "+mPregunta.toString());
                                        }
                                    }
                                    if(!Utils.isEmpty(mostrarRespuestas)){
                                        for (ShowAnswers mRespuesta : mostrarRespuestas ) {
                                            mRespuesta.setShowSelectId(mostrarSiSeleccionaId);
                                            Long mostrarRespuestaId = mostrarRespuestasRepository.insert(mRespuesta);
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
        EncuestaCuestionarios encuestaCuestionarios = surveyRepository.loadCuestionarioRespuestasSync();
        if( !Utils.isEmpty(encuestaCuestionarios) ) {
            survey = encuestaCuestionarios.getSurvey();
            survey.setQuestionnaires(encuestaCuestionarios.getQuestionnaires());
            //Recorremos cada uno de los cuestionarios para obtener las preguntas
            for ( Questionnaire questionnaire : survey.getQuestionnaires() ) {
                questionnaire.setQuestions(preguntaRepository.loadByCuestionarioIdSync(questionnaire.getQuestionnaireId()));
                for ( Question question : questionnaire.getQuestions() ) {
                   if( !question.getType().equalsIgnoreCase(CustomConstants.TEXT) ) {
                       List<Answer> answers = respuestaRepository.loadByPreguntaIdSync(question.getQuestionId());
                       question.setAnswers(answers);
                       //Validamos que el arreglo de objetos contenga informacion
                       if( !Utils.isEmpty( question.getAnswers() )){
                           for ( Answer answer : question.getAnswers() ) {
                               RelacionSiSelecciona relacionSiSelecciona = mostrarSiSeleccionaRepository.loadMosstrarSiSeleccionaByRespuestaId(answer.getAnswerId());
                               if (!Utils.isEmpty(relacionSiSelecciona)) {
                                   ShowSelect showSelect = relacionSiSelecciona.getShowSelect();
                                   if (!Utils.isEmpty(showSelect)) {
                                       showSelect.setQuestionnaires(relacionSiSelecciona.getCuestionarios());
                                       showSelect.setQuestions(relacionSiSelecciona.getPreguntas());
                                       showSelect.setAnswers(relacionSiSelecciona.getRespuestas());
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
