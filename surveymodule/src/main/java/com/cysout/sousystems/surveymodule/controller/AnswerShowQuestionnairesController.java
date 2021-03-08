package com.cysout.sousystems.surveymodule.controller;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.AnswerShowQuestionnaires;
import com.cysout.sousystems.surveymodule.repository.AnswerShowQuestionnairesRepository;
import com.cysout.sousystems.surveymodule.repository.impl.AnswerShowQuestionnairesRepositoryImpl;
/**
 * Developed by cysout.com and sousystems.com.mx
 * Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class AnswerShowQuestionnairesController extends AndroidViewModel {
    private AnswerShowQuestionnairesRepository answerShowQuestionnairesRepository;
    public AnswerShowQuestionnairesController(@NonNull Application application) {
        super(application);
        this.answerShowQuestionnairesRepository = new AnswerShowQuestionnairesRepositoryImpl(application);
    }

    public Long insert(AnswerShowQuestionnaires item) {
        return this.answerShowQuestionnairesRepository.insert(item);
    }

    public Long[] insertList(List<AnswerShowQuestionnaires> list) {
        return this.answerShowQuestionnairesRepository.insertList(list);
    }

    public LiveData<List<AnswerShowQuestionnaires>> loadAll() {
        return this.answerShowQuestionnairesRepository.loadAll();
    }


    public List<AnswerShowQuestionnaires> loadAllSync() {
        return this.answerShowQuestionnairesRepository.loadAllSync();
    }

    public void deleteAll() {
        this.answerShowQuestionnairesRepository.deleteAll();
    }

    public void deleteByPreguntaId(Long preguntaId) {
        this.answerShowQuestionnairesRepository.deleteByPreguntaId(preguntaId);
    }

    public void deleteByCuestionarioOrigenId(Long cuestionarioOrigenId) {
        this.answerShowQuestionnairesRepository.deleteByCuestionarioOrigenId(cuestionarioOrigenId);
    }

    public void deleteByPreguntaIdAndRespuestaId(Long preguntaId, Long respuestaId) {
        this.answerShowQuestionnairesRepository.deleteByPreguntaIdAndRespuestaId(preguntaId,respuestaId);
    }
}
