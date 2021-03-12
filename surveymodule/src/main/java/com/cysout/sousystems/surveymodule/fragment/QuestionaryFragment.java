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
 *Developed by cysout.com and sousystems.com.mx
 *Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class QuestionaryFragment extends Fragment implements WidgetFragment.FragmentCallback {
    private Questionnaire questionnaire;
    private Survey survey;
    private TextView tvQuestionaryTitle;
    private Map<WidgetFragment, Question> preguntas = new HashMap<WidgetFragment, Question>();
    private LinearLayout fieldset;
    private TextView cuestionarioTitulo;
    private PrivateSurveyService privateSurveyService;
    private static final int NUMBER_OF_THREADS = 10;
    public static final ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private String fechaInicial = "";
    public QuestionaryFragment(Survey survey, Questionnaire questionnaire) {
        this.survey = survey;
        this.questionnaire = questionnaire;
        this.fechaInicial = Utils.dateTime();
    }
    public Questionnaire getQuestionnaire(){
      return this.questionnaire;
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_questionary, container, CustomConstants.FALSE);
        fieldset = rootView.findViewById(R.id.fieldset);
        cuestionarioTitulo = rootView.findViewById(R.id.tvCuestionarioTitulo);
        cuestionarioTitulo.setText(this.questionnaire.getTitle());
        return rootView;
    }

    public Map<WidgetFragment, Question> getPreguntas(){
        return this.preguntas;
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(CustomConstants.TAG_LOG, "RESUMEN QuestionaryFragment");
        load();
        this.privateSurveyService = new PrivateSurveyServiceImpl(getActivity().getApplication());
    }

    public void save(){
        Log.d(CustomConstants.TAG_LOG, "Master save() ");
        executorService.execute(() -> {
            Long encuestaRegistroId = Utils.findPreferenceLong(getContext(), CustomConstants.PREFERENCE_NAME_CUESTIONARIO, CustomConstants.CUESTIONARIO_REGISTRO_ID);
            //Si es la primera vez que se guarda una encuesta_registro, en caso contrario obtiene el identificador que se ha generado anteriormente
            if( encuestaRegistroId == 0L){
                encuestaRegistroId = privateSurveyService.encuestaRegistro(survey, CustomConstants.ENCUESTA_EN_PROCESO, this.fechaInicial, this.fechaInicial);
                    Log.d(CustomConstants.TAG_LOG, "PRIMERA VEZ PARA GUARDAR " + encuestaRegistroId + " --- "+ this.fechaInicial);
                    //Guardamos el ID auto-increment de la encuesta que se esta encuestaRegistroId
                    Utils.saveOnPreferenceLong(getContext(), CustomConstants.PREFERENCE_NAME_CUESTIONARIO, CustomConstants.CUESTIONARIO_REGISTRO_ID, encuestaRegistroId);
            }
            for ( Map.Entry<WidgetFragment, Question> entry : preguntas.entrySet() ) {
                WidgetFragment widgetFragment = entry.getKey();
                Question question = entry.getValue();
                Log.d(CustomConstants.TAG_LOG, "id: "+ question.getQuestionId() + " titulo: " + question.getTitle());
                widgetFragment.save(survey, questionnaire, question, encuestaRegistroId);
            }
        });
    }
    // onResumeFragment/
    public void load() {
        //executorService.execute(() -> {
            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            for (WidgetFragment widgetFragment : preguntas.keySet()) {
                fragmentTransaction.remove(widgetFragment);
            }
            preguntas.clear();
            fieldset.removeAllViews();
            for (Question question : questionnaire.getQuestions()) {
                //Log.d(CustomConstants.TAG_LOG, "GENERA LOS TIPOS DE PREGUNTAS DE LOS CUESTIONARIOS "+pregunta.getPreguntaId()+" - "+ pregunta.getTipo());
                if (question.getType().equalsIgnoreCase(CustomConstants.TEXT)) {
                    // Class<? extends WidgetFragment> widget = WidgetFragment.classForType(getActivity(), subQuestion.type);
                    WidgetFragment widgetFragment = new TextFragment();
                    widgetFragment.setCallback(this);
                    widgetFragment.setMenuVisibility(CustomConstants.FALSE);
                    fragmentTransaction.add(R.id.fieldset, widgetFragment);
                    preguntas.put(widgetFragment, question);
                }
                if(question.getType().equalsIgnoreCase(CustomConstants.RADIOGROUP)){
                    WidgetFragment widgetFragment = new SelectFragment();
                    widgetFragment.setCallback(this);
                    widgetFragment.setMenuVisibility(CustomConstants.FALSE);
                    fragmentTransaction.add(R.id.fieldset, widgetFragment);
                    preguntas.put(widgetFragment, question);
                }
                if(question.getType().equalsIgnoreCase(CustomConstants.CHECKBOX)){
                    WidgetFragment widgetFragment = new CheckBoxFragment();
                    widgetFragment.setCallback(this);
                    widgetFragment.setMenuVisibility(CustomConstants.FALSE);
                    fragmentTransaction.add(R.id.fieldset, widgetFragment);
                    preguntas.put(widgetFragment, question);
                }
                if(question.getType().equalsIgnoreCase(CustomConstants.SELECT)){
                    Log.i(CustomConstants.TAG_LOG, "--------------------------------ENTRO SELECT-----------------------------");
                    WidgetFragment widgetFragment = new SpinnerFragment();
                    widgetFragment.setCallback(this);
                    widgetFragment.setMenuVisibility(CustomConstants.FALSE);
                    fragmentTransaction.add(R.id.fieldset, widgetFragment);
                    preguntas.put(widgetFragment, question);
                }
            }
            fragmentTransaction.commit();
        //});
    }


    @Override
    public void onFragmentResume(WidgetFragment widgetFragment) {
        //Log.d(CustomConstants.TAG_LOG, "onFragmentResume Tamanio Lista " +preguntas.size());
       // for (WidgetFragment widgetFragment : preguntas.keySet()) {
            Question question = preguntas.get(widgetFragment);
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
