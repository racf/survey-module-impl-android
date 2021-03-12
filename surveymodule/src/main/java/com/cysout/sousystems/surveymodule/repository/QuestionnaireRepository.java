package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Questionnaire;
/**
 * Developed by cysout.com and sousystems.com.mx
 * Contact info@cysout.com or contacto@sousystems.com.mx
**/
public interface QuestionnaireRepository {
    Long insert(Questionnaire questionnaire);
    Long[] insertList(List<Questionnaire> list);
    void update(Questionnaire questionnaire);
    void delete(Questionnaire questionnaire);
    void deleteAllRows();
    LiveData<List<Questionnaire>> findAllLiveData();
    List<Questionnaire> findAllList();
}
