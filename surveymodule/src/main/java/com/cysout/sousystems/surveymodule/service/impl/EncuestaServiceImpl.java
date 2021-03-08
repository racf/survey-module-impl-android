package com.cysout.sousystems.surveymodule.service.impl;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.EncuestaRegistroDao;
import com.cysout.sousystems.surveymodule.dao.EncuestaRespuestaDao;
import com.cysout.sousystems.surveymodule.entity.Question;
import com.cysout.sousystems.surveymodule.entity.Questionnaire;
import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.SurveyAnswer;
import com.cysout.sousystems.surveymodule.entity.SurveyRecord;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecordAnswers;
import com.cysout.sousystems.surveymodule.service.EncuestaService;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
import com.cysout.sousystems.surveymodule.utils.Utils;

public class EncuestaServiceImpl extends AndroidViewModel implements EncuestaService {
    private EncuestaRegistroDao encuestaRegistroDao;
    private EncuestaRespuestaDao encuestaRespuestaDao;

    public EncuestaServiceImpl(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDataBase(application);
        this.encuestaRegistroDao = db.encuestaRegistroDao();
        this.encuestaRespuestaDao = db.encuestaRespuestaDao();
    }

    @Override
    public Long encuestaRegistro(Survey survey, Integer catEncuestaEstatusId, String fechaInicial, String fechaFinal) {
        SurveyRecord encuestaRegistro = Utils.getEncuestaRegistro(survey, catEncuestaEstatusId,fechaInicial, fechaFinal);
        return this.encuestaRegistroDao.insert(encuestaRegistro);
    }

    @Override
    public Long encuestaRespuesta(Survey survey, Questionnaire questionnaire, Question question, String respuesta, Long encuestaRegistroId) {
        Long encuestaRespuestaId = 0L;
        SurveyAnswer surveyAnswer = Utils.getEncuestaRespuesta(questionnaire, question,respuesta, encuestaRegistroId);
        Log.i(CustomConstants.TAG_LOG, "encuestaRespuesta() "+ surveyAnswer.toString());
        if( surveyAnswer.getType().equalsIgnoreCase(CustomConstants.TEXT) || surveyAnswer.getType().equalsIgnoreCase(CustomConstants.SELECT)){
            Log.i(CustomConstants.TAG_LOG, "ENTRO TIPO "+ surveyAnswer.getType());
            SurveyAnswer surveyAnswerResult = this.encuestaRespuestaDao.surveyAnswerByRegistroIdAndPregIdSync(encuestaRegistroId, question.getQuestionId());
            if( surveyAnswerResult != null ) {
                surveyAnswerResult.setAnswer(surveyAnswer.getAnswer());
                this.encuestaRespuestaDao.update(surveyAnswerResult);
                Log.i(CustomConstants.TAG_LOG, "ENTRO ACTUALIZA "+ surveyAnswer.getType()+": "+ surveyAnswerResult.toString());
            } else {
                encuestaRespuestaId = this.encuestaRespuestaDao.insert(surveyAnswer);
                Log.i(CustomConstants.TAG_LOG, "ENTRO CREA "+ surveyAnswer.getType()+": "+ surveyAnswer.toString());
            }
        }else {
            encuestaRespuestaId = this.encuestaRespuestaDao.insert(surveyAnswer);
            Log.i(CustomConstants.TAG_LOG, "ENTRO CREA "+ surveyAnswer.toString());
        }
        return encuestaRespuestaId;
    }

    @Override
    public SurveyRecord findEncuestaregistro(Survey survey) {
        SurveyRecord surveyRecord = null;
        if( survey.getSurveyType() == CustomConstants.UNICA) {
            surveyRecord = this.encuestaRegistroDao.encuestaRegistro(survey.getSurveyId());
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
    public LiveData<SurveyAnswer> encuestaRespuestaByRegistroIdAndPregId(Long encuestaRegistroId, Long preguntaId) {
        return this.encuestaRespuestaDao.surveyAnswerByRegistroIdAndPregId(encuestaRegistroId, preguntaId);
    }

    @Override
    public SurveyAnswer encuestaRespuestaByRegistroIdAndPregIdSync(Long encuestaRegistroId, Long preguntaId) {
        return this.encuestaRespuestaDao.surveyAnswerByRegistroIdAndPregIdSync(encuestaRegistroId, preguntaId);
    }

    @Override
    public LiveData<SurveyAnswer> encuestaRespuestaByRegtroIdAndPregIdAndRespId(Long encuestaRegistroId, Long preguntaId, String respuestaId) {
        return this.encuestaRespuestaDao.surveyAnswerByRegtroIdAndPregIdAndRespId(encuestaRegistroId, preguntaId, respuestaId);
    }

    @Override
    public SurveyAnswer encuestaRespuestaByRegtroIdAndPregIdAndRespIdSync(Long encuestaRegistroId, Long preguntaId, String respuestaId) {
        return this.encuestaRespuestaDao.surveyAnswerByRegtroIdAndPregIdAndRespIdSync(encuestaRegistroId, preguntaId, respuestaId);
    }

    @Override
    public SurveyRecordAnswers encuentaFinaliza(Long encuestaRegistroId, Integer catEncuestaEstatusId, String fechaFinal) {
        //Actualizamos el estatus del registro de la encuesta a 1 que es una encuesta terminada
        this.encuestaRegistroDao.updateEncuestaRegistroByEnctRegtroId(catEncuestaEstatusId, fechaFinal, encuestaRegistroId);
        SurveyRecordAnswers surveyRecordAnswers = this.encuestaRegistroDao.loadRegistroRespByEnctRegtroIdSync(encuestaRegistroId);
        return surveyRecordAnswers;
    }
    @Override
    public void eliminarEncuestaRegistroByCuestionarioId(Long encuestaRegistroId, Long cuestionarioId){
        SurveyAnswer surveyAnswer = this.encuestaRespuestaDao.surveyAnswerByRegistroIdAndCuestIdSync(encuestaRegistroId, cuestionarioId);
        if( surveyAnswer != null ) {
            Log.i(CustomConstants.TAG_LOG, "Elimina eliminarEncuestaRegistroByCuestionarioId");
            this.encuestaRespuestaDao.deleteByEnctRegtIdAndCuestId(encuestaRegistroId, cuestionarioId);
        }

    }

    @Override
    public void eliminarEncuestaRegistroByPreguntaId(Long encuestaRegistroId, Long preguntaId) {
        this.encuestaRespuestaDao.deleteByEnctRegtIdAndPreguntaId(encuestaRegistroId, preguntaId);
    }

    @Override
    public void eliminarEncuestaRegistroByPregtIdAndResp(Long encuestaRegistroId, Long preguntaId, String respuesta) {
        this.encuestaRespuestaDao.deleteByEnctRegtIdAndPregtIdAndResp(encuestaRegistroId, preguntaId, respuesta);
    }

}
