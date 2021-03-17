package com.cysout.sousystems.surveymodule.validation.text;

import androidx.annotation.Nullable;

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
