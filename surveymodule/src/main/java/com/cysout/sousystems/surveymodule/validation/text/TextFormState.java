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
