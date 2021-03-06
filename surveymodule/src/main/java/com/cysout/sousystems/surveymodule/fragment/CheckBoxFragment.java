package com.cysout.sousystems.surveymodule.fragment;

import android.os.Bundle;

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
import com.cysout.sousystems.surveymodule.entity.Answer;
import com.cysout.sousystems.surveymodule.entity.Question;
import com.cysout.sousystems.surveymodule.entity.Questionnaire;
import com.cysout.sousystems.surveymodule.entity.ShowSelect;
import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.SurveyAnswer;
import com.cysout.sousystems.surveymodule.service.EncuestaService;
import com.cysout.sousystems.surveymodule.service.impl.EncuestaServiceImpl;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
import com.cysout.sousystems.surveymodule.utils.Utils;
import com.cysout.sousystems.surveymodule.view.QuestionaryActivity;

/**
 *Developed by cysout.com and sousystems.com.mx
 *Contact info@cysout.com or contacto@sousystems.com.mx
**/
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
    public boolean load(Questionnaire questionnaire, Question question) {
        Log.i(CustomConstants.TAG_LOG, "CheckBoxFragment - load(Cuestionario cuestionario, Pregunta pregunta)");
        Long encuestaRegistroId = Utils.findPreferenceLong(getContext(), CustomConstants.PREFERENCE_NAME_CUESTIONARIO, CustomConstants.CUESTIONARIO_REGISTRO_ID);
        final boolean[] status = {false};
        checkboxesLinerLayout.removeAllViews();
        checkBoxes = new ArrayList<>();
        for (Answer answer : question.getAnswers()) {
            status[0] = true;
            int checkBoxId =  Integer.parseInt(question.getQuestionId()+""+ answer.getAnswerId());
            CheckBox checkBox = getCheckBox(checkBoxId, answer.getText(), answer);
            //Si visible es igual a false ocultamos el checkbox, a mostrar de una determinada pregunta
            if( !answer.getVisible() ){
                checkBox.setVisibility(View.GONE);
            }
            //Asignamos informacion al regresar a la encuesta anterior
            if (encuestaRegistroId > 0L) {
                String respuestaId = String.valueOf(answer.getAnswerId());
                encuestaService.encuestaRespuestaByRegtroIdAndPregIdAndRespId(encuestaRegistroId, question.getQuestionId(), respuestaId).observe(getViewLifecycleOwner(), new Observer<SurveyAnswer>() {
                    @Override
                    public void onChanged(SurveyAnswer surveyAnswer) {
                        Log.i(CustomConstants.TAG_LOG, "--------------------------- CHANGE - CheckBoxFragment--------------------------------------");
                        if(surveyAnswer != null) {
                            Log.i(CustomConstants.TAG_LOG, "NO NULL - encuestaRespuesta");
                            if(question.getQuestionId() == surveyAnswer.getQuestionId() && respuestaId.equalsIgnoreCase(surveyAnswer.getAnswer())){
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
                    Map<WidgetFragment, Question> preguntas = questionaryFragment.getPreguntas();
                    Answer opcionChecked = (Answer) compoundButton.getTag();
                    ShowSelect showSelect = Utils.infoMostrarSiSelecciona(opcionChecked);
                    if(compoundButton.isChecked()){
                        if (showSelect != null) {
                            Log.d(CustomConstants.TAG_LOG, "CheckBox - Checked - Muestra preguntas");
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
                    if(!compoundButton.isChecked()){
                        Log.d(CustomConstants.TAG_LOG, "CheckBox - No checked - Oculta preguntas "  + opcionChecked.getText());
                        if (showSelect != null) {
                           hideQuestions(preguntas, showSelect, encuestaRegistroId);
                           //hideSurvey(pregunta, opcionChecked, mostrarSiSelecciona);
                        }
                        //Eliminamos la respuesta de la pregunta que se deschequea
                        if (encuestaRegistroId > 0L) {
                            Executors.newSingleThreadExecutor().execute(() -> {
                                String respuestaId = String.valueOf(opcionChecked.getAnswerId());
                                encuestaService.eliminarEncuestaRegistroByPregtIdAndResp(encuestaRegistroId, question.getQuestionId(), respuestaId);
                            });
                        }
                    }
                }
            });
        }
        return status[0];
    }

    @Override
    public boolean save(Survey survey, Questionnaire questionnaire, Question question, Long encuestaRegistroId) {
        final boolean[] estatus = new boolean[1];
        Log.d(CustomConstants.TAG_LOG, "CheckboxFragment.save()");
        Executors.newSingleThreadExecutor().execute(() -> {
            for (CheckBox checkBox: checkBoxes){
                if (checkBox.isChecked()){
                    Answer answer = (Answer) checkBox.getTag();
                    String  opcion = String.valueOf(answer.getAnswerId());
                    Log.i(CustomConstants.TAG_LOG, "CheckboxFragment.save() - GUARDAR RESPUESTA: "+ answer.toString());
                    //String  respuesta = String.valueOf(opcion.getValor());
                    //Logica para guardar informacion
                    this.encuestaService.encuestaRespuesta(survey, questionnaire, question, opcion, encuestaRegistroId);
                    Map<Long, Long> preguntaRespuesta = new HashMap<>();
                    preguntaRespuesta.put(question.getQuestionId(), answer.getAnswerId());

                }
            }
            estatus[0] = true;
        });
        return estatus[0];
    }
}
