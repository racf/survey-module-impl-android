package com.cysout.sousystems.surveymodule.utils;

/**
 *Developed by cysout.com and sousystems.com.mx
 *Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class Validation {
    public static boolean isTextValid(String texto) {
        return texto != null && texto.trim().length() > 0;
    }
}
