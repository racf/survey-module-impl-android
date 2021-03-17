package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.ShowQuestionnaires;
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
public interface ShowQuestionnairesRepository {
    Long insert(ShowQuestionnaires item);
    Long[] insertList(List<ShowQuestionnaires> list);
    void update(ShowQuestionnaires item);
    LiveData<List<ShowQuestionnaires>> loadAll();
    List<ShowQuestionnaires> loadAllSync();
    LiveData<List<ShowQuestionnaires>> loadByMostrarSiSeleccionaId(Long id);
    List<ShowQuestionnaires> loadByMostrarSiSeleccionaIdSync(Long id);
    void deleteAll();
}
