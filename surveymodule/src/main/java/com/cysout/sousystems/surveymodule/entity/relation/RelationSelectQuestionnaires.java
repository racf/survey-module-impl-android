package com.cysout.sousystems.surveymodule.entity.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cysout.sousystems.surveymodule.entity.ShowQuestionnaires;
import com.cysout.sousystems.surveymodule.entity.ShowSelect;

public class RelationSelectQuestionnaires implements Serializable {

    @Embedded
    ShowSelect showSelect = new ShowSelect();

    @Relation(parentColumn = "showSelectId", entityColumn = "showSelectId", entity = ShowQuestionnaires.class)
    List<ShowQuestionnaires> questionnaires = new ArrayList<>();

    public ShowSelect getShowSelect() {
        return showSelect;
    }

    public void setShowSelect(ShowSelect showSelect) {
        this.showSelect = showSelect;
    }

    public List<ShowQuestionnaires> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(List<ShowQuestionnaires> questionnaires) {
        this.questionnaires = questionnaires;
    }
}
