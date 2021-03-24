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
import com.cysout.sousystems.surveymodule.view.QuestionaryActivity;

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

    /**
     * Método que genera el UUID (Universally Unique Identifier)
     * @return el identificador.
     */
    public static String generateUUID(){
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString().concat("d.0");
        return randomUUIDString;
    }

    public static ArrayList<Survey> convertJsonToObjectSurveys(String surveys){
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Survey>>(){}.getType();
        ArrayList<Survey> array = gson.fromJson(surveys, listType);
        //EncuestaDto[] array = gson.fromJson(encuestas, EncuestaDto[].class);
        return array;
    }


    public static String convertObjectToJson(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static String jsonArrayTest1(){
        String json = "[{\n" +
                "    \"surveyId\": 1,\n" +
                "    \"title\": \"Survey showing the different types of components\",\n" +
                "    \"phase\": 2,\n" +
                "    \"description\": \"Survey description\",\n" +
                "    \"surveyType\": 1,\n" +
                "    \"visible\": true,\n" +
                "    \"versionCode\": 1,\n" +
                "    \"questionnaires\": [{\n" +
                "        \"questionnaireId\": 10,\n" +
                "        \"name\": \"Component questionnaires\",\n" +
                "        \"order\": 1,\n" +
                "        \"title\": \"Component questionnaires\",\n" +
                "        \"visible\": true,\n" +
                "        \"questions\": [{\n" +
                "                \"description\": \"Subtitle of the label component\",\n" +
                "                \"name\": \"name\",\n" +
                "                \"order\": 1,\n" +
                "                \"questionId\": 1,\n" +
                "                \"required\": false,\n" +
                "                \"type\": \"label\",\n" +
                "                \"title\": \"Title of the label component\",\n" +
                "                \"validations\": [],\n" +
                "                \"visible\": true\n" +
                "            },\n" +
                "            {\n" +
                "                \"description\": \"Component description is typeInput textPersonName\",\n" +
                "                \"name\": \"\",\n" +
                "                \"order\": 2,\n" +
                "                \"questionId\": 2,\n" +
                "                \"required\": true,\n" +
                "                \"type\": \"text\",\n" +
                "                \"typeInput\": \"textPersonName\",\n" +
                "                \"title\": \"Title of the text component\",\n" +
                "                \"validations\": [],\n" +
                "                \"visible\": true\n" +
                "            },\n" +
                "            {\n" +
                "                \"description\": \"Component description is typeInput number\",\n" +
                "                \"name\": \"\",\n" +
                "                \"order\": 3,\n" +
                "                \"questionId\": 3,\n" +
                "                \"required\": false,\n" +
                "                \"type\": \"text\",\n" +
                "                \"typeInput\": \"number\",\n" +
                "                \"title\": \"Title of the text component\",\n" +
                "                \"validations\": [],\n" +
                "                \"visible\": true\n" +
                "            },\n" +
                "            {\n" +
                "                \"description\": \"Component description is typeInput phone\",\n" +
                "                \"name\": \"\",\n" +
                "                \"order\": 4,\n" +
                "                \"questionId\": 4,\n" +
                "                \"required\": false,\n" +
                "                \"type\": \"text\",\n" +
                "                \"typeInput\": \"phone\",\n" +
                "                \"title\": \"Title of the text component\",\n" +
                "                \"validations\": [],\n" +
                "                \"visible\": true\n" +
                "            },\n" +
                "            {\n" +
                "                \"description\": \"Component description is typeInput text\",\n" +
                "                \"name\": \"\",\n" +
                "                \"order\": 5,\n" +
                "                \"questionId\": 5,\n" +
                "                \"required\": false,\n" +
                "                \"type\": \"text\",\n" +
                "                \"typeInput\": \"text\",\n" +
                "                \"title\": \"Title of the text component\",\n" +
                "                \"validations\": [],\n" +
                "                \"visible\": true\n" +
                "            }\n" +
                "        ]\n" +
                "    }]\n" +
                "}]";
        return json;
    }

    public static String jsonArrayTest(){
        //json en ingles
        String json = "[{\n" +
                "        \"surveyId\": 2,\n" +
                "        \"title\": \"AUTODIAGNÓSTICO COVID-11\",\n" +
                "        \"phase\": 2,\n" +
                "        \"description\": \"Información para guiar a personas con sospecha de haber contraido COVID-19\",\n" +
                "        \"surveyType\": 1,\n" +
                "        \"visible\": true,\n" +
                "        \"versionCode\": 7,\n" +
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
                "        \"versionCode\": 1,\n" +
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

    public static String dateTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CustomConstants.DATE_TIME_FORMAT);
        String date = simpleDateFormat.format(new Date());
        return date;
    }
    private Survey getSurvey(Long id, String titulo, String descripcion, Integer etapa, Long catEncuestaTipoId, String json){
        Survey survey = new Survey();
        survey.setSurveyId(id);
        survey.setTitle(titulo);
        survey.setDescription(descripcion);
        survey.setPhase(etapa);
        survey.setSurveyType(catEncuestaTipoId);
        // encuesta.setJson(json);
        return survey;
    }
    public static SurveyRecord getSurveyRecord(Survey survey, Integer catEncuestaEstatusId, String fechaInicial, String fechaFinal){
        SurveyRecord surveyRecord = new SurveyRecord();
        surveyRecord.setSurveyId(survey.getSurveyId());
        surveyRecord.setSurveyStatus(catEncuestaEstatusId);
        surveyRecord.setStartDate(fechaInicial);
        surveyRecord.setEndDate(fechaFinal);
        return surveyRecord;
    }
    public static SurveyAnswer getSurveyAnswer(Questionnaire questionnaire,
                                                    Question question, String respuesta, Long encuestaRegistroId){
        SurveyAnswer surveyAnswer = new SurveyAnswer();
        surveyAnswer.setSurveyRecordId(encuestaRegistroId);
        surveyAnswer.setQuestionnaireId(questionnaire.getQuestionnaireId());
        surveyAnswer.setQuestionId(question.getQuestionId());
        surveyAnswer.setType(question.getType());
        surveyAnswer.setAnswer(respuesta);
        return surveyAnswer;
    }

    public static ShowSelect infoShowSelect(Answer answer){
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

    public static void startNewSurvey(Context context, Activity activity, Survey survey){
        Intent intent = new Intent(context, QuestionaryActivity.class);
        intent.putExtra(CustomConstants.SURVEY_KEY, survey);
        activity.startActivityForResult(intent, CustomConstants.QUESTIONNAIRES_REQUEST);
    }

    public static Answer getAnswerSpinnerDefault(Context mContext){
        Answer answer = new Answer();
        answer.setAnswerId(0L);
        answer.setQuestionId(0L);
        answer.setText(mContext.getString(R.string.message_spinner_default));
        return answer;
    }
}
