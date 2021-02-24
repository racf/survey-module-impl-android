package com.cysout.sousystems.surveymodule.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import com.cysout.sousystems.surveymodule.R;
import com.cysout.sousystems.surveymodule.entity.Cuestionario;
import com.cysout.sousystems.surveymodule.entity.Encuesta;
import com.cysout.sousystems.surveymodule.entity.EncuestaRespuesta;
import com.cysout.sousystems.surveymodule.entity.MostrarSiSelecciona;
import com.cysout.sousystems.surveymodule.entity.Pregunta;
import com.cysout.sousystems.surveymodule.entity.Respuesta;
import com.cysout.sousystems.surveymodule.service.EncuestaService;
import com.cysout.sousystems.surveymodule.service.impl.EncuestaServiceImpl;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
import com.cysout.sousystems.surveymodule.utils.Utils;
import com.cysout.sousystems.surveymodule.view.QuestionaryActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectFragment extends WidgetFragment {

   // private NestedScrollView scrollView;
    private RadioGroup radioGroup;
    private EncuestaService encuestaService;

    public SelectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select, container, false);
        //Cargamos el servicio para gestionar encuestas
        this.encuestaService = new ViewModelProvider(this).get(EncuestaServiceImpl.class);
        bindView(view);
        return view;
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);
        //scrollView = view.findViewById(R.id.scroll_view);
        radioGroup = view.findViewById(R.id.radio_group);
    }


    @Override
    public boolean load(Cuestionario cuestionario, Pregunta pregunta) {
        Log.i(CustomConstants.TAG_LOG, "SelectFragment - load(Cuestionario cuestionario, Pregunta pregunta)");
        Long encuestaRegistroId = Utils.findPreferenceLong(getContext(), CustomConstants.PREFERENCE_NAME_CUESTIONARIO, CustomConstants.CUESTIONARIO_REGISTRO_ID);
        final boolean[] status = {false};
        radioGroup.removeAllViews();
        for (Respuesta respuesta : pregunta.getRespuestas()) {
            final RadioButton radioButton = getRadioButton(radioGroup.getChildCount(), respuesta.getTexto(), respuesta, null);
            //Si visible es igual a false ocultamos el radioButton a mostrar de una determinada pregunta
            if( !respuesta.getVisible() ){
                radioButton.setVisibility(View.GONE);
            }
            //Asignamos informacion al regresar a la encuesta anterior
            if (encuestaRegistroId > 0L) {
                encuestaService.encuestaRespuestaByRegistroIdAndPregId(encuestaRegistroId, pregunta.getPreguntaId()).observe(getViewLifecycleOwner(), new Observer<EncuestaRespuesta>() {
                    @Override
                    public void onChanged(EncuestaRespuesta encuestaRespuesta) {
                        if(encuestaRespuesta != null) {
                           if(pregunta.getPreguntaId() == encuestaRespuesta.getPreguntaId() && String.valueOf(respuesta.getRespuestaId()).equalsIgnoreCase(encuestaRespuesta.getRespuesta())){
                               radioButton.setChecked(CustomConstants.TRUE);
                           }
                        }
                    }
                });
            }
            radioGroup.addView(radioButton);
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    QuestionaryFragment questionaryFragment = (QuestionaryFragment) getParentFragment();
                    Map<WidgetFragment, Pregunta> preguntas = questionaryFragment.getPreguntas();
                    Respuesta opcionChecked = (Respuesta) compoundButton.getTag();
                    MostrarSiSelecciona mostrarSiSelecciona = Utils.infoMostrarSiSelecciona(opcionChecked);
                    if( compoundButton.isChecked() ){
                        if ( mostrarSiSelecciona != null ) {
                            Log.d(CustomConstants.TAG_LOG, "Radio - Checked - Muestra preguntas");
                            showQuestions(preguntas, mostrarSiSelecciona);
                            showSurvey(cuestionario, pregunta, opcionChecked, mostrarSiSelecciona);
                        }
                        if( respuesta.getFinalizarSiSelecciona() ) {
                            QuestionaryActivity.btnNext.setText(R.string.texto_terminar);
                            hideSurvey(cuestionario, pregunta);
                        } else {
                            QuestionaryActivity.btnNext.setText(R.string.texto_siguiente);
                        }

                    }
                    if( !compoundButton.isChecked() ){
                        if ( mostrarSiSelecciona != null ) {
                            Log.d(CustomConstants.TAG_LOG, "Radio - No Checked - Oculta preguntas");
                            hideQuestions(preguntas, mostrarSiSelecciona, encuestaRegistroId);
                            //hideSurvey(pregunta, opcionChecked, mostrarSiSelecciona);
                        }
                        //Eliminamos la respuesta de la pregunta que se deschequea
                        if (encuestaRegistroId > 0L) {
                            Executors.newSingleThreadExecutor().execute(() -> {
                                String respuestaId = String.valueOf(opcionChecked.getRespuestaId());
                                encuestaService.eliminarEncuestaRegistroByPregtIdAndResp(encuestaRegistroId, pregunta.getPreguntaId(), respuestaId);
                            });
                        }
                    }
                }
            });
        }
        if(pregunta.getDescripcion() != null && !pregunta.getDescripcion().equals("")){
            labelDescription.setVisibility(View.VISIBLE);
            labelDescription.setText(String.valueOf(pregunta.getDescripcion()));
        }else {
            labelDescription.setVisibility(View.GONE);
        }

        return status[0];
    }


    @Override
    public boolean save(Encuesta encuesta, Cuestionario cuestionario, Pregunta pregunta, Long encuestaRegistroId) {
        final boolean[] estatus = new boolean[1];
        Log.d(CustomConstants.TAG_LOG, "SelectFragment.save()");
        Executors.newSingleThreadExecutor().execute(() -> {
            //RespuestaDto respuesta =  null;
            if (radioGroup.getChildCount() > 0) {
                int buttonID = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton)radioGroup.findViewById(buttonID);
                if (radioButton != null) {
                    Respuesta respuesta = (Respuesta) radioButton.getTag();
                    String  opcion = String.valueOf(respuesta.getRespuestaId());
                    Log.i(CustomConstants.TAG_LOG, "SelectFragment.save() - GUARDAR RESPUESTA: "+respuesta.toString());
                    //Logica para guardar informacion localmente
                    this.encuestaService.encuestaRespuesta(encuesta, cuestionario, pregunta, opcion, encuestaRegistroId);
                    Map<Long, Long> preguntaRespuesta = new HashMap<>();
                    preguntaRespuesta.put(pregunta.getPreguntaId(), respuesta.getRespuestaId());

                }else {
                    //Log.i(this, "save %s none selected", question.name);
                    //answer.value = null;
                }
            }
            else {
                //answer.value = null;
            }
            //answer.save();
            if (pregunta.getRequerido()) {
                radioGroup.requestFocus();
                // Toast.makeText(getContext(), R.string.seleccion_requerida, Toast.LENGTH_SHORT).show();
                estatus[0] = false;
            }
           /* if (pregunta.getRequerido() && respuesta == null) {
                radioGroup.requestFocus();
               // Toast.makeText(getContext(), R.string.seleccion_requerida, Toast.LENGTH_SHORT).show();
                estatus[0] = false;
            }*/
        });
        return estatus[0];
    }

}
