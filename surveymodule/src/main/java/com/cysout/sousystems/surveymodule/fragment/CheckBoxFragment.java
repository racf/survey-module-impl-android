package com.cysout.sousystems.surveymodule.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class CheckBoxFragment extends WidgetFragment {
    private LinearLayout checkboxesLinerLayout;
    private List<CheckBox> checkBoxes;
    private EncuestaService encuestaService;
    public CheckBoxFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_box, container, false);
        //Cargamos el servicio para gestionar encuestas
        this.encuestaService = new ViewModelProvider(this).get(EncuestaServiceImpl.class);
        bindView(view);
        return view;
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);
        checkboxesLinerLayout = view.findViewById(R.id.checkboxes_liner_layout);
    }

    @Override
    public boolean load(Cuestionario cuestionario, Pregunta pregunta) {
        Log.i(CustomConstants.TAG_LOG, "CheckBoxFragment - load(Cuestionario cuestionario, Pregunta pregunta)");
        Long encuestaRegistroId = Utils.findPreferenceLong(getContext(), CustomConstants.PREFERENCE_NAME_CUESTIONARIO, CustomConstants.CUESTIONARIO_REGISTRO_ID);
        final boolean[] status = {false};
        checkboxesLinerLayout.removeAllViews();
        checkBoxes = new ArrayList<>();
        for (Respuesta respuesta : pregunta.getRespuestas()) {
            status[0] = true;
            int checkBoxId =  Integer.parseInt(pregunta.getPreguntaId()+""+respuesta.getRespuestaId());
            CheckBox checkBox = getCheckBox(checkBoxId, respuesta.getTexto(), respuesta);
            //Si visible es igual a false ocultamos el checkbox, a mostrar de una determinada pregunta
            if( !respuesta.getVisible() ){
                checkBox.setVisibility(View.GONE);
            }
            //Asignamos informacion al regresar a la encuesta anterior
            if (encuestaRegistroId > 0L) {
                String respuestaId = String.valueOf(respuesta.getRespuestaId());
                encuestaService.encuestaRespuestaByRegtroIdAndPregIdAndRespId(encuestaRegistroId, pregunta.getPreguntaId(), respuestaId).observe(getViewLifecycleOwner(), new Observer<EncuestaRespuesta>() {
                    @Override
                    public void onChanged(EncuestaRespuesta encuestaRespuesta) {
                        Log.i(CustomConstants.TAG_LOG, "--------------------------- CHANGE - CheckBoxFragment--------------------------------------");
                        if(encuestaRespuesta != null) {
                            Log.i(CustomConstants.TAG_LOG, "NO NULL - encuestaRespuesta");
                            if(pregunta.getPreguntaId() == encuestaRespuesta.getPreguntaId() && respuestaId.equalsIgnoreCase(encuestaRespuesta.getRespuesta())){
                                checkBox.setChecked(CustomConstants.TRUE);
                                Log.i(CustomConstants.TAG_LOG, "ENTRO CHECK");
                            }
                        }

                        Log.i(CustomConstants.TAG_LOG, "--------------------------- CHANGE - CheckBoxFragment--------------------------------------");
                    }
                });
            }
            checkBoxes.add(checkBox);
            checkboxesLinerLayout.addView(checkBox);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    QuestionaryFragment questionaryFragment = (QuestionaryFragment) getParentFragment();
                    Map<WidgetFragment, Pregunta> preguntas = questionaryFragment.getPreguntas();
                    Respuesta opcionChecked = (Respuesta) compoundButton.getTag();
                    MostrarSiSelecciona mostrarSiSelecciona = Utils.infoMostrarSiSelecciona(opcionChecked);
                    if(compoundButton.isChecked()){
                        if (mostrarSiSelecciona != null) {
                            Log.d(CustomConstants.TAG_LOG, "CheckBox - Checked - Muestra preguntas");
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
                    if(!compoundButton.isChecked()){
                        Log.d(CustomConstants.TAG_LOG, "CheckBox - No checked - Oculta preguntas "  + opcionChecked.getTexto());
                        if (mostrarSiSelecciona != null) {
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
        return status[0];
    }

    @Override
    public boolean save(Encuesta encuesta, Cuestionario cuestionario, Pregunta pregunta, Long encuestaRegistroId) {
        final boolean[] estatus = new boolean[1];
        Log.d(CustomConstants.TAG_LOG, "CheckboxFragment.save()");
        Executors.newSingleThreadExecutor().execute(() -> {
            for (CheckBox checkBox: checkBoxes){
                if (checkBox.isChecked()){
                    Respuesta respuesta = (Respuesta) checkBox.getTag();
                    String  opcion = String.valueOf(respuesta.getRespuestaId());
                    Log.i(CustomConstants.TAG_LOG, "CheckboxFragment.save() - GUARDAR RESPUESTA: "+respuesta.toString());
                    //String  respuesta = String.valueOf(opcion.getValor());
                    //Logica para guardar informacion
                    this.encuestaService.encuestaRespuesta(encuesta, cuestionario, pregunta, opcion, encuestaRegistroId);
                    Map<Long, Long> preguntaRespuesta = new HashMap<>();
                    preguntaRespuesta.put(pregunta.getPreguntaId(), respuesta.getRespuestaId());

                }
            }
            estatus[0] = true;
        });
        return estatus[0];
    }
}
