package com.cysout.sousystems.surveymodule.validation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.cysout.sousystems.surveymodule.R;
import com.cysout.sousystems.surveymodule.entity.Question;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
import com.cysout.sousystems.surveymodule.utils.Validation;
import com.cysout.sousystems.surveymodule.validation.text.TextFormState;

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
public class TextController extends AndroidViewModel {
    private MutableLiveData<TextFormState> textFormState = new MutableLiveData<>();
    public TextController(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<TextFormState> getTextFormState() {
        return textFormState;
    }

    public void setTextFormState(MutableLiveData<TextFormState> textFormState) {
        this.textFormState = textFormState;
    }

    public void textDataChanged(String text, Question question) {
        if (!Validation.isTextValid(text)) {
            textFormState.setValue( new TextFormState(R.string.required));
        }else {
            textFormState.setValue(new TextFormState(CustomConstants.TRUE));
        }
    }


}
