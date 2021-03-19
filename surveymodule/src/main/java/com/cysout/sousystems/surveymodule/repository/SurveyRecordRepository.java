package com.cysout.sousystems.surveymodule.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.SurveyRecord;
import com.cysout.sousystems.surveymodule.entity.relation.SurveyRecordAnswers;
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
public interface SurveyRecordRepository {
    Long insert(SurveyRecord item);
    Long[] insertList(List<SurveyRecord> list);
    void update(SurveyRecord item);
    void deleteAll();
    LiveData<List<SurveyRecord>> loadAll();
    List<SurveyRecord> loadAllSync();
    LiveData<List<SurveyRecord>> loadByEncuestaId(Long encuestaId);
    List<SurveyRecord> loadByEncuestaIdSync(Long encuestaId);
    SurveyRecord encuestaRegistro(Long encuestaId);
    SurveyRecord encuestaRegistroByEncuestaIdAndCatEncuestaEstatusId(Long encuestaId, Integer catEncuestaEstatusId);
    LiveData<List<SurveyRecord>> loadByEncuestaIdAndCatEncuestaEstatusId(Long encuestaId, Integer catEncuestaEstatusId);
    List<SurveyRecord> loadByEncuestaIdAndCatEncuestaEstatusIdSync(Long encuestaId, Integer catEncuestaEstatusId);
    void updateEncuestaRegistroByEnctRegtroId(Integer catEncuestaEstatusId, Long encuestaRegistroId);
    void updateEncuestaRegistroByEnctRegtroId(Integer catEncuestaEstatusId, String fechaFinal, Long encuestaRegistroId);
    LiveData<SurveyRecordAnswers> loadRegistroRespByEnctRegtroId(Long encuestaRegistroId);
    SurveyRecordAnswers loadRegistroRespByEnctRegtroIdSync(Long encuestaRegistroId);
    LiveData<List<SurveyRecordAnswers>> loadRegistrosRespByEnctRegtroId(Long encuestaRegistroId);
    List<SurveyRecordAnswers> loadRegistrosRespByEnctRegtroIdSync(Long encuestaRegistroId);
    LiveData<List<SurveyRecordAnswers>> loadRegistrosRespuestasByEstatus(Integer catEncuestaEstatusId);
    List<SurveyRecordAnswers> loadRegistrosRespuestasByEstatusSync(Integer catEncuestaEstatusId);
}
