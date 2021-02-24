package com.cysout.sousystems.surveymodule.validation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.cysout.sousystems.surveymodule.R;
import com.cysout.sousystems.surveymodule.entity.Pregunta;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
import com.cysout.sousystems.surveymodule.utils.Validation;
import com.cysout.sousystems.surveymodule.validation.text.TextFormState;

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

    public void textDataChanged(String texto, Pregunta pregunta) {
        if (!Validation.isTextValid(texto)) {
            textFormState.setValue( new TextFormState(R.string.texto_requerido));
        }else {
            textFormState.setValue(new TextFormState(CustomConstants.TRUE));
        }
    }


}
