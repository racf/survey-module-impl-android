package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.Question;
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
public interface QuestionRepository {
    Long insert(Question item);
    Long[] insertList(List<Question> list);
    void update(Question item);
    LiveData<List<Question>> loadAll();
    List<Question> loadAllSync();
    Question loadQuestionSync(Long id);
    LiveData<List<Question>> loadByQuestionId(Long id);
    List<Question> loadByQuestionIdSync(Long id);
    LiveData<List<Question>> loadByQuestionnaireId(Long id);
    List<Question> loadByQuestionnaireIdSync(Long id);
    void deleteAll();
}
