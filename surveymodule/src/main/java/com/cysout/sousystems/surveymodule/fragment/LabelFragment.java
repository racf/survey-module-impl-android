package com.cysout.sousystems.surveymodule.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cysout.sousystems.surveymodule.R;
import com.cysout.sousystems.surveymodule.entity.Question;
import com.cysout.sousystems.surveymodule.entity.Questionnaire;
import com.cysout.sousystems.surveymodule.entity.Survey;

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
public class LabelFragment extends WidgetFragment {
    private TextView tvLabelTitle;
    private TextView tvLabelSubTitle;
    public LabelFragment() {
        // Required empty public constructor
    }

    public static LabelFragment newInstance(String param1, String param2) {
        LabelFragment fragment = new LabelFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_label, container, false);
        bindView(view);
        return  view;
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);
        tvLabelTitle = view.findViewById(R.id.tvLabelTitle);
        tvLabelSubTitle = view.findViewById(R.id.tvLabelSubTitle);
    }

    @Override
    public boolean load(Questionnaire questionnaire, Question question) {
        String title = question.getTitle() == null ? "" : question.getTitle();
        String subTitle = question.getDescription() == null ? "" : question.getDescription();
        tvLabelTitle.setText(title);
        tvLabelSubTitle.setText(subTitle);
        return false;
    }

    @Override
    public boolean save(Survey survey, Questionnaire questionnaire, Question question, Long surveyRecordId) {
        return false;
    }
}