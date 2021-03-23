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
* Copyright 2021 CysOut Solutions and SouSystems
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
**/
public class SelectFragment extends WidgetFragment {
    private RadioGroup radioGroup;
    private PrivateSurveyService privateSurveyService;

    public SelectFragment() {
        //
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
        Log.i(CustomConstants.TAG_LOG, "SelectFragment - load(Questionnaire questionnaire, Question question)");
        Long surveyRecordId = Utils.findPreferenceLong(getContext(), CustomConstants.PREFERENCE_NAME_QUESTIONNAIRE, CustomConstants.QUESTIONNAIRE_REGISTRATION_ID);
        final boolean[] status = {false};
        radioGroup.removeAllViews();
        for (Answer answer : question.getAnswers()) {
            final RadioButton radioButton = getRadioButton(radioGroup.getChildCount(), answer.getText(), answer, null);
            //Si visible es igual a false ocultamos el radioButton a mostrar de una determinada pregunta
            if( !answer.getVisible() ){
                radioButton.setVisibility(View.GONE);
            }
            //Asignamos informacion al regresar a la encuesta anterior
            if (surveyRecordId > 0L) {
                privateSurveyService.surveyAnswer(surveyRecordId, question.getQuestionId()).observe(getViewLifecycleOwner(), new Observer<SurveyAnswer>() {
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
                    Map<WidgetFragment, Question> answers = questionaryFragment.getQuestions();
                    Answer optionChecked = (Answer) compoundButton.getTag();
                    ShowSelect showSelect = Utils.infoShowSelect(optionChecked);
                    if( compoundButton.isChecked() ){
                        if ( showSelect != null ) {
                            Log.d(CustomConstants.TAG_LOG, "Radio - Checked - Show questions");
                            showQuestions(answers, showSelect);
                            showSurvey(questionnaire, question, optionChecked, showSelect);
                        }
                        if( answer.getFinishSelect() ) {
                            QuestionaryActivity.btnNext.setText(R.string.finish);
                            hideSurvey(questionnaire, question);
                        } else {
                            QuestionaryActivity.btnNext.setText(R.string.next);
                        }

                    }
                    if( !compoundButton.isChecked() ){
                        if ( showSelect != null ) {
                            Log.d(CustomConstants.TAG_LOG, "Radio - No Checked - Hide questions");
                            hideQuestions(answers, showSelect, surveyRecordId);
                            //hideSurvey(pregunta, optionChecked, mostrarSiSelecciona);
                        }
                        //Eliminamos la respuesta de la pregunta que se deschequea
                        if (surveyRecordId > 0L) {
                            Executors.newSingleThreadExecutor().execute(() -> {
                                String respuestaId = String.valueOf(optionChecked.getAnswerId());
                                privateSurveyService.deleteSurveyRecordByQuestionIdAndAnswer(surveyRecordId, question.getQuestionId(), respuestaId);
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
    public boolean save(Survey survey, Questionnaire questionnaire, Question question, Long surveyRecordId) {
        final boolean[] status = new boolean[1];
        Log.d(CustomConstants.TAG_LOG, "SelectFragment.save()");
        Executors.newSingleThreadExecutor().execute(() -> {
            //RespuestaDto respuesta =  null;
            if (radioGroup.getChildCount() > 0) {
                int buttonID = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton)radioGroup.findViewById(buttonID);
                if (radioButton != null) {
                    Answer answer = (Answer) radioButton.getTag();
                    String  option = String.valueOf(answer.getAnswerId());
                    Log.i(CustomConstants.TAG_LOG, "SelectFragment.save() - Save answer: "+ answer.toString());
                    //Logica para guardar informacion localmente
                    this.privateSurveyService.surveyAnswer(survey, questionnaire, question, option, surveyRecordId);
                    Map<Long, Long> questionAnswer = new HashMap<>();
                    questionAnswer.put(question.getQuestionId(), answer.getAnswerId());

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
                status[0] = false;
            }
           /* if (pregunta.getRequerido() && respuesta == null) {
                radioGroup.requestFocus();
               // Toast.makeText(getContext(), R.string.seleccion_requerida, Toast.LENGTH_SHORT).show();
                status[0] = false;
            }*/
        });
        return status[0];
    }

}
