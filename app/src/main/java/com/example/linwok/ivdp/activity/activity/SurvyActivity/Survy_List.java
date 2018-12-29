package com.example.linwok.ivdp.activity.activity.SurvyActivity;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.linwok.ivdp.R;
import com.example.linwok.ivdp.activity.Adepter.All_survey_Adepter;
import com.example.linwok.ivdp.activity.SessionManeger.SessionManager;
import com.example.linwok.ivdp.activity.model.All_survey_Model;
import com.example.linwok.ivdp.activity.sql.DBManager;

import java.util.ArrayList;
import java.util.List;

public class Survy_List extends AppCompatActivity {
    private AppCompatActivity activity = Survy_List.this;
    Context context;
    private SessionManager session;
    private DBManager manager;
    private RecyclerView recyclerView;
    List<All_survey_Model> modelList;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    All_survey_Adepter RecycleAdepter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survy_list);
        recyclerView=findViewById(R.id.recycleSureyList);



        recyclerViewlayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(recyclerViewlayoutManager);
        recyclerView.setHasFixedSize(true);
        modelList=new ArrayList<>();
        manager=new DBManager(activity);
        manager.open();
        try {
            Cursor Surey_count =manager.getSurveyes();
            if(Surey_count.getCount()>0){
                 if(Surey_count.moveToFirst()){
                     String tital,dicription,Duration;
                     int surveyid,projectID;

                    do{

                        All_survey_Model model=new All_survey_Model();
                        tital=Surey_count.getString(Surey_count.getColumnIndex("title"));
                        model.setSurveyTital(tital);
                        dicription=Surey_count.getString(Surey_count.getColumnIndex("description"));
                        Toast.makeText(activity,dicription,Toast.LENGTH_LONG).show();
                        model.setSurveyDescripction(dicription);
                        Duration=Surey_count.getString(Surey_count.getColumnIndex("duration_months"));
                        model.setSurveyDuration(Duration);
                        surveyid=Surey_count.getInt(Surey_count.getColumnIndex("id"));
                        model.setSurveyID(surveyid);
                        projectID=Surey_count.getInt(Surey_count.getColumnIndex("project_id"));
                        model.setProjectID(projectID);
                        Log.d("@@",tital+" ,"+ dicription +" ," +" ," + Duration );
                        modelList.add(model);




                }while (Surey_count.moveToNext());
                RecycleAdepter = new All_survey_Adepter(modelList, activity);
                recyclerView.setAdapter(RecycleAdepter);


            }
        }}catch (Exception e){Log.d("@@model", String.valueOf(modelList));}
    }
}
