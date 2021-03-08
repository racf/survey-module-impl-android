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
import com.cysout.sousystems.surveymodule.service.EncuestaService;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
import com.cysout.sousystems.surveymodule.utils.Utils;

public class EncuestaServiceImpl extends AndroidViewModel implements EncuestaService {
    private SurveyRecordDao surveyRecordDao;
    private SurveyAnswerDao surveyAnswerDao;

    public EncuestaServiceImpl(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDataBase(application);
        this.surveyRecordDao = db.surveyRecordDao();
        this.surveyAnswerDao = db.surveyAnswerDao();
    }

    @Override
    public Long encuestaRegistro(Survey survey, Integer catEncuestaEstatusId, String fechaInicial, String fechaFinal) {
        SurveyRecord encuestaRegistro = Utils.getEncuestaRegistro(survey, catEncuestaEstatusId,fechaInicial, fechaFinal);
        return this.surveyRecordDao.insert(encuestaRegistro);
    }

    @Override
    public Long encuestaRespuesta(Survey survey, Questionnaire questionnaire, Question question, String respuesta, Long encuestaRegistroId) {
        Long encuestaRespuestaId = 0L;
        SurveyAnswer surveyAnswer = Utils.getEncuestaRespuesta(questionnaire, question,respuesta, encuestaRegistroId);
        Log.i(CustomConstants.TAG_LOG, "encuestaRespuesta() "+ surveyAnswer.toString());
        if( surveyAnswer.getType().equalsIgnoreCase(CustomConstants.TEXT) || surveyAnswer.getType().equalsIgnoreCase(CustomConstants.SELECT)){
            Log.i(CustomConstants.TAG_LOG, "ENTRO TIPO "+ surveyAnswer.getType());
            SurveyAnswer surveyAnswerResult = this.surveyAnswerDao.surveyAnswerByRegistroIdAndPregIdSync(encuestaRegistroId, question.getQuestionId());
            if( surveyAnswerResult != null ) {
                surveyAnswerResult.setAnswer(surveyAnswer.getAnswer());
                this.surveyAnswerDao.update(surveyAnswerResult);
                Log.i(CustomConstants.TAG_LOG, "ENTRO ACTUALIZA "+ surveyAnswer.getType()+": "+ surveyAnswerResult.toString());
            } else {
                encuestaRespuestaId = this.surveyAnswerDao.insert(surveyAnswer);
                Log.i(CustomConstants.TAG_LOG, "ENTRO CREA "+ surveyAnswer.getType()+": "+ surveyAnswer.toString());
            }
        }else {
            encuestaRespuestaId = this.surveyAnswerDao.insert(surveyAnswer);
            Log.i(CustomConstants.TAG_LOG, "ENTRO CREA "+ surveyAnswer.toString());
        }
        return encuestaRespuestaId;
    }

    @Override
    public SurveyRecord findEncuestaregistro(Survey survey) {
        SurveyRecord surveyRecord = null;
        if( survey.getSurveyType() == CustomConstants.UNICA) {
            surveyRecord = this.surveyRecordDao.encuestaRegistro(survey.getSurveyId());
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
        return this.surveyAnswerDao.surveyAnswerByRegistroIdAndPregId(encuestaRegistroId, preguntaId);
    }

    @Override
    public SurveyAnswer encuestaRespuestaByRegistroIdAndPregIdSync(Long encuestaRegistroId, Long preguntaId) {
        return this.surveyAnswerDao.surveyAnswerByRegistroIdAndPregIdSync(encuestaRegistroId, preguntaId);
    }

    @Override
    public LiveData<SurveyAnswer> encuestaRespuestaByRegtroIdAndPregIdAndRespId(Long encuestaRegistroId, Long preguntaId, String respuestaId) {
        return this.surveyAnswerDao.surveyAnswerByRegtroIdAndPregIdAndRespId(encuestaRegistroId, preguntaId, respuestaId);
    }

    @Override
    public SurveyAnswer encuestaRespuestaByRegtroIdAndPregIdAndRespIdSync(Long encuestaRegistroId, Long preguntaId, String respuestaId) {
        return this.surveyAnswerDao.surveyAnswerByRegtroIdAndPregIdAndRespIdSync(encuestaRegistroId, preguntaId, respuestaId);
    }

    @Override
    public SurveyRecordAnswers encuentaFinaliza(Long encuestaRegistroId, Integer catEncuestaEstatusId, String fechaFinal) {
        //Actualizamos el estatus del registro de la encuesta a 1 que es una encuesta terminada
        this.surveyRecordDao.updateEncuestaRegistroByEnctRegtroId(catEncuestaEstatusId, fechaFinal, encuestaRegistroId);
        SurveyRecordAnswers surveyRecordAnswers = this.surveyRecordDao.loadRegistroRespByEnctRegtroIdSync(encuestaRegistroId);
        return surveyRecordAnswers;
    }
    @Override
    public void eliminarEncuestaRegistroByCuestionarioId(Long encuestaRegistroId, Long cuestionarioId){
        SurveyAnswer surveyAnswer = this.surveyAnswerDao.surveyAnswerByRegistroIdAndCuestIdSync(encuestaRegistroId, cuestionarioId);
        if( surveyAnswer != null ) {
            Log.i(CustomConstants.TAG_LOG, "Elimina eliminarEncuestaRegistroByCuestionarioId");
            this.surveyAnswerDao.deleteByEnctRegtIdAndCuestId(encuestaRegistroId, cuestionarioId);
        }

    }

    @Override
    public void eliminarEncuestaRegistroByPreguntaId(Long encuestaRegistroId, Long preguntaId) {
        this.surveyAnswerDao.deleteByEnctRegtIdAndPreguntaId(encuestaRegistroId, preguntaId);
    }

    @Override
    public void eliminarEncuestaRegistroByPregtIdAndResp(Long encuestaRegistroId, Long preguntaId, String respuesta) {
        this.surveyAnswerDao.deleteByEnctRegtIdAndPregtIdAndResp(encuestaRegistroId, preguntaId, respuesta);
    }

}
