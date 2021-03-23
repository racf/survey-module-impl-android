package com.cysout.sousystems.surveymodule.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class Validation {
    public static boolean isTextValid(String text) {
        return text != null && text.trim().length() > 0;
    }

    //Funciones para validar la INE
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

    //Funciones para validar la CURP
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
}
