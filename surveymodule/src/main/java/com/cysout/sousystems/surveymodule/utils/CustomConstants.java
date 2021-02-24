package com.cysout.sousystems.surveymodule.utils;

public class CustomConstants {

    public static  final boolean TRUE = true;
    public static  final boolean FALSE = false;

    //Numeric
    public static final Long LONG_0L = 0L;
    public static final int INT_0 = 0;
    //Code
    public static final int CODE_200 = 200;
    public static final int CODE_500 = 500;
    //Result
    public static final int QUESTIONNAIRES_REQUEST = 2468;
    //Signs
    public static final String BACK_SLASH = "\\";
    public static final String SLASH ="/";
    public static final String PERIOT = ".";
    public static final String SPACE = " ";
    public static final String COMMA = ",";
    public static final String COLON = ":";
    public static final String SEMI_COLON = ";";
    public static final String ELLIPSIS = "...";
    public static final String ASTERISK = "*";
    public static final String HYPHEN = "-";
    public static final String LOW_DASH = "_";
    public static final String OPEN_PARENTHESIS = "(";
    public static final String CLOSE_PARENTHESIS = ")";
    public static final String OPEN_BRACKETS = "[";
    public static final String CLOSE_BRACKETS = "]";
    public static final String AT = "@";
    public static final String DOT = ".";
    public static final String POUND_KEY = "#";
    public static final String EQUALS = "=";
    public static final String WEIGHT_SIGN = "$";

    //Tag general de la aplicación
    public static final String TAG_LOG = "TAG_ENCUESTA";

    //Formato de fechas
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    // Database
    public static final String DATABASE_NAME = "encuesta";
    public static final int DATABASE_VERSION = 3;

    // Estados
    public static Integer ENCUESTA_TERMINADA = 1;
    public static Integer ENCUESTA_EN_PROCESO = 2;
    public static Integer ENCUESTA_ENVIADA = 3;

    // Preferences
    public static final String PREFERENCE_NAME_CUESTIONARIO = "cuestionario_registro";
    public static final String CUESTIONARIO_REGISTRO_ID = "cuestionario_registro_id";

    // Parameters
    public static final String SURVEY_KEY = "surveyKey";
    public static final String REGISTRATION_SURVEY_KEY = "registrationSurveyKey";
    public static final String SURVEY_RESPONSE = "surveyResponse";

    //Propiedades de visibilidad
    public static final int VISIBLE = 0;
    public static final int INVISIBLE = 4;
    public static final int GONE = 8;

    //Tipos de componentes
    public static final String TEXT = "text";
    public static final String RADIOGROUP = "radiogroup";
    public static final String CHECKBOX = "checkbox";
    public static final String SELECT = "select";

    //CatEncuestaTipo
    public static final int UNICA = 1; //Este tipo de encuesta solo se aplica una sola vez
    public static final int LIBRE_MULTIPLE = 2; //Este tipo de encuesta generará una nueva encuesta cada vez que se aplique
    public static final int LIBRE_UNICA = 3; //Este tipo de encuesta es única y cada vez que se aplique se generará el historial de las respuestas

    //CatEncuestaEstatus
    public static final int TERMINADA = 1; //Encuesta terminada localmente
    public static final int PENDIENTE = 2; //Encuesta en proceso
    public static final int ENVIADA = 3; // Encuesta guardada en el servidor

    //Message types
    public static final String MESSAGE_SURVEY_RESPONSE = "SurveyResponse";

}