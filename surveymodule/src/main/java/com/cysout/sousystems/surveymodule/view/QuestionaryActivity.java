package com.cysout.sousystems.surveymodule.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cysout.sousystems.surveymodule.controller.AnswerShowQuestionnairesController;
import com.cysout.sousystems.surveymodule.entity.Answer;
import com.cysout.sousystems.surveymodule.entity.AnswerShowQuestionnaires;
import com.cysout.sousystems.surveymodule.entity.Question;
import com.cysout.sousystems.surveymodule.entity.Questionnaire;
import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.SurveyRecord;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecordAnswers;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import com.cysout.sousystems.surveymodule.R;
import com.cysout.sousystems.surveymodule.dto.ResponseMessageDto;
import com.cysout.sousystems.surveymodule.fragment.QuestionaryFragment;
import com.cysout.sousystems.surveymodule.fragment.WidgetFragment;
import com.cysout.sousystems.surveymodule.service.PrivateSurveyService;
import com.cysout.sousystems.surveymodule.service.impl.PrivateSurveyServiceImpl;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
import com.cysout.sousystems.surveymodule.utils.Utils;
import com.cysout.sousystems.surveymodule.utils.Validation;
import com.cysout.sousystems.surveymodule.viewpager.NoSwipeViewPager;
import com.cysout.sousystems.surveymodule.viewpager.QuestionaryViewPagerAdapter;

/**
 *Developed by cysout.com and sousystems.com.mx
 *Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class QuestionaryActivity extends AppCompatActivity{
    private NoSwipeViewPager questionaryViewPager;
    private QuestionaryViewPagerAdapter mPagerAdapter;
    public static Button btnPrev;
    public static Button btnNext;
    private Survey survey;
    private final List<QuestionaryFragment> questionnaires = new ArrayList<>();

    private List<AnswerShowQuestionnaires> answerShowQuestionnaires;
    //DB
    private AnswerShowQuestionnairesController answerShowQuestionnairesController;
    private PrivateSurveyService privateSurveyService;
    //Validadores
    private List<Question> invalidQuestions;
    private boolean isValid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionary);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);
        questionaryViewPager = findViewById(R.id.nsvpQuestionary);
        questionaryViewPager.setOffscreenPageLimit(1);
        startObj();
        //Obtenemos el parametro del Intent
        Intent intent = getIntent();
        Survey surveyParameter = (Survey) intent.getSerializableExtra(CustomConstants.SURVEY_KEY);
        SurveyRecord surveyRecord = (SurveyRecord) intent.getSerializableExtra(CustomConstants.REGISTRATION_SURVEY_KEY);
        if ( surveyParameter != null && surveyRecord != null ){ //Para una encuesta con información que se ha iniciado
            //Guardamos el ID auto-increment de EncuestaRegistro, es util para encuestas que se han quedado sin concluir
            Utils.saveOnPreferenceLong(getApplicationContext(), CustomConstants.PREFERENCE_NAME_CUESTIONARIO, CustomConstants.CUESTIONARIO_REGISTRO_ID, surveyRecord.getSurveyRecordId());
            Log.i(CustomConstants.TAG_LOG, "Pending survey");
        }else if ( surveyParameter != null ){ //Es para una encuesta nueva
            Utils.deleteSinglePreference(getApplicationContext(), CustomConstants.PREFERENCE_NAME_CUESTIONARIO, CustomConstants.CUESTIONARIO_REGISTRO_ID);
            Log.i(CustomConstants.TAG_LOG, "New survey");
        } else {
            //Logica para el result de error
            Log.i(CustomConstants.TAG_LOG, "Error");
            Utils.deleteSinglePreference(getApplicationContext(), CustomConstants.PREFERENCE_NAME_CUESTIONARIO, CustomConstants.CUESTIONARIO_REGISTRO_ID);
        }

        //String jsonEncuesta = Utils.jsonStringFinal();
        Log.i(CustomConstants.TAG_LOG, "Survey " +surveyParameter.toString());
        startAdapter(surveyParameter);

        answerShowQuestionnairesController.loadAll().observe(this, new Observer<List<AnswerShowQuestionnaires>>() {
            @Override
            public void onChanged(List<AnswerShowQuestionnaires> respuestaMostrarCuestionarios) {
                answerShowQuestionnaires = respuestaMostrarCuestionarios;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }



    private void startObj(){
        answerShowQuestionnairesController = new ViewModelProvider(this).get(AnswerShowQuestionnairesController.class);
        privateSurveyService = new ViewModelProvider(this).get(PrivateSurveyServiceImpl.class);
        invalidQuestions = new ArrayList<>();
    }

    private void startAdapter(Survey survey){
        Executors.newSingleThreadExecutor().execute(() -> {
            //testMethods();
            /*//Guardamos Encuesta
            obtenerEncuestaService.guardarEncuesta(jsonEncuesta);
            //Obtenemos la encuesta
            encuesta = obtenerEncuestaService.obtenerEncuesta();
            Log.i(CustomConstants.TAG_LOG, "ENCUESTA INSERTADA: "+encuesta.toString());*/
            //Modificar lógica para obtener la encuesta desde el paraetro del intent
            //Encuesta encuestaReturn = surveyService.loadSurveyByIdSync(2L);
            //encuesta = new Gson().fromJson(jsonEncuesta, Encuesta.class);
            this.survey = new Gson().fromJson(survey.getJson(), Survey.class);
            for (Questionnaire questionnaire : this.survey.getQuestionnaires()) {
                questionnaires.add(new QuestionaryFragment(this.survey, questionnaire));
            }
            mPagerAdapter = new QuestionaryViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, questionnaires);
            questionaryViewPager.setAdapter(mPagerAdapter);
            questionaryViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    showPagerButtons(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            showPagerButtons(questionaryViewPager.getCurrentItem());
        });
    }

    private void showPagerButtons(int position){
        if (position > CustomConstants.INT_0) {
            btnPrev.setVisibility(View.VISIBLE);
        } else {
            btnPrev.setVisibility(View.INVISIBLE);
        }
        if (position + 1 < mPagerAdapter.getCount()) {
            btnNext.setText(getString(R.string.texto_siguiente));
            btnNext.setVisibility(View.VISIBLE);
        } else {
            btnNext.setText(getString(R.string.texto_terminar));
        }
    }

    /**
     * Metódo que regresa a una encuesta anterior
     * @param view
     */
    public void prevQuestionary(View view){
        Log.i(CustomConstants.TAG_LOG, "----------------------prevQuestionary--------------------------------");
        AlphaAnimation animation = new AlphaAnimation(1F, 0.5F);
        animation.setDuration(400);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i(CustomConstants.TAG_LOG, "prevQuestionary - onAnimationStart");
                deleteSurveyRecordByQuestionnaire();
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(CustomConstants.TAG_LOG, "prevQuestionary - onAnimationEnd");
                int previous = questionaryViewPager.getCurrentItem() - 1;
                if (previous >= 0) {
                    questionaryViewPager.setCurrentItem(previous, CustomConstants.TRUE);
                    mPagerAdapter.getItem(previous).onResume();
                }
                Log.i(CustomConstants.TAG_LOG, "----------------------prevQuestionary--------------------------------");
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i(CustomConstants.TAG_LOG, "onAnimationRepeat");
            }
        });

        view.startAnimation(animation);
    }

    /**
     * Método que avanza a la siguiente encuesta
     * @param view
     */
    public void nextQuestionary(View view) {
        Log.i(CustomConstants.TAG_LOG, "----------------------nextQuestionary--------------------------------");
        AlphaAnimation animation = new AlphaAnimation(1F, 0.5F);
        animation.setDuration(400);
        //QuestionaryFragment questionaryFragment = (QuestionaryFragment) mPagerAdapter.getItem(questionaryViewPager.getCurrentItem());
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d(CustomConstants.TAG_LOG, "onAnimationStart()");
                //Logica que permitira pasar al siguiente cuestionario
                QuestionaryFragment questionaryFragment = (QuestionaryFragment) mPagerAdapter.getItem(questionaryViewPager.getCurrentItem());
                Map<WidgetFragment, Question> questions = questionaryFragment.getQuestions();
                for ( Map.Entry<WidgetFragment, Question> entry : questions.entrySet() ) {
                    WidgetFragment widgetFragment = entry.getKey();
                    Question question = entry.getValue();
                    if( question.getRequired() ){
                        if(widgetFragment.getView().getVisibility() == CustomConstants.VISIBLE){
                            if( question.getType().equalsIgnoreCase(CustomConstants.TEXT) ) {
                                EditText editText = widgetFragment.getView().findViewById(R.id.edit_text);
                                if( !Validation.isTextValid(editText.getText().toString()) ) {
                                    isValid = CustomConstants.FALSE;
                                    editText.setError(getString(R.string.texto_requerido));
                                    invalidQuestions.add(question);
                                }
                            } else if( question.getType().equalsIgnoreCase(CustomConstants.SELECT) ) {
                                TextView tvTitleSpinner = widgetFragment.getView().findViewById(R.id.tvTitleSpinner);
                                Answer answer = (Answer) tvTitleSpinner.getTag();
                                if( answer.getAnswerId() == CustomConstants.LONG_0L ) {
                                    isValid = CustomConstants.FALSE;
                                    tvTitleSpinner.setError(getString(R.string.texto_requerido));
                                    //editText.setError(getString(R.string.texto_requerido));
                                    TextView labelRequired = widgetFragment.getView().findViewById(R.id.label_required);
                                    labelRequired.setText(getString(R.string.texto_requerido).concat(" ").concat(getString(R.string.question_required)));
                                    invalidQuestions.add(question);
                                }
                            } else if( question.getType().equalsIgnoreCase(CustomConstants.RADIOGROUP) ) {
                                RadioGroup radioGroup = widgetFragment.getView().findViewById(R.id.radio_group);
                                //Se valida que se encuentre seleccionado algun radio button dentro del RadioGroup
                                if( radioGroup.getCheckedRadioButtonId() == -1 ) {
                                    isValid = CustomConstants.FALSE;
                                    TextView labelRequired = widgetFragment.getView().findViewById(R.id.label_required);
                                    labelRequired.setText(getString(R.string.texto_requerido).concat(" ").concat(getString(R.string.question_required)));
                                    invalidQuestions.add(question);
                                }
                            } else if ( question.getType().equalsIgnoreCase(CustomConstants.CHECKBOX) ) {
                                List<Answer> listValidatedAnswers = new ArrayList<>();
                                LinearLayout checkboxesLinerLayout =  widgetFragment.getView().findViewById(R.id.checkboxes_liner_layout);
                                for ( int i = CustomConstants.INT_0; i < checkboxesLinerLayout.getChildCount(); i++) {
                                    CheckBox checkBox =  (CheckBox) checkboxesLinerLayout.getChildAt(i);
                                    if( checkBox.getVisibility() == CustomConstants.VISIBLE) {
                                        Answer answer = (Answer) checkBox.getTag();
                                        if( checkBox.isChecked() ){
                                            listValidatedAnswers.add(answer);
                                        }
                                    }

                                }
                                if( Utils.isEmpty(listValidatedAnswers) ){
                                    isValid = CustomConstants.FALSE;
                                    TextView labelRequired = widgetFragment.getView().findViewById(R.id.label_required);
                                    labelRequired.setText(getString(R.string.texto_requerido).concat(" ").concat(getString(R.string.question_required)));
                                    invalidQuestions.add(question);
                                } else {
                                    listValidatedAnswers.clear();
                                }
                            }
                        }
                    }
                }
                //Validamos si no hay alguna pregunta que no sea valida
                // Si la lista esta vacía significa que el formulario es correcto
                if( Utils.isEmpty(invalidQuestions) ){
                    // establecemos la bandera del formulario a valido true
                    isValid = CustomConstants.TRUE;
                    //Guardamos las preguntas con sus respectivas respuestas.
                    questionaryFragment.save();
                } else {
                    invalidQuestions.clear();
                }
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                    Log.d(CustomConstants.TAG_LOG, "onAnimationEnd()");
                    //Si el formulario del cuestionario es correcto. Pasa al siguiente cuestioanrio.
                    //En caso contrarío muestra mensajes de error de captura de información
                    if(isValid) {
                        //QuestionaryFragment questionaryFragment = (QuestionaryFragment) mPagerAdapter.getItem(questionaryViewPager.getCurrentItem());
                        //questionaryFragment.save();
                        int next = questionaryViewPager.getCurrentItem() + 1;
                        if (next < mPagerAdapter.getCount()) {
                            QuestionaryFragment questionaryFragmentNext = (QuestionaryFragment) mPagerAdapter.getItem(next);
                            Questionnaire questionnaire = questionaryFragmentNext.getQuestionnaire();
                            Log.i(CustomConstants.TAG_LOG, "Next questionnaire: " + questionnaire.toString());
                            //Verifica si el cuestionario siguiente se encuentra oculto. Estatus false.
                            if (!questionnaire.getVisible()) {
                                Log.i(CustomConstants.TAG_LOG, "onAnimationEnd-1: ");
                                //Se verifica si existe alguna RespuestaMostrarCuestionarios que desencadene el siguiente cuestionario
                                //En caso contrario finaliza la encuesta
                                if (!Utils.isEmpty(answerShowQuestionnaires)) {
                                    Log.i(CustomConstants.TAG_LOG, "onAnimationEnd-2: ");
                                    //Recorremos listMostrarCuestionarios para verificar si alguna de la respuesta anterior
                                    // desencadena el siguiente cuestioanrio
                                    for (AnswerShowQuestionnaires answerShowQuestionnaire : answerShowQuestionnaires) {
                                        //Si se encuentra dentro de la lista el valor del siguiente cuestionario se cambia el estatus a true.
                                        if (answerShowQuestionnaire.getQuestionnaireId() == questionnaire.getQuestionnaireId()) {
                                            Log.i(CustomConstants.TAG_LOG, "onAnimationEnd-3: ");
                                            questionnaire.setVisible(CustomConstants.TRUE);
                                        }
                                    }
                                    //Si el estatus de visibilidad del cuestionario es verdadero mostramos el cuestionario y
                                    // regresamos al estatus anterior de visibilidad es decir false.
                                    if (questionnaire.getVisible()) {
                                        Log.i(CustomConstants.TAG_LOG, "onAnimationEnd-4: ");
                                        //Regresando el cuestionario a su estado anterior false
                                        questionnaire.setVisible(CustomConstants.FALSE);
                                        //Mostramos el siguiente cuestionario
                                        questionaryViewPager.setCurrentItem(next, CustomConstants.TRUE);
                                    } else {
                                        Log.i(CustomConstants.TAG_LOG, "onAnimationEnd-5: ");
                                        surveyResponse();
                                        /*Intent returnIntent = new Intent();
                                        setResult(Activity.RESULT_OK, returnIntent);
                                        finish();*/
                                    }
                                } else {
                                    Log.i(CustomConstants.TAG_LOG, "onAnimationEnd-6: ");
                                    surveyResponse();
                                }
                            }else {
                                if (next > mPagerAdapter.getCount()) {
                                    Log.i(CustomConstants.TAG_LOG, "onAnimationEnd-7: ");
                                    surveyResponse();
                                } else {
                                    Log.i(CustomConstants.TAG_LOG, "onAnimationEnd-8: ");
                                    questionaryViewPager.setCurrentItem(next, true);
                                }
                            }
                        } else {
                            Log.i(CustomConstants.TAG_LOG, "onAnimationEnd-9: ");
                            if (next == mPagerAdapter.getCount()) {
                                Log.i(CustomConstants.TAG_LOG, "onAnimationEnd-10: ");
                                surveyResponse();
                            }
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),getString(R.string.mensaje_campos_requeridos), Toast.LENGTH_LONG).show();
                    }
                    Log.i(CustomConstants.TAG_LOG, "----------------------nextQuestionary--------------------------------");
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d(CustomConstants.TAG_LOG, "onAnimationRepeat()");
            }
        });

        view.startAnimation(animation);
    }
    private void surveyResponse(){
        Executors.newSingleThreadExecutor().execute(() -> {
            Long surveyRecordId = Utils.findPreferenceLong(getApplicationContext(), CustomConstants.PREFERENCE_NAME_CUESTIONARIO, CustomConstants.CUESTIONARIO_REGISTRO_ID);
            SurveyRecordAnswers surveyRecordAnswers = privateSurveyService.encuentaFinaliza(surveyRecordId, CustomConstants.TERMINADA, Utils.dateTime());
            Log.i(CustomConstants.TAG_LOG, "RETURN INFO QUESTIONARY : "+ surveyRecordAnswers.getSurveyRecord().toString());
            Intent returnIntent = new Intent();
            ResponseMessageDto responseMessage = new ResponseMessageDto(CustomConstants.MESSAGE_SURVEY_RESPONSE, "", CustomConstants.CODE_200,
                    getString(R.string.mensaje_encuesta_finalizada), surveyRecordAnswers);
            String responseMessageString = Utils.convertirObjToJson(responseMessage);
            returnIntent.putExtra(CustomConstants.SURVEY_RESPONSE, responseMessageString);
            Log.i(CustomConstants.TAG_LOG, responseMessageString);
            //returnIntent.setData(Uri.parse(encuestaRegistroRespuestas));
            setResult(Activity.RESULT_OK, returnIntent);
            //Limpiamos la preferencia del identificador del Cuestionario Registro.
            Utils.deleteSinglePreference(getApplicationContext(), CustomConstants.PREFERENCE_NAME_CUESTIONARIO, CustomConstants.CUESTIONARIO_REGISTRO_ID);
            finish();
        });
    }

    private void deleteSurveyRecordByQuestionnaire(){
        Executors.newSingleThreadExecutor().execute(() -> {
            Long surveyRecordId = Utils.findPreferenceLong(getApplicationContext(), CustomConstants.PREFERENCE_NAME_CUESTIONARIO, CustomConstants.CUESTIONARIO_REGISTRO_ID);
            if( surveyRecordId > CustomConstants.LONG_0L ) {
                QuestionaryFragment questionaryFragment = (QuestionaryFragment) mPagerAdapter.getItem(questionaryViewPager.getCurrentItem());
                Long questionnaireId = questionaryFragment.getQuestionnaire().getQuestionnaireId();
                Log.i(CustomConstants.TAG_LOG, "PREV-QUESTIONARY "+questionaryFragment.getQuestionnaire().toString());
                privateSurveyService.eliminarEncuestaRegistroByCuestionarioId(surveyRecordId, questionnaireId);
            }
        });
    }
}