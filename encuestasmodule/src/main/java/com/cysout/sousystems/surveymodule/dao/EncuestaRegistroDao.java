package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;
import com.cysout.sousystems.surveymodule.entity.EncuestaRegistro;
import com.cysout.sousystems.surveymodule.entity.relation.EncuestaRegistroRespuestas;


@Dao
public interface EncuestaRegistroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(EncuestaRegistro item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<EncuestaRegistro> list);

    @Update
    void update(EncuestaRegistro item);

    @Query("DELETE FROM encuestaRegistro")
    void deleteAll();

    @Query("SELECT * FROM encuestaRegistro")
    LiveData<List<EncuestaRegistro>> loadAll();

    @Query("SELECT * FROM encuestaRegistro")
    List<EncuestaRegistro> loadAllSync();

    @Query("SELECT * FROM encuestaRegistro WHERE encuestaIdFK=:encuestaId")
    LiveData<List<EncuestaRegistro>> loadByEncuestaId(Long encuestaId);

    @Query("SELECT * FROM encuestaRegistro WHERE encuestaIdFK=:encuestaId LIMIT 1")
    List<EncuestaRegistro> loadByEncuestaIdSync(Long encuestaId);

    @Query("SELECT * FROM encuestaRegistro WHERE encuestaIdFK=:encuestaId")
    EncuestaRegistro encuestaRegistro(Long encuestaId);

    @Query("SELECT * FROM encuestaRegistro WHERE encuestaIdFK=:encuestaId AND catEncuestaEstatusId=:catEncuestaEstatusId LIMIT 1")
    EncuestaRegistro encuestaRegistroByEncuestaIdAndCatEncuestaEstatusId(Long encuestaId, Integer catEncuestaEstatusId);

    @Query("SELECT * FROM encuestaRegistro WHERE encuestaIdFK=:encuestaId AND catEncuestaEstatusId=:catEncuestaEstatusId")
    LiveData<List<EncuestaRegistro>> loadByEncuestaIdAndCatEncuestaEstatusId(Long encuestaId, Integer catEncuestaEstatusId);

    @Query("SELECT * FROM encuestaRegistro WHERE encuestaIdFK=:encuestaId AND catEncuestaEstatusId=:catEncuestaEstatusId")
    List<EncuestaRegistro> loadByEncuestaIdAndCatEncuestaEstatusIdSync(Long encuestaId, Integer catEncuestaEstatusId);

    @Query("UPDATE encuestaRegistro SET catEncuestaEstatusId =:catEncuestaEstatusId WHERE encuestaRegistroId=:encuestaRegistroId")
    void updateEncuestaRegistroByEnctRegtroId(Integer catEncuestaEstatusId, Long encuestaRegistroId);

    @Query("UPDATE encuestaRegistro SET catEncuestaEstatusId =:catEncuestaEstatusId, fechaFinal=:fechaFinal WHERE encuestaRegistroId=:encuestaRegistroId")
    void updateEncuestaRegistroByEnctRegtroId(Integer catEncuestaEstatusId, String fechaFinal, Long encuestaRegistroId);

    @Transaction
    @Query("SELECT * FROM encuestaRegistro WHERE encuestaRegistroId=:encuestaRegistroId")
    LiveData<EncuestaRegistroRespuestas> loadRegistroRespByEnctRegtroId(Long encuestaRegistroId);

    @Transaction
    @Query("SELECT * FROM encuestaRegistro WHERE encuestaRegistroId=:encuestaRegistroId")
    EncuestaRegistroRespuestas loadRegistroRespByEnctRegtroIdSync(Long encuestaRegistroId);

    @Transaction
    @Query("SELECT * FROM encuestaRegistro WHERE encuestaRegistroId=:encuestaRegistroId")
    LiveData<List<EncuestaRegistroRespuestas>> loadRegistrosRespByEnctRegtroId(Long encuestaRegistroId);

    @Transaction
    @Query("SELECT * FROM encuestaRegistro WHERE encuestaRegistroId=:encuestaRegistroId")
    List<EncuestaRegistroRespuestas> loadRegistrosRespByEnctRegtroIdSync(Long encuestaRegistroId);

    @Transaction
    @Query("SELECT * FROM encuestaRegistro WHERE catEncuestaEstatusId=:catEncuestaEstatusId")
    LiveData<List<EncuestaRegistroRespuestas>> loadRegistrosRespuestasByEstatus(Integer catEncuestaEstatusId);

    @Transaction
    @Query("SELECT * FROM encuestaRegistro WHERE catEncuestaEstatusId=:catEncuestaEstatusId")
    List<EncuestaRegistroRespuestas> loadRegistrosRespuestasByEstatusSync(Integer catEncuestaEstatusId);

}
