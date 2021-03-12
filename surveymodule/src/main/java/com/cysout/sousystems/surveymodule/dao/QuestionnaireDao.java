package com.cysout.sousystems.surveymodule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import com.cysout.sousystems.surveymodule.entity.Questionnaire;

@Dao
public interface QuestionnaireDao {
    //onConflict = OnConflictStrategy.REPLACE
    @Insert()
    Long insert(Questionnaire item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertList(List<Questionnaire> list);

    @Update
    void update(Questionnaire item);

    @Query("select * from questionnaire where questionnaireId = :questionnaireId LIMIT 1")
    Questionnaire loadCuestionarioSync(Long questionnaireId);

    @Query("SELECT * FROM questionnaire")
    LiveData<List<Questionnaire>> loadAll();

    @Query("SELECT * FROM questionnaire")
    List<Questionnaire> loadAllSync();

    @Query("SELECT * FROM questionnaire WHERE questionnaireId = :id")
    LiveData<List<Questionnaire>> loadByCuestionarioId(Long id);

    @Query("SELECT * FROM questionnaire WHERE questionnaireId = :id")
    List<Questionnaire> loadByCuestionarioIdSync(Long id);

    @Query("SELECT * FROM questionnaire WHERE surveyId = :id")
    LiveData<List<Questionnaire>> loadByEncuestaId(Long id);

    @Query("SELECT * FROM questionnaire WHERE surveyId = :id")
    List<Questionnaire> loadByEncuestaIdSync(Long id);

    @Query("DELETE FROM questionnaire")
    void deleteAll();
}
