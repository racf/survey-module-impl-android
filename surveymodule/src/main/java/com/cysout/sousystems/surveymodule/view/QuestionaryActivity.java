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

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import com.cysout.sousystems.surveymodule.R;
import com.cysout.sousystems.surveymodule.controller.RespuestaMostrarCuestionariosController;
import com.cysout.sousystems.surveymodule.dto.ResponseMessageDto;
import com.cysout.sousystems.surveymodule.entity.Cuestionario;
import com.cysout.sousystems.surveymodule.entity.Encuesta;
import com.cysout.sousystems.surveymodule.entity.EncuestaRegistro;
import com.cysout.sousystems.surveymodule.entity.Pregunta;
import com.cysout.sousystems.surveymodule.entity.Respuesta;
import com.cysout.sousystems.surveymodule.entity.RespuestaMostrarCuestionarios;
import com.cysout.sousystems.surveymodule.entity.relation.EncuestaRegistroRespuestas;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecords;
import com.cysout.sousystems.surveymodule.fragment.QuestionaryFragment;
import com.cysout.sousystems.surveymodule.fragment.WidgetFragment;
import com.cysout.sousystems.surveymodule.service.EncuestaService;
import com.cysout.sousystems.surveymodule.service.ObtenerEncuestaService;
import com.cysout.sousystems.surveymodule.service.SurveyService;
import com.cysout.sousystems.surveymodule.service.impl.EncuestaServiceImpl;
import com.cysout.sousystems.surveymodule.service.impl.ObtenerEncuestaServiceImpl;
import com.cysout.sousystems.surveymodule.service.impl.SurveyServiceImpl;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
import com.cysout.sousystems.surveymodule.utils.Utils;
import com.cysout.sousystems.surveymodule.utils.Validation;
import com.cysout.sousystems.surveymodule.viewpager.NoSwipeViewPager;
import com.cysout.sousystems.surveymodule.viewpager.QuestionaryViewPagerAdapter;

public class QuestionaryActivity extends AppCompatActivity{
    private NoSwipeViewPager questionaryViewPager;
    private QuestionaryViewPagerAdapter mPagerAdapter;
    public static Button btnPrev, btnNext;
    private Encuesta encuesta;
    private final List<QuestionaryFragment> cuestionarios = new ArrayList<>();

    private List<RespuestaMostrarCuestionarios> listMostrarCuestionarios;
    //DB
    private ObtenerEncuestaService obtenerEncuestaService;
    private RespuestaMostrarCuestionariosController respuestaMostrarCuestionariosController;
    private EncuestaService encuestaService;
    private SurveyService surveyService;
    //Validadores
    private List<Pregunta> listPreguntasInvalidas;
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
        Encuesta encuestaParameter = (Encuesta) intent.getSerializableExtra(CustomConstants.SURVEY_KEY);
        EncuestaRegistro encuestaRegistro = (EncuestaRegistro) intent.getSerializableExtra(CustomConstants.REGISTRATION_SURVEY_KEY);
        if ( encuestaParameter != null && encuestaRegistro != null ){ //Para una encuesta con información que se ha iniciado
            //Guardamos el ID auto-increment de EncuestaRegistro, es util para encuestas que se han quedado sin concluir
            Utils.saveOnPreferenceLong(getApplicationContext(), CustomConstants.PREFERENCE_NAME_CUESTIONARIO, CustomConstants.CUESTIONARIO_REGISTRO_ID, encuestaRegistro.getEncuestaRegistroId());
            Log.i(CustomConstants.TAG_LOG, "ENCUESTA PENDIENTE");
        }else if ( encuestaParameter != null ){ //Es para una encuesta nueva
            Utils.deleteSinglePreference(getApplicationContext(), CustomConstants.PREFERENCE_NAME_CUESTIONARIO, CustomConstants.CUESTIONARIO_REGISTRO_ID);
            Log.i(CustomConstants.TAG_LOG, "ENCUESTA NUEVA");
        } else {
            //Logica para el result de error
            Log.i(CustomConstants.TAG_LOG, "INTENT NULL");
            Utils.deleteSinglePreference(getApplicationContext(), CustomConstants.PREFERENCE_NAME_CUESTIONARIO, CustomConstants.CUESTIONARIO_REGISTRO_ID);
        }

        //String jsonEncuesta = Utils.jsonStringFinal();
        startAdapter(encuestaParameter);

        respuestaMostrarCuestionariosController.loadAll().observe(this, new Observer<List<RespuestaMostrarCuestionarios>>() {
            @Override
            public void onChanged(List<RespuestaMostrarCuestionarios> respuestaMostrarCuestionarios) {
                listMostrarCuestionarios = respuestaMostrarCuestionarios;
            }
        });

        //TEst
        /*surveyService.loadAllSurveys().observe(this, new Observer<List<Encuesta>>() {
            @Override
            public void onChanged(List<Encuesta> encuestas) {
                for( Encuesta encuesta : encuestas ) {
                    Log.i(CustomConstants.TAG_LOG, "DATA: "+encuesta.toString());
                }
            }
        });*/

        /*surveyService.loadSurveyCompleted().observe(this, new Observer<List<SurveyRecords>>() {
            @Override
            public void onChanged(List<SurveyRecords> surveyRecords) {
                for( SurveyRecords surveyRecord : surveyRecords ) {
                    Log.i(CustomConstants.TAG_LOG, "DATA1: "+surveyRecord.getSurvey().toString());
                    Log.i(CustomConstants.TAG_LOG, "DATA2: "+surveyRecord.getRecord().toString());
                }
            }
        });*/


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
        obtenerEncuestaService = new ViewModelProvider(this).get(ObtenerEncuestaServiceImpl.class);
        respuestaMostrarCuestionariosController = new ViewModelProvider(this).get(RespuestaMostrarCuestionariosController.class);
        encuestaService = new ViewModelProvider(this).get(EncuestaServiceImpl.class);
        surveyService = new ViewModelProvider(this).get(SurveyServiceImpl.class);
        listPreguntasInvalidas = new ArrayList<>();
    }
    private void testMethods(){
        Log.i(CustomConstants.TAG_LOG, "testMethods");
        surveyService.saveSurveys(Utils.jsonArrayTest());
        //Todas las encuesta registradas
        for (Encuesta encuesta : surveyService.loadAllSurveysSync()){
            encuesta.setJson("");
            Log.i(CustomConstants.TAG_LOG, "DATA: "+Utils.convertirObjToJson(encuesta));
        }

        for( SurveyRecords surveyRecordCompleted : surveyService.loadSurveyCompletedSync() ) {
            surveyRecordCompleted.getSurvey().setJson("");
            Log.i(CustomConstants.TAG_LOG, "DATA-surveyRecordCompleted : "+Utils.convertirObjToJson(surveyRecordCompleted));
        }

        for( SurveyRecords surveyRecordPending : surveyService.loadSurveyPendingSync() ) {
            surveyRecordPending.getSurvey().setJson("");
            Log.i(CustomConstants.TAG_LOG, "DATA-surveyRecordPending : "+Utils.convertirObjToJson(surveyRecordPending));
        }
    }
    private void startAdapter(Encuesta survey){
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
            encuesta = new Gson().fromJson(survey.getJson(), Encuesta.class);
            Log.i(CustomConstants.TAG_LOG, "PARAMETER SURVEY: "+encuesta.toString());
            for (Cuestionario cuestionario: encuesta.getCuestionarios()) {
                cuestionarios.add(new QuestionaryFragment(encuesta, cuestionario));
            }
            mPagerAdapter = new QuestionaryViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, cuestionarios);
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
                eliminarEncuestaRegistroByCuestionario();
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
                Map<WidgetFragment, Pregunta> preguntas = questionaryFragment.getPreguntas();
                for ( Map.Entry<WidgetFragment, Pregunta> entry : preguntas.entrySet() ) {
                    WidgetFragment widgetFragment = entry.getKey();
                    Pregunta pregunta = entry.getValue();
                    if( pregunta.getRequerido() ){
                        if(widgetFragment.getView().getVisibility() == CustomConstants.VISIBLE){
                            if( pregunta.getTipo().equalsIgnoreCase(CustomConstants.TEXT) ) {
                                EditText editText = widgetFragment.getView().findViewById(R.id.edit_text);
                                if( !Validation.isTextValid(editText.getText().toString()) ) {
                                    isValid = CustomConstants.FALSE;
                                    editText.setError(getString(R.string.texto_requerido));
                                    listPreguntasInvalidas.add(pregunta);
                                }
                            } else if( pregunta.getTipo().equalsIgnoreCase(CustomConstants.SELECT) ) {
                                TextView tvTitleSpinner = widgetFragment.getView().findViewById(R.id.tvTitleSpinner);
                                Respuesta respuesta = (Respuesta) tvTitleSpinner.getTag();
                                if( respuesta.getRespuestaId() == CustomConstants.LONG_0L ) {
                                    isValid = CustomConstants.FALSE;
                                    tvTitleSpinner.setError(getString(R.string.texto_requerido));
                                    //editText.setError(getString(R.string.texto_requerido));
                                    TextView labelRequired = widgetFragment.getView().findViewById(R.id.label_required);
                                    labelRequired.setText(getString(R.string.texto_requerido).concat(" ").concat(getString(R.string.question_required)));
                                    listPreguntasInvalidas.add(pregunta);
                                }
                            } else if( pregunta.getTipo().equalsIgnoreCase(CustomConstants.RADIOGROUP) ) {
                                RadioGroup radioGroup = widgetFragment.getView().findViewById(R.id.radio_group);
                                //Se valida que se encuentre seleccionado algun radio button dentro del RadioGroup
                                if( radioGroup.getCheckedRadioButtonId() == -1 ) {
                                    isValid = CustomConstants.FALSE;
                                    TextView labelRequired = widgetFragment.getView().findViewById(R.id.label_required);
                                    labelRequired.setText(getString(R.string.texto_requerido).concat(" ").concat(getString(R.string.question_required)));
                                    listPreguntasInvalidas.add(pregunta);
                                }
                            } else if ( pregunta.getTipo().equalsIgnoreCase(CustomConstants.CHECKBOX) ) {
                                List<Respuesta> listRespuestasValidadas = new ArrayList<>();
                                LinearLayout checkboxesLinerLayout =  widgetFragment.getView().findViewById(R.id.checkboxes_liner_layout);
                                for ( int i = CustomConstants.INT_0; i < checkboxesLinerLayout.getChildCount(); i++) {
                                    CheckBox checkBox =  (CheckBox) checkboxesLinerLayout.getChildAt(i);
                                    if( checkBox.getVisibility() == CustomConstants.VISIBLE) {
                                        Respuesta respuesta = (Respuesta) checkBox.getTag();
                                        if( checkBox.isChecked() ){
                                            listRespuestasValidadas.add(respuesta);
                                        }
                                    }

                                }
                                if( Utils.isEmpty(listRespuestasValidadas) ){
                                    isValid = CustomConstants.FALSE;
                                    TextView labelRequired = widgetFragment.getView().findViewById(R.id.label_required);
                                    labelRequired.setText(getString(R.string.texto_requerido).concat(" ").concat(getString(R.string.question_required)));
                                    listPreguntasInvalidas.add(pregunta);
                                } else {
                                    listRespuestasValidadas.clear();
                                }
                            }
                        }
                    }
                }
                //Validamos si no hay alguna pregunta que no sea valida
                // Si la lista esta vacía significa que el formulario es correcto
                if( Utils.isEmpty(listPreguntasInvalidas) ){
                    // establecemos la bandera del formulario a valido true
                    isValid = CustomConstants.TRUE;
                    //Guardamos las preguntas con sus respectivas respuestas.
                    questionaryFragment.save();
                } else {
                  listPreguntasInvalidas.clear();
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
                            Cuestionario cuestionario = questionaryFragmentNext.getCuestionario();
                            Log.i(CustomConstants.TAG_LOG, "CUESTIONARIO SIGUIENTE: " + cuestionario.toString());
                            //Verifica si el cuestionario siguiente se encuentra oculto. Estatus false.
                            if (!cuestionario.getVisible()) {
                                Log.i(CustomConstants.TAG_LOG, "ENTRO 1: ");
                                //Se verifica si existe alguna RespuestaMostrarCuestionarios que desencadene el siguiente cuestionario
                                //En caso contrario finaliza la encuesta
                                if (!Utils.isEmpty(listMostrarCuestionarios)) {
                                    Log.i(CustomConstants.TAG_LOG, "ENTRO 2: ");
                                    //Recorremos listMostrarCuestionarios para verificar si alguna de la respuesta anterior
                                    // desencadena el siguiente cuestioanrio
                                    for (RespuestaMostrarCuestionarios respuestaMostrarCuestionario : listMostrarCuestionarios) {
                                        //Si se encuentra dentro de la lista el valor del siguiente cuestionario se cambia el estatus a true.
                                        if (respuestaMostrarCuestionario.getCuestionarioId() == cuestionario.getCuestionarioId()) {
                                            Log.i(CustomConstants.TAG_LOG, "ENTRO 3: ");
                                            cuestionario.setVisible(CustomConstants.TRUE);
                                        }
                                    }
                                    //Si el estatus de visibilidad del cuestionario es verdadero mostramos el cuestionario y
                                    // regresamos al estatus anterior de visibilidad es decir false.
                                    if (cuestionario.getVisible()) {
                                        Log.i(CustomConstants.TAG_LOG, "ENTRO 4: ");
                                        //Regresando el cuestionario a su estado anterior false
                                        cuestionario.setVisible(CustomConstants.FALSE);
                                        //Mostramos el siguiente cuestionario
                                        questionaryViewPager.setCurrentItem(next, CustomConstants.TRUE);
                                    } else {
                                        Log.i(CustomConstants.TAG_LOG, "ENTRO 5: ");
                                        retornarRespuestasEncuesta();
                                        /*Intent returnIntent = new Intent();
                                        setResult(Activity.RESULT_OK, returnIntent);
                                        finish();*/
                                    }
                                } else {
                                    Log.i(CustomConstants.TAG_LOG, "ENTRO 6: ");
                                    retornarRespuestasEncuesta();
                                }
                            }else {
                                if (next > mPagerAdapter.getCount()) {
                                    Log.i(CustomConstants.TAG_LOG, "ENTRO 7: ");
                                    retornarRespuestasEncuesta();
                                } else {
                                    Log.i(CustomConstants.TAG_LOG, "ENTRO 8: ");
                                    questionaryViewPager.setCurrentItem(next, true);
                                }
                            }
                        } else {
                            Log.i(CustomConstants.TAG_LOG, "ENTRO 9: ");
                            if (next == mPagerAdapter.getCount()) {
                                Log.i(CustomConstants.TAG_LOG, "ENTRO 10: ");
                                retornarRespuestasEncuesta();
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
    private void retornarRespuestasEncuesta(){
        Executors.newSingleThreadExecutor().execute(() -> {
            Long encuestaRegistroId = Utils.findPreferenceLong(getApplicationContext(), CustomConstants.PREFERENCE_NAME_CUESTIONARIO, CustomConstants.CUESTIONARIO_REGISTRO_ID);
            EncuestaRegistroRespuestas encuestaRegistroRespuestas = encuestaService.encuentaFinaliza(encuestaRegistroId, CustomConstants.TERMINADA, Utils.dateTime());
            Log.i(CustomConstants.TAG_LOG, "RETURN INFO QUESTIONARY : "+encuestaRegistroRespuestas.getEncuestaRegistro().toString());
            Intent returnIntent = new Intent();
            ResponseMessageDto responseMessage = new ResponseMessageDto(CustomConstants.MESSAGE_SURVEY_RESPONSE, "", CustomConstants.CODE_200,
                    getString(R.string.mensaje_encuesta_finalizada), encuestaRegistroRespuestas);
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

    private void eliminarEncuestaRegistroByCuestionario(){
        Executors.newSingleThreadExecutor().execute(() -> {
            Long encuestaRegistroId = Utils.findPreferenceLong(getApplicationContext(), CustomConstants.PREFERENCE_NAME_CUESTIONARIO, CustomConstants.CUESTIONARIO_REGISTRO_ID);
            if( encuestaRegistroId > CustomConstants.LONG_0L ) {
                QuestionaryFragment questionaryFragment = (QuestionaryFragment) mPagerAdapter.getItem(questionaryViewPager.getCurrentItem());
                Long cuestionarioId = questionaryFragment.getCuestionario().getCuestionarioId();
                Log.i(CustomConstants.TAG_LOG, "PREV-QUESTIONARY "+questionaryFragment.getCuestionario().toString());
                encuestaService.eliminarEncuestaRegistroByCuestionarioId(encuestaRegistroId, cuestionarioId);
            }
        });
    }
}