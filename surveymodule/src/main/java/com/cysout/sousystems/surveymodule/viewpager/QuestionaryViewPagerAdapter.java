package com.cysout.sousystems.surveymodule.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;
import com.cysout.sousystems.surveymodule.fragment.QuestionaryFragment;

/**
 *Developed by cysout.com and sousystems.com.mx
 *Contact info@cysout.com or contacto@sousystems.com.mx
**/
public class QuestionaryViewPagerAdapter extends FragmentPagerAdapter {
    private final List<QuestionaryFragment> questionnaires;
    public QuestionaryViewPagerAdapter(@NonNull FragmentManager fm, int behavior, List<QuestionaryFragment> questionnaires) {
        super(fm, behavior);
        this.questionnaires = questionnaires;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return this.questionnaires.get(position);
    }

    @Override
    public int getCount() {
        return this.questionnaires.size();
    }

}
