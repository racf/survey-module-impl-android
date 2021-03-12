package com.cysout.sousystems.surveymodule.validation.Form;

import androidx.annotation.Nullable;

/**
 *Developed by cysout.com and sousystems.com.mx
 *Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class FormState {
    @Nullable
    private Integer textError;
    private boolean isDataValid;

    public FormState(@Nullable Integer textError) {
        this.textError = textError;
    }

    public FormState(boolean isDataValid) {
        this.isDataValid = isDataValid;
    }

    @Nullable
    public Integer getTextError() {
        return textError;
    }

    public void setTextError(@Nullable Integer textError) {
        this.textError = textError;
    }

    public boolean isDataValid() {
        return isDataValid;
    }

    public void setDataValid(boolean dataValid) {
        isDataValid = dataValid;
    }
}
