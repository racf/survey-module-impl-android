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
import com.cysout.sousystems.surveymodule.service.EncuestaService;
import com.cysout.sousystems.surveymodule.service.impl.EncuestaServiceImpl;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
import com.cysout.sousystems.surveymodule.utils.Utils;
import com.cysout.sousystems.surveymodule.validation.TextController;
import com.cysout.sousystems.surveymodule.validation.text.TextFormState;

/**
 *Developed by cysout.com and sousystems.com.mx
 *Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class TextFragment extends WidgetFragment {
    private TextView labelPrefix;
    private EditText editText;
    private TextView labelSuffix;
    private EncuestaService encuestaService;
    private TextController textController;
    public TextFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, container, false);
        //Cargamos el servicio para gestionar encuestas
        this.encuestaService = new ViewModelProvider(this).get(EncuestaServiceImpl.class);
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
        Log.i(CustomConstants.TAG_LOG, "TextFragment - load(Cuestionario cuestionario, Pregunta pregunta)");
        Long encuestaRegistroId = Utils.findPreferenceLong(getContext(), CustomConstants.PREFERENCE_NAME_CUESTIONARIO, CustomConstants.CUESTIONARIO_REGISTRO_ID);
        //Asignamos informacion al regresar a la encuesta anterior
        if (encuestaRegistroId > 0L) {
            encuestaService.encuestaRespuestaByRegistroIdAndPregId(encuestaRegistroId, question.getQuestionId()).observe(getViewLifecycleOwner(), new Observer<SurveyAnswer>() {
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
        }
        if (question.getTypeInput().equalsIgnoreCase("textPersonName")) {
            editText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        }
        if (question.getTypeInput().equalsIgnoreCase("number")) {
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        if (question.getTitle() != null || !question.getTitle().equals("")) {
            editText.setHint(R.string.escribir_aqui_respuesta);
        } else {
            editText.setHint(R.string.escribir_aqui_respuesta);
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
    public boolean save(Survey survey, Questionnaire questionnaire, Question question, Long encuestaRegistroId) {
        final boolean[] estatus = new boolean[1];
        Log.d(CustomConstants.TAG_LOG, "TextFragment.save()");
        Executors.newSingleThreadExecutor().execute(() -> {
            String  respuesta = String.valueOf(editText.getText());
            if(!respuesta.trim().equalsIgnoreCase("")) {
                //Logica del guardado de la información
                this.encuestaService.encuestaRespuesta(survey, questionnaire, question, respuesta, encuestaRegistroId);
                estatus[0] = true;
            }
        });
        return estatus[0];
    }
}
