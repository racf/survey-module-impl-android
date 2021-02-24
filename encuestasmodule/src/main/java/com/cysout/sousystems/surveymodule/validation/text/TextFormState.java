package com.cysout.sousystems.surveymodule.validation.text;

import androidx.annotation.Nullable;

public class TextFormState {
    @Nullable
    private Integer textError;
    private boolean isDataValid;

    public TextFormState(@Nullable Integer textError) {
        this.textError = textError;
        this.isDataValid = false;
    }

    public TextFormState(boolean isDataValid) {
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
