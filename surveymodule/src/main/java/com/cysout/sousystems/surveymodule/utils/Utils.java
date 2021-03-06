package com.cysout.sousystems.surveymodule.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.cysout.sousystems.surveymodule.entity.Answer;
import com.cysout.sousystems.surveymodule.entity.Question;
import com.cysout.sousystems.surveymodule.entity.Questionnaire;
import com.cysout.sousystems.surveymodule.entity.ShowSelect;
import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.SurveyAnswer;
import com.cysout.sousystems.surveymodule.entity.SurveyRecord;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cysout.sousystems.surveymodule.R;
import com.cysout.sousystems.surveymodule.entity.CatEncuestaEstatus;
import com.cysout.sousystems.surveymodule.entity.CatEncuestaTipo;
import com.cysout.sousystems.surveymodule.view.QuestionaryActivity;

/**
 *Developed by cysout.com and sousystems.com.mx
 *Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class Utils {
    /**
     * Método que guarda un estado de la aplicación en una preferencia de tipo texto(string)
     * @param context es el contexto de la aplicación donde se ejecuta la aplicación
     * @param preferenceName es el nombre de la preferencia
     * @param preferenceKey es la clave de la preferencia esta debe ser unica
     * @param valor es el contenido de la preferencia a guardar
     */
    public static void  saveOnPreferenceString(Context context, String preferenceName, String preferenceKey, String valor){
        SharedPreferences settings = context.getSharedPreferences(preferenceName, context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putString(preferenceKey, valor);
        editor.commit();
        editor.apply();
    }

    /**
     * Método que guarda un estado de la aplicación en una preferencia de tipo Long(long)
     * @param context es el contexto de la aplicación donde se ejecuta la aplicación
     * @param preferenceName es el nombre de la preferencia
     * @param preferenceKey es la clave de la preferencia esta debe ser unica
     * @param valor es el contenido de la preferencia a guardar
     */
    public static void  saveOnPreferenceLong(Context context, String preferenceName, String preferenceKey, Long valor){
        SharedPreferences settings = context.getSharedPreferences(preferenceName, context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putLong(preferenceKey, valor);
        editor.commit();
        editor.apply();
    }

    /**
     * Método para encontrar una determinada preferencia de tipo texto(string)
     * @param context es el contexto de la aplicación donde se ejecuta la aplicación
     * @param preferenceName es el nombre de la preferencia
     * @param preferenceKey es la clave de la preferencia
     * @return el valor de la preferencia
     */
    public static String findPreferenceString(Context context, String preferenceName, String preferenceKey) {
        SharedPreferences preferences = context.getSharedPreferences(preferenceName, context.MODE_PRIVATE);
        return  preferences.getString(preferenceKey, "");
    }

    /**
     * Método para encontrar una determinada preferencia de tipo texto(string)
     * @param context es el contexto de la aplicación donde se ejecuta la aplicación
     * @param preferenceName es el nombre de la preferencia
     * @param preferenceKey es la clave de la preferencia
     * @return el valor de la preferencia
     */
    public static Long findPreferenceLong(Context context, String preferenceName, String preferenceKey) {
        SharedPreferences preferences = context.getSharedPreferences(preferenceName, context.MODE_PRIVATE);
        return  preferences.getLong(preferenceKey, 0L);
    }

    public static void deleteSinglePreference(Context context, String preferenceName, String preferenceKey){
        SharedPreferences preferences = context.getSharedPreferences(preferenceName, context.MODE_PRIVATE);
        preferences.edit().remove(preferenceKey).commit();
    }

    public static boolean isValidCURP( String curp, int option){
        boolean status = false;
        if( curp.trim().length() == 18) {
            int divisor = 10;
            String pattern = "^([a-zA-Z]{4})([0-9]{6})(H|M{1})([a-zA-Z]{2})([a-zA-Z]{3})([a-kA-K0-9]{1})([0-9]{1})$";
            // Create a Pattern object
            Pattern r = Pattern.compile(pattern);
            // Now create matcher object.
            Matcher m = r.matcher(curp);
            if (m.find()) {
                String dateCurp = m.group(2);
                isValidDateByCurp(dateCurp);
                status = true;
            } else {
                status = false;
            }
        } else  {
            status = false;
        }

        return status;
    }

    public static boolean isValidDateByCurp( String dateCurp ){
        System.out.println(dateCurp);
        return true;
    }

   /* public static void main(String []srg){
        System.out.println(isValidCURP("RACF910228HGRMPR02", 1));
    }*/


    /**
     * Método que verifica si la INE es valida
     * @param ife clave del INE
     * @param option Si la opción es 1 solo verifica el formato del INE y la logitud, en caso contrarío
     *               si es un numero diferente verifica el formato del INE, la logitud y el número verificador del INE.
     * @return retorna verdadero(true) si la validación es correcta en caso contrarío retorna falso(false).
     */
    public static boolean isValidIFE( String ife, int option){
        boolean status = false;
        if( ife.trim().length() == 18) {
            int divisor = 10;
            //String pattern = "/^([a-zA-Z]{6})([0-9]{6})([0-9]{2})(H|M{1})([0-9]{1})([0-9]{2})$/i";
            String pattern = "^([a-zA-Z]{6})([0-9]{6})([0-9]{2})(H|M{1})([0-9]{1})([0-9]{2})$";
            // Create a Pattern object
            Pattern r = Pattern.compile(pattern);
            // Now create matcher object.
            Matcher m = r.matcher(ife);
            if (m.find()) {
                if (option != 1) {
                    String claveTrunc = m.group(1).concat(m.group(2)).concat(m.group(3));
                    String noVerif = m.group(5);
                    int pos = 1;
                    int suma = 0;
                    for (int i = 0; i < claveTrunc.trim().length(); i++) {
                        suma = suma + numera(claveTrunc.charAt(i) + "", pos);
                        pos++;
                    }
                    int mod = ((suma % divisor) + divisor) % divisor;
                    int verif = divisor - mod;
                    if (verif == 10) {
                        verif = 0;
                    }

                    if (verif == Integer.parseInt(noVerif)) {
                        status = true;
                    } else {
                        status = false;
                    }
                } else {
                    status = true;
                }
            } else {
                status = false;
            }
        }
        return status;
    }

    /**
     * Método que obtiene el numero que se utiliza para obtener el número verificador del INE.
     * @param val, letra de la cadena que permite verificar el INE.
     * @param pos, posición de cada letra de la cadena de verificación.
     * @return número para el calculo del numero verificador.
     */
    private static int numera(String val, int pos){
        int mult = 0;
        int valorConv = 0;
        if ((pos % 2) == 0) {
            mult = -3;
        } else {
            mult = 3;
        }
        String patternNumber = "^\\-{0,1}(?:[0-9]+){0,1}(?:\\.[0-9]+){0,1}$";
        // Create a Pattern object
        Pattern r = Pattern.compile(patternNumber);
        // Now create matcher object.
        Matcher m = r.matcher(val);
        if(m.find()){
            valorConv = Integer.parseInt(val);
            //System.out.println("NUMBER "+valorConv * mult);
        }else {
            valorConv = letterEnumeration(val);
            //System.out.println("LETRA "+valorConv * mult);
        }
        return valorConv * mult;
    }

    /**
     * Método que obtiene el número que le corresponde a una determinada letra del alfabeto
     * @param letter, letra del alfabeto
     * @return el número de una determianda letra.
     */
    private static int letterEnumeration(String letter){
        switch (letter.toUpperCase()){
            case "A": return 1;
            case "B": return 2;
            case "C": return 3;
            case "D": return 4;
            case "E": return 5;
            case "F": return 6;
            case "G": return 7;
            case "H": return 8;
            case "I": return 9;
            case "J": return 10;
            case "K": return 11;
            case "L": return 12;
            case "M": return 13;
            case "N": return 14;
            case "O": return 15;
            case "P": return 16;
            case "Q": return 17;
            case "R": return 18;
            case "S": return 19;
            case "T": return 20;
            case "U": return 21;
            case "V": return 22;
            case "W": return 23;
            case "X": return 24;
            case "Y": return 25;
            case "Z": return 26;
            default: return 0;
        }

    }

    /**
     * Método que genera el UUID (Universally Unique Identifier)
     * @return el identificador.
     */
    public static String generateUUID(){
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString().concat("d.0");
        return randomUUIDString;
    }

    public static List<CatEncuestaTipo> listCatEncuestaTipo(){
        List<CatEncuestaTipo> list = new ArrayList<>();
        list.add(getCatEncuestaTipo(1,"Única", "Este tipo de encuesta solo se aplica una sola vez"));
        list.add(getCatEncuestaTipo(2,"Libre multiple", "Este tipo de encuesta generará una nueva encuesta cada vez que se aplique"));
        list.add(getCatEncuestaTipo(3,"Libre única", "Este tipo de encuesta es única y cada vez que se aplique se generará el historial de las respuestas"));
        return list;
    }

    public static List<CatEncuestaEstatus> listCatEncuestaEstatus(){
        List<CatEncuestaEstatus> list = new ArrayList<>();
        list.add(getCatEncuestaEstatus(1,"Terminada", "Encuesta terminada localmente"));
        list.add(getCatEncuestaEstatus(2,"Pendiente", "Encuesta en proceso"));
        list.add(getCatEncuestaEstatus(3,"Enviada", "Encuesta guardada en el servidor"));
        return list;
    }

    public static CatEncuestaTipo getCatEncuestaTipo (Integer id, String nombre, String descripcion){
        CatEncuestaTipo cat = new CatEncuestaTipo();
        cat.setCatEncuestaTipoId(id);
        cat.setNombre(nombre);
        cat.setDescripcion(descripcion);
        return cat;
    }

    public static CatEncuestaEstatus getCatEncuestaEstatus(Integer id, String nombre, String descripcion){
        CatEncuestaEstatus cat = new CatEncuestaEstatus();
        cat.setCatEncuestaEstatusId(id);
        cat.setNombre(nombre);
        cat.setDescripcion(descripcion);
        return cat;
    }

    public static ArrayList<Survey> convertJsonToObjectSurveys(String surveys){
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Survey>>(){}.getType();
        ArrayList<Survey> array = gson.fromJson(surveys, listType);
        //EncuestaDto[] array = gson.fromJson(encuestas, EncuestaDto[].class);
        return array;
    }


    public static String convertirObjToJson(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static String jsonArrayTest(){
        //Version Test 0.0.1
        //String json = "[{\"encuestaId\":2,\"titulo\":\"AUTODIAGNÓSTICO COVID-19\",\"etapa\":2,\"descripcion\":\"Información para guiar a personas con sospecha de haber contraido COVID-19\",\"catEncuestaTipoId\":1,\"visible\":true,\"cuestionarios\":[{\"cuestionarioId\":10,\"nombre\":\"sospechaCasos\",\"orden\":1,\"preguntas\":[{\"descripcion\":\"Escribe su nombre completo\",\"nombre\":\"nombre\",\"orden\":1,\"preguntaId\":1,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Nombre de la persona\",\"validaciones\":[],\"visible\":true},{\"descripcion\":\"Escribe sus dos apellidos separados por espacios\",\"nombre\":\"apellidos\",\"orden\":2,\"preguntaId\":2,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Apellidos\",\"validaciones\":[],\"visible\":true},{\"descripcion\":\"\",\"nombre\":\"sospecha\",\"orden\":3,\"preguntaId\":3,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{\"cuestionarios\":[],\"preguntas\":[{\"cuestionarioId\":10,\"preguntaId\":4},{\"cuestionarioId\":10,\"preguntaId\":5},{\"cuestionarioId\":10,\"preguntaId\":30},{\"cuestionarioId\":10,\"preguntaId\":31},{\"cuestionarioId\":10,\"preguntaId\":32}],\"respuestas\":[]},\"ocultarSiSelecciona\":{},\"respuestaId\":1,\"texto\":\"SI\",\"visible\":true},{\"finalizarSiSelecciona\":true,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":2,\"texto\":\"NO\",\"visible\":true}],\"tipo\":\"radiogroup\",\"titulo\":\"¿Sospechas que tienes coronavirus o alguien cercano a ti puede tenerlo?\",\"visible\":true},{\"descripcion\":\"\",\"nombre\":\"presentaSintomas\",\"orden\":4,\"preguntaId\":4,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":3,\"texto\":\"SI\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":4,\"texto\":\"NO\",\"visible\":true}],\"tipo\":\"radiogroup\",\"titulo\":\"¿Esta persona tiene algún síntoma como: tos, dolor de garganta, dolor de cabeza y/o fiebre igual o mayor a 38°C?\",\"visible\":false},{\"descripcion\":\"Muestra checkBox y RadioButton - Opoción SI\",\"nombre\":\"presentaSintomasSaltaCuestionario\",\"orden\":5,\"preguntaId\":5,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{\"cuestionarios\":[],\"preguntas\":[],\"respuestas\":[{\"cuestionarioId\":10,\"preguntaId\":31,\"respuestaId\":31},{\"cuestionarioId\":10,\"preguntaId\":32,\"respuestaId\":35}]},\"ocultarSiSelecciona\":{},\"respuestaId\":5,\"texto\":\"SI\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":6,\"texto\":\"NO\",\"visible\":true}],\"tipo\":\"radiogroup\",\"titulo\":\"¿Pregunta que activa respuestas? Opción SI\",\"visible\":false},{\"descripcion\":\"Prueba tipo text\",\"nombre\":\"tipoText\",\"orden\":6,\"preguntaId\":30,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Prueba tipo text\",\"validaciones\":[],\"visible\":false},{\"preguntaId\":31,\"orden\":7,\"tipo\":\"checkbox\",\"nombre\":\"checkTest\",\"titulo\":\"¿Prueba tipo check?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":false,\"respuestas\":[{\"respuestaId\":30,\"texto\":\"1\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":31,\"texto\":\"2\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":false},{\"respuestaId\":32,\"texto\":\"3\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true}]},{\"descripcion\":\"\",\"nombre\":\"testRadiogroup\",\"orden\":8,\"preguntaId\":32,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{\"cuestionarios\":[{\"cuestionarioId\":11}],\"preguntas\":[],\"respuestas\":[]},\"ocultarSiSelecciona\":{},\"respuestaId\":33,\"texto\":\"SI\",\"visible\":true},{\"finalizarSiSelecciona\":true,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":34,\"texto\":\"NO\",\"visible\":true},{\"finalizarSiSelecciona\":true,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":35,\"texto\":\"OTRO\",\"visible\":false}],\"tipo\":\"radiogroup\",\"titulo\":\"¿SALTA AL SIGUIENTE CUESTIONARIO - OPCIÓN SI?\",\"visible\":false}],\"titulo\":\"Sospecha de casos\",\"visible\":true},{\"cuestionarioId\":11,\"titulo\":\"Diagnostico\",\"nombre\":\"cuestionarioDiagnostico\",\"orden\":2,\"visible\":false,\"preguntas\":[{\"descripcion\":\"Escribe su edad\",\"nombre\":\"edad\",\"orden\":1,\"preguntaId\":6,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"number\",\"titulo\":\"Edad en años\",\"validaciones\":[\"verifyEdad\"],\"visible\":true},{\"descripcion\":\"\",\"nombre\":\"sexo\",\"orden\":2,\"preguntaId\":7,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":10,\"texto\":\"Masculino\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":11,\"texto\":\"Femenino\",\"visible\":true}],\"tipo\":\"select\",\"titulo\":\"Género\",\"visible\":true},{\"descripcion\":\"Su número de teléfono tiene que tener 10 dígitos\",\"nombre\":\"telefono\",\"orden\":3,\"preguntaId\":8,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"phone\",\"titulo\":\"Teléfono\",\"validaciones\":[\"verifyTelefono\",\"verifyLadaMorelos\"],\"visible\":true},{\"descripcion\":\"Descripción pregunta municipio\",\"nombre\":\"municipio\",\"orden\":4,\"preguntaId\":9,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"text\",\"titulo\":\"Municipio/Alcaldía\",\"validaciones\":[],\"visible\":true},{\"descripcion\":\"\",\"titulo\":\"¿Pertenece a alguno(s) de los siguientes grupos?\",\"tipo\":\"checkbox\",\"nombre\":\"grupos\",\"orden\":5,\"preguntaId\":10,\"requerido\":true,\"visible\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{\"cuestionarios\":[],\"preguntas\":[{\"cuestionarioId\":11,\"preguntaId\":11}],\"respuestas\":[]},\"ocultarSiSelecciona\":{},\"respuestaId\":12,\"texto\":\"Embarazo\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":13,\"texto\":\"Diabetes\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":14,\"texto\":\"Hipertensión\",\"visible\":true},{\"respuestaId\":15,\"texto\":\"Obesidad\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":16,\"texto\":\"Padece una enfermedad o toma algún medicamento que baje sus defensas\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true}]},{\"preguntaId\":11,\"orden\":6,\"tipo\":\"radiogroup\",\"nombre\":\"mesesEmbarazos\",\"titulo\":\"¿Cuantos meses de embarazo tiene?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":false,\"respuestas\":[{\"respuestaId\":17,\"texto\":\"Un mes\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":18,\"texto\":\"Dos a tres meses\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":19,\"texto\":\"Cuatro a seis meses\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":20,\"texto\":\"Siete a nueve meses\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true}]}]},{\"cuestionarioId\":12,\"nombre\":\"datosFinales\",\"orden\":3,\"titulo\":\"Cuestionario final\",\"visible\":true,\"preguntas\":[{\"descripcion\":\"Alguna opinión\",\"nombre\":\"opinion\",\"orden\":1,\"preguntaId\":40,\"requerido\":false,\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Opinión\",\"validaciones\":[],\"visible\":true}]}]},{\"encuestaId\":3,\"titulo\":\"INFLUENZA\",\"etapa\":1,\"descripcion\":\"Autodiagnóstico influenza\",\"catEncuestaTipoId\":2,\"visible\":true,\"cuestionarios\":[{\"cuestionarioId\":20,\"orden\":1,\"titulo\":\"Datos personales\",\"nombre\":\"datosPersonales\",\"visible\":true,\"preguntas\":[{\"preguntaId\":21,\"orden\":1,\"nombre\":\"nombre\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Nombre de la persona\",\"descripcion\":\"Escribe su nombre\",\"requerido\":true,\"visible\":true,\"validaciones\":[]},{\"preguntaId\":22,\"orden\":2,\"nombre\":\"primerApellido\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Primer Apellido\",\"descripcion\":\"Escribe su apellido paterno\",\"requerido\":true,\"visible\":true,\"validaciones\":[]},{\"preguntaId\":23,\"orden\":3,\"nombre\":\"segundoApellido\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Segundo Apellido\",\"descripcion\":\"Escribe su apellido materno\",\"requerido\":true,\"visible\":true,\"validaciones\":[]},{\"preguntaId\":24,\"orden\":4,\"tipo\":\"radiogroup\",\"nombre\":\"sexo\",\"titulo\":\"Género\",\"descripcion\":\"Género de la persona\",\"requerido\":true,\"visible\":true,\"respuestas\":[{\"respuestaId\":30,\"texto\":\"Masculino\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":31,\"texto\":\"Femenino\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":false}]},{\"preguntaId\":25,\"orden\":5,\"nombre\":\"curp\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Curp\",\"descripcion\":\"La CURP consta de 18 caracteres\",\"requerido\":true,\"visible\":true,\"validaciones\":[]},{\"preguntaId\":26,\"orden\":6,\"tipo\":\"radiogroup\",\"nombre\":\"edadMayor\",\"titulo\":\"¿Usted cuenta con más de 18 años?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":true,\"respuestas\":[{\"respuestaId\":32,\"texto\":\"SI\",\"mostrarSiSelecciona\":{\"cuestionarios\":[{\"cuestionarioId\":21}],\"preguntas\":[{\"cuestionarioId\":20,\"preguntaId\":27}],\"respuestas\":[]},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":33,\"texto\":\"NO\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":true}]},{\"preguntaId\":27,\"orden\":7,\"nombre\":\"ine\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"INE\",\"descripcion\":\"Escribe su clave electoral\",\"requerido\":true,\"visible\":false,\"validaciones\":[]}]},{\"cuestionarioId\":21,\"orden\":2,\"titulo\":\"Datos de diagnóstico\",\"nombre\":\"cuestionarioDiagnostico\",\"visible\":false,\"preguntas\":[{\"preguntaId\":30,\"orden\":1,\"tipo\":\"radiogroup\",\"nombre\":\"sospecha\",\"titulo\":\"¿Sospechas que tienes influenza o alguien cercano a ti puede tenerlo?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":true,\"respuestas\":[{\"respuestaId\":40,\"texto\":\"SI\",\"mostrarSiSelecciona\":{\"cuestionarios\":[],\"preguntas\":[{\"cuestionarioId\":21,\"preguntaId\":31}],\"respuestas\":[]},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":41,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":true}]},{\"preguntaId\":31,\"orden\":2,\"tipo\":\"radiogroup\",\"nombre\":\"diaSintomas\",\"titulo\":\"¿Cuantos días tienes de síntomas?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":false,\"respuestas\":[{\"respuestaId\":42,\"texto\":\"Un día\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":false,\"finalizarSiSelecciona\":false},{\"respuestaId\":43,\"texto\":\"Dos a tres días\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":false,\"finalizarSiSelecciona\":false},{\"respuestaId\":44,\"texto\":\"Cuatro a seis días\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":false,\"finalizarSiSelecciona\":false},{\"respuestaId\":45,\"texto\":\"Siete  o mas días\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":false,\"finalizarSiSelecciona\":false}]}]}]}]";
        //String json = "[{\"encuestaId\":2,\"titulo\":\"AUTODIAGNÓSTICO COVID-19\",\"etapa\":2,\"descripcion\":\"Información para guiar a personas con sospecha de haber contraido COVID-19\",\"catEncuestaTipoId\":1,\"visible\":true,\"cuestionarios\":[{\"cuestionarioId\":10,\"nombre\":\"sospechaCasos\",\"orden\":1,\"preguntas\":[{\"descripcion\":\"Escribe su nombre completo\",\"nombre\":\"nombre\",\"orden\":1,\"preguntaId\":1,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Nombre de la persona\",\"validaciones\":[],\"visible\":true},{\"descripcion\":\"Escribe sus dos apellidos separados por espacios\",\"nombre\":\"apellidos\",\"orden\":2,\"preguntaId\":2,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Apellidos\",\"validaciones\":[],\"visible\":true},{\"descripcion\":\"\",\"nombre\":\"sospecha\",\"orden\":3,\"preguntaId\":3,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{\"cuestionarios\":[],\"preguntas\":[{\"cuestionarioId\":10,\"preguntaId\":4},{\"cuestionarioId\":10,\"preguntaId\":5},{\"cuestionarioId\":10,\"preguntaId\":30},{\"cuestionarioId\":10,\"preguntaId\":31},{\"cuestionarioId\":10,\"preguntaId\":32}],\"respuestas\":[]},\"ocultarSiSelecciona\":{},\"respuestaId\":1,\"texto\":\"SI\",\"visible\":true},{\"finalizarSiSelecciona\":true,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":2,\"texto\":\"NO\",\"visible\":true}],\"tipo\":\"radiogroup\",\"titulo\":\"¿Sospechas que tienes coronavirus o alguien cercano a ti puede tenerlo?\",\"visible\":true},{\"descripcion\":\"\",\"nombre\":\"presentaSintomas\",\"orden\":4,\"preguntaId\":4,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":3,\"texto\":\"SI\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":4,\"texto\":\"NO\",\"visible\":true}],\"tipo\":\"radiogroup\",\"titulo\":\"¿Esta persona tiene algún síntoma como: tos, dolor de garganta, dolor de cabeza y/o fiebre igual o mayor a 38°C?\",\"visible\":false},{\"descripcion\":\"Muestra checkBox y RadioButton - Opoción SI\",\"nombre\":\"presentaSintomasSaltaCuestionario\",\"orden\":5,\"preguntaId\":5,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{\"cuestionarios\":[],\"preguntas\":[],\"respuestas\":[{\"cuestionarioId\":10,\"preguntaId\":31,\"respuestaId\":31},{\"cuestionarioId\":10,\"preguntaId\":32,\"respuestaId\":35}]},\"ocultarSiSelecciona\":{},\"respuestaId\":5,\"texto\":\"SI\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":6,\"texto\":\"NO\",\"visible\":true}],\"tipo\":\"radiogroup\",\"titulo\":\"¿Pregunta que activa respuestas? Opción SI\",\"visible\":false},{\"descripcion\":\"Prueba tipo text\",\"nombre\":\"tipoText\",\"orden\":6,\"preguntaId\":30,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Prueba tipo text\",\"validaciones\":[],\"visible\":false},{\"preguntaId\":31,\"orden\":7,\"tipo\":\"checkbox\",\"nombre\":\"checkTest\",\"titulo\":\"¿Prueba tipo check?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":false,\"respuestas\":[{\"respuestaId\":30,\"texto\":\"1\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":31,\"texto\":\"2\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":false},{\"respuestaId\":32,\"texto\":\"3\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true}]},{\"descripcion\":\"\",\"nombre\":\"testRadiogroup\",\"orden\":8,\"preguntaId\":32,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{\"cuestionarios\":[{\"cuestionarioId\":11}],\"preguntas\":[],\"respuestas\":[]},\"ocultarSiSelecciona\":{},\"respuestaId\":33,\"texto\":\"SI\",\"visible\":true},{\"finalizarSiSelecciona\":true,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":34,\"texto\":\"NO\",\"visible\":true},{\"finalizarSiSelecciona\":true,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":35,\"texto\":\"OTRO\",\"visible\":false}],\"tipo\":\"radiogroup\",\"titulo\":\"¿SALTA AL SIGUIENTE CUESTIONARIO - OPCIÓN SI?\",\"visible\":false}],\"titulo\":\"Sospecha de casos\",\"visible\":true},{\"cuestionarioId\":11,\"titulo\":\"Diagnostico\",\"nombre\":\"cuestionarioDiagnostico\",\"orden\":2,\"visible\":false,\"preguntas\":[{\"descripcion\":\"Escribe su edad\",\"nombre\":\"edad\",\"orden\":1,\"preguntaId\":6,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"number\",\"titulo\":\"Edad en años\",\"validaciones\":[\"verifyEdad\"],\"visible\":true},{\"descripcion\":\"\",\"nombre\":\"sexo\",\"orden\":2,\"preguntaId\":7,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":10,\"texto\":\"Masculino\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":11,\"texto\":\"Femenino\",\"visible\":true}],\"tipo\":\"select\",\"titulo\":\"Género\",\"visible\":true},{\"descripcion\":\"Su número de teléfono tiene que tener 10 dígitos\",\"nombre\":\"telefono\",\"orden\":3,\"preguntaId\":8,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"phone\",\"titulo\":\"Teléfono\",\"validaciones\":[\"verifyTelefono\",\"verifyLadaMorelos\"],\"visible\":true},{\"descripcion\":\"Descripción pregunta municipio\",\"nombre\":\"municipio\",\"orden\":4,\"preguntaId\":9,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"text\",\"titulo\":\"Municipio/Alcaldía\",\"validaciones\":[],\"visible\":true},{\"descripcion\":\"\",\"titulo\":\"¿Pertenece a alguno(s) de los siguientes grupos?\",\"tipo\":\"checkbox\",\"nombre\":\"grupos\",\"orden\":5,\"preguntaId\":10,\"requerido\":true,\"visible\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{\"cuestionarios\":[],\"preguntas\":[{\"cuestionarioId\":11,\"preguntaId\":11}],\"respuestas\":[]},\"ocultarSiSelecciona\":{},\"respuestaId\":12,\"texto\":\"Embarazo\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":13,\"texto\":\"Diabetes\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":14,\"texto\":\"Hipertensión\",\"visible\":true},{\"respuestaId\":15,\"texto\":\"Obesidad\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":16,\"texto\":\"Padece una enfermedad o toma algún medicamento que baje sus defensas\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true}]},{\"preguntaId\":11,\"orden\":6,\"tipo\":\"radiogroup\",\"nombre\":\"mesesEmbarazos\",\"titulo\":\"¿Cuantos meses de embarazo tiene?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":false,\"respuestas\":[{\"respuestaId\":17,\"texto\":\"Un mes\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":18,\"texto\":\"Dos a tres meses\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":19,\"texto\":\"Cuatro a seis meses\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":20,\"texto\":\"Siete a nueve meses\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true}]}]},{\"cuestionarioId\":12,\"nombre\":\"datosFinales\",\"orden\":3,\"titulo\":\"Cuestionario final\",\"visible\":true,\"preguntas\":[{\"descripcion\":\"Alguna opinión\",\"nombre\":\"opinion\",\"orden\":1,\"preguntaId\":40,\"requerido\":false,\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Opinión\",\"validaciones\":[],\"visible\":true}]}]},{\"encuestaId\":3,\"titulo\":\"INFLUENZA\",\"etapa\":1,\"descripcion\":\"Autodiagnóstico influenza\",\"catEncuestaTipoId\":2,\"visible\":true,\"cuestionarios\":[{\"cuestionarioId\":20,\"orden\":1,\"titulo\":\"Datos personales\",\"nombre\":\"datosPersonales\",\"visible\":true,\"preguntas\":[{\"preguntaId\":21,\"orden\":1,\"nombre\":\"nombre\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Nombre de la persona\",\"descripcion\":\"Escribe su nombre\",\"requerido\":true,\"visible\":true,\"validaciones\":[]},{\"preguntaId\":22,\"orden\":2,\"nombre\":\"primerApellido\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Primer Apellido\",\"descripcion\":\"Escribe su apellido paterno\",\"requerido\":true,\"visible\":true,\"validaciones\":[]},{\"preguntaId\":23,\"orden\":3,\"nombre\":\"segundoApellido\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Segundo Apellido\",\"descripcion\":\"Escribe su apellido materno\",\"requerido\":true,\"visible\":true,\"validaciones\":[]},{\"preguntaId\":24,\"orden\":4,\"tipo\":\"radiogroup\",\"nombre\":\"sexo\",\"titulo\":\"Género\",\"descripcion\":\"Género de la persona\",\"requerido\":true,\"visible\":true,\"respuestas\":[{\"respuestaId\":30,\"texto\":\"Masculino\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":31,\"texto\":\"Femenino\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":false}]},{\"preguntaId\":25,\"orden\":5,\"nombre\":\"curp\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Curp\",\"descripcion\":\"La CURP consta de 18 caracteres\",\"requerido\":true,\"visible\":true,\"validaciones\":[]},{\"preguntaId\":26,\"orden\":6,\"tipo\":\"radiogroup\",\"nombre\":\"edadMayor\",\"titulo\":\"¿Usted cuenta con más de 18 años?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":true,\"respuestas\":[{\"respuestaId\":32,\"texto\":\"SI\",\"mostrarSiSelecciona\":{\"cuestionarios\":[{\"cuestionarioId\":21}],\"preguntas\":[{\"cuestionarioId\":20,\"preguntaId\":27}],\"respuestas\":[]},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":33,\"texto\":\"NO\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":true}]},{\"preguntaId\":27,\"orden\":7,\"nombre\":\"ine\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"INE\",\"descripcion\":\"Escribe su clave electoral\",\"requerido\":true,\"visible\":false,\"validaciones\":[]}]},{\"cuestionarioId\":21,\"orden\":2,\"titulo\":\"Datos de diagnóstico\",\"nombre\":\"cuestionarioDiagnostico\",\"visible\":false,\"preguntas\":[{\"preguntaId\":30,\"orden\":1,\"tipo\":\"radiogroup\",\"nombre\":\"sospecha\",\"titulo\":\"¿Sospechas que tienes influenza o alguien cercano a ti puede tenerlo?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":true,\"respuestas\":[{\"respuestaId\":40,\"texto\":\"SI\",\"mostrarSiSelecciona\":{\"cuestionarios\":[],\"preguntas\":[{\"cuestionarioId\":21,\"preguntaId\":31}],\"respuestas\":[]},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":41,\"texto\":\"NO\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":true}]},{\"preguntaId\":31,\"orden\":2,\"tipo\":\"radiogroup\",\"nombre\":\"diaSintomas\",\"titulo\":\"¿Cuantos días tienes de síntomas?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":false,\"respuestas\":[{\"respuestaId\":42,\"texto\":\"Un día\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":43,\"texto\":\"Dos a tres días\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":44,\"texto\":\"Cuatro a seis días\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":45,\"texto\":\"Siete  o mas días\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":false}]}]}]}]";
        //Agrego la pregunta componente Select v0.0.1
        //String json = "[{\"encuestaId\":2,\"versionCode\":2,\"titulo\":\"AUTODIAGNÓSTICO COVID-19\",\"etapa\":2,\"descripcion\":\"Información para guiar a personas con sospecha de haber contraido COVID-19\",\"catEncuestaTipoId\":1,\"visible\":true,\"cuestionarios\":[{\"cuestionarioId\":10,\"nombre\":\"sospechaCasos\",\"orden\":1,\"preguntas\":[{\"descripcion\":\"Escribe su nombre completo\",\"nombre\":\"nombre\",\"orden\":1,\"preguntaId\":1,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Nombre de la persona\",\"validaciones\":[],\"visible\":true},{\"descripcion\":\"Escribe sus dos apellidos separados por espacios\",\"nombre\":\"apellidos\",\"orden\":2,\"preguntaId\":2,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Apellidos\",\"validaciones\":[],\"visible\":true},{\"descripcion\":\"\",\"nombre\":\"sospecha\",\"orden\":3,\"preguntaId\":3,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{\"cuestionarios\":[],\"preguntas\":[{\"cuestionarioId\":10,\"preguntaId\":4},{\"cuestionarioId\":10,\"preguntaId\":5},{\"cuestionarioId\":10,\"preguntaId\":30},{\"cuestionarioId\":10,\"preguntaId\":31},{\"cuestionarioId\":10,\"preguntaId\":32}],\"respuestas\":[]},\"ocultarSiSelecciona\":{},\"respuestaId\":1,\"texto\":\"SI\",\"visible\":true},{\"finalizarSiSelecciona\":true,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":2,\"texto\":\"NO\",\"visible\":true}],\"tipo\":\"radiogroup\",\"titulo\":\"¿Sospechas que tienes coronavirus o alguien cercano a ti puede tenerlo?\",\"visible\":true},{\"descripcion\":\"\",\"nombre\":\"presentaSintomas\",\"orden\":4,\"preguntaId\":4,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":3,\"texto\":\"SI\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":4,\"texto\":\"NO\",\"visible\":true}],\"tipo\":\"radiogroup\",\"titulo\":\"¿Esta persona tiene algún síntoma como: tos, dolor de garganta, dolor de cabeza y/o fiebre igual o mayor a 38°C?\",\"visible\":false},{\"descripcion\":\"Muestra checkBox y RadioButton - Opoción SI\",\"nombre\":\"presentaSintomasSaltaCuestionario\",\"orden\":5,\"preguntaId\":5,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{\"cuestionarios\":[],\"preguntas\":[],\"respuestas\":[{\"cuestionarioId\":10,\"preguntaId\":31,\"respuestaId\":31},{\"cuestionarioId\":10,\"preguntaId\":32,\"respuestaId\":35}]},\"ocultarSiSelecciona\":{},\"respuestaId\":5,\"texto\":\"SI\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":6,\"texto\":\"NO\",\"visible\":true}],\"tipo\":\"radiogroup\",\"titulo\":\"¿Pregunta que activa respuestas? Opción SI\",\"visible\":false},{\"descripcion\":\"Prueba tipo text\",\"nombre\":\"tipoText\",\"orden\":6,\"preguntaId\":30,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Prueba tipo text\",\"validaciones\":[],\"visible\":false},{\"preguntaId\":31,\"orden\":7,\"tipo\":\"checkbox\",\"nombre\":\"checkTest\",\"titulo\":\"¿Prueba tipo check?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":false,\"respuestas\":[{\"respuestaId\":30,\"texto\":\"1\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":31,\"texto\":\"2\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":false},{\"respuestaId\":32,\"texto\":\"3\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true}]},{\"descripcion\":\"\",\"nombre\":\"testRadiogroup\",\"orden\":8,\"preguntaId\":32,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{\"cuestionarios\":[{\"cuestionarioId\":11}],\"preguntas\":[],\"respuestas\":[]},\"ocultarSiSelecciona\":{},\"respuestaId\":33,\"texto\":\"SI\",\"visible\":true},{\"finalizarSiSelecciona\":true,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":34,\"texto\":\"NO\",\"visible\":true},{\"finalizarSiSelecciona\":true,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":35,\"texto\":\"OTRO\",\"visible\":false}],\"tipo\":\"radiogroup\",\"titulo\":\"¿SALTA AL SIGUIENTE CUESTIONARIO - OPCIÓN SI?\",\"visible\":false}],\"titulo\":\"Sospecha de casos\",\"visible\":true},{\"cuestionarioId\":11,\"titulo\":\"Diagnostico\",\"nombre\":\"cuestionarioDiagnostico\",\"orden\":2,\"visible\":false,\"preguntas\":[{\"descripcion\":\"Escribe su edad\",\"nombre\":\"edad\",\"orden\":1,\"preguntaId\":6,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"number\",\"titulo\":\"Edad en años\",\"validaciones\":[\"verifyEdad\"],\"visible\":true},{\"descripcion\":\"\",\"nombre\":\"sexo\",\"orden\":2,\"preguntaId\":7,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":10,\"texto\":\"Masculino\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":11,\"texto\":\"Femenino\",\"visible\":true}],\"tipo\":\"radiogroup\",\"titulo\":\"Género\",\"visible\":true},{\"descripcion\":\"Su número de teléfono tiene que tener 10 dígitos\",\"nombre\":\"telefono\",\"orden\":3,\"preguntaId\":8,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"phone\",\"titulo\":\"Teléfono\",\"validaciones\":[\"verifyTelefono\",\"verifyLadaMorelos\"],\"visible\":true},{\"descripcion\":\"Descripción pregunta municipio\",\"nombre\":\"municipio\",\"orden\":4,\"preguntaId\":9,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"text\",\"titulo\":\"Municipio/Alcaldía\",\"validaciones\":[],\"visible\":true},{\"descripcion\":\"\",\"titulo\":\"¿Pertenece a alguno(s) de los siguientes grupos?\",\"tipo\":\"checkbox\",\"nombre\":\"grupos\",\"orden\":5,\"preguntaId\":10,\"requerido\":true,\"visible\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{\"cuestionarios\":[],\"preguntas\":[{\"cuestionarioId\":11,\"preguntaId\":11}],\"respuestas\":[]},\"ocultarSiSelecciona\":{},\"respuestaId\":12,\"texto\":\"Embarazo\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":13,\"texto\":\"Diabetes\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":14,\"texto\":\"Hipertensión\",\"visible\":true},{\"respuestaId\":15,\"texto\":\"Obesidad\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":16,\"texto\":\"Padece una enfermedad o toma algún medicamento que baje sus defensas\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true}]},{\"preguntaId\":11,\"orden\":6,\"tipo\":\"radiogroup\",\"nombre\":\"mesesEmbarazos\",\"titulo\":\"¿Cuantos meses de embarazo tiene?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":false,\"respuestas\":[{\"respuestaId\":17,\"texto\":\"Un mes\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":18,\"texto\":\"Dos a tres meses\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":19,\"texto\":\"Cuatro a seis meses\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":20,\"texto\":\"Siete a nueve meses\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true}]}]},{\"cuestionarioId\":12,\"nombre\":\"datosFinales\",\"orden\":3,\"titulo\":\"Cuestionario final\",\"visible\":true,\"preguntas\":[{\"descripcion\":\"Alguna opinión\",\"nombre\":\"opinion\",\"orden\":1,\"preguntaId\":40,\"requerido\":false,\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Opinión\",\"validaciones\":[],\"visible\":true}]}]},{\"encuestaId\":3,\"versionCode\":1,\"titulo\":\"INFLUENZA\",\"etapa\":1,\"descripcion\":\"Autodiagnóstico influenza\",\"catEncuestaTipoId\":2,\"visible\":true,\"cuestionarios\":[{\"cuestionarioId\":20,\"orden\":1,\"titulo\":\"Datos personales\",\"nombre\":\"datosPersonales\",\"visible\":true,\"preguntas\":[{\"preguntaId\":21,\"orden\":1,\"nombre\":\"nombre\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Nombre de la persona\",\"descripcion\":\"Escribe su nombre\",\"requerido\":true,\"visible\":true,\"validaciones\":[]},{\"preguntaId\":22,\"orden\":2,\"nombre\":\"primerApellido\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Primer Apellido\",\"descripcion\":\"Escribe su apellido paterno\",\"requerido\":true,\"visible\":true,\"validaciones\":[]},{\"preguntaId\":23,\"orden\":3,\"nombre\":\"segundoApellido\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Segundo Apellido\",\"descripcion\":\"Escribe su apellido materno\",\"requerido\":true,\"visible\":true,\"validaciones\":[]},{\"preguntaId\":24,\"orden\":4,\"tipo\":\"select\",\"tipoInput\":\"selectServer\",\"nombre\":\"sexo\",\"titulo\":\"Género\",\"descripcion\":\"Género de la persona\",\"requerido\":true,\"visible\":true,\"respuestas\":[{\"respuestaId\":30,\"texto\":\"Masculino\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":31,\"texto\":\"Femenino\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":false}]},{\"preguntaId\":25,\"orden\":5,\"nombre\":\"curp\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Curp\",\"descripcion\":\"La CURP consta de 18 caracteres\",\"requerido\":true,\"visible\":true,\"validaciones\":[]},{\"preguntaId\":26,\"orden\":6,\"tipo\":\"radiogroup\",\"nombre\":\"edadMayor\",\"titulo\":\"¿Usted cuenta con más de 18 años?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":true,\"respuestas\":[{\"respuestaId\":32,\"texto\":\"SI\",\"mostrarSiSelecciona\":{\"cuestionarios\":[{\"cuestionarioId\":21}],\"preguntas\":[{\"cuestionarioId\":20,\"preguntaId\":27}],\"respuestas\":[]},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":33,\"texto\":\"NO\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":true}]},{\"preguntaId\":27,\"orden\":7,\"nombre\":\"ine\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"INE\",\"descripcion\":\"Escribe su clave electoral\",\"requerido\":true,\"visible\":false,\"validaciones\":[]}]},{\"cuestionarioId\":21,\"orden\":2,\"titulo\":\"Datos de diagnóstico\",\"nombre\":\"cuestionarioDiagnostico\",\"visible\":false,\"preguntas\":[{\"preguntaId\":30,\"orden\":1,\"tipo\":\"radiogroup\",\"nombre\":\"sospecha\",\"titulo\":\"¿Sospechas que tienes influenza o alguien cercano a ti puede tenerlo?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":true,\"respuestas\":[{\"respuestaId\":40,\"texto\":\"SI\",\"mostrarSiSelecciona\":{\"cuestionarios\":[],\"preguntas\":[{\"cuestionarioId\":21,\"preguntaId\":31}],\"respuestas\":[]},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":41,\"texto\":\"NO\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":true}]},{\"preguntaId\":31,\"orden\":2,\"tipo\":\"radiogroup\",\"nombre\":\"diaSintomas\",\"titulo\":\"¿Cuantos días tienes de síntomas?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":false,\"respuestas\":[{\"respuestaId\":42,\"texto\":\"Un día\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":43,\"texto\":\"Dos a tres días\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":44,\"texto\":\"Cuatro a seis días\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":45,\"texto\":\"Siete  o mas días\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"visible\":true,\"finalizarSiSelecciona\":false}]}]}]}]";
        //json en ingles
        String json = "[{\n" +
                "        \"surveyId\": 2,\n" +
                "        \"title\": \"AUTODIAGNÓSTICO COVID-19\",\n" +
                "        \"phase\": 2,\n" +
                "        \"description\": \"Información para guiar a personas con sospecha de haber contraido COVID-19\",\n" +
                "        \"surveyType\": 1,\n" +
                "        \"visible\": true,\n" +
                "        \"questionnaires\": [{\n" +
                "                \"questionnaireId\": 10,\n" +
                "                \"name\": \"sospechaCasos\",\n" +
                "                \"order\": 1,\n" +
                "                \"questions\": [{\n" +
                "                        \"description\": \"Escribe su nombre completo\",\n" +
                "                        \"name\": \"name\",\n" +
                "                        \"order\": 1,\n" +
                "                        \"questionId\": 1,\n" +
                "                        \"required\": true,\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"typeInput\": \"textPersonName\",\n" +
                "                        \"title\": \"name de la persona\",\n" +
                "                        \"validations\": [],\n" +
                "                        \"visible\": true\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"description\": \"Escribe sus dos apellidos separados por espacios\",\n" +
                "                        \"name\": \"apellidos\",\n" +
                "                        \"order\": 2,\n" +
                "                        \"questionId\": 2,\n" +
                "                        \"required\": true,\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"typeInput\": \"textPersonName\",\n" +
                "                        \"title\": \"Apellidos\",\n" +
                "                        \"validations\": [],\n" +
                "                        \"visible\": true\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"description\": \"\",\n" +
                "                        \"name\": \"sospecha\",\n" +
                "                        \"order\": 3,\n" +
                "                        \"questionId\": 3,\n" +
                "                        \"required\": true,\n" +
                "                        \"answers\": [{\n" +
                "                                \"finishSelect\": false,\n" +
                "                                \"showSelect\": {\n" +
                "                                    \"questionnaires\": [],\n" +
                "                                    \"questions\": [{\n" +
                "                                            \"questionnaireId\": 10,\n" +
                "                                            \"questionId\": 4\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"questionnaireId\": 10,\n" +
                "                                            \"questionId\": 5\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"questionnaireId\": 10,\n" +
                "                                            \"questionId\": 30\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"questionnaireId\": 10,\n" +
                "                                            \"questionId\": 31\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"questionnaireId\": 10,\n" +
                "                                            \"questionId\": 32\n" +
                "                                        }\n" +
                "                                    ],\n" +
                "                                    \"answers\": []\n" +
                "                                },\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"answerId\": 1,\n" +
                "                                \"text\": \"SI\",\n" +
                "                                \"visible\": true\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"finishSelect\": true,\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"answerId\": 2,\n" +
                "                                \"text\": \"NO\",\n" +
                "                                \"visible\": true\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"type\": \"radiogroup\",\n" +
                "                        \"title\": \"¿Sospechas que tienes coronavirus o alguien cercano a ti puede tenerlo?\",\n" +
                "                        \"visible\": true\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"description\": \"\",\n" +
                "                        \"name\": \"presentaSintomas\",\n" +
                "                        \"order\": 4,\n" +
                "                        \"questionId\": 4,\n" +
                "                        \"required\": true,\n" +
                "                        \"answers\": [{\n" +
                "                                \"finishSelect\": false,\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"answerId\": 3,\n" +
                "                                \"text\": \"SI\",\n" +
                "                                \"visible\": true\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"finishSelect\": false,\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"answerId\": 4,\n" +
                "                                \"text\": \"NO\",\n" +
                "                                \"visible\": true\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"type\": \"radiogroup\",\n" +
                "                        \"title\": \"¿Esta persona tiene algún síntoma como: tos, dolor de garganta, dolor de cabeza y/o fiebre igual o mayor a 38°C?\",\n" +
                "                        \"visible\": false\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"description\": \"Muestra checkBox y RadioButton - Opoción SI\",\n" +
                "                        \"name\": \"presentaSintomasSaltaquestionnaire\",\n" +
                "                        \"order\": 5,\n" +
                "                        \"questionId\": 5,\n" +
                "                        \"required\": true,\n" +
                "                        \"answers\": [{\n" +
                "                                \"finishSelect\": false,\n" +
                "                                \"showSelect\": {\n" +
                "                                    \"questionnaires\": [],\n" +
                "                                    \"questions\": [],\n" +
                "                                    \"answers\": [{\n" +
                "                                            \"questionnaireId\": 10,\n" +
                "                                            \"questionId\": 31,\n" +
                "                                            \"answerId\": 31\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"questionnaireId\": 10,\n" +
                "                                            \"questionId\": 32,\n" +
                "                                            \"answerId\": 35\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                                },\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"answerId\": 5,\n" +
                "                                \"text\": \"SI\",\n" +
                "                                \"visible\": true\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"finishSelect\": false,\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"answerId\": 6,\n" +
                "                                \"text\": \"NO\",\n" +
                "                                \"visible\": true\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"type\": \"radiogroup\",\n" +
                "                        \"title\": \"¿Pregunta que activa answers? Opción SI\",\n" +
                "                        \"visible\": false\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"description\": \"Prueba type text\",\n" +
                "                        \"name\": \"typeText\",\n" +
                "                        \"order\": 6,\n" +
                "                        \"questionId\": 30,\n" +
                "                        \"required\": true,\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"typeInput\": \"textPersonName\",\n" +
                "                        \"title\": \"Prueba type text\",\n" +
                "                        \"validations\": [],\n" +
                "                        \"visible\": false\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"questionId\": 31,\n" +
                "                        \"order\": 7,\n" +
                "                        \"type\": \"checkbox\",\n" +
                "                        \"name\": \"checkTest\",\n" +
                "                        \"title\": \"¿Prueba type check?\",\n" +
                "                        \"description\": \"\",\n" +
                "                        \"required\": true,\n" +
                "                        \"visible\": false,\n" +
                "                        \"answers\": [{\n" +
                "                                \"answerId\": 30,\n" +
                "                                \"text\": \"1\",\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"finishSelect\": false,\n" +
                "                                \"visible\": true\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"answerId\": 31,\n" +
                "                                \"text\": \"2\",\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"finishSelect\": false,\n" +
                "                                \"visible\": false\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"answerId\": 32,\n" +
                "                                \"text\": \"3\",\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"finishSelect\": false,\n" +
                "                                \"visible\": true\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"description\": \"\",\n" +
                "                        \"name\": \"testRadiogroup\",\n" +
                "                        \"order\": 8,\n" +
                "                        \"questionId\": 32,\n" +
                "                        \"required\": true,\n" +
                "                        \"answers\": [{\n" +
                "                                \"finishSelect\": false,\n" +
                "                                \"showSelect\": {\n" +
                "                                    \"questionnaires\": [{\n" +
                "                                        \"questionnaireId\": 11\n" +
                "                                    }],\n" +
                "                                    \"questions\": [],\n" +
                "                                    \"answers\": []\n" +
                "                                },\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"answerId\": 33,\n" +
                "                                \"text\": \"SI\",\n" +
                "                                \"visible\": true\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"finishSelect\": true,\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"answerId\": 34,\n" +
                "                                \"text\": \"NO\",\n" +
                "                                \"visible\": true\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"finishSelect\": true,\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"answerId\": 35,\n" +
                "                                \"text\": \"OTRO\",\n" +
                "                                \"visible\": false\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"type\": \"radiogroup\",\n" +
                "                        \"title\": \"¿SALTA AL SIGUIENTE questionnaire - OPCIÓN SI?\",\n" +
                "                        \"visible\": false\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"title\": \"Sospecha de casos\",\n" +
                "                \"visible\": true\n" +
                "            },\n" +
                "            {\n" +
                "                \"questionnaireId\": 11,\n" +
                "                \"title\": \"Diagnostico\",\n" +
                "                \"name\": \"questionnaireDiagnostico\",\n" +
                "                \"order\": 2,\n" +
                "                \"visible\": false,\n" +
                "                \"questions\": [{\n" +
                "                        \"description\": \"Escribe su edad\",\n" +
                "                        \"name\": \"edad\",\n" +
                "                        \"order\": 1,\n" +
                "                        \"questionId\": 6,\n" +
                "                        \"required\": true,\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"typeInput\": \"number\",\n" +
                "                        \"title\": \"Edad en años\",\n" +
                "                        \"validations\": [\n" +
                "                            \"verifyEdad\"\n" +
                "                        ],\n" +
                "                        \"visible\": true\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"description\": \"\",\n" +
                "                        \"name\": \"sexo\",\n" +
                "                        \"order\": 2,\n" +
                "                        \"questionId\": 7,\n" +
                "                        \"required\": true,\n" +
                "                        \"answers\": [{\n" +
                "                                \"finishSelect\": false,\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"answerId\": 10,\n" +
                "                                \"text\": \"Masculino\",\n" +
                "                                \"visible\": true\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"finishSelect\": false,\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"answerId\": 11,\n" +
                "                                \"text\": \"Femenino\",\n" +
                "                                \"visible\": true\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"type\": \"radiogroup\",\n" +
                "                        \"title\": \"Género\",\n" +
                "                        \"visible\": true\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"description\": \"Su número de teléfono tiene que tener 10 dígitos\",\n" +
                "                        \"name\": \"telefono\",\n" +
                "                        \"order\": 3,\n" +
                "                        \"questionId\": 8,\n" +
                "                        \"required\": true,\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"typeInput\": \"phone\",\n" +
                "                        \"title\": \"Teléfono\",\n" +
                "                        \"validations\": [\n" +
                "                            \"verifyTelefono\",\n" +
                "                            \"verifyLadaMorelos\"\n" +
                "                        ],\n" +
                "                        \"visible\": true\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"description\": \"Descripción pregunta municipio\",\n" +
                "                        \"name\": \"municipio\",\n" +
                "                        \"order\": 4,\n" +
                "                        \"questionId\": 9,\n" +
                "                        \"required\": true,\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"typeInput\": \"text\",\n" +
                "                        \"title\": \"Municipio/Alcaldía\",\n" +
                "                        \"validations\": [],\n" +
                "                        \"visible\": true\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"description\": \"\",\n" +
                "                        \"title\": \"¿Pertenece a alguno(s) de los siguientes grupos?\",\n" +
                "                        \"type\": \"checkbox\",\n" +
                "                        \"name\": \"grupos\",\n" +
                "                        \"order\": 5,\n" +
                "                        \"questionId\": 10,\n" +
                "                        \"required\": true,\n" +
                "                        \"visible\": true,\n" +
                "                        \"answers\": [{\n" +
                "                                \"finishSelect\": false,\n" +
                "                                \"showSelect\": {\n" +
                "                                    \"questionnaires\": [],\n" +
                "                                    \"questions\": [{\n" +
                "                                        \"questionnaireId\": 11,\n" +
                "                                        \"questionId\": 11\n" +
                "                                    }],\n" +
                "                                    \"answers\": []\n" +
                "                                },\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"answerId\": 12,\n" +
                "                                \"text\": \"Embarazo\",\n" +
                "                                \"visible\": true\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"finishSelect\": false,\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"answerId\": 13,\n" +
                "                                \"text\": \"Diabetes\",\n" +
                "                                \"visible\": true\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"finishSelect\": false,\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"answerId\": 14,\n" +
                "                                \"text\": \"Hipertensión\",\n" +
                "                                \"visible\": true\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"answerId\": 15,\n" +
                "                                \"text\": \"Obesidad\",\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"finishSelect\": false,\n" +
                "                                \"visible\": true\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"answerId\": 16,\n" +
                "                                \"text\": \"Padece una enfermedad o toma algún medicamento que baje sus defensas\",\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"finishSelect\": false,\n" +
                "                                \"visible\": true\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"questionId\": 11,\n" +
                "                        \"order\": 6,\n" +
                "                        \"type\": \"radiogroup\",\n" +
                "                        \"name\": \"mesesEmbarazos\",\n" +
                "                        \"title\": \"¿Cuantos meses de embarazo tiene?\",\n" +
                "                        \"description\": \"\",\n" +
                "                        \"required\": true,\n" +
                "                        \"visible\": false,\n" +
                "                        \"answers\": [{\n" +
                "                                \"answerId\": 17,\n" +
                "                                \"text\": \"Un mes\",\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"finishSelect\": false,\n" +
                "                                \"visible\": true\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"answerId\": 18,\n" +
                "                                \"text\": \"Dos a tres meses\",\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"finishSelect\": false,\n" +
                "                                \"visible\": true\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"answerId\": 19,\n" +
                "                                \"text\": \"Cuatro a seis meses\",\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"finishSelect\": false,\n" +
                "                                \"visible\": true\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"answerId\": 20,\n" +
                "                                \"text\": \"Siete a nueve meses\",\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"finishSelect\": false,\n" +
                "                                \"visible\": true\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"questionnaireId\": 12,\n" +
                "                \"name\": \"datosFinales\",\n" +
                "                \"order\": 3,\n" +
                "                \"title\": \"questionnaire final\",\n" +
                "                \"visible\": true,\n" +
                "                \"questions\": [{\n" +
                "                    \"description\": \"Alguna opinión\",\n" +
                "                    \"name\": \"opinion\",\n" +
                "                    \"order\": 1,\n" +
                "                    \"questionId\": 40,\n" +
                "                    \"required\": false,\n" +
                "                    \"type\": \"text\",\n" +
                "                    \"typeInput\": \"textPersonName\",\n" +
                "                    \"title\": \"Opinión\",\n" +
                "                    \"validations\": [],\n" +
                "                    \"visible\": true\n" +
                "                }]\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"surveyId\": 3,\n" +
                "        \"title\": \"INFLUENZA\",\n" +
                "        \"phase\": 1,\n" +
                "        \"description\": \"Autodiagnóstico influenza\",\n" +
                "        \"surveyType\": 2,\n" +
                "        \"visible\": true,\n" +
                "        \"questionnaires\": [{\n" +
                "                \"questionnaireId\": 20,\n" +
                "                \"order\": 1,\n" +
                "                \"title\": \"Datos personales\",\n" +
                "                \"name\": \"datosPersonales\",\n" +
                "                \"visible\": true,\n" +
                "                \"questions\": [{\n" +
                "                        \"questionId\": 21,\n" +
                "                        \"order\": 1,\n" +
                "                        \"name\": \"name\",\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"typeInput\": \"textPersonName\",\n" +
                "                        \"title\": \"name de la persona\",\n" +
                "                        \"description\": \"Escribe su name\",\n" +
                "                        \"required\": true,\n" +
                "                        \"visible\": true,\n" +
                "                        \"validations\": []\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"questionId\": 22,\n" +
                "                        \"order\": 2,\n" +
                "                        \"name\": \"primerApellido\",\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"typeInput\": \"textPersonName\",\n" +
                "                        \"title\": \"Primer Apellido\",\n" +
                "                        \"description\": \"Escribe su apellido paterno\",\n" +
                "                        \"required\": true,\n" +
                "                        \"visible\": true,\n" +
                "                        \"validations\": []\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"questionId\": 23,\n" +
                "                        \"order\": 3,\n" +
                "                        \"name\": \"segundoApellido\",\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"typeInput\": \"textPersonName\",\n" +
                "                        \"title\": \"Segundo Apellido\",\n" +
                "                        \"description\": \"Escribe su apellido materno\",\n" +
                "                        \"required\": true,\n" +
                "                        \"visible\": true,\n" +
                "                        \"validations\": []\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"questionId\": 24,\n" +
                "                        \"order\": 4,\n" +
                "                        \"type\": \"select\",\n" +
                "                        \"typeInput\": \"selectServer\",\n" +
                "                        \"name\": \"sexo\",\n" +
                "                        \"title\": \"Género\",\n" +
                "                        \"description\": \"Género de la persona\",\n" +
                "                        \"required\": true,\n" +
                "                        \"visible\": true,\n" +
                "                        \"answers\": [{\n" +
                "                                \"answerId\": 30,\n" +
                "                                \"text\": \"Masculino\",\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"visible\": true,\n" +
                "                                \"finishSelect\": false\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"answerId\": 31,\n" +
                "                                \"text\": \"Femenino\",\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"visible\": true,\n" +
                "                                \"finishSelect\": false\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"questionId\": 25,\n" +
                "                        \"order\": 5,\n" +
                "                        \"name\": \"curp\",\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"typeInput\": \"textPersonName\",\n" +
                "                        \"title\": \"Curp\",\n" +
                "                        \"description\": \"La CURP consta de 18 caracteres\",\n" +
                "                        \"required\": true,\n" +
                "                        \"visible\": true,\n" +
                "                        \"validations\": []\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"questionId\": 26,\n" +
                "                        \"order\": 6,\n" +
                "                        \"type\": \"radiogroup\",\n" +
                "                        \"name\": \"edadMayor\",\n" +
                "                        \"title\": \"¿Usted cuenta con más de 18 años?\",\n" +
                "                        \"description\": \"\",\n" +
                "                        \"required\": true,\n" +
                "                        \"visible\": true,\n" +
                "                        \"answers\": [{\n" +
                "                                \"answerId\": 32,\n" +
                "                                \"text\": \"SI\",\n" +
                "                                \"showSelect\": {\n" +
                "                                    \"questionnaires\": [{\n" +
                "                                        \"questionnaireId\": 21\n" +
                "                                    }],\n" +
                "                                    \"questions\": [{\n" +
                "                                        \"questionnaireId\": 20,\n" +
                "                                        \"questionId\": 27\n" +
                "                                    }],\n" +
                "                                    \"answers\": []\n" +
                "                                },\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"visible\": true,\n" +
                "                                \"finishSelect\": false\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"answerId\": 33,\n" +
                "                                \"text\": \"NO\",\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"visible\": true,\n" +
                "                                \"finishSelect\": true\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"questionId\": 27,\n" +
                "                        \"order\": 7,\n" +
                "                        \"name\": \"ine\",\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"typeInput\": \"textPersonName\",\n" +
                "                        \"title\": \"INE\",\n" +
                "                        \"description\": \"Escribe su clave electoral\",\n" +
                "                        \"required\": true,\n" +
                "                        \"visible\": false,\n" +
                "                        \"validations\": []\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"questionnaireId\": 21,\n" +
                "                \"order\": 2,\n" +
                "                \"title\": \"Datos de diagnóstico\",\n" +
                "                \"name\": \"questionnaireDiagnostico\",\n" +
                "                \"visible\": false,\n" +
                "                \"questions\": [{\n" +
                "                        \"questionId\": 30,\n" +
                "                        \"order\": 1,\n" +
                "                        \"type\": \"radiogroup\",\n" +
                "                        \"name\": \"sospecha\",\n" +
                "                        \"title\": \"¿Sospechas que tienes influenza o alguien cercano a ti puede tenerlo?\",\n" +
                "                        \"description\": \"\",\n" +
                "                        \"required\": true,\n" +
                "                        \"visible\": true,\n" +
                "                        \"answers\": [{\n" +
                "                                \"answerId\": 40,\n" +
                "                                \"text\": \"SI\",\n" +
                "                                \"showSelect\": {\n" +
                "                                    \"questionnaires\": [],\n" +
                "                                    \"questions\": [{\n" +
                "                                        \"questionnaireId\": 21,\n" +
                "                                        \"questionId\": 31\n" +
                "                                    }],\n" +
                "                                    \"answers\": []\n" +
                "                                },\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"visible\": true,\n" +
                "                                \"finishSelect\": false\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"answerId\": 41,\n" +
                "                                \"text\": \"NO\",\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"visible\": true,\n" +
                "                                \"finishSelect\": true\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"questionId\": 31,\n" +
                "                        \"order\": 2,\n" +
                "                        \"type\": \"radiogroup\",\n" +
                "                        \"name\": \"diaSintomas\",\n" +
                "                        \"title\": \"¿Cuantos días tienes de síntomas?\",\n" +
                "                        \"description\": \"\",\n" +
                "                        \"required\": true,\n" +
                "                        \"visible\": false,\n" +
                "                        \"answers\": [{\n" +
                "                                \"answerId\": 42,\n" +
                "                                \"text\": \"Un día\",\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"visible\": true,\n" +
                "                                \"finishSelect\": false\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"answerId\": 43,\n" +
                "                                \"text\": \"Dos a tres días\",\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"visible\": true,\n" +
                "                                \"finishSelect\": false\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"answerId\": 44,\n" +
                "                                \"text\": \"Cuatro a seis días\",\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"visible\": true,\n" +
                "                                \"finishSelect\": false\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"answerId\": 45,\n" +
                "                                \"text\": \"Siete  o mas días\",\n" +
                "                                \"showSelect\": {},\n" +
                "                                \"hideSelect\": {},\n" +
                "                                \"visible\": true,\n" +
                "                                \"finishSelect\": false\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]";
        return json;
    }

    public static String jsonArrayTestFinal(){
        String json = "[{\"catEncuestaTipoId\":1,\"cuestionarios\":[{\"cuestionarioId\":10,\"nombre\":\"sospechaCasos\",\"orden\":1,\"preguntas\":[{\"descripcion\":\"Escribe su nombre completo\",\"nombre\":\"nombre\",\"orden\":1,\"preguntaId\":1,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Nombre de la persona\",\"validaciones\":[],\"visible\":true},{\"descripcion\":\"Escribe sus dos apellidos separados por espacios\",\"nombre\":\"apellidos\",\"orden\":2,\"preguntaId\":2,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Apellidos\",\"validaciones\":[],\"visible\":true},{\"descripcion\":\"\",\"nombre\":\"sospecha\",\"orden\":3,\"preguntaId\":3,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":[{\"cuestionarios\":[],\"preguntas\":[{\"cuestionarioId\":10,\"preguntaId\":4},{\"cuestionarioId\":10,\"preguntaId\":5}],\"respuestas\":[{\"cuestionarioId\":10,\"preguntaId\":4,\"respuestaId\":4}]}],\"ocultarSiSelecciona\":[],\"respuestaId\":1,\"texto\":\"SI\",\"visible\":true},{\"finalizarSiSelecciona\":true,\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"respuestaId\":2,\"texto\":\"NO\",\"visible\":true}],\"tipo\":\"radiogroup\",\"titulo\":\"¿Sospechas que tienes coronavirus o alguien cercano a ti puede tenerlo?\",\"visible\":true},{\"descripcion\":\"\",\"nombre\":\"presentaSintomas\",\"orden\":4,\"preguntaId\":4,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"respuestaId\":3,\"texto\":\"SI\",\"visible\":false},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"respuestaId\":4,\"texto\":\"NO\",\"visible\":false}],\"tipo\":\"radiogroup\",\"titulo\":\"¿Esta persona tiene algún síntoma como: tos, dolor de garganta, dolor de cabeza y/o fiebre igual o mayor a 38°C?\",\"visible\":false},{\"descripcion\":\"\",\"nombre\":\"presentaSintomasSaltaCuestionario\",\"orden\":5,\"preguntaId\":5,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":[{\"cuestionarios\":[11],\"preguntas\":[],\"respuestas\":[]}],\"ocultarSiSelecciona\":[],\"respuestaId\":5,\"texto\":\"SI\",\"visible\":false},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"respuestaId\":6,\"texto\":\"NO\",\"visible\":false}],\"tipo\":\"radiogroup\",\"titulo\":\"¿Pregunta que salta al siguiente cuestionario? SI\",\"visible\":false}],\"titulo\":\"Sospecha de casos\",\"visible\":true},{\"cuestionarioId\":11,\"nombre\":\"cuestionarioDiagnostico\",\"orden\":2,\"preguntas\":[{\"descripcion\":\"Escribe su edad\",\"nombre\":\"edad\",\"orden\":1,\"preguntaId\":6,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"number\",\"titulo\":\"Edad en años\",\"validaciones\":[\"verifyEdad\"],\"visible\":true},{\"descripcion\":\"\",\"nombre\":\"sexo\",\"orden\":2,\"preguntaId\":7,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"respuestaId\":10,\"texto\":\"Masculino\",\"visible\":false},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"respuestaId\":11,\"texto\":\"Femenino\",\"visible\":false}],\"tipo\":\"select\",\"titulo\":\"Género\",\"visible\":true},{\"descripcion\":\"Su número de teléfono tiene que tener 10 dígitos\",\"nombre\":\"telefono\",\"orden\":3,\"preguntaId\":8,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"phone\",\"titulo\":\"Teléfono\",\"validaciones\":[\"verifyTelefono\",\"verifyLadaMorelos\"],\"visible\":true},{\"descripcion\":\"Descripción pregunta municipio\",\"nombre\":\"municipio\",\"orden\":4,\"preguntaId\":9,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"text\",\"titulo\":\"Municipio/Alcaldía\",\"validaciones\":[],\"visible\":true},{\"descripcion\":\"\",\"nombre\":\"grupos\",\"orden\":5,\"preguntaId\":10,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":[{\"cuestionarios\":[],\"preguntas\":[{\"cuestionarioId\":11,\"preguntaId\":11}],\"respuestas\":[]}],\"ocultarSiSelecciona\":[],\"respuestaId\":12,\"texto\":\"Embarazo\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"respuestaId\":13,\"texto\":\"Diabetes\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"respuestaId\":14,\"texto\":\"Hipertensión\",\"visible\":true},{\"respuestaId\":15,\"texto\":\"Obesidad\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"finalizarSiSelecciona\":false},{\"respuestaId\":16,\"texto\":\"Padece una enfermedad o toma algún medicamento que baje sus defensas\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"finalizarSiSelecciona\":false}]},{\"preguntaId\":11,\"orden\":6,\"tipo\":\"radiogroup\",\"nombre\":\"mesesEmbarazos\",\"titulo\":\"¿Cuantos meses de embarazo tiene?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":false,\"respuestas\":[{\"respuestaId\":17,\"texto\":\"Un mes\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"finalizarSiSelecciona\":false},{\"respuestaId\":18,\"texto\":\"Dos a tres meses\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"finalizarSiSelecciona\":false},{\"respuestaId\":19,\"texto\":\"Cuatro a seis meses\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"finalizarSiSelecciona\":false},{\"respuestaId\":20,\"texto\":\"Siete a nueve meses\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"finalizarSiSelecciona\":false}]}]}]},{\"encuestaId\":3,\"titulo\":\"INFLUENZA\",\"etapa\":1,\"descripcion\":\"Autodiagnóstico influenza\",\"catEncuestaTipoId\":2,\"visible\":true,\"cuestionarios\":[{\"cuestionarioId\":20,\"orden\":1,\"titulo\":\"Datos personales\",\"nombre\":\"datosPersonales\",\"visible\":true,\"preguntas\":[{\"preguntaId\":21,\"orden\":1,\"nombre\":\"nombre\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Nombre de la persona\",\"descripcion\":\"Escribe su nombre\",\"requerido\":true,\"visible\":true,\"validaciones\":[]},{\"preguntaId\":22,\"orden\":2,\"nombre\":\"primerApellido\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Primer Apellido\",\"descripcion\":\"Escribe su apellido paterno\",\"requerido\":true,\"visible\":true,\"validaciones\":[]},{\"preguntaId\":23,\"orden\":3,\"nombre\":\"segundoApellido\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Segundo Apellido\",\"descripcion\":\"Escribe su apellido materno\",\"requerido\":true,\"visible\":true,\"validaciones\":[]},{\"preguntaId\":24,\"orden\":4,\"tipo\":\"radiogroup\",\"nombre\":\"sexo\",\"titulo\":\"Género\",\"descripcion\":\"Género de la persona\",\"requerido\":true,\"visible\":true,\"respuestas\":[{\"respuestaId\":30,\"texto\":\"Masculino\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":31,\"texto\":\"Femenino\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"visible\":true,\"finalizarSiSelecciona\":false}]},{\"preguntaId\":25,\"orden\":5,\"nombre\":\"curp\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Curp\",\"descripcion\":\"La CURP consta de 18 caracteres\",\"requerido\":true,\"visible\":true,\"validaciones\":[]},{\"preguntaId\":26,\"orden\":6,\"tipo\":\"radiogroup\",\"nombre\":\"edadMayor\",\"titulo\":\"¿Usted cuenta con más de 18 años?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":true,\"respuestas\":[{\"respuestaId\":32,\"texto\":\"SI\",\"mostrarSiSelecciona\":[{\"cuestionarios\":[21],\"preguntas\":[{\"cuestionarioId\":20,\"preguntaId\":27}],\"respuestas\":[]}],\"ocultarSiSelecciona\":[],\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":33,\"texto\":\"NO\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"visible\":true,\"finalizarSiSelecciona\":true}]},{\"preguntaId\":27,\"orden\":7,\"nombre\":\"ine\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"INE\",\"descripcion\":\"Escribe su clave electoral\",\"requerido\":true,\"visible\":false,\"validaciones\":[]}]},{\"cuestionarioId\":21,\"orden\":2,\"titulo\":\"Datos de diagnóstico\",\"nombre\":\"cuestionarioDiagnostico\",\"visible\":false,\"preguntas\":[{\"preguntaId\":30,\"orden\":1,\"tipo\":\"radiogroup\",\"nombre\":\"sospecha\",\"titulo\":\"¿Sospechas que tienes influenza o alguien cercano a ti puede tenerlo?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":true,\"respuestas\":[{\"respuestaId\":40,\"texto\":\"SI\",\"mostrarSiSelecciona\":[{\"cuestionarios\":[],\"preguntas\":[{\"cuestionarioId\":21,\"preguntaId\":31}],\"respuestas\":[]}],\"ocultarSiSelecciona\":[],\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":41,\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"visible\":true,\"finalizarSiSelecciona\":true}]},{\"preguntaId\":31,\"orden\":2,\"tipo\":\"radiogroup\",\"nombre\":\"diaSintomas\",\"titulo\":\"¿Cuantos días tienes de síntomas?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":false,\"respuestas\":[{\"respuestaId\":42,\"texto\":\"Un día\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"visible\":false,\"finalizarSiSelecciona\":false},{\"respuestaId\":43,\"texto\":\"Dos a tres días\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"visible\":false,\"finalizarSiSelecciona\":false},{\"respuestaId\":44,\"texto\":\"Cuatro a seis días\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"visible\":false,\"finalizarSiSelecciona\":false},{\"respuestaId\":45,\"texto\":\"Siete  o mas días\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"visible\":false,\"finalizarSiSelecciona\":false}]}]}]}]";
        return  json;
    }
    public static String jsonStringFinal(){
        String json = "{\"encuestaId\":2,\"titulo\":\"AUTODIAGNÓSTICO COVID-19\",\"etapa\":2,\"descripcion\":\"Información para guiar a personas con sospecha de haber contraido COVID-19\",\"catEncuestaTipoId\":1,\"visible\":true,\"cuestionarios\":[{\"cuestionarioId\":10,\"nombre\":\"sospechaCasos\",\"orden\":1,\"preguntas\":[{\"descripcion\":\"Escribe su nombre completo\",\"nombre\":\"nombre\",\"orden\":1,\"preguntaId\":1,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Nombre de la persona\",\"validaciones\":[],\"visible\":true},{\"descripcion\":\"Escribe sus dos apellidos separados por espacios\",\"nombre\":\"apellidos\",\"orden\":2,\"preguntaId\":2,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Apellidos\",\"validaciones\":[],\"visible\":true},{\"descripcion\":\"\",\"nombre\":\"sospecha\",\"orden\":3,\"preguntaId\":3,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{\"cuestionarios\":[],\"preguntas\":[{\"cuestionarioId\":10,\"preguntaId\":4},{\"cuestionarioId\":10,\"preguntaId\":5},{\"cuestionarioId\":10,\"preguntaId\":30},{\"cuestionarioId\":10,\"preguntaId\":31},{\"cuestionarioId\":10,\"preguntaId\":32}],\"respuestas\":[]},\"ocultarSiSelecciona\":{},\"respuestaId\":1,\"texto\":\"SI\",\"visible\":true},{\"finalizarSiSelecciona\":true,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":2,\"texto\":\"NO\",\"visible\":true}],\"tipo\":\"radiogroup\",\"titulo\":\"¿Sospechas que tienes coronavirus o alguien cercano a ti puede tenerlo?\",\"visible\":true},{\"descripcion\":\"\",\"nombre\":\"presentaSintomas\",\"orden\":4,\"preguntaId\":4,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":3,\"texto\":\"SI\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":4,\"texto\":\"NO\",\"visible\":true}],\"tipo\":\"radiogroup\",\"titulo\":\"¿Esta persona tiene algún síntoma como: tos, dolor de garganta, dolor de cabeza y/o fiebre igual o mayor a 38°C?\",\"visible\":false},{\"descripcion\":\"Muestra checkBox y RadioButton - Opoción SI\",\"nombre\":\"presentaSintomasSaltaCuestionario\",\"orden\":5,\"preguntaId\":5,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{\"cuestionarios\":[],\"preguntas\":[],\"respuestas\":[{\"cuestionarioId\":10,\"preguntaId\":31,\"respuestaId\":31},{\"cuestionarioId\":10,\"preguntaId\":32,\"respuestaId\":35}]},\"ocultarSiSelecciona\":{},\"respuestaId\":5,\"texto\":\"SI\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":6,\"texto\":\"NO\",\"visible\":true}],\"tipo\":\"radiogroup\",\"titulo\":\"¿Pregunta que activa respuestas? Opción SI\",\"visible\":false},{\"descripcion\":\"Prueba tipo text\",\"nombre\":\"tipoText\",\"orden\":6,\"preguntaId\":30,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Prueba tipo text\",\"validaciones\":[],\"visible\":false},{\"preguntaId\":31,\"orden\":7,\"tipo\":\"checkbox\",\"nombre\":\"checkTest\",\"titulo\":\"¿Prueba tipo check?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":false,\"respuestas\":[{\"respuestaId\":30,\"texto\":\"1\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":31,\"texto\":\"2\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":false},{\"respuestaId\":32,\"texto\":\"3\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true}]},{\"descripcion\":\"\",\"nombre\":\"testRadiogroup\",\"orden\":8,\"preguntaId\":32,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{\"cuestionarios\":[{\"cuestionarioId\":11}],\"preguntas\":[],\"respuestas\":[]},\"ocultarSiSelecciona\":{},\"respuestaId\":33,\"texto\":\"SI\",\"visible\":true},{\"finalizarSiSelecciona\":true,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":34,\"texto\":\"NO\",\"visible\":true},{\"finalizarSiSelecciona\":true,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":35,\"texto\":\"OTRO\",\"visible\":false}],\"tipo\":\"radiogroup\",\"titulo\":\"¿SALTA AL SIGUIENTE CUESTIONARIO - OPCIÓN SI?\",\"visible\":false}],\"titulo\":\"Sospecha de casos\",\"visible\":true},{\"cuestionarioId\":11,\"titulo\":\"Diagnostico\",\"nombre\":\"cuestionarioDiagnostico\",\"orden\":2,\"visible\":false,\"preguntas\":[{\"descripcion\":\"Escribe su edad\",\"nombre\":\"edad\",\"orden\":1,\"preguntaId\":6,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"number\",\"titulo\":\"Edad en años\",\"validaciones\":[\"verifyEdad\"],\"visible\":true},{\"descripcion\":\"\",\"nombre\":\"sexo\",\"orden\":2,\"preguntaId\":7,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":10,\"texto\":\"Masculino\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":11,\"texto\":\"Femenino\",\"visible\":true}],\"tipo\":\"select\",\"titulo\":\"Género\",\"visible\":true},{\"descripcion\":\"Su número de teléfono tiene que tener 10 dígitos\",\"nombre\":\"telefono\",\"orden\":3,\"preguntaId\":8,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"phone\",\"titulo\":\"Teléfono\",\"validaciones\":[\"verifyTelefono\",\"verifyLadaMorelos\"],\"visible\":true},{\"descripcion\":\"Descripción pregunta municipio\",\"nombre\":\"municipio\",\"orden\":4,\"preguntaId\":9,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"text\",\"titulo\":\"Municipio/Alcaldía\",\"validaciones\":[],\"visible\":true},{\"descripcion\":\"\",\"titulo\":\"¿Pertenece a alguno(s) de los siguientes grupos?\",\"tipo\":\"checkbox\",\"nombre\":\"grupos\",\"orden\":5,\"preguntaId\":10,\"requerido\":true,\"visible\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{\"cuestionarios\":[],\"preguntas\":[{\"cuestionarioId\":11,\"preguntaId\":11}],\"respuestas\":[]},\"ocultarSiSelecciona\":{},\"respuestaId\":12,\"texto\":\"Embarazo\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":13,\"texto\":\"Diabetes\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":14,\"texto\":\"Hipertensión\",\"visible\":true},{\"respuestaId\":15,\"texto\":\"Obesidad\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":16,\"texto\":\"Padece una enfermedad o toma algún medicamento que baje sus defensas\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true}]},{\"preguntaId\":11,\"orden\":6,\"tipo\":\"radiogroup\",\"nombre\":\"mesesEmbarazos\",\"titulo\":\"¿Cuantos meses de embarazo tiene?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":false,\"respuestas\":[{\"respuestaId\":17,\"texto\":\"Un mes\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":18,\"texto\":\"Dos a tres meses\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":19,\"texto\":\"Cuatro a seis meses\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":20,\"texto\":\"Siete a nueve meses\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true}]}]},{\"cuestionarioId\":12,\"nombre\":\"datosFinales\",\"orden\":3,\"titulo\":\"Cuestionario final\",\"visible\":true,\"preguntas\":[{\"descripcion\":\"Alguna opinión\",\"nombre\":\"opinion\",\"orden\":1,\"preguntaId\":40,\"requerido\":false,\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Opinión\",\"validaciones\":[],\"visible\":true}]}]}";
        //String json = "{\"encuestaId\":2,\"titulo\":\"AUTODIAGNÓSTICO COVID-19\",\"etapa\":2,\"descripcion\":\"Información para guiar a personas con sospecha de haber contraido COVID-19\",\"catEncuestaTipoId\":1,\"visible\":true,\"cuestionarios\":[{\"cuestionarioId\":10,\"nombre\":\"sospechaCasos\",\"orden\":1,\"preguntas\":[{\"descripcion\":\"Escribe su nombre completo\",\"nombre\":\"nombre\",\"orden\":1,\"preguntaId\":1,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Nombre de la persona\",\"validaciones\":[],\"visible\":true},{\"descripcion\":\"Escribe sus dos apellidos separados por espacios\",\"nombre\":\"apellidos\",\"orden\":2,\"preguntaId\":2,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Apellidos\",\"validaciones\":[],\"visible\":true},{\"descripcion\":\"\",\"nombre\":\"sospecha\",\"orden\":3,\"preguntaId\":3,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{\"cuestionarios\":[],\"preguntas\":[{\"cuestionarioId\":10,\"preguntaId\":4},{\"cuestionarioId\":10,\"preguntaId\":5},{\"cuestionarioId\":10,\"preguntaId\":30},{\"cuestionarioId\":10,\"preguntaId\":31},{\"cuestionarioId\":10,\"preguntaId\":32}],\"respuestas\":[]},\"ocultarSiSelecciona\":{},\"respuestaId\":1,\"texto\":\"SI\",\"visible\":true},{\"finalizarSiSelecciona\":true,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":2,\"texto\":\"NO\",\"visible\":true}],\"tipo\":\"radiogroup\",\"titulo\":\"¿Sospechas que tienes coronavirus o alguien cercano a ti puede tenerlo?\",\"visible\":true},{\"descripcion\":\"\",\"nombre\":\"presentaSintomas\",\"orden\":4,\"preguntaId\":4,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":3,\"texto\":\"SI\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":4,\"texto\":\"NO\",\"visible\":true}],\"tipo\":\"radiogroup\",\"titulo\":\"¿Esta persona tiene algún síntoma como: tos, dolor de garganta, dolor de cabeza y/o fiebre igual o mayor a 38°C?\",\"visible\":false},{\"descripcion\":\"Muestra checkBox y RadioButton - Opoción SI\",\"nombre\":\"presentaSintomasSaltaCuestionario\",\"orden\":5,\"preguntaId\":5,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{\"cuestionarios\":[],\"preguntas\":[],\"respuestas\":[{\"cuestionarioId\":10,\"preguntaId\":31,\"respuestaId\":31},{\"cuestionarioId\":10,\"preguntaId\":32,\"respuestaId\":35}]},\"ocultarSiSelecciona\":{},\"respuestaId\":5,\"texto\":\"SI\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":6,\"texto\":\"NO\",\"visible\":true}],\"tipo\":\"radiogroup\",\"titulo\":\"¿Pregunta que activa respuestas? Opción SI\",\"visible\":false},{\"descripcion\":\"Prueba tipo text\",\"nombre\":\"tipoText\",\"orden\":6,\"preguntaId\":30,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Prueba tipo text\",\"validaciones\":[],\"visible\":false},{\"preguntaId\":31,\"orden\":7,\"tipo\":\"checkbox\",\"nombre\":\"checkTest\",\"titulo\":\"¿Prueba tipo check?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":false,\"respuestas\":[{\"respuestaId\":30,\"texto\":\"1\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":31,\"texto\":\"2\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":false},{\"respuestaId\":32,\"texto\":\"3\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true}]},{\"descripcion\":\"\",\"nombre\":\"testRadiogroup\",\"orden\":8,\"preguntaId\":32,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{\"cuestionarios\":[{\"cuestionarioId\":11}],\"preguntas\":[],\"respuestas\":[]},\"ocultarSiSelecciona\":{},\"respuestaId\":33,\"texto\":\"SI\",\"visible\":true},{\"finalizarSiSelecciona\":true,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":34,\"texto\":\"NO\",\"visible\":true},{\"finalizarSiSelecciona\":true,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":35,\"texto\":\"OTRO\",\"visible\":false}],\"tipo\":\"radiogroup\",\"titulo\":\"¿SALTA AL SIGUIENTE CUESTIONARIO - OPCIÓN SI?\",\"visible\":false}],\"titulo\":\"Sospecha de casos\",\"visible\":true},{\"cuestionarioId\":11,\"titulo\":\"Diagnostico\",\"nombre\":\"cuestionarioDiagnostico\",\"orden\":2,\"visible\":false,\"preguntas\":[{\"descripcion\":\"Escribe su edad\",\"nombre\":\"edad\",\"orden\":1,\"preguntaId\":6,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"number\",\"titulo\":\"Edad en años\",\"validaciones\":[\"verifyEdad\"],\"visible\":true},{\"descripcion\":\"\",\"nombre\":\"sexo\",\"orden\":2,\"preguntaId\":7,\"requerido\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":10,\"texto\":\"Masculino\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":11,\"texto\":\"Femenino\",\"visible\":true}],\"tipo\":\"select\",\"titulo\":\"Género\",\"visible\":true},{\"descripcion\":\"Su número de teléfono tiene que tener 10 dígitos\",\"nombre\":\"telefono\",\"orden\":3,\"preguntaId\":8,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"phone\",\"titulo\":\"Teléfono\",\"validaciones\":[\"verifyTelefono\",\"verifyLadaMorelos\"],\"visible\":true},{\"descripcion\":\"Descripción pregunta municipio\",\"nombre\":\"municipio\",\"orden\":4,\"preguntaId\":9,\"requerido\":true,\"tipo\":\"text\",\"tipoInput\":\"text\",\"titulo\":\"Municipio/Alcaldía\",\"validaciones\":[],\"visible\":true},{\"descripcion\":\"\",\"titulo\":\"¿Pertenece a alguno(s) de los siguientes grupos?\",\"tipo\":\"checkbox\",\"nombre\":\"grupos\",\"orden\":5,\"preguntaId\":10,\"requerido\":true,\"visible\":true,\"respuestas\":[{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{\"cuestionarios\":[],\"preguntas\":[{\"cuestionarioId\":11,\"preguntaId\":11}],\"respuestas\":[]},\"ocultarSiSelecciona\":{},\"respuestaId\":12,\"texto\":\"Embarazo\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":13,\"texto\":\"Diabetes\",\"visible\":true},{\"finalizarSiSelecciona\":false,\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"respuestaId\":14,\"texto\":\"Hipertensión\",\"visible\":true},{\"respuestaId\":15,\"texto\":\"Obesidad\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":16,\"texto\":\"Padece una enfermedad o toma algún medicamento que baje sus defensas\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true}]},{\"preguntaId\":11,\"orden\":6,\"tipo\":\"radiogroup\",\"nombre\":\"mesesEmbarazos\",\"titulo\":\"¿Cuantos meses de embarazo tiene?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":false,\"respuestas\":[{\"respuestaId\":17,\"texto\":\"Un mes\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":18,\"texto\":\"Dos a tres meses\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":19,\"texto\":\"Cuatro a seis meses\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true},{\"respuestaId\":20,\"texto\":\"Siete a nueve meses\",\"mostrarSiSelecciona\":{},\"ocultarSiSelecciona\":{},\"finalizarSiSelecciona\":false,\"visible\":true}]}]}]}";
        return json;
    }
    public static String jsonString(){
        //Una sola encuesta
        String jsonString = "{\"encuestaId\":2,\"titulo\":\"AUTODIAGNÓSTICO COVID-19\",\"etapa\":2,\"descripcion\":\"Información para guiar a personas con sospecha de haber contraido COVID-19\",\"catEncuestaTipoId\":1,\"visible\":true,\"cuestionarios\":[{\"cuestionarioId\":10,\"orden\":1,\"titulo\":\"Sospecha de casos\",\"nombre\":\"sospechaCasos\",\"visible\":true,\"preguntas\":[{\"preguntaId\":1,\"orden\":1,\"nombre\":\"nombre\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Nombre de la persona\",\"descripcion\":\"Escribe su nombre completo\",\"requerido\":true,\"visible\":false,\"validaciones\":[]},{\"preguntaId\":2,\"orden\":2,\"nombre\":\"apellidos\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Apellidos\",\"descripcion\":\"Escribe sus dos apellidos separados por espacios\",\"requerido\":true,\"visible\":true,\"validaciones\":[]},{\"preguntaId\":3,\"orden\":3,\"tipo\":\"radiogroup\",\"nombre\":\"sospecha\",\"titulo\":\"¿Sospechas que tienes coronavirus o alguien cercano a ti puede tenerlo?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":true,\"respuestas\":[{\"respuestaId\":1,\"texto\":\"SI\",\"mostrarSiSelecciona\":[{\"cuestionarios\":[],\"preguntas\":[{\"cuestionarioId\":10,\"preguntaId\":4},{\"cuestionarioId\":10,\"preguntaId\":5}],\"respuestas\":[{\"cuestionarioId\":10,\"preguntaId\":4,\"respuestaId\":4}]}],\"ocultarSiSelecciona\":[],\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":2,\"texto\":\"NO\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"visible\":true,\"finalizarSiSelecciona\":true}]},{\"preguntaId\":4,\"orden\":4,\"tipo\":\"radiogroup\",\"nombre\":\"presentaSintomas\",\"titulo\":\"¿Esta persona tiene algún síntoma como: tos, dolor de garganta, dolor de cabeza y/o fiebre igual o mayor a 38°C?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":false,\"respuestas\":[{\"respuestaId\":3,\"texto\":\"SI\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"visible\":false,\"finalizarSiSelecciona\":false},{\"respuestaId\":4,\"texto\":\"NO\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"visible\":false,\"finalizarSiSelecciona\":false}]},{\"preguntaId\":5,\"orden\":5,\"tipo\":\"radiogroup\",\"nombre\":\"presentaSintomasSaltaCuestionario\",\"titulo\":\"¿Pregunta que salta al siguiente cuestionario? SI\",\"descripcion\":\"\",\"requerido\":true,\"visible\":false,\"respuestas\":[{\"respuestaId\":5,\"texto\":\"SI\",\"mostrarSiSelecciona\":[{\"cuestionarios\":[11],\"preguntas\":[],\"respuestas\":[]}],\"ocultarSiSelecciona\":[],\"visible\":false,\"finalizarSiSelecciona\":false},{\"respuestaId\":6,\"texto\":\"NO\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"visible\":false,\"finalizarSiSelecciona\":false}]}]},{\"cuestionarioId\":11,\"orden\":2,\"titulo\":\"Datos de diagnóstico\",\"nombre\":\"cuestionarioDiagnostico\",\"visible\":false,\"preguntas\":[{\"preguntaId\":6,\"orden\":1,\"nombre\":\"edad\",\"tipo\":\"text\",\"tipoInput\":\"number\",\"titulo\":\"Edad en años\",\"descripcion\":\"Escribe su edad\",\"requerido\":true,\"visible\":true,\"validaciones\":[\"verifyEdad\"]},{\"preguntaId\":7,\"orden\":2,\"tipo\":\"select\",\"nombre\":\"sexo\",\"titulo\":\"Género\",\"descripcion\":\"\",\"requerido\":true,\"visible\":true,\"respuestas\":[{\"respuestaId\":10,\"texto\":\"Masculino\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"visible\":false},{\"respuestaId\":11,\"texto\":\"Femenino\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"visible\":false}]},{\"preguntaId\":8,\"orden\":3,\"nombre\":\"telefono\",\"tipo\":\"text\",\"tipoInput\":\"phone\",\"titulo\":\"Teléfono\",\"descripcion\":\"Su número de teléfono tiene que tener 10 dígitos\",\"requerido\":true,\"visible\":true,\"validaciones\":[\"verifyTelefono\",\"verifyLadaMorelos\"]},{\"preguntaId\":9,\"orden\":4,\"nombre\":\"municipio\",\"tipo\":\"text\",\"tipoInput\":\"text\",\"titulo\":\"Municipio/Alcaldía\",\"descripcion\":\"Descripción pregunta municipio\",\"requerido\":true,\"visible\":true,\"validaciones\":[]},{\"preguntaId\":10,\"orden\":5,\"tipo\":\"checkbox\",\"nombre\":\"grupos\",\"titulo\":\"¿Pertenece a alguno(s) de los siguientes grupos?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":true,\"respuestas\":[{\"respuestaId\":12,\"texto\":\"Embarazo\",\"mostrarSiSelecciona\":[{\"cuestionarios\":[],\"preguntas\":[{\"cuestionarioId\":11,\"preguntaId\":11}],\"respuestas\":[]}],\"ocultarSiSelecciona\":[],\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":13,\"texto\":\"Diabetes\",\"mostrarSiSelecciona\":[],\"finalizarSiSelecciona\":false},{\"respuestaId\":14,\"texto\":\"Hipertensión\",\"mostrarSiSelecciona\":[],\"finalizarSiSelecciona\":false},{\"respuestaId\":15,\"texto\":\"Obesidad\",\"mostrarSiSelecciona\":[],\"finalizarSiSelecciona\":false},{\"respuestaId\":16,\"texto\":\"Padece una enfermedad o toma algún medicamento que baje sus defensas\",\"mostrarSiSelecciona\":[],\"finalizarSiSelecciona\":false}]},{\"preguntaId\":11,\"orden\":6,\"tipo\":\"radiogroup\",\"nombre\":\"mesesEmbarazos\",\"titulo\":\"¿Cuantos meses de embarazo tiene?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":false,\"respuestas\":[{\"respuestaId\":17,\"texto\":\"Un mes\",\"mostrarSiSelecciona\":[],\"finalizarSiSelecciona\":false},{\"respuestaId\":18,\"texto\":\"Dos a tres meses\",\"mostrarSiSelecciona\":[],\"finalizarSiSelecciona\":false},{\"respuestaId\":19,\"texto\":\"Cuatro a seis meses\",\"mostrarSiSelecciona\":[],\"finalizarSiSelecciona\":false},{\"respuestaId\":20,\"texto\":\"Siete a nueve meses\",\"mostrarSiSelecciona\":[],\"finalizarSiSelecciona\":false}]}]}]}";
        //String jsonString = "{\"encuestaId\":3,\"titulo\":\"INFLUENZA\",\"etapa\":1,\"descripcion\":\"Autodiagnóstico influenza\",\"catEncuestaTipoId\":2,\"visible\":true,\"cuestionarios\":[{\"cuestionarioId\":20,\"orden\":1,\"titulo\":\"Datos personales\",\"nombre\":\"datosPersonales\",\"visible\":true,\"preguntas\":[{\"preguntaId\":21,\"orden\":1,\"nombre\":\"nombre\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Nombre de la persona\",\"descripcion\":\"Escribe su nombre\",\"requerido\":true,\"visible\":true,\"validaciones\":[]},{\"preguntaId\":22,\"orden\":2,\"nombre\":\"primerApellido\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Primer Apellido\",\"descripcion\":\"Escribe su apellido paterno\",\"requerido\":true,\"visible\":true,\"validaciones\":[]},{\"preguntaId\":23,\"orden\":3,\"nombre\":\"segundoApellido\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Segundo Apellido\",\"descripcion\":\"Escribe su apellido materno\",\"requerido\":true,\"visible\":true,\"validaciones\":[]},{\"preguntaId\":24,\"orden\":4,\"tipo\":\"radiogroup\",\"nombre\":\"sexo\",\"titulo\":\"Género\",\"descripcion\":\"Género de la persona\",\"requerido\":true,\"visible\":true,\"respuestas\":[{\"respuestaId\":30,\"texto\":\"Masculino\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":31,\"texto\":\"Femenino\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"visible\":true,\"finalizarSiSelecciona\":false}]},{\"preguntaId\":25,\"orden\":5,\"nombre\":\"curp\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"Curp\",\"descripcion\":\"La CURP consta de 18 caracteres\",\"requerido\":true,\"visible\":true,\"validaciones\":[]},{\"preguntaId\":26,\"orden\":6,\"tipo\":\"radiogroup\",\"nombre\":\"edadMayor\",\"titulo\":\"¿Usted cuenta con más de 18 años?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":true,\"respuestas\":[{\"respuestaId\":32,\"texto\":\"SI\",\"mostrarSiSelecciona\":[{\"cuestionarios\":[21],\"preguntas\":[{\"cuestionarioId\":20,\"preguntaId\":27}],\"respuestas\":[]}],\"ocultarSiSelecciona\":[],\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":33,\"texto\":\"NO\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"visible\":true,\"finalizarSiSelecciona\":true}]},{\"preguntaId\":27,\"orden\":7,\"nombre\":\"ine\",\"tipo\":\"text\",\"tipoInput\":\"textPersonName\",\"titulo\":\"INE\",\"descripcion\":\"Escribe su clave electoral\",\"requerido\":true,\"visible\":false,\"validaciones\":[]}]},{\"cuestionarioId\":21,\"orden\":2,\"titulo\":\"Datos de diagnóstico\",\"nombre\":\"cuestionarioDiagnostico\",\"visible\":false,\"preguntas\":[{\"preguntaId\":30,\"orden\":1,\"tipo\":\"radiogroup\",\"nombre\":\"sospecha\",\"titulo\":\"¿Sospechas que tienes influenza o alguien cercano a ti puede tenerlo?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":true,\"respuestas\":[{\"respuestaId\":40,\"texto\":\"SI\",\"mostrarSiSelecciona\":[{\"cuestionarios\":[],\"preguntas\":[{\"cuestionarioId\":21,\"preguntaId\":31}],\"respuestas\":[]}],\"ocultarSiSelecciona\":[],\"visible\":true,\"finalizarSiSelecciona\":false},{\"respuestaId\":41,\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"visible\":true,\"finalizarSiSelecciona\":true}]},{\"preguntaId\":31,\"orden\":2,\"tipo\":\"radiogroup\",\"nombre\":\"diaSintomas\",\"titulo\":\"¿Cuantos días tienes de síntomas?\",\"descripcion\":\"\",\"requerido\":true,\"visible\":false,\"respuestas\":[{\"respuestaId\":42,\"texto\":\"Un día\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"visible\":false,\"finalizarSiSelecciona\":false},{\"respuestaId\":43,\"texto\":\"Dos a tres días\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"visible\":false,\"finalizarSiSelecciona\":false},{\"respuestaId\":44,\"texto\":\"Cuatro a seis días\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"visible\":false,\"finalizarSiSelecciona\":false},{\"respuestaId\":45,\"texto\":\"Siete  o mas días\",\"mostrarSiSelecciona\":[],\"ocultarSiSelecciona\":[],\"visible\":false,\"finalizarSiSelecciona\":false}]}]}]}";
        return jsonString;
    }

    public static String dateTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CustomConstants.DATE_TIME_FORMAT);
        String date = simpleDateFormat.format(new Date());
        return date;
    }
    private Survey getEncuesta(Long id, String titulo, String descripcion, Integer etapa, Long catEncuestaTipoId, String json){
        Survey survey = new Survey();
        survey.setSurveyId(id);
        survey.setTitle(titulo);
        survey.setDescription(descripcion);
        survey.setPhase(etapa);
        survey.setSurveyType(catEncuestaTipoId);
        // encuesta.setJson(json);
        return survey;
    }
    public static SurveyRecord getEncuestaRegistro(Survey survey, Integer catEncuestaEstatusId, String fechaInicial, String fechaFinal){
        SurveyRecord surveyRecord = new SurveyRecord();
        surveyRecord.setSurveyId(survey.getSurveyId());
        surveyRecord.setSurveyStatus(catEncuestaEstatusId);
        surveyRecord.setStartDate(fechaInicial);
        surveyRecord.setEndDate(fechaFinal);
        return surveyRecord;
    }
    public static SurveyAnswer getEncuestaRespuesta(Questionnaire questionnaire,
                                                    Question question, String respuesta, Long encuestaRegistroId){
        SurveyAnswer surveyAnswer = new SurveyAnswer();
        surveyAnswer.setSurveyRecordId(encuestaRegistroId);
        surveyAnswer.setQuestionnaireId(questionnaire.getQuestionnaireId());
        surveyAnswer.setQuestionId(question.getQuestionId());
        surveyAnswer.setType(question.getType());
        surveyAnswer.setAnswer(respuesta);
        return surveyAnswer;
    }

    public static ShowSelect infoMostrarSiSelecciona(Answer answer){
        ShowSelect showSelect = null;
        if(!Utils.isEmpty(answer.getShowSelect())){
            if( !Utils.isEmpty(answer.getShowSelect().getQuestionnaires()) || !Utils.isEmpty(answer.getShowSelect().getQuestions())
                    || !Utils.isEmpty(answer.getShowSelect().getAnswers())){
                showSelect = answer.getShowSelect();
            }
        }
        return showSelect;
    }

    public static boolean isEmpty(Collection obj) {
        return obj == null || obj.isEmpty();
    }

    public static boolean isEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

    public static boolean isEmpty(Object obj) {
        return obj == null || obj.toString().trim().isEmpty();
    }

    public static void startNewSurveyFragment(Context context, Activity activity, Survey survey){
        Intent intent = new Intent(context, QuestionaryActivity.class);
        intent.putExtra(CustomConstants.SURVEY_KEY, survey);
        activity.startActivityForResult(intent, CustomConstants.QUESTIONNAIRES_REQUEST);
    }

    public static void startNewSurvey(Context context, Survey survey){
        Intent intent = new Intent(context, QuestionaryActivity.class);
        intent.putExtra(CustomConstants.SURVEY_KEY, survey);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /*public static void startOldSurvey(Context context, Activity activity, Survey survey, EncuestaRegistro registrationSurvey){
        Intent intent = new Intent(context, QuestionaryActivity.class);
        intent.putExtra(CustomConstants.SURVEY_KEY, survey);
        intent.putExtra(CustomConstants.REGISTRATION_SURVEY_KEY, registrationSurvey);
        activity.startActivityForResult(intent, CustomConstants.QUESTIONNAIRES_REQUEST);
    }

    public static void startOldSurvey(Context context, Activity activity, SurveyRecords surveyRecords){
        Survey survey = surveyRecords.getSurvey();
        EncuestaRegistro registrationSurvey = surveyRecords.getRecord();
        Intent intent = new Intent(context, QuestionaryActivity.class);
        intent.putExtra(CustomConstants.SURVEY_KEY, survey);
        intent.putExtra(CustomConstants.REGISTRATION_SURVEY_KEY, registrationSurvey);
        activity.startActivityForResult(intent, CustomConstants.QUESTIONNAIRES_REQUEST);
    }*/

    public static Answer getRespuestaSpinnerDefault(Context mContext){
        Answer answer = new Answer();
        answer.setAnswerId(0L);
        answer.setQuestionId(0L);
        answer.setText(mContext.getString(R.string.spinner_mensaje_default));
        return answer;
    }
}
