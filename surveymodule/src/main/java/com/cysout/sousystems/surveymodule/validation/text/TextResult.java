package com.cysout.sousystems.surveymodule.validation.text;

import androidx.annotation.Nullable;

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
