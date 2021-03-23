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

    public void deleteByQuestionId(Long questionId) {
        this.answerShowQuestionnairesRepository.deleteByQuestionId(questionId);
    }

    public void deleteByQuestionnaireOriginId(Long questionnaireOriginId) {
        this.answerShowQuestionnairesRepository.deleteByQuestionnaireOriginId(questionnaireOriginId);
    }

    public void deleteByQuestionIdAndAnswerId(Long questionId, Long answerId) {
        this.answerShowQuestionnairesRepository.deleteByQuestionIdAndAnswerId(questionId, answerId);
    }
}
