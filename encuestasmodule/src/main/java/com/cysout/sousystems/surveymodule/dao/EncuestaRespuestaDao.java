package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.cysout.sousystems.surveymodule.entity.EncuestaRespuesta;

@Dao
public interface EncuestaRespuestaDao {
    @Insert()
    Long insert(EncuestaRespuesta item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<EncuestaRespuesta> list);

    @Update
    void update(EncuestaRespuesta item);

    @Query("DELETE FROM encuestaRespuesta")
    void deleteAll();

    @Query("SELECT * FROM encuestaRespuesta")
    LiveData<List<EncuestaRespuesta>> loadAll();

    @Query("SELECT * FROM encuestaRespuesta")
    List<EncuestaRespuesta> loadAllSync();

    @Query("SELECT * FROM encuestaRespuesta WHERE encuestaRegistroId =:encuestaRegistroId AND preguntaId=:preguntaId LIMIT 1")
    LiveData<EncuestaRespuesta> encuestaRespuestaByRegistroIdAndPregId(Long encuestaRegistroId, Long preguntaId);

    @Query("SELECT * FROM encuestaRespuesta WHERE encuestaRegistroId =:encuestaRegistroId AND preguntaId=:preguntaId LIMIT 1")
    EncuestaRespuesta encuestaRespuestaByRegistroIdAndPregIdSync(Long encuestaRegistroId, Long preguntaId);

    @Query("SELECT * FROM encuestaRespuesta WHERE encuestaRegistroId =:encuestaRegistroId AND preguntaId=:preguntaId AND respuesta=:respuestaId LIMIT 1")
    LiveData<EncuestaRespuesta> encuestaRespuestaByRegtroIdAndPregIdAndRespId(Long encuestaRegistroId, Long preguntaId, String respuestaId);

    @Query("SELECT * FROM encuestaRespuesta WHERE encuestaRegistroId =:encuestaRegistroId AND preguntaId=:preguntaId AND respuesta=:respuestaId LIMIT 1")
    EncuestaRespuesta encuestaRespuestaByRegtroIdAndPregIdAndRespIdSync(Long encuestaRegistroId, Long preguntaId, String respuestaId);

    @Query("SELECT * FROM encuestaRespuesta WHERE encuestaRegistroId =:encuestaRegistroId")
    List<EncuestaRespuesta> loadByEncuestaRegistroIdSync(Long encuestaRegistroId);

    @Query("SELECT * FROM encuestaRespuesta WHERE encuestaRegistroId =:encuestaRegistroId")
    LiveData<List<EncuestaRespuesta>> loadByEncuestaRegistroId(Long encuestaRegistroId);

    @Query("SELECT * FROM encuestaRespuesta WHERE cuestionarioId=:cuestionarioId")
    LiveData<List<EncuestaRespuesta>> loadByCuestionarioId(Long cuestionarioId);

    @Query("SELECT * FROM encuestaRespuesta WHERE cuestionarioId=:cuestionarioId")
    List<EncuestaRespuesta> loadByCuestionarioIdSync(Long cuestionarioId);

    @Query("SELECT * FROM encuestaRespuesta WHERE encuestaRegistroId =:encuestaRegistroId AND cuestionarioId=:cuestionarioId LIMIT 1")
    LiveData<EncuestaRespuesta> encuestaRespuestaByRegistroIdAndCuestId(Long encuestaRegistroId, Long cuestionarioId);

    @Query("SELECT * FROM encuestaRespuesta WHERE encuestaRegistroId =:encuestaRegistroId AND cuestionarioId=:cuestionarioId LIMIT 1")
    EncuestaRespuesta encuestaRespuestaByRegistroIdAndCuestIdSync(Long encuestaRegistroId, Long cuestionarioId);

    @Query("DELETE FROM encuestaRespuesta WHERE encuestaRegistroId=:encuestaRegistroId AND cuestionarioId=:cuestionarioId")
    void deleteByEnctRegtIdAndCuestId(Long encuestaRegistroId, Long cuestionarioId);

    @Query("DELETE FROM encuestaRespuesta WHERE encuestaRegistroId=:encuestaRegistroId AND preguntaId=:preguntaId")
    void deleteByEnctRegtIdAndPreguntaId(Long encuestaRegistroId, Long preguntaId);

    @Query("DELETE FROM encuestaRespuesta WHERE encuestaRegistroId=:encuestaRegistroId AND preguntaId=:preguntaId AND respuesta=:respuesta")
    void deleteByEnctRegtIdAndPregtIdAndResp(Long encuestaRegistroId, Long preguntaId, String respuesta);
}
