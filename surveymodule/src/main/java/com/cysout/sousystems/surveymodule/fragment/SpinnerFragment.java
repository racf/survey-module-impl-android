package com.cysout.sousystems.surveymodule.fragment;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.Executors;

import com.cysout.sousystems.surveymodule.R;
import com.cysout.sousystems.surveymodule.entity.Cuestionario;
import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.SurveyAnswer;
import com.cysout.sousystems.surveymodule.entity.Pregunta;
import com.cysout.sousystems.surveymodule.entity.Respuesta;
import com.cysout.sousystems.surveymodule.service.EncuestaService;
import com.cysout.sousystems.surveymodule.service.impl.EncuestaServiceImpl;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
import com.cysout.sousystems.surveymodule.utils.Utils;

/**
 *Developed by cysout.com and sousystems.com.mx
 *Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class SpinnerFragment extends WidgetFragment {
    private Spinner spinnerCustom;
    SpinnerAdapter adapter;
    Respuesta respuesta = null;

    private EncuestaService encuestaService;
    public SpinnerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spinner, container, false);
        //Cargamos el servicio para gestionar encuestas
        this.encuestaService = new ViewModelProvider(this).get(EncuestaServiceImpl.class);
        bindView(view);
        return view;
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);
        spinnerCustom = view.findViewById(R.id.spinnerCustom);
    }

    /**
     * Método que carga la información del Spinner en orden
     * @param answers lista de respuestas que se mostraran en el Spinner
     * @param selectedAnswer Respuesta seleccionada en el orden que se mostrara el Spinner, si es null
     *                       la información se mostrara de manera por default
     */
    private void loadSpinner(List<Respuesta> answers, Respuesta selectedAnswer){
        int listSize = answers.size();
        if( selectedAnswer != null ) {
            answers.remove(selectedAnswer);
            answers.add(0, selectedAnswer);
            if( answers.get(listSize-1).getRespuestaId() != CustomConstants.LONG_0L){
                Log.i(CustomConstants.TAG_LOG, "AGREGO DEFAULT 1");
                answers.add(listSize, Utils.getRespuestaSpinnerDefault(getContext()));
            }
        } else {
            if( answers.get(CustomConstants.INT_0).getRespuestaId() != CustomConstants.LONG_0L){
                Log.i(CustomConstants.TAG_LOG, "AGREGO DEFAULT 2");
                answers.add(CustomConstants.INT_0, Utils.getRespuestaSpinnerDefault(getContext()));
            }
        }
        adapter = new com.cysout.sousystems.surveymodule.adapter.SpinnerAdapter(getContext(), R.layout.spinner_item_layout, answers);
        spinnerCustom.setAdapter(adapter);
        spinnerCustom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if( view != null ){
                    TextView tvTitleSpinner = view.findViewById(R.id.tvTitleSpinner);
                    respuesta = (Respuesta) tvTitleSpinner.getTag();
                    Log.i(CustomConstants.TAG_LOG, "SpinnerFragment - SELECTED "+respuesta.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    @Override
    public boolean load(Cuestionario cuestionario, Pregunta pregunta) {
        //boolean status = false;
        Log.i(CustomConstants.TAG_LOG, "SpinnerFragment - load(Cuestionario cuestionario, Pregunta pregunta)");
        Long encuestaRegistroId = Utils.findPreferenceLong(getContext(), CustomConstants.PREFERENCE_NAME_CUESTIONARIO, CustomConstants.CUESTIONARIO_REGISTRO_ID);
        List<Respuesta> listRespuesta = pregunta.getRespuestas();
        //Asignamos informacion al regresar a la encuesta anterior
        if (encuestaRegistroId > 0L) {
            encuestaService.encuestaRespuestaByRegistroIdAndPregId(encuestaRegistroId, pregunta.getPreguntaId()).observe(getViewLifecycleOwner(), new Observer<SurveyAnswer>() {
                @Override
                public void onChanged(SurveyAnswer surveyAnswer) {
                    if(surveyAnswer != null) {
                        if(pregunta.getPreguntaId() == surveyAnswer.getPreguntaId()){
                            Log.i(CustomConstants.TAG_LOG, "ORDENAR INFORMACION "+ surveyAnswer.toString());
                            Long respuestaId = Long.parseLong(surveyAnswer.getRespuesta());
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                               Respuesta respSelected = listRespuesta.stream()
                                                .filter(resp -> resp.getRespuestaId() == respuestaId)
                                                .findAny()
                                                .orElse(null);
                                loadSpinner(listRespuesta, respSelected);
                            } else {
                                Respuesta respSelected = null;
                                for ( Respuesta respuesta : listRespuesta ) {
                                    if ( respuesta.getRespuestaId() == respuestaId ) {
                                        respSelected = respuesta;
                                        break;
                                    }
                                }
                                loadSpinner(listRespuesta, respSelected);
                                Log.i(CustomConstants.TAG_LOG, "VERSIONES MENORES LOGICA ");
                            }
                        }
                    } else {
                        loadSpinner(listRespuesta, null);
                    }
                }
            });
        }else {
            loadSpinner(listRespuesta, null);
        }
        return true;
    }

    @Override
    public boolean save(Survey survey, Cuestionario cuestionario, Pregunta pregunta, Long encuestaRegistroId) {
        //boolean status = false;
        Log.i(CustomConstants.TAG_LOG, "SpinnerFragment.save()");
        //if ( spinnerCustom != null){
            Executors.newSingleThreadExecutor().execute(() -> {
                if(respuesta != null && respuesta.getRespuestaId() > CustomConstants.LONG_0L){
                    Log.i(CustomConstants.TAG_LOG, "RESPUESTA SpinnerFragment "+respuesta.toString());
                    String respuestaString  = String.valueOf(respuesta.getRespuestaId());
                    //Logica del guardado de la información
                    this.encuestaService.encuestaRespuesta(survey, cuestionario, pregunta, respuestaString, encuestaRegistroId);
                } else {
                    Log.i(CustomConstants.TAG_LOG, "NO HA SELECCIONADO");
                    if( !pregunta.getRequerido() ) {
                        this.encuestaService.eliminarEncuestaRegistroByPreguntaId(encuestaRegistroId, pregunta.getPreguntaId());
                    }
                }
            });
        //}
        return true;
    }
}
