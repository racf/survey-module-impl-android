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
import com.cysout.sousystems.surveymodule.controller.RespuestaMostrarCuestionariosController;
import com.cysout.sousystems.surveymodule.entity.Cuestionario;
import com.cysout.sousystems.surveymodule.entity.Encuesta;
import com.cysout.sousystems.surveymodule.entity.MostrarCuestionarios;
import com.cysout.sousystems.surveymodule.entity.MostrarPreguntas;
import com.cysout.sousystems.surveymodule.entity.MostrarRespuestas;
import com.cysout.sousystems.surveymodule.entity.MostrarSiSelecciona;
import com.cysout.sousystems.surveymodule.entity.Pregunta;
import com.cysout.sousystems.surveymodule.entity.Respuesta;
import com.cysout.sousystems.surveymodule.entity.RespuestaMostrarCuestionarios;
import com.cysout.sousystems.surveymodule.service.EncuestaService;
import com.cysout.sousystems.surveymodule.service.impl.EncuestaServiceImpl;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
import com.cysout.sousystems.surveymodule.utils.Utils;

public abstract class WidgetFragment extends Fragment {
    private static final Map<String, Class<? extends WidgetFragment>> widgets = new HashMap<>();

    protected TextView labelName;
    protected TextView labelRequired;
    protected TextView labelDescription;
    protected View rootView;
    private FragmentCallback callback;
    //DB
    private RespuestaMostrarCuestionariosController respuestaMostrarCuestionariosController;
    private EncuestaService encuestaService;

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
        respuestaMostrarCuestionariosController = new ViewModelProvider(this).get(RespuestaMostrarCuestionariosController.class);;//new RespuestaMostrarCuestionariosController(getActivity().getApplication());
        encuestaService = new ViewModelProvider(this).get(EncuestaServiceImpl.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.callback.onFragmentResume(this);
    }

    /**
     * Metódo que inicializa el cuestionario
     * @param cuestionario el cuestionarío de la encuesta
     * @param pregunta pregunta de un determinado cuestionario
     * @return true si contiene pregunta en casa contrario false
     */
    public boolean init(Cuestionario cuestionario, Pregunta pregunta) {
        if (pregunta != null) {
            rootView.setVisibility(View.GONE);
            labelDescription.setVisibility(View.GONE);
            labelRequired.setVisibility(View.GONE);
            labelName.setText(Html.fromHtml(pregunta.getTitulo()));
            if(pregunta.getDescripcion() != null && !pregunta.getDescripcion().trim().isEmpty()){
                labelDescription.setVisibility(View.VISIBLE);
                labelDescription.setText(Html.fromHtml(pregunta.getDescripcion()));
            }
            if (pregunta.getRequerido()) {
                labelRequired.setVisibility(View.VISIBLE);
            }
            //En esta parte mostramos u ocultamos la preguntas donde su propiedad visible sea true o false respectivamente
            if (pregunta.getVisible()){
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


    public abstract boolean load(Cuestionario cuestionario, Pregunta pregunta);

    public abstract boolean save(Encuesta encuesta, Cuestionario cuestionario, Pregunta pregunta, Long encuestaRegistroId);

    protected void showSurvey(Cuestionario cuestionario, Pregunta pregunta, Respuesta respuesta, MostrarSiSelecciona mostrarSiSelecciona) {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<MostrarCuestionarios> mostrarCuestionarios = mostrarSiSelecciona.getCuestionarios();
            if (!Utils.isEmpty(mostrarCuestionarios)) {
                for (MostrarCuestionarios mostrarCuestionario : mostrarCuestionarios) {
                    RespuestaMostrarCuestionarios respuestaMostrarCuestionarios = new RespuestaMostrarCuestionarios();
                    respuestaMostrarCuestionarios.setCuestionarioId(mostrarCuestionario.getCuestionarioId());
                    respuestaMostrarCuestionarios.setPreguntaId(pregunta.getPreguntaId());
                    respuestaMostrarCuestionarios.setRespuestaId(respuesta.getRespuestaId());
                    respuestaMostrarCuestionarios.setCuestionarioOrigenId(cuestionario.getCuestionarioId());
                    Long respuestaMostrarCuestionariosId = respuestaMostrarCuestionariosController.insert(respuestaMostrarCuestionarios);
                    Log.i(CustomConstants.TAG_LOG, "INSERTO EN LA BASE DE DATOS PARA MOSTRAR ENCUESTA  " + respuestaMostrarCuestionariosId+" "+respuestaMostrarCuestionarios.toString());
                }
            }
        });
    }

    /**
     * Método que realiza la validación para mostrar u ocultar una determina encuesta
     * @param cuestionario de donde proviene el siguiente cuestionario a ocultar
     * @param pregunta pregunta que ejecuta la acción para mostrar cuestionario
     */
    protected void hideSurvey(Cuestionario cuestionario, Pregunta pregunta) {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<Respuesta> listRespuestas = pregunta.getRespuestas();
            if( !Utils.isEmpty(listRespuestas) ) {
                for ( Respuesta auxRespuesta : listRespuestas ){
                    MostrarSiSelecciona mostrarSiSelecciona = Utils.infoMostrarSiSelecciona(auxRespuesta);
                    if (mostrarSiSelecciona!=null) {
                        List<MostrarCuestionarios> mostrarCuestionarios = mostrarSiSelecciona.getCuestionarios();
                        if (!Utils.isEmpty(mostrarCuestionarios)) {
                            respuestaMostrarCuestionariosController.deleteByPreguntaId(pregunta.getPreguntaId());
                        }
                    } else {
                        respuestaMostrarCuestionariosController.deleteByCuestionarioOrigenId(cuestionario.getCuestionarioId());
                    }
                }
            } else {
               respuestaMostrarCuestionariosController.deleteByCuestionarioOrigenId(cuestionario.getCuestionarioId());
            }
        });
    }

    protected void showQuestions(Map<WidgetFragment, Pregunta> preguntas, MostrarSiSelecciona mostrarSiSelecciona){
        for ( Map.Entry<WidgetFragment, Pregunta> entry : preguntas.entrySet() ) {
            WidgetFragment widgetFragment = entry.getKey();
            Pregunta pregunta = entry.getValue();
            Log.i(CustomConstants.TAG_LOG, " WidgetFragment - showQuestions");
            Log.i(CustomConstants.TAG_LOG, pregunta.getTitulo());
            //Preguntas
            List<MostrarPreguntas> mostrarPreguntas = mostrarSiSelecciona.getPreguntas();
            //Muestra una determinada pregunta completa
            if( !Utils.isEmpty( mostrarPreguntas ) ) {
                for ( MostrarPreguntas mostrarPregunta : mostrarPreguntas ) {
                    if( pregunta.getPreguntaId() == mostrarPregunta.getPreguntaId() ) {
                        //Logica para actualizar la base de datos al estatus true de la pregunta
                        //Validamos que la vista no este vacía esto aplica en el momento que se regresa al cuestionario anterior
                        if ( widgetFragment.rootView != null) {
                            widgetFragment.rootView.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
            //Respuesta
            showHideAnswer(widgetFragment, pregunta, mostrarSiSelecciona, 0L,1);
        }
    }

    protected void hideQuestions(Map<WidgetFragment, Pregunta> preguntas, MostrarSiSelecciona mostrarSiSelecciona, Long encuestaRegistroId){
        for (Map.Entry<WidgetFragment, Pregunta> entry : preguntas.entrySet()) {
            WidgetFragment widgetFragment = entry.getKey();
            Pregunta pregunta = entry.getValue();
            Log.i(CustomConstants.TAG_LOG, " -------------------------- WidgetFragment - hideQuestions ---------------------------");
            Log.i(CustomConstants.TAG_LOG, pregunta.getTitulo());
            //Preguntas
            List<MostrarPreguntas> mostrarPreguntas = mostrarSiSelecciona.getPreguntas();
            if (!Utils.isEmpty(mostrarPreguntas)) {
                for (MostrarPreguntas mostrarPregunta : mostrarPreguntas) {
                    if (pregunta.getPreguntaId() == mostrarPregunta.getPreguntaId()) {
                        //Ocultamos la pregunta anteriormente mostrada
                        widgetFragment.rootView.setVisibility(View.GONE);
                        clearRespuestas(widgetFragment, pregunta);
                        //Logica para eliminar preguntas registradas en encuestaRespuesta al ocultar las preguntas que dependen
                        if (encuestaRegistroId > 0L) {
                            Executors.newSingleThreadExecutor().execute(() -> {
                                encuestaService.eliminarEncuestaRegistroByPreguntaId(encuestaRegistroId, pregunta.getPreguntaId());
                            });
                        }
                    }
                }
            }

            //Respuesta
            showHideAnswer(widgetFragment, pregunta, mostrarSiSelecciona, encuestaRegistroId,2);
        }
        Log.i(CustomConstants.TAG_LOG, " -------------------------- WidgetFragment - hideQuestions ---------------------------");

    }
    private void showHideAnswer(WidgetFragment widgetFragment, Pregunta pregunta, MostrarSiSelecciona mostrarSiSelecciona, Long encuestaRegistroId, int option){
        List<MostrarRespuestas> mostrarRespuestas = mostrarSiSelecciona.getRespuestas();
        if( !Utils.isEmpty( mostrarRespuestas ) ){
            for ( MostrarRespuestas mostrarRespuesta : mostrarRespuestas ) {
                if( pregunta.getPreguntaId() == mostrarRespuesta.getPreguntaId() ){
                    for (Respuesta respuesta : pregunta.getRespuestas() ) {
                        //Validamos que la vista no este vacía esto aplica en el momento que se regresa al cuestionario anterior
                        if ( widgetFragment.rootView != null) {
                            if(mostrarRespuesta.getRespuestaId() == respuesta.getRespuestaId()) {
                                String respuestaStringId = String.valueOf(respuesta.getRespuestaId());
                                if (pregunta.getTipo().equalsIgnoreCase(CustomConstants.CHECKBOX)) {
                                    int checkBoxId = Integer.parseInt(pregunta.getPreguntaId() + "" + respuesta.getRespuestaId());
                                    CheckBox checkBox = widgetFragment.rootView.findViewById(checkBoxId);
                                    if (option == 1) {
                                        checkBox.setVisibility(View.VISIBLE);
                                    } else {
                                        checkBox.setVisibility(View.GONE);
                                        checkBox.setChecked(false);
                                        deleteRespuesta(encuestaRegistroId, pregunta.getPreguntaId(), respuestaStringId);
                                    }
                                } else if (pregunta.getTipo().equalsIgnoreCase(CustomConstants.RADIOGROUP)) {
                                    RadioButton radioButton = widgetFragment.rootView.findViewWithTag(respuesta);
                                    if (option == 1) {
                                        radioButton.setVisibility(View.VISIBLE);
                                    } else {
                                        radioButton.setVisibility(View.GONE);
                                        if (radioButton.isChecked()) {
                                            //Obtenemos el padre que contiene al radioButton en este caso es un RadioGroup
                                            // y posteriormente lo limpiamos
                                            if (radioButton.getParent() instanceof RadioGroup) {
                                                ((RadioGroup) radioButton.getParent()).clearCheck();
                                                deleteRespuesta(encuestaRegistroId, pregunta.getPreguntaId(), respuestaStringId);
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

    private void deleteRespuesta(Long encuestaRegistroId, Long preguntaId, String respuesta){
        if (encuestaRegistroId > 0L) {
            Executors.newSingleThreadExecutor().execute(() -> {
                encuestaService.eliminarEncuestaRegistroByPregtIdAndResp(encuestaRegistroId, preguntaId, respuesta);
            });
        }
    }
    private void clearRespuestas(WidgetFragment widgetFragment, Pregunta pregunta) {
        switch (pregunta.getTipo()){
            case "text":
                EditText editText = widgetFragment.rootView.findViewById(R.id.edit_text);
                editText.setText("");
                break;
            case "radiogroup":
                clearRadioButton(widgetFragment, pregunta);
                break;
            case "checkbox":
                clearCheckBox(widgetFragment, pregunta);
                break;
            default:
                break;
        }
    }

    private void clearRadioButton(WidgetFragment widgetFragment, Pregunta pregunta){
        //Lógica para limpiar la respuesta seleccionadas anteriormente que dependan de alguna
        // determinada respuesta de una pregunta
        for (Respuesta respuesta : pregunta.getRespuestas() ) {
            //Lógica para limpiar las respuestas de los RadioButton
            RadioButton radioButton = widgetFragment.rootView.findViewWithTag(respuesta);
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

    private void clearCheckBox(WidgetFragment widgetFragment, Pregunta pregunta){
        for (Respuesta respuesta : pregunta.getRespuestas() ) {
            int checkBoxId =  Integer.parseInt(pregunta.getPreguntaId()+""+respuesta.getRespuestaId());
            CheckBox checkBox = widgetFragment.rootView.findViewById(checkBoxId);
            if( checkBox.isChecked() ){
                Log.i(CustomConstants.TAG_LOG, "CHECKBOX - clearCheckBox");
                checkBox.setChecked(false);
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
            radioButton.setChecked(true);
        }else {
            radioButton.setChecked(false);
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
    /*public static Class<? extends WidgetFragment> classForType(Context context, String type) {
        if (widgets.size() == 0) {
            try {
                for (Class<? extends WidgetFragment> widgetClass : ca.dalezak.androidbase.utils.Runtime.getClasses(context, WidgetFragment.class))  {
                    Annotation annotation = widgetClass.getAnnotation(Type.class);
                    if (annotation instanceof Type) {
                        String widgetType = ((Type)annotation).value().toLowerCase();
                        widgets.put(widgetType, widgetClass);
                    }
                }
            }
            catch (PackageManager.NameNotFoundException e) {
                Log.w(WidgetFragment.class, "NameNotFoundException", e);
            }
            catch (IOException e) {
                Log.w(WidgetFragment.class, "IOException", e);
            }
        }
        return widgets.get(type);
    }*/

    public interface FragmentCallback {
        void onFragmentResume(WidgetFragment widgetFragment);
    }
}
