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
import com.cysout.sousystems.surveymodule.service.PrivateSurveyService;
import com.cysout.sousystems.surveymodule.service.impl.PrivateSurveyServiceImpl;
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
    private PrivateSurveyService privateSurveyService;
    public CheckBoxFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_box, container, false);
        //Cargamos el servicio para gestionar encuestas
        this.privateSurveyService = new ViewModelProvider(this).get(PrivateSurveyServiceImpl.class);
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
        Log.i(CustomConstants.TAG_LOG, "CheckBoxFragment - load(Questionnaire questionnaire, Question question)");
        Long surveyRecordId = Utils.findPreferenceLong(getContext(), CustomConstants.PREFERENCE_NAME_CUESTIONARIO, CustomConstants.CUESTIONARIO_REGISTRO_ID);
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
            if (surveyRecordId > 0L) {
                String answerId = String.valueOf(answer.getAnswerId());
                privateSurveyService.encuestaRespuestaByRegtroIdAndPregIdAndRespId(surveyRecordId, question.getQuestionId(), answerId).observe(getViewLifecycleOwner(), new Observer<SurveyAnswer>() {
                    @Override
                    public void onChanged(SurveyAnswer surveyAnswer) {
                        if(surveyAnswer != null) {
                            if(question.getQuestionId() == surveyAnswer.getQuestionId() && answerId.equalsIgnoreCase(surveyAnswer.getAnswer())){
                                checkBox.setChecked(CustomConstants.TRUE);
                            }
                        }
                    }
                });
            }
            checkBoxes.add(checkBox);
            checkboxesLinerLayout.addView(checkBox);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    QuestionaryFragment questionaryFragment = (QuestionaryFragment) getParentFragment();
                    Map<WidgetFragment, Question> preguntas = questionaryFragment.getQuestions();
                    Answer optionChecked = (Answer) compoundButton.getTag();
                    ShowSelect showSelect = Utils.infoMostrarSiSelecciona(optionChecked);
                    if(compoundButton.isChecked()){
                        if (showSelect != null) {
                            Log.d(CustomConstants.TAG_LOG, "CheckBox - Checked - Show questions");
                            showQuestions(preguntas, showSelect);
                            showSurvey(questionnaire, question, optionChecked, showSelect);
                        }
                        if( answer.getFinishSelect() ) {
                            QuestionaryActivity.btnNext.setText(R.string.finish);
                            hideSurvey(questionnaire, question);
                        } else {
                            QuestionaryActivity.btnNext.setText(R.string.next);
                        }
                    }
                    if(!compoundButton.isChecked()){
                        Log.d(CustomConstants.TAG_LOG, "CheckBox - No checked - Hide questions "  + optionChecked.getText());
                        if (showSelect != null) {
                           hideQuestions(preguntas, showSelect, surveyRecordId);
                           //hideSurvey(pregunta, opcionChecked, mostrarSiSelecciona);
                        }
                        //Eliminamos la respuesta de la pregunta que se deschequea
                        if (surveyRecordId > 0L) {
                            Executors.newSingleThreadExecutor().execute(() -> {
                                String respuestaId = String.valueOf(optionChecked.getAnswerId());
                                privateSurveyService.eliminarEncuestaRegistroByPregtIdAndResp(surveyRecordId, question.getQuestionId(), respuestaId);
                            });
                        }
                    }
                }
            });
        }
        return status[0];
    }

    @Override
    public boolean save(Survey survey, Questionnaire questionnaire, Question question, Long surveyRecordId) {
        final boolean[] status = new boolean[1];
        Log.d(CustomConstants.TAG_LOG, "CheckboxFragment.save()");
        Executors.newSingleThreadExecutor().execute(() -> {
            for (CheckBox checkBox: checkBoxes){
                if (checkBox.isChecked()){
                    Answer answer = (Answer) checkBox.getTag();
                    String  option = String.valueOf(answer.getAnswerId());
                    Log.i(CustomConstants.TAG_LOG, "CheckboxFragment.save() - Save answer: "+ answer.toString());
                    //String  respuesta = String.valueOf(opcion.getValor());
                    //Logica para guardar informacion
                    this.privateSurveyService.encuestaRespuesta(survey, questionnaire, question, option, surveyRecordId);
                    Map<Long, Long> questionAnswer = new HashMap<>();
                    questionAnswer.put(question.getQuestionId(), answer.getAnswerId());

                }
            }
            status[0] = true;
        });
        return status[0];
    }
}
