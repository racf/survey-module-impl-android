package com.cysout.sousystems.surveymodule.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;


import com.cysout.sousystems.surveymodule.fragment.QuestionaryFragment;

public class QuestionaryViewPagerAdapter extends FragmentPagerAdapter {
    private final List<QuestionaryFragment> cuestionarios;
    public QuestionaryViewPagerAdapter(@NonNull FragmentManager fm, int behavior, List<QuestionaryFragment> cuestionarios) {
        super(fm, behavior);
        this.cuestionarios = cuestionarios;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return this.cuestionarios.get(position);
    }

    @Override
    public int getCount() {
        return this.cuestionarios.size();
    }

}
