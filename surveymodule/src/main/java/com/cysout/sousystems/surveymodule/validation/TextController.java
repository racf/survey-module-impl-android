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
 *Developed by cysout.com and sousystems.com.mx
 *Contact info@cysout.com or contacto@sousystems.com.mx
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
