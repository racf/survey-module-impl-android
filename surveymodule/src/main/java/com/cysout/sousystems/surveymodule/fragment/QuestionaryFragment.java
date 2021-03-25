package com.cysout.sousystems.surveymodule.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cysout.sousystems.surveymodule.R;
import com.cysout.sousystems.surveymodule.entity.Questionnaire;
import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.Question;
import com.cysout.sousystems.surveymodule.service.PrivateSurveyService;
import com.cysout.sousystems.surveymodule.service.impl.PrivateSurveyServiceImpl;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
import com.cysout.sousystems.surveymodule.utils.Utils;

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
public class QuestionaryFragment extends Fragment implements WidgetFragment.FragmentCallback {
    private Questionnaire questionnaire;
    private Survey survey;
    private Map<WidgetFragment, Question> questions = new HashMap<WidgetFragment, Question>();
    private LinearLayout fieldset;
    private TextView tvQuestionnaireTitle;
    private PrivateSurveyService privateSurveyService;
    private static final int NUMBER_OF_THREADS = 10;
    public static final ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private String startDate = "";
    public QuestionaryFragment(Survey survey, Questionnaire questionnaire) {
        this.survey = survey;
        this.questionnaire = questionnaire;
        this.startDate = Utils.dateTime();
    }
    public Questionnaire getQuestionnaire(){
      return this.questionnaire;
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_questionary, container, CustomConstants.FALSE);
        fieldset = rootView.findViewById(R.id.fieldset);
        tvQuestionnaireTitle = rootView.findViewById(R.id.tvQuestionnaireTitle);
        tvQuestionnaireTitle.setText(this.questionnaire.getTitle());
        return rootView;
    }

    public Map<WidgetFragment, Question> getQuestions(){
        return this.questions;
    }
    @Override
    public void onResume() {
        super.onResume();
        load();
        this.privateSurveyService = new PrivateSurveyServiceImpl(getActivity().getApplication());
    }

    public void save(){
        Log.d(CustomConstants.TAG_LOG, "QuestionaryFragment - save()");
        executorService.execute(() -> {
            Long surveyRecordId = Utils.findPreferenceLong(getContext(), CustomConstants.PREFERENCE_NAME_QUESTIONNAIRE, CustomConstants.QUESTIONNAIRE_REGISTRATION_ID);
            //Si es la primera vez que se guarda una encuesta_registro, en caso contrario obtiene el identificador que se ha generado anteriormente
            if( surveyRecordId == CustomConstants.LONG_0L){
                surveyRecordId = privateSurveyService.surveyRecord(survey, CustomConstants.PENDING, this.startDate, this.startDate);
                    Log.d(CustomConstants.TAG_LOG, "Information is saved for the first time " + surveyRecordId + " --- "+ this.startDate);
                    //Guardamos el ID auto-increment de la encuesta que se esta encuestaRegistroId
                    Utils.saveOnPreferenceLong(getContext(), CustomConstants.PREFERENCE_NAME_QUESTIONNAIRE, CustomConstants.QUESTIONNAIRE_REGISTRATION_ID, surveyRecordId);
            }
            for ( Map.Entry<WidgetFragment, Question> entry : questions.entrySet() ) {
                WidgetFragment widgetFragment = entry.getKey();
                Question question = entry.getValue();
                Log.d(CustomConstants.TAG_LOG, "id: "+ question.getQuestionId() + " title: " + question.getTitle());
                widgetFragment.save(survey, questionnaire, question, surveyRecordId);
            }
        });
    }
    // onResumeFragment/
    public void load() {
        //executorService.execute(() -> {
            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            for (WidgetFragment widgetFragment : questions.keySet()) {
                fragmentTransaction.remove(widgetFragment);
            }
            questions.clear();
            fieldset.removeAllViews();
            for (Question question : questionnaire.getQuestions()) {
                WidgetFragment widgetFragment = null;
                if (question.getType().equalsIgnoreCase(CustomConstants.TEXT)) {
                    widgetFragment = new TextFragment();
                } else if(question.getType().equalsIgnoreCase(CustomConstants.RADIOGROUP)){
                    widgetFragment = new SelectFragment();
                } else if(question.getType().equalsIgnoreCase(CustomConstants.CHECKBOX)){
                    widgetFragment = new CheckBoxFragment();
                } else if(question.getType().equalsIgnoreCase(CustomConstants.SELECT)){
                    widgetFragment = new SpinnerFragment();
                } else if(question.getType().equalsIgnoreCase(CustomConstants.LABEL)){
                    widgetFragment = new LabelFragment();
                }
                if(widgetFragment != null){
                    widgetFragment.setCallback(this);
                    widgetFragment.setMenuVisibility(CustomConstants.FALSE);
                    fragmentTransaction.add(R.id.fieldset, widgetFragment);
                    questions.put(widgetFragment, question);
                }
            }
            fragmentTransaction.commit();
        //});
    }


    @Override
    public void onFragmentResume(WidgetFragment widgetFragment) {
        //Log.d(CustomConstants.TAG_LOG, "onFragmentResume Tamanio Lista " +preguntas.size());
       // for (WidgetFragment widgetFragment : preguntas.keySet()) {
            Question question = questions.get(widgetFragment);
            // Answer answer = submission.answerForQuestion(question);
            if (question != null) { //&& answer != null) {
                //Log.d(CustomConstants.TAG_LOG, "onFragmentResume " +pregunta.getTitulo());
                widgetFragment.init(questionnaire, question);
                widgetFragment.load(questionnaire, question);
                //Log.d(CustomConstants.TAG_LOG,"Count " + fieldset.getChildCount());
            } else {
                //Log.i(this, "Fragment %s Question NULL Answer NULL", baseFragment);
            }
     //   }
    }
}
