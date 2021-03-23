package com.cysout.sousystems.surveymodule.validation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.cysout.sousystems.surveymodule.R;
import com.cysout.sousystems.surveymodule.utils.Validation;
import com.cysout.sousystems.surveymodule.validation.Form.FormResult;
import com.cysout.sousystems.surveymodule.validation.Form.ValidatedInFormView;

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
public class FormController extends AndroidViewModel {
    private MutableLiveData<FormResult> formResult = new MutableLiveData<>();

    public FormController(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<FormResult> getFormResult() {
        return formResult;
    }

    public void setFormResult(MutableLiveData<FormResult> formResult) {
        this.formResult = formResult;
    }

    public void isValidForm(String texto) {
        if (!Validation.isTextValid(texto)) {
            formResult.setValue(new FormResult(R.string.required));
        } else {
            formResult.setValue(new FormResult( new ValidatedInFormView("Mensaje prueba")));
        }
    }
}
