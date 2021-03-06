package com.cysout.sousystems.surveymodule.fragment;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import com.cysout.sousystems.surveymodule.R;
import com.cysout.sousystems.surveymodule.controller.AnswerShowQuestionnairesController;
import com.cysout.sousystems.surveymodule.entity.Answer;
import com.cysout.sousystems.surveymodule.entity.AnswerShowQuestionnaires;
import com.cysout.sousystems.surveymodule.entity.Question;
import com.cysout.sousystems.surveymodule.entity.Questionnaire;
import com.cysout.sousystems.surveymodule.entity.ShowAnswers;
import com.cysout.sousystems.surveymodule.entity.ShowQuestionnaires;
import com.cysout.sousystems.surveymodule.entity.ShowQuestions;
import com.cysout.sousystems.surveymodule.entity.ShowSelect;
import com.cysout.sousystems.surveymodule.entity.Survey;
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
public abstract class WidgetFragment extends Fragment {
    private static final Map<String, Class<? extends WidgetFragment>> widgets = new HashMap<>();

    protected TextView labelName;
    protected TextView labelRequired;
    protected TextView labelDescription;
    protected View rootView;
    private FragmentCallback callback;
    //DB
    private AnswerShowQuestionnairesController answerShowQuestionnairesController;
    private PrivateSurveyService privateSurveyService;

    public WidgetFragment(){

    }
    public void setCallback(FragmentCallback fragmentCallback){
        this.callback = fragmentCallback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void bindView(View view){
        rootView = view;
        labelName = view.findViewById(R.id.label_name);
        labelRequired = view.findViewById(R.id.label_required);
        labelDescription = view.findViewById(R.id.label_description);
        answerShowQuestionnairesController = new ViewModelProvider(this).get(AnswerShowQuestionnairesController.class);;//new RespuestaMostrarCuestionariosController(getActivity().getApplication());
        privateSurveyService = new ViewModelProvider(this).get(PrivateSurveyServiceImpl.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.callback.onFragmentResume(this);
    }

    /**
     * Met??do que inicializa el cuestionario
     * @param questionnaire el cuestionar??o de la encuesta
     * @param question pregunta de un determinado cuestionario
     * @return true si contiene pregunta en casa contrario false
     */
    public boolean init(Questionnaire questionnaire, Question question) {
        Log.i(CustomConstants.TAG_LOG, "Start questionnaire - "+ question.toString());
        if (question != null) {
            rootView.setVisibility(View.GONE);
            labelDescription.setVisibility(View.GONE);
            labelRequired.setVisibility(View.GONE);
            labelName.setText(Html.fromHtml(question.getTitle()));
            if(question.getDescription() != null && !question.getDescription().trim().isEmpty()){
                labelDescription.setVisibility(View.VISIBLE);
                labelDescription.setText(Html.fromHtml(question.getDescription()));
            }
            if (question.getRequired()) {
                labelRequired.setVisibility(View.VISIBLE);
            }
            //En esta parte mostramos u ocultamos la preguntas donde su propiedad visible sea true o false respectivamente
            if (question.getVisible()){
                rootView.setVisibility(View.VISIBLE);
            }
            return true;
        } else {
            /* labelName.setText(R.string.question_error);
            labelDescription.setVisibility(View.VISIBLE);
            labelDescription.setText(R.string.question_error_description);*/
            labelRequired.setVisibility(View.GONE);
            rootView.setVisibility(View.GONE);
            return false;
        }
    }


    public abstract boolean load(Questionnaire questionnaire, Question question);

    public abstract boolean save(Survey survey, Questionnaire questionnaire, Question question, Long surveyRecordId);

    protected void showSurvey(Questionnaire questionnaire, Question question, Answer answer, ShowSelect showSelect) {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<ShowQuestionnaires> showQuestionnaires = showSelect.getQuestionnaires();
            if (!Utils.isEmpty(showQuestionnaires)) {
                for (ShowQuestionnaires showQuestionnaire : showQuestionnaires) {
                    AnswerShowQuestionnaires answerShowQuestionnaires = new AnswerShowQuestionnaires();
                    answerShowQuestionnaires.setQuestionnaireId(showQuestionnaire.getQuestionnaireId());
                    answerShowQuestionnaires.setQuestionId(question.getQuestionId());
                    answerShowQuestionnaires.setAnswerId(answer.getAnswerId());
                    answerShowQuestionnaires.setQuestionnaireOriginId(questionnaire.getQuestionnaireId());
                    Long answerShowQuestionnairesId = answerShowQuestionnairesController.insert(answerShowQuestionnaires);
                    Log.i(CustomConstants.TAG_LOG, "WidgetFragment - showSurvey " + answerShowQuestionnairesId+" "+ answerShowQuestionnaires.toString());
                }
            }
        });
    }

    /**
     * M??todo que realiza la validaci??n para mostrar u ocultar una determina encuesta
     * @param questionnaire de donde proviene el siguiente cuestionario a ocultar
     * @param question pregunta que ejecuta la acci??n para mostrar cuestionario
     */
    protected void hideSurvey(Questionnaire questionnaire, Question question) {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<Answer> listAnswers = question.getAnswers();
            if( !Utils.isEmpty(listAnswers) ) {
                for ( Answer auxAnswer : listAnswers){
                    ShowSelect showSelect = Utils.infoShowSelect(auxAnswer);
                    if (showSelect !=null) {
                        List<ShowQuestionnaires> showQuestionnaires = showSelect.getQuestionnaires();
                        if (!Utils.isEmpty(showQuestionnaires)) {
                            answerShowQuestionnairesController.deleteByQuestionId(question.getQuestionId());
                        }
                    } else {
                        answerShowQuestionnairesController.deleteByQuestionnaireOriginId(questionnaire.getQuestionnaireId());
                    }
                }
            } else {
               answerShowQuestionnairesController.deleteByQuestionnaireOriginId(questionnaire.getQuestionnaireId());
            }
        });
    }

    protected void showQuestions(Map<WidgetFragment, Question> questions, ShowSelect showSelect){
        for ( Map.Entry<WidgetFragment, Question> entry : questions.entrySet() ) {
            WidgetFragment widgetFragment = entry.getKey();
            Question question = entry.getValue();
            Log.i(CustomConstants.TAG_LOG, " WidgetFragment - showQuestions");
            //Preguntas
            List<ShowQuestions> mostrarPreguntas = showSelect.getQuestions();
            //Muestra una determinada pregunta completa
            if( !Utils.isEmpty( mostrarPreguntas ) ) {
                for ( ShowQuestions mostrarPregunta : mostrarPreguntas ) {
                    if( question.getQuestionId() == mostrarPregunta.getQuestionId() ) {
                        //Logica para actualizar la base de datos al estatus true de la pregunta
                        //Validamos que la vista no este vac??a esto aplica en el momento que se regresa al cuestionario anterior
                        if ( widgetFragment.rootView != null) {
                            widgetFragment.rootView.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
            //Respuesta
            showHideAnswer(widgetFragment, question, showSelect, CustomConstants.LONG_0L,CustomConstants.INT_1);
        }
    }

    protected void hideQuestions(Map<WidgetFragment, Question> questionMap, ShowSelect showSelect, Long surveyRecordId){
        for (Map.Entry<WidgetFragment, Question> entry : questionMap.entrySet()) {
            WidgetFragment widgetFragment = entry.getKey();
            Question question = entry.getValue();
            Log.i(CustomConstants.TAG_LOG, "WidgetFragment - hideQuestions");
            //Preguntas
            List<ShowQuestions> showQuestions = showSelect.getQuestions();
            if (!Utils.isEmpty(showQuestions)) {
                for (ShowQuestions mostrarPregunta : showQuestions) {
                    if (question.getQuestionId() == mostrarPregunta.getQuestionId()) {
                        //Ocultamos la pregunta anteriormente mostrada
                        widgetFragment.rootView.setVisibility(View.GONE);
                        clearAnswers(widgetFragment, question);
                        //Logica para eliminar preguntas registradas en encuestaRespuesta al ocultar las preguntas que dependen
                        if (surveyRecordId > CustomConstants.LONG_0L) {
                            Executors.newSingleThreadExecutor().execute(() -> {
                                privateSurveyService.deleteSurveyRecordByQuestionId(surveyRecordId, question.getQuestionId());
                            });
                        }
                    }
                }
            }

            //Answer
            showHideAnswer(widgetFragment, question, showSelect, surveyRecordId, CustomConstants.INT_2);
        }
    }
    private void showHideAnswer(WidgetFragment widgetFragment, Question question, ShowSelect showSelect, Long surveyRecordId, int option){
        List<ShowAnswers> showAnswers = showSelect.getAnswers();
        if( !Utils.isEmpty( showAnswers ) ){
            for ( ShowAnswers showAnswer : showAnswers ) {
                if( question.getQuestionId() == showAnswer.getQuestionId() ){
                    for (Answer answer : question.getAnswers() ) {
                        //Validamos que la vista no este vac??a esto aplica en el momento que se regresa al cuestionario anterior
                        if ( widgetFragment.rootView != null) {
                            if(showAnswer.getAnswerId() == answer.getAnswerId()) {
                                String answerStringId = String.valueOf(answer.getAnswerId());
                                if (question.getType().equalsIgnoreCase(CustomConstants.CHECKBOX)) {
                                    int checkBoxId = Integer.parseInt(question.getQuestionId() + "" + answer.getAnswerId());
                                    CheckBox checkBox = widgetFragment.rootView.findViewById(checkBoxId);
                                    if (option == CustomConstants.INT_1) {
                                        checkBox.setVisibility(View.VISIBLE);
                                    } else {
                                        checkBox.setVisibility(View.GONE);
                                        checkBox.setChecked(CustomConstants.FALSE);
                                        deleteAnswer(surveyRecordId, question.getQuestionId(), answerStringId);
                                    }
                                } else if (question.getType().equalsIgnoreCase(CustomConstants.RADIOGROUP)) {
                                    RadioButton radioButton = widgetFragment.rootView.findViewWithTag(answer);
                                    if (option == CustomConstants.INT_1) {
                                        radioButton.setVisibility(View.VISIBLE);
                                    } else {
                                        radioButton.setVisibility(View.GONE);
                                        if (radioButton.isChecked()) {
                                            //Obtenemos el padre que contiene al radioButton en este caso es un RadioGroup
                                            // y posteriormente lo limpiamos
                                            if (radioButton.getParent() instanceof RadioGroup) {
                                                ((RadioGroup) radioButton.getParent()).clearCheck();
                                                deleteAnswer(surveyRecordId, question.getQuestionId(), answerStringId);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    private void deleteAnswer(Long surveyRecordId, Long answerId, String answer){
        if (surveyRecordId > CustomConstants.LONG_0L) {
            Executors.newSingleThreadExecutor().execute(() -> {
                privateSurveyService.deleteSurveyRecordByQuestionIdAndAnswer(surveyRecordId, answerId, answer);
            });
        }
    }
    private void clearAnswers(WidgetFragment widgetFragment, Question question) {
        switch (question.getType()){
            case CustomConstants.TEXT:
                EditText editText = widgetFragment.rootView.findViewById(R.id.edit_text);
                editText.setText("");
                break;
            case CustomConstants.RADIOGROUP:
                clearRadioButton(widgetFragment, question);
                break;
            case CustomConstants.CHECKBOX:
                clearCheckBox(widgetFragment, question);
                break;
            default:
                break;
        }
    }

    private void clearRadioButton(WidgetFragment widgetFragment, Question question){
        //L??gica para limpiar la respuesta seleccionadas anteriormente que dependan de alguna
        // determinada respuesta de una pregunta
        for (Answer answer : question.getAnswers() ) {
            //L??gica para limpiar las respuestas de los RadioButton
            RadioButton radioButton = widgetFragment.rootView.findViewWithTag(answer);
            //Verificamos si el radioButton se encuentra seleccionado, si es asi.
            if( radioButton.isChecked() ) {
                //Obtenemos el padre que contiene al radioButton en este caso es un RadioGroup
                // y posteriormente lo limpiamos
                if(radioButton.getParent() instanceof RadioGroup) {
                    ((RadioGroup)radioButton.getParent()).clearCheck();
                }
            }
        }
    }

    private void clearCheckBox(WidgetFragment widgetFragment, Question question){
        for (Answer answer : question.getAnswers() ) {
            int checkBoxId =  Integer.parseInt(question.getQuestionId()+""+ answer.getAnswerId());
            CheckBox checkBox = widgetFragment.rootView.findViewById(checkBoxId);
            if( checkBox.isChecked() ){
                Log.i(CustomConstants.TAG_LOG, "CHECKBOX - clearCheckBox");
                checkBox.setChecked(CustomConstants.FALSE);
            }
        }
    }

    protected RadioButton getRadioButton(Integer id, String text, Object tag, String value) {
        RadioButton radioButton = new RadioButton(getActivity());
        radioButton.setId(id);
        radioButton.setText(text);
        radioButton.setTag(tag);
        radioButton.setTextAppearance(getActivity(), R.style.RadioButton_Full);
        radioButton.setLayoutParams(new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.MATCH_PARENT,
                RadioGroup.LayoutParams.WRAP_CONTENT));
        if (value != null && value.equals(tag)) {
            radioButton.setChecked(CustomConstants.TRUE);
        }else {
            radioButton.setChecked(CustomConstants.FALSE);
        }
        return radioButton;
    }

    protected TextView getTextView(String text, int left, int top, int right, int bottom) {
        TextView textView = new TextView(getActivity());
        textView.setText(text);
        textView.setTextAppearance(getActivity(), R.style.TextView_Label);
        textView.setPadding(left, top, right, bottom);
        return textView;
    }
    protected RadioGroup getRadioGroup(String tag, int left, int top, int right, int bottom) {
        RadioGroup radioGroup = new RadioGroup(getActivity());
        radioGroup.setPadding(left, top, right, bottom);
        radioGroup.setTag(tag);
        return radioGroup;
    }
    protected CheckBox getCheckBox(Integer id, String text, Object tag){
        final CheckBox checkBox = new CheckBox(getActivity());
        checkBox.setId(id);
        checkBox.setText(text);
        checkBox.setTextAppearance(getActivity(), R.style.CheckBox_Full);
        checkBox.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        checkBox.setTag(tag);
        return checkBox;
    }

    public interface FragmentCallback {
        void onFragmentResume(WidgetFragment widgetFragment);
    }
}
