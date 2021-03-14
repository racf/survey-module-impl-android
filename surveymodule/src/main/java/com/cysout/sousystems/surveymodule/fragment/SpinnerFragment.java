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
import com.cysout.sousystems.surveymodule.entity.Answer;
import com.cysout.sousystems.surveymodule.entity.Questionnaire;
import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.SurveyAnswer;
import com.cysout.sousystems.surveymodule.entity.Question;
import com.cysout.sousystems.surveymodule.service.PrivateSurveyService;
import com.cysout.sousystems.surveymodule.service.impl.PrivateSurveyServiceImpl;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
import com.cysout.sousystems.surveymodule.utils.Utils;

/**
 *Developed by cysout.com and sousystems.com.mx
 *Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class SpinnerFragment extends WidgetFragment {
    private Spinner spinnerCustom;
    SpinnerAdapter adapter;
    Answer answer = null;

    private PrivateSurveyService privateSurveyService;
    public SpinnerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spinner, container, false);
        //Cargamos el servicio para gestionar encuestas
        this.privateSurveyService = new ViewModelProvider(this).get(PrivateSurveyServiceImpl.class);
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
    private void loadSpinner(List<Answer> answers, Answer selectedAnswer){
        int listSize = answers.size();
        if( selectedAnswer != null ) {
            answers.remove(selectedAnswer);
            answers.add(0, selectedAnswer);
            if( answers.get(listSize-1).getAnswerId() != CustomConstants.LONG_0L){
                answers.add(listSize, Utils.getAnswerSpinnerDefault(getContext()));
            }
        } else {
            if( answers.get(CustomConstants.INT_0).getAnswerId() != CustomConstants.LONG_0L){
                answers.add(CustomConstants.INT_0, Utils.getAnswerSpinnerDefault(getContext()));
            }
        }
        adapter = new com.cysout.sousystems.surveymodule.adapter.SpinnerAdapter(getContext(), R.layout.spinner_item_layout, answers);
        spinnerCustom.setAdapter(adapter);
        spinnerCustom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if( view != null ){
                    TextView tvTitleSpinner = view.findViewById(R.id.tvTitleSpinner);
                    answer = (Answer) tvTitleSpinner.getTag();
                    Log.i(CustomConstants.TAG_LOG, "SpinnerFragment-loadSpinner-SELECTED "+ answer.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    @Override
    public boolean load(Questionnaire questionnaire, Question question) {
        //boolean status = false;
        Log.i(CustomConstants.TAG_LOG, "SpinnerFragment - load(Questionnaire questionnaire, Question question)");
        Long surveyRecordId = Utils.findPreferenceLong(getContext(), CustomConstants.PREFERENCE_NAME_CUESTIONARIO, CustomConstants.CUESTIONARIO_REGISTRO_ID);
        List<Answer> listAnswer = question.getAnswers();
        //Asignamos informacion al regresar a la encuesta anterior
        if (surveyRecordId > 0L) {
            privateSurveyService.encuestaRespuestaByRegistroIdAndPregId(surveyRecordId, question.getQuestionId()).observe(getViewLifecycleOwner(), new Observer<SurveyAnswer>() {
                @Override
                public void onChanged(SurveyAnswer surveyAnswer) {
                    if(surveyAnswer != null) {
                        if(question.getQuestionId() == surveyAnswer.getQuestionId()){
                            Log.i(CustomConstants.TAG_LOG, "Sort information"+ surveyAnswer.toString());
                            Long answerId = Long.parseLong(surveyAnswer.getAnswer());
                            Answer respSelected;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                                respSelected = listAnswer.stream()
                                        .filter(resp -> resp.getAnswerId() == answerId)
                                        .findAny()
                                        .orElse(null);
                            } else {
                                respSelected = null;
                                for ( Answer answer : listAnswer) {
                                    if ( answer.getAnswerId() == answerId ) {
                                        respSelected = answer;
                                        break;
                                    }
                                }
                            }
                            loadSpinner(listAnswer, respSelected);
                        }
                    } else {
                        loadSpinner(listAnswer, null);
                    }
                }
            });
        }else {
            loadSpinner(listAnswer, null);
        }
        return true;
    }

    @Override
    public boolean save(Survey survey, Questionnaire questionnaire, Question question, Long surveyRecordId) {
        //boolean status = false;
        Log.i(CustomConstants.TAG_LOG, "SpinnerFragment-save()");
        //if ( spinnerCustom != null){
            Executors.newSingleThreadExecutor().execute(() -> {
                if(answer != null && answer.getAnswerId() > CustomConstants.LONG_0L){
                    Log.i(CustomConstants.TAG_LOG, "SpinnerFragment-save()-answer "+ answer.toString());
                    String answerString  = String.valueOf(answer.getAnswerId());
                    //Logica del guardado de la información
                    this.privateSurveyService.encuestaRespuesta(survey, questionnaire, question, answerString, surveyRecordId);
                } else {
                    if( !question.getRequired() ) {
                        this.privateSurveyService.eliminarEncuestaRegistroByPreguntaId(surveyRecordId, question.getQuestionId());
                    }
                }
            });
        //}
        return true;
    }
}
