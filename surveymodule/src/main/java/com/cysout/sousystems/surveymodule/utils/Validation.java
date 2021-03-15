package com.cysout.sousystems.surveymodule.utils;

/**
 *Developed by cysout.com and sousystems.com.mx
 *Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class Validation {
    public static boolean isTextValid(String text) {
        return text != null && text.trim().length() > 0;
    }
}
