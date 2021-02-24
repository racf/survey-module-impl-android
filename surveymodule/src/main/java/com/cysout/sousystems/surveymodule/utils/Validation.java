package com.cysout.sousystems.surveymodule.utils;

public class Validation {
    public static boolean isTextValid(String texto) {
        return texto != null && texto.trim().length() > 0;
    }
}
