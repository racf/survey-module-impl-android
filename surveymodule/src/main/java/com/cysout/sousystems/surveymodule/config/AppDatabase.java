package com.cysout.sousystems.surveymodule.config;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cysout.sousystems.surveymodule.dao.AnswerShowQuestionnairesDao;
import com.cysout.sousystems.surveymodule.dao.QuestionDao;
import com.cysout.sousystems.surveymodule.dao.QuestionnaireDao;
import com.cysout.sousystems.surveymodule.dao.ShowAnswersDao;
import com.cysout.sousystems.surveymodule.dao.ShowQuestionnairesDao;
import com.cysout.sousystems.surveymodule.dao.ShowSelectDao;
import com.cysout.sousystems.surveymodule.dao.SurveyDao;
import com.cysout.sousystems.surveymodule.dao.SurveyRecordDao;
import com.cysout.sousystems.surveymodule.dao.SurveyAnswerDao;
import com.cysout.sousystems.surveymodule.dao.ShowQuestionsDao;
import com.cysout.sousystems.surveymodule.dao.AnswerDao;
import com.cysout.sousystems.surveymodule.entity.Questionnaire;
import com.cysout.sousystems.surveymodule.entity.AnswerShowQuestionnaires;
import com.cysout.sousystems.surveymodule.entity.ShowQuestionnaires;
import com.cysout.sousystems.surveymodule.entity.ShowQuestions;
import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.entity.SurveyAnswer;
import com.cysout.sousystems.surveymodule.entity.ShowAnswers;
import com.cysout.sousystems.surveymodule.entity.ShowSelect;
import com.cysout.sousystems.surveymodule.entity.Question;
import com.cysout.sousystems.surveymodule.entity.Answer;
import com.cysout.sousystems.surveymodule.entity.SurveyRecord;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
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
@Database(entities = {Survey.class, Questionnaire.class, Question.class, Answer.class,
        ShowSelect.class, ShowQuestionnaires.class, ShowQuestions.class, ShowAnswers.class,
        SurveyRecord.class, SurveyAnswer.class,
        AnswerShowQuestionnaires.class}, version = CustomConstants.DATABASE_VERSION, exportSchema = CustomConstants.FALSE)
public abstract class AppDatabase  extends RoomDatabase {

    //DAOs
    public abstract QuestionnaireDao questionnaireDao();
    public abstract SurveyDao surveyDao();
    public abstract QuestionDao questionDao();
    public abstract AnswerDao answerDao();
    public abstract ShowSelectDao showSelectDao();
    public abstract ShowQuestionnairesDao showQuestionnairesDao();
    public abstract ShowQuestionsDao showQuestionsDao();
    public abstract ShowAnswersDao showAnswersDao();
    public abstract SurveyRecordDao surveyRecordDao();
    public abstract SurveyAnswerDao surveyAnswerDao();
    public abstract AnswerShowQuestionnairesDao answerShowQuestionnairesDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 10;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDataBase(final Context context){
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, CustomConstants.DATABASE_NAME)//.createFromAsset("database/sider.db")
                            .enableMultiInstanceInvalidation() //Si tu app se ejecuta en múltiples procesos, cuando tienes una instancia de AppDatabase en cada proceso, puedes invalidar el archivo de base de datos compartida en un proceso y esta invalidación se propaga automáticamente a las instancias de AppDatabase dentro de los otros procesos.
                            .fallbackToDestructiveMigration()
                            .build();
                    /*databaseWriteExecutor.execute(() -> {
                            //Insertamos catálogos - cuando los catalogos tiene poca información - caso contrario implementar .createFromAsset("database/sider.db")
                            INSTANCE.catEncuestaTipoDao().insertList(Utils.listCatEncuestaTipo());
                            INSTANCE.catEncuestaEstatusDao().insertList(Utils.listCatEncuestaEstatus());

                    });*/

                }
            }
        }
        return INSTANCE;
    }
}