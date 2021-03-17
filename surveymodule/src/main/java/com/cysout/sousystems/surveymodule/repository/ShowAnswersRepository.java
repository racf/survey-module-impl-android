package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.ShowAnswers;
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
public interface ShowAnswersRepository {
    Long insert(ShowAnswers item);
    Long[] insertList(List<ShowAnswers> list);
    void update(ShowAnswers item);
    LiveData<List<ShowAnswers>> loadAll();
    List<ShowAnswers> loadAllSync();
    LiveData<List<ShowAnswers>> loadByMostrarSiSeleccionaId(Long id);
    List<ShowAnswers> loadByMostrarSiSeleccionaIdSync(Long id);
    void deleteAll();
}
