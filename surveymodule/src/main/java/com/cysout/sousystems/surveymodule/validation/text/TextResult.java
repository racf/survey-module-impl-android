package com.cysout.sousystems.surveymodule.validation.text;

import androidx.annotation.Nullable;

/**
 *Developed by cysout.com and sousystems.com.mx
 *Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class TextResult {
    @Nullable
    private TextInEditView success;
    @Nullable
    private Integer error;

    public TextResult(@Nullable TextInEditView success) {
        this.success = success;
    }

    public TextResult(@Nullable Integer error) {
        this.error = error;
    }

    @Nullable
    public TextInEditView getSuccess() {
        return success;
    }

    public void setSuccess(@Nullable TextInEditView success) {
        this.success = success;
    }

    @Nullable
    public Integer getError() {
        return error;
    }

    public void setError(@Nullable Integer error) {
        this.error = error;
    }
}
