package com.cysout.sousystems.surveymodule.service.impl;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cysout.sousystems.surveymodule.config.AppDatabase;
import com.cysout.sousystems.surveymodule.dao.EncuestaRegistroDao;
import com.cysout.sousystems.surveymodule.dao.EncuestaRespuestaDao;
import com.cysout.sousystems.surveymodule.entity.Cuestionario;
import com.cysout.sousystems.surveymodule.entity.Encuesta;
import com.cysout.sousystems.surveymodule.entity.EncuestaRegistro;
import com.cysout.sousystems.surveymodule.entity.EncuestaRespuesta;
import com.cysout.sousystems.surveymodule.entity.Pregunta;
import com.cysout.sousystems.surveymodule.entity.relation.EncuestaRegistroRespuestas;
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
    public Long encuestaRegistro(Encuesta encuesta, Integer catEncuestaEstatusId, String fechaInicial, String fechaFinal) {
        EncuestaRegistro encuestaRegistro = Utils.getEncuestaRegistro(encuesta, catEncuestaEstatusId,fechaInicial, fechaFinal);
        return this.encuestaRegistroDao.insert(encuestaRegistro);
    }

    @Override
    public Long encuestaRespuesta(Encuesta encuesta, Cuestionario cuestionario, Pregunta pregunta, String respuesta, Long encuestaRegistroId) {
        Long encuestaRespuestaId = 0L;
        EncuestaRespuesta encuestaRespuesta = Utils.getEncuestaRespuesta(cuestionario, pregunta,respuesta, encuestaRegistroId);
        Log.i(CustomConstants.TAG_LOG, "encuestaRespuesta() "+encuestaRespuesta.toString());
        if( encuestaRespuesta.getTipo().equalsIgnoreCase(CustomConstants.TEXT) || encuestaRespuesta.getTipo().equalsIgnoreCase(CustomConstants.SELECT)){
            Log.i(CustomConstants.TAG_LOG, "ENTRO TIPO "+encuestaRespuesta.getTipo());
            EncuestaRespuesta encuestaRespuestaResult = this.encuestaRespuestaDao.encuestaRespuestaByRegistroIdAndPregIdSync(encuestaRegistroId, pregunta.getPreguntaId());
            if( encuestaRespuestaResult != null ) {
                encuestaRespuestaResult.setRespuesta(encuestaRespuesta.getRespuesta());
                this.encuestaRespuestaDao.update(encuestaRespuestaResult);
                Log.i(CustomConstants.TAG_LOG, "ENTRO ACTUALIZA "+encuestaRespuesta.getTipo()+": "+encuestaRespuestaResult.toString());
            } else {
                encuestaRespuestaId = this.encuestaRespuestaDao.insert(encuestaRespuesta);
                Log.i(CustomConstants.TAG_LOG, "ENTRO CREA "+encuestaRespuesta.getTipo()+": "+encuestaRespuesta.toString());
            }
        }else {
            encuestaRespuestaId = this.encuestaRespuestaDao.insert(encuestaRespuesta);
            Log.i(CustomConstants.TAG_LOG, "ENTRO CREA "+encuestaRespuesta.toString());
        }
        return encuestaRespuestaId;
    }

    @Override
    public EncuestaRegistro findEncuestaregistro(Encuesta encuesta) {
        EncuestaRegistro encuestaRegistro = null;
        if( encuesta.getCatEncuestaTipoId() == CustomConstants.UNICA) {
            encuestaRegistro = this.encuestaRegistroDao.encuestaRegistro(encuesta.getEncuestaId());
        /*Long encuestaRegistroId = 0L;
        EncuestaRegistro encuestaRegistro = this.encuestaRegistroDao.encuestaRegistro(encuesta.getEncuestaId());
            if( encuestaRegistro == null){
                EncuestaRegistro encuestaRegistroAux = Utils.getEncuestaRegistro(encuesta, catEncuestaEstatusId);
                encuestaRegistroId = this.encuestaRegistroDao.insert(encuestaRegistroAux);;
            }*/
        }
        return encuestaRegistro;
    }

    @Override
    public LiveData<EncuestaRespuesta> encuestaRespuestaByRegistroIdAndPregId(Long encuestaRegistroId, Long preguntaId) {
        return this.encuestaRespuestaDao.encuestaRespuestaByRegistroIdAndPregId(encuestaRegistroId, preguntaId);
    }

    @Override
    public EncuestaRespuesta encuestaRespuestaByRegistroIdAndPregIdSync(Long encuestaRegistroId, Long preguntaId) {
        return this.encuestaRespuestaDao.encuestaRespuestaByRegistroIdAndPregIdSync(encuestaRegistroId, preguntaId);
    }

    @Override
    public LiveData<EncuestaRespuesta> encuestaRespuestaByRegtroIdAndPregIdAndRespId(Long encuestaRegistroId, Long preguntaId, String respuestaId) {
        return this.encuestaRespuestaDao.encuestaRespuestaByRegtroIdAndPregIdAndRespId(encuestaRegistroId, preguntaId, respuestaId);
    }

    @Override
    public EncuestaRespuesta encuestaRespuestaByRegtroIdAndPregIdAndRespIdSync(Long encuestaRegistroId, Long preguntaId, String respuestaId) {
        return this.encuestaRespuestaDao.encuestaRespuestaByRegtroIdAndPregIdAndRespIdSync(encuestaRegistroId, preguntaId, respuestaId);
    }

    @Override
    public EncuestaRegistroRespuestas encuentaFinaliza(Long encuestaRegistroId, Integer catEncuestaEstatusId, String fechaFinal) {
        //Actualizamos el estatus del registro de la encuesta a 1 que es una encuesta terminada
        this.encuestaRegistroDao.updateEncuestaRegistroByEnctRegtroId(catEncuestaEstatusId, fechaFinal, encuestaRegistroId);
        EncuestaRegistroRespuestas encuestaRegistroRespuestas = this.encuestaRegistroDao.loadRegistroRespByEnctRegtroIdSync(encuestaRegistroId);
        return encuestaRegistroRespuestas;
    }
    @Override
    public void eliminarEncuestaRegistroByCuestionarioId(Long encuestaRegistroId, Long cuestionarioId){
        EncuestaRespuesta encuestaRespuesta = this.encuestaRespuestaDao.encuestaRespuestaByRegistroIdAndCuestIdSync(encuestaRegistroId, cuestionarioId);
        if( encuestaRespuesta != null ) {
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