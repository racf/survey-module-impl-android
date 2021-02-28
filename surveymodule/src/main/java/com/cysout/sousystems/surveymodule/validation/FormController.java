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
 *Developed by cysout.com and sousystems.com.mx
 *Contact info@cysout.com or contacto@sousystems.com.mx
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
            formResult.setValue(new FormResult(R.string.texto_requerido));
        } else {
            formResult.setValue(new FormResult( new ValidatedInFormView("Mensaje prueba")));
        }
    }
}
