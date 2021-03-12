package com.cysout.sousystems.surveymodule.fragment;


import android.os.Bundle;
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
import com.cysout.sousystems.surveymodule.entity.Answer;
import com.cysout.sousystems.surveymodule.entity.Question;
import com.cysout.sousystems.surveymodule.entity.Questionnaire;
import com.cysout.sousystems.surveymodule.entity.ShowSelect;
import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.SurveyAnswer;
import com.cysout.sousystems.surveymodule.service.PrivateSurveyService;
import com.cysout.sousystems.surveymodule.service.impl.PrivateSurveyServiceImpl;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
import com.cysout.sousystems.surveymodule.utils.Utils;
import com.cysout.sousystems.surveymodule.view.QuestionaryActivity;

/**
 *Developed by cysout.com and sousystems.com.mx
 *Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class SelectFragment extends WidgetFragment {

   // private NestedScrollView scrollView;
    private RadioGroup radioGroup;
    private PrivateSurveyService privateSurveyService;

    public SelectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select, container, false);
        //Cargamos el servicio para gestionar encuestas
        this.privateSurveyService = new ViewModelProvider(this).get(PrivateSurveyServiceImpl.class);
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
    public boolean load(Questionnaire questionnaire, Question question) {
        Log.i(CustomConstants.TAG_LOG, "SelectFragment - load(Cuestionario cuestionario, Pregunta pregunta)");
        Long encuestaRegistroId = Utils.findPreferenceLong(getContext(), CustomConstants.PREFERENCE_NAME_CUESTIONARIO, CustomConstants.CUESTIONARIO_REGISTRO_ID);
        final boolean[] status = {false};
        radioGroup.removeAllViews();
        for (Answer answer : question.getAnswers()) {
            final RadioButton radioButton = getRadioButton(radioGroup.getChildCount(), answer.getText(), answer, null);
            //Si visible es igual a false ocultamos el radioButton a mostrar de una determinada pregunta
            if( !answer.getVisible() ){
                radioButton.setVisibility(View.GONE);
            }
            //Asignamos informacion al regresar a la encuesta anterior
            if (encuestaRegistroId > 0L) {
                privateSurveyService.encuestaRespuestaByRegistroIdAndPregId(encuestaRegistroId, question.getQuestionId()).observe(getViewLifecycleOwner(), new Observer<SurveyAnswer>() {
                    @Override
                    public void onChanged(SurveyAnswer surveyAnswer) {
                        if(surveyAnswer != null) {
                           if(question.getQuestionId() == surveyAnswer.getQuestionId() && String.valueOf(answer.getAnswerId()).equalsIgnoreCase(surveyAnswer.getAnswer())){
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
                    Map<WidgetFragment, Question> preguntas = questionaryFragment.getPreguntas();
                    Answer opcionChecked = (Answer) compoundButton.getTag();
                    ShowSelect showSelect = Utils.infoMostrarSiSelecciona(opcionChecked);
                    if( compoundButton.isChecked() ){
                        if ( showSelect != null ) {
                            Log.d(CustomConstants.TAG_LOG, "Radio - Checked - Muestra preguntas");
                            showQuestions(preguntas, showSelect);
                            showSurvey(questionnaire, question, opcionChecked, showSelect);
                        }
                        if( answer.getFinishSelect() ) {
                            QuestionaryActivity.btnNext.setText(R.string.texto_terminar);
                            hideSurvey(questionnaire, question);
                        } else {
                            QuestionaryActivity.btnNext.setText(R.string.texto_siguiente);
                        }

                    }
                    if( !compoundButton.isChecked() ){
                        if ( showSelect != null ) {
                            Log.d(CustomConstants.TAG_LOG, "Radio - No Checked - Oculta preguntas");
                            hideQuestions(preguntas, showSelect, encuestaRegistroId);
                            //hideSurvey(pregunta, opcionChecked, mostrarSiSelecciona);
                        }
                        //Eliminamos la respuesta de la pregunta que se deschequea
                        if (encuestaRegistroId > 0L) {
                            Executors.newSingleThreadExecutor().execute(() -> {
                                String respuestaId = String.valueOf(opcionChecked.getAnswerId());
                                privateSurveyService.eliminarEncuestaRegistroByPregtIdAndResp(encuestaRegistroId, question.getQuestionId(), respuestaId);
                            });
                        }
                    }
                }
            });
        }
        if(question.getDescription() != null && !question.getDescription().equals("")){
            labelDescription.setVisibility(View.VISIBLE);
            labelDescription.setText(String.valueOf(question.getDescription()));
        }else {
            labelDescription.setVisibility(View.GONE);
        }

        return status[0];
    }


    @Override
    public boolean save(Survey survey, Questionnaire questionnaire, Question question, Long encuestaRegistroId) {
        final boolean[] estatus = new boolean[1];
        Log.d(CustomConstants.TAG_LOG, "SelectFragment.save()");
        Executors.newSingleThreadExecutor().execute(() -> {
            //RespuestaDto respuesta =  null;
            if (radioGroup.getChildCount() > 0) {
                int buttonID = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton)radioGroup.findViewById(buttonID);
                if (radioButton != null) {
                    Answer answer = (Answer) radioButton.getTag();
                    String  opcion = String.valueOf(answer.getAnswerId());
                    Log.i(CustomConstants.TAG_LOG, "SelectFragment.save() - GUARDAR RESPUESTA: "+ answer.toString());
                    //Logica para guardar informacion localmente
                    this.privateSurveyService.encuestaRespuesta(survey, questionnaire, question, opcion, encuestaRegistroId);
                    Map<Long, Long> preguntaRespuesta = new HashMap<>();
                    preguntaRespuesta.put(question.getQuestionId(), answer.getAnswerId());

                }else {
                    //Log.i(this, "save %s none selected", question.name);
                    //answer.value = null;
                }
            }
            else {
                //answer.value = null;
            }
            //answer.save();
            if (question.getRequired()) {
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
