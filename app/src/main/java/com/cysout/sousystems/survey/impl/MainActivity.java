package com.cysout.sousystems.survey.impl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cysout.sousystems.survey.impl.adapter.SurveyAdapter;
import com.cysout.sousystems.surveymodule.entity.Survey;
import com.cysout.sousystems.surveymodule.service.SurveyService;
import com.cysout.sousystems.surveymodule.service.impl.SurveyServiceImpl;
import com.cysout.sousystems.surveymodule.utils.CustomConstants;
import com.cysout.sousystems.surveymodule.utils.Utils;

import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private SurveyService surveyService;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SurveyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startUI();
        String  surveys = Utils.jsonArrayTest();//Utils.jsonArrayTest();
        saveSurveys(surveys);
        actionRecyclerView();

    }

    public void startUI(){
        surveyService = new ViewModelProvider(this).get(SurveyServiceImpl.class);
        recyclerView = findViewById(R.id.recyclerViewSurveys);
        layoutManager = new LinearLayoutManager(this);
    }

    private void actionRecyclerView(){
        surveyService.loadAllSurveys().observe(this, new Observer<List<Survey>>() {
            @Override
            public void onChanged(List<Survey> surveys) {
                for(Survey survey1 : surveys){
                    Log.i(CustomConstants.TAG_LOG, "ENCUESTA A MOSTRAR "+survey1.toString());
                }
                adapter = new SurveyAdapter(surveys, R.layout.cards_surveys_layout, new SurveyAdapter.CustomHolder.OnItemClickListener() {
                    @Override
                    public void onItemClick(Survey survey, int position) {

                    }

                    @Override
                    public void btnOnClick(View v, Survey survey, int position) {
                        Utils.startNewSurvey(getApplicationContext(), survey);
                        //Utils.startNewSurvey(getApplicationContext(), activity, encuesta);
                    }
                });
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    public void saveSurveys(String surveys) {
        surveyService.saveSurveys(surveys);
       // Log.i(CustomConstants.TAG_LOG, "Ejecuto --- "+surveyService.saveSurveys(surveys));
        Executors.newSingleThreadExecutor().execute(() -> {
            for (Survey survey : surveyService.loadAllSurveysSync()) {
                Log.i(CustomConstants.TAG_LOG, "DATA Front: " + survey.toString());
            }
        });
    }


}