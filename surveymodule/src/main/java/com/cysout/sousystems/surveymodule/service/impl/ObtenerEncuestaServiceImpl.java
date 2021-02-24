package com.cysout.sousystems.surveymodule.service.impl;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.google.gson.Gson;
import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Cuestionario;
import com.cysout.sousystems.surveymodule.entity.Encuesta;
import com.cysout.sousystems.surveymodule.entity.MostrarCuestionarios;
import com.cysout.sousystems.surveymodule.entity.MostrarPreguntas;
import com.cysout.sousystems.surveymodule.entity.MostrarRespuestas;
import com.cysout.sousystems.surveymodule.entity.MostrarSiSelecciona;
import com.cysout.sousystems.surveymodule.entity.Pregunta;
import com.cysout.sousystems.surveymodule.entity.Respuesta;
import com.cysout.sousystems.surveymodule.entity.relation.EncuestaCuestionarios;
import com.cysout.sousystems.surveymodule.entity.relation.RelacionSiSelecciona;
import com.cysout.sousystems.surveymodule.repository.CuestionarioRepository;
import com.cysout.sousystems.surveymodule.repository.EncuestaRepository;
import com.cysout.sousystems.surveymodule.repository.MostrarCuestionariosRepository;
import com.cysout.sousystems.surveymodule.repository.MostrarPreguntasRepository;
import com.cysout.sousystems.surveymodule.repository.MostrarRespuestasRepository;
import com.cysout.sousystems.surveymodule.repository.MostrarSiSeleccionaRepository;
import com.cysout.sousystems.surveymodule.repository.PreguntaRepository;
import com.cysout.sousystems.surveymodule.repository.RespuestaRepository;
import com.cysout.sousystems.surveymodule.repository.impl.CuestionarioRepositoryImpl;
import com.cysout.sousystems.surveymodule.repository.impl.EncuestaRepositoryImpl;
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
    private EncuestaRepository encuestaRepository;
    private CuestionarioRepository cuestionarioRepository;
    private PreguntaRepository preguntaRepository;
    private RespuestaRepository respuestaRepository;
    private MostrarSiSeleccionaRepository mostrarSiSeleccionaRepository;
    private MostrarCuestionariosRepository mostrarCuestionariosRepository;
    private MostrarPreguntasRepository mostrarPreguntasRepository;
    private MostrarRespuestasRepository mostrarRespuestasRepository;

    public ObtenerEncuestaServiceImpl(@NonNull Application application) {
        super(application);
        this.encuestaRepository = new EncuestaRepositoryImpl(application);
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
        Encuesta encuesta = gson.fromJson(jsonEncuesta, Encuesta.class);
        //Log.i(CustomConstants.TAG_LOG, encuestaRepository.findEncuestaById(encuesta.getEncuestaId()).toString());
        if(encuestaRepository.loadEncuestaByIdSync(encuesta.getEncuestaId()) == null){
            //encuestaRepository.delete(encuesta);
            Long encuestaId = this.encuestaRepository.insert(encuesta);
            //Log.i(CustomConstants.TAG_LOG, "Encuesta ID: "+encuestaId);
            //Log.i(CustomConstants.TAG_LOG, "Encuesta: "+encuesta.toString());
            //Obtenemos todos los cuestionarios de una determinada encuesta
            for( Cuestionario cuestionario : encuesta.getCuestionarios() ){
                cuestionario.setEncuestaId(encuestaId);
                Long cuestionarioId = cuestionarioRepository.insert(cuestionario);
                //Log.i(CustomConstants.TAG_LOG, "Cuestionario ID: "+cuestionarioId);
                //Log.i(CustomConstants.TAG_LOG, "Cuestionario: "+cuestionario.toString());
                //Obtenemos las preguntas de los cuestionarios
                for (Pregunta pregunta : cuestionario.getPreguntas() ){
                    pregunta.setCuestionarioId(cuestionarioId);
                    Long preguntaId = preguntaRepository.insert(pregunta);
                    //Log.i(CustomConstants.TAG_LOG, "Pregunta ID: "+preguntaId);
                    //Log.i(CustomConstants.TAG_LOG, "Pregunta: "+pregunta.toString());
                    //Obtenemos las respuestas de las preguntas
                    List<Respuesta> respuestas = pregunta.getRespuestas();
                    //Si la pregunta tiene respuestas insertamos
                    if ( respuestas.size() > 0 ){
                        for (Respuesta respuesta : respuestas ) {
                            respuesta.setPreguntaId(preguntaId);
                            Long respuestaId = respuestaRepository.insert(respuesta);
                            //Log.i(CustomConstants.TAG_LOG, "Respuesta ID: "+respuestaId);
                            //Log.i(CustomConstants.TAG_LOG, "Respuesta: "+respuesta.toString());
                            MostrarSiSelecciona mostrarSiSelecciona = respuesta.getMostrarSiSelecciona();
                            //Si mostrar si selecciona no esta vacío
                            if(!Utils.isEmpty(mostrarSiSelecciona)){
                                List<MostrarCuestionarios> mostrarCuestionarios = mostrarSiSelecciona.getCuestionarios();
                                List<MostrarPreguntas> mostrarPreguntas = mostrarSiSelecciona.getPreguntas();
                                List<MostrarRespuestas> mostrarRespuestas = mostrarSiSelecciona.getRespuestas();
                                if( Utils.isEmpty(mostrarCuestionarios) && Utils.isEmpty(mostrarPreguntas) && Utils.isEmpty(mostrarRespuestas)){
                                    Log.i(CustomConstants.TAG_LOG, "MostrarSiSeleccion: SIN info");
                                }else{
                                    mostrarSiSelecciona.setRespuestaId(respuestaId);
                                    Long mostrarSiSeleccionaId = mostrarSiSeleccionaRepository.insert(mostrarSiSelecciona);
                                    //Log.i(CustomConstants.TAG_LOG, "MostrarSiSeleccionaId ID: "+mostrarSiSeleccionaId);
                                    //Si no esta vacio se realiza la inserción en cada objeto
                                    if(!Utils.isEmpty(mostrarCuestionarios)){
                                        for (MostrarCuestionarios mCuestionario : mostrarCuestionarios ) {
                                            mCuestionario.setMostrarSiSeleccionaId(mostrarSiSeleccionaId);
                                            Long mostrarCuestionarioId = mostrarCuestionariosRepository.insert(mCuestionario);
                                            mCuestionario.setMostrarCuestionariosId(mostrarCuestionarioId);
                                           // Log.i(CustomConstants.TAG_LOG, "mCuestionario: "+mCuestionario.toString());
                                        }
                                    }
                                    if(!Utils.isEmpty(mostrarPreguntas)){
                                        for (MostrarPreguntas mPregunta : mostrarPreguntas ) {
                                            mPregunta.setMostrarSiSeleccionaId(mostrarSiSeleccionaId);
                                            Long mostrarPreguntaId = mostrarPreguntasRepository.insert(mPregunta);
                                            mPregunta.setMostrarPreguntasId(mostrarPreguntaId);
                                            //Log.i(CustomConstants.TAG_LOG, "mPregunta: "+mPregunta.toString());
                                        }
                                    }
                                    if(!Utils.isEmpty(mostrarRespuestas)){
                                        for (MostrarRespuestas mRespuesta : mostrarRespuestas ) {
                                            mRespuesta.setMostrarSiSeleccionaId(mostrarSiSeleccionaId);
                                            Long mostrarRespuestaId = mostrarRespuestasRepository.insert(mRespuesta);
                                            mRespuesta.setMostrarRespuestasId(mostrarRespuestaId);
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
    public Encuesta obtenerEncuesta() {
        Encuesta encuesta = null;
        EncuestaCuestionarios encuestaCuestionarios = encuestaRepository.loadCuestionarioRespuestasSync();
        if( !Utils.isEmpty(encuestaCuestionarios) ) {
            encuesta = encuestaCuestionarios.getEncuesta();
            encuesta.setCuestionarios(encuestaCuestionarios.getCuestionarios());
            //Recorremos cada uno de los cuestionarios para obtener las preguntas
            for ( Cuestionario cuestionario : encuesta.getCuestionarios() ) {
                cuestionario.setPreguntas(preguntaRepository.loadByCuestionarioIdSync(cuestionario.getCuestionarioId()));
                for ( Pregunta pregunta : cuestionario.getPreguntas() ) {
                   if( !pregunta.getTipo().equalsIgnoreCase("text") ) {
                       List<Respuesta> respuestas = respuestaRepository.loadByPreguntaIdSync(pregunta.getPreguntaId());
                       pregunta.setRespuestas(respuestas);
                       //Validamos que el arreglo de objetos contenga informacion
                       if( !Utils.isEmpty( pregunta.getRespuestas() )){
                           for ( Respuesta respuesta : pregunta.getRespuestas() ) {
                               RelacionSiSelecciona relacionSiSelecciona = mostrarSiSeleccionaRepository.loadMosstrarSiSeleccionaByRespuestaId(respuesta.getRespuestaId());
                               if (!Utils.isEmpty(relacionSiSelecciona)) {
                                   MostrarSiSelecciona mostrarSiSelecciona = relacionSiSelecciona.getMostrarSiSelecciona();
                                   if (!Utils.isEmpty(mostrarSiSelecciona)) {
                                       mostrarSiSelecciona.setCuestionarios(relacionSiSelecciona.getCuestionarios());
                                       mostrarSiSelecciona.setPreguntas(relacionSiSelecciona.getPreguntas());
                                       mostrarSiSelecciona.setRespuestas(relacionSiSelecciona.getRespuestas());
                                       respuesta.setMostrarSiSelecciona(mostrarSiSelecciona);
                                   }
                               }
                           }
                       }
                   }
                }
            }
        }
        Log.i(CustomConstants.TAG_LOG, "Cuestionario INSERTADO: "+gson.toJson(encuesta));
        return encuesta;
    }
}
