package com.cysout.sousystems.surveymodule.validation.Form;

import androidx.annotation.Nullable;

public class FormResult {
    @Nullable
    private ValidatedInFormView success;
    @Nullable
    private Integer error;

    public FormResult(@Nullable ValidatedInFormView success) {
        this.success = success;
    }

    public FormResult(@Nullable Integer error) {
        this.error = error;
    }

    @Nullable
    public ValidatedInFormView getSuccess() {
        return success;
    }

    public void setSuccess(@Nullable ValidatedInFormView success) {
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
