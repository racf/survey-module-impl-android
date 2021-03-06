package com.cysout.sousystems.surveymodule.utils;

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
public class CustomConstants {

    public static  final boolean TRUE = true;
    public static  final boolean FALSE = false;

    //Numeric
    public static final Long LONG_0L = 0L;
    public static final int INT_0 = 0;
    public static final int INT_1 = 1;
    public static final int INT_2 = 2;
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

    //Tag general de la aplicaci??n
    public static final String TAG_LOG = "TAG_SURVEY";

    //Formato de fechas
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    // Database
    public static final String DATABASE_NAME = "survey";
    public static final int DATABASE_VERSION = 1;

    // Preferences
    public static final String PREFERENCE_NAME_QUESTIONNAIRE = "registration_questionnaire";
    public static final String QUESTIONNAIRE_REGISTRATION_ID = "questionnaire_registration_id";

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
    public static final String LABEL = "label";

    //CatEncuestaTipo
    public static final int UNICA = 1; //Este tipo de encuesta solo se aplica una sola vez
    public static final int LIBRE_MULTIPLE = 2; //Este tipo de encuesta generar?? una nueva encuesta cada vez que se aplique
    public static final int LIBRE_UNICA = 3; //Este tipo de encuesta es ??nica y cada vez que se aplique se generar?? el historial de las respuestas

    //CatEncuestaEstatus
    public static final int FINISHED = 1; //Encuesta terminada localmente
    public static final int PENDING = 2; //Encuesta en proceso
    public static final int UPLOADED = 3; // Encuesta guardada en el servidor

    //Message types
    public static final String MESSAGE_SURVEY_RESPONSE = "SurveyResponse";

}