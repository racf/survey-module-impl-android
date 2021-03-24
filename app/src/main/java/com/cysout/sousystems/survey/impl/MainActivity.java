package com.cysout.sousystems.survey.impl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cysout.sousystems.survey.impl.adapter.SurveyAdapter;
import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.service.SurveyService;
import com.cysout.sousystems.surveymodule.service.impl.SurveyServiceImpl;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
import com.cysout.sousystems.surveymodule.utils.Utils;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SurveyService surveyService;
    private Activity activity;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SurveyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startUI();
        String surveys = Utils.jsonArrayTest1();
        surveyService.saveSurveys(surveys);
        actionRecyclerView();

    }

    public void startUI(){
        surveyService = new ViewModelProvider(this).get(SurveyServiceImpl.class);
        activity = this;
        recyclerView = findViewById(R.id.recyclerViewSurveys);
        layoutManager = new LinearLayoutManager(this);
    }

    private void actionRecyclerView(){
        surveyService.loadAllSurveys().observe(this, new Observer<List<Survey>>() {
            @Override
            public void onChanged(List<Survey> surveys) {
                adapter = new SurveyAdapter(surveys, R.layout.cards_surveys_layout, new SurveyAdapter.CustomHolder.OnItemClickListener() {
                    @Override
                    public void onItemClick(Survey survey, int position) {

                    }

                    @Override
                    public void btnOnClick(View v, Survey survey, int position) {
                        surveyService.startNewSurvey(getApplicationContext(), activity, survey);
                    }
                });
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }
        });
    }

   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CustomConstants.QUESTIONNAIRES_REQUEST) {
            if (resultCode == RESULT_OK) {
                String surveyResponses = data.getStringExtra(CustomConstants.SURVEY_RESPONSE);
                Log.i(CustomConstants.TAG_LOG.concat(" RESULT "), surveyResponses);
                Toast.makeText(this, getString(R.string.message_finished_survey), Toast.LENGTH_LONG).show();
            }
        }
    }


}