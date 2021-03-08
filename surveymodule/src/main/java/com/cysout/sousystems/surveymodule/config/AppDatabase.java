package com.cysout.sousystems.surveymodule.config;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cysout.sousystems.surveymodule.dao.CuestionarioDao;
import com.cysout.sousystems.surveymodule.dao.RespuestaMostrarCuestionariosDao;
import com.cysout.sousystems.surveymodule.dao.SurveyDao;
import com.cysout.sousystems.surveymodule.dao.EncuestaRegistroDao;
import com.cysout.sousystems.surveymodule.dao.EncuestaRespuestaDao;
import com.cysout.sousystems.surveymodule.dao.MostrarCuestionariosDao;
import com.cysout.sousystems.surveymodule.dao.MostrarPreguntasDao;
import com.cysout.sousystems.surveymodule.dao.MostrarRespuestasDao;
import com.cysout.sousystems.surveymodule.dao.MostrarSiSeleccionaDao;
import com.cysout.sousystems.surveymodule.dao.PreguntaDao;
import com.cysout.sousystems.surveymodule.dao.RespuestaDao;
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
 *Developed by cysout.com and sousystems.com.mx
 *Contact info@cysout.com or contacto@sousystems.com.mx
**/
@Database(entities = {Survey.class, Questionnaire.class, Question.class, Answer.class,
        ShowSelect.class, ShowQuestionnaires.class, ShowQuestions.class, ShowAnswers.class,
        SurveyRecord.class, SurveyAnswer.class,
        AnswerShowQuestionnaires.class}, version = CustomConstants.DATABASE_VERSION, exportSchema = CustomConstants.FALSE)
public abstract class AppDatabase  extends RoomDatabase {

    //DAOs
    public abstract CuestionarioDao cuestionarioDao();
    public abstract SurveyDao surveyDao();
    public abstract PreguntaDao preguntaDao();
    public abstract RespuestaDao respuestaDao();
    public abstract MostrarSiSeleccionaDao mostrarSiSeleccionaDao();
    public abstract MostrarCuestionariosDao mostrarCuestionariosDao();
    public abstract MostrarPreguntasDao mostrarPreguntasDao();
    public abstract MostrarRespuestasDao mostrarRespuestasDao();
    public abstract EncuestaRegistroDao encuestaRegistroDao();
    public abstract EncuestaRespuestaDao encuestaRespuestaDao();
    public abstract RespuestaMostrarCuestionariosDao respuestaMostrarCuestionariosDao();

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