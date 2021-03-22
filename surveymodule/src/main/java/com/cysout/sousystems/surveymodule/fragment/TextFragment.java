package com.cysout.sousystems.surveymodule.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.Executors;

import com.cysout.sousystems.surveymodule.R;
import com.cysout.sousystems.surveymodule.entity.Questionnaire;
import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.SurveyAnswer;
import com.cysout.sousystems.surveymodule.entity.Question;
import com.cysout.sousystems.surveymodule.service.PrivateSurveyService;
import com.cysout.sousystems.surveymodule.service.impl.PrivateSurveyServiceImpl;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
import com.cysout.sousystems.surveymodule.utils.Utils;
import com.cysout.sousystems.surveymodule.validation.TextController;
import com.cysout.sousystems.surveymodule.validation.text.TextFormState;

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
public class TextFragment extends WidgetFragment {
    private TextView labelPrefix;
    private EditText editText;
    private TextView labelSuffix;
    private PrivateSurveyService privateSurveyService;
    private TextController textController;
    public TextFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, container, false);
        //Cargamos el servicio para gestionar encuestas
        this.privateSurveyService = new ViewModelProvider(this).get(PrivateSurveyServiceImpl.class);
        bindView(view);
        textControllerChange();
        return view;
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);
        labelPrefix = view.findViewById(R.id.label_prefix);
        editText = view.findViewById(R.id.edit_text);
        labelSuffix = view.findViewById(R.id.label_suffix);
        textController = new ViewModelProvider(this).get(TextController.class);
    }
    private void  textControllerChange(){
        textController.getTextFormState().observe(getViewLifecycleOwner(), new Observer<TextFormState>() {
            @Override
            public void onChanged(TextFormState textFormState) {
                if (textFormState == null) {
                    return;
                }
                //boolean isDataValid = textFormState.isDataValid();
                //QuestionaryActivity.btnNext.setEnabled(isDataValid);
                if (textFormState.getTextError() != null) {
                    editText.setError(getString(textFormState.getTextError()));
                }
            }
        });
    }

    @Override
    public boolean load(Questionnaire questionnaire, Question question) {
        Log.i(CustomConstants.TAG_LOG, "TextFragment - load(Questionnaire questionnaire, Question question)");
        Long surveyRecordId = Utils.findPreferenceLong(getContext(), CustomConstants.PREFERENCE_NAME_CUESTIONARIO, CustomConstants.CUESTIONARIO_REGISTRO_ID);
        //Asignamos informacion al regresar a la encuesta anterior
        if (surveyRecordId > 0L) {
            privateSurveyService.surveyAnswer(surveyRecordId, question.getQuestionId()).observe(getViewLifecycleOwner(), new Observer<SurveyAnswer>() {
                @Override
                public void onChanged(SurveyAnswer surveyAnswer) {
                    if(surveyAnswer != null) {
                        editText.setText(String.valueOf(surveyAnswer.getAnswer()));
                    }
                }
            });
        }
        if (question.getTypeInput().equalsIgnoreCase("phone")) {
            editText.setInputType(InputType.TYPE_CLASS_PHONE);
        } else if (question.getTypeInput().equalsIgnoreCase("textPersonName")) {
            editText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        } else if (question.getTypeInput().equalsIgnoreCase("number")) {
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else {
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
        }

        if (question.getTitle() != null || !question.getTitle().equals("")) {
            editText.setHint(R.string.message_write_answer);
        } else {
            editText.setHint(R.string.message_write_answer);
        }

        if (question.getDescription() != null || !question.getDescription().equals("")) {
            labelDescription.setText(String.valueOf(question.getDescription()));
        } else {
            labelDescription.setVisibility(View.GONE);
        }
        labelPrefix.setVisibility(View.GONE);
        labelSuffix.setVisibility(View.GONE);

        //Validamos campos que sean requeridos para la validación minima
        if (question.getRequired()) {
            //Evento para la validación de los campos de login
            TextWatcher afterTextChangedListener = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    textController.textDataChanged(editText.getText().toString(), question);
                }
            };
            this.editText.addTextChangedListener(afterTextChangedListener);
        }

        return false;
    }

    @Override
    public boolean save(Survey survey, Questionnaire questionnaire, Question question, Long surveyRecordId) {
        final boolean[] status = new boolean[1];
        Log.d(CustomConstants.TAG_LOG, "TextFragment.save()");
        Executors.newSingleThreadExecutor().execute(() -> {
            String answer = String.valueOf(editText.getText());
            if(!answer.trim().equalsIgnoreCase("")) {
                //Logica del guardado de la información
                this.privateSurveyService.surveyAnswer(survey, questionnaire, question, answer, surveyRecordId);
                status[0] = true;
            }
        });
        return status[0];
    }
}
