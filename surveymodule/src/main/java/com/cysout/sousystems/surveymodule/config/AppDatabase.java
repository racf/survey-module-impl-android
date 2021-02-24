package com.cysout.sousystems.surveymodule.config;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cysout.sousystems.surveymodule.dao.CatEncuestaEstatusDao;
import com.cysout.sousystems.surveymodule.dao.CatEncuestaTipoDao;
import com.cysout.sousystems.surveymodule.dao.CuestionarioDao;
import com.cysout.sousystems.surveymodule.dao.EncuestaDao;
import com.cysout.sousystems.surveymodule.dao.EncuestaRegistroDao;
import com.cysout.sousystems.surveymodule.dao.EncuestaRespuestaDao;
import com.cysout.sousystems.surveymodule.dao.MostrarCuestionariosDao;
import com.cysout.sousystems.surveymodule.dao.MostrarPreguntasDao;
import com.cysout.sousystems.surveymodule.dao.MostrarRespuestasDao;
import com.cysout.sousystems.surveymodule.dao.MostrarSiSeleccionaDao;
import com.cysout.sousystems.surveymodule.dao.PreguntaDao;
import com.cysout.sousystems.surveymodule.dao.RespuestaDao;
import com.cysout.sousystems.surveymodule.dao.RespuestaMostrarCuestionariosDao;
import com.cysout.sousystems.surveymodule.entity.CatEncuestaEstatus;
import com.cysout.sousystems.surveymodule.entity.CatEncuestaTipo;
import com.cysout.sousystems.surveymodule.entity.Cuestionario;
import com.cysout.sousystems.surveymodule.entity.Encuesta;
import com.cysout.sousystems.surveymodule.entity.EncuestaRegistro;
import com.cysout.sousystems.surveymodule.entity.EncuestaRespuesta;
import com.cysout.sousystems.surveymodule.entity.MostrarCuestionarios;
import com.cysout.sousystems.surveymodule.entity.MostrarPreguntas;
import com.cysout.sousystems.surveymodule.entity.MostrarRespuestas;
import com.cysout.sousystems.surveymodule.entity.MostrarSiSelecciona;
import com.cysout.sousystems.surveymodule.entity.Pregunta;
import com.cysout.sousystems.surveymodule.entity.Respuesta;
import com.cysout.sousystems.surveymodule.entity.RespuestaMostrarCuestionarios;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;

@Database(entities = {CatEncuestaTipo.class, CatEncuestaEstatus.class, Encuesta.class, Cuestionario.class, Pregunta.class, Respuesta.class,
        MostrarSiSelecciona.class, MostrarCuestionarios.class, MostrarPreguntas.class, MostrarRespuestas.class,
        EncuestaRegistro.class, EncuestaRespuesta.class,
        RespuestaMostrarCuestionarios.class}, version = CustomConstants.DATABASE_VERSION, exportSchema = CustomConstants.FALSE)
public abstract class AppDatabase  extends RoomDatabase {

    //DAOs
    public abstract CatEncuestaTipoDao catEncuestaTipoDao();
    public abstract CatEncuestaEstatusDao catEncuestaEstatusDao();
    public abstract CuestionarioDao cuestionarioDao();
    public abstract EncuestaDao encuestaDao();
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