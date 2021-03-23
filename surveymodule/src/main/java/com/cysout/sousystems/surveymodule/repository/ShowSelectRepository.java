package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.ShowSelect;
import com.cysout.sousystems.surveymodule.entity.relation.RelationShowSelect;
import com.cysout.sousystems.surveymodule.entity.relation.RelationSelectQuestionnaires;
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
public interface ShowSelectRepository {
    Long insert(ShowSelect item);
    Long[] insertList(List<ShowSelect> list);
    void update(ShowSelect item);
    LiveData<List<ShowSelect>> loadAll();
    List<ShowSelect> loadAllSync();
    LiveData<List<ShowSelect>> loadByAnswerId(Long id);
    List<ShowSelect> loadByAnswerIdSync(Long id);
    void deleteAll();
    RelationShowSelect loadShowSelectByAnswerId(Long id);
    LiveData<List<RelationSelectQuestionnaires>> loadShowQuestionnaires();
    List<RelationSelectQuestionnaires> loadShowQuestionnairesSync();
}
