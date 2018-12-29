package com.example.linwok.ivdp.activity.Adepter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linwok.ivdp.R;
import com.example.linwok.ivdp.activity.activity.Beneficial_profile;
import com.example.linwok.ivdp.activity.activity.SurvyActivity.Surey_start;
import com.example.linwok.ivdp.activity.activity.SurvyActivity.Survy_List;
import com.example.linwok.ivdp.activity.model.All_Beneficiari_Model;
import com.example.linwok.ivdp.activity.model.All_survey_Model;
import com.google.zxing.integration.android.IntentIntegrator;

import java.util.List;

public class All_survey_Adepter extends RecyclerView.Adapter<All_survey_Adepter.myholder>{
    private Context context;
    private List<All_survey_Model> getDatamodel;
    String id;

    public All_survey_Adepter(List<All_survey_Model> getDataModel, Context context){
        this.getDatamodel=getDataModel;
        this.context = context;
    }




    @NonNull
    @Override
    public All_survey_Adepter.myholder onCreateViewHolder( final ViewGroup viewGroup,final int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.survy_list_view,viewGroup,false);
        return  new myholder(view);
    }

    @Override
    public void onBindViewHolder(final myholder my, int i) {

        final All_survey_Model model=getDatamodel.get(i);
        my.tital.setText(model.getSurveyTital());
        my.Discription.setText(model.getSurveyDescripction());
        my.Duration.setText(model.getSurveyDuration()+"- Month");

        my.startSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("SURVEYID", String.valueOf(model.getSurveyID()));
                bundle.putString("PROJECTID", String.valueOf(model.getProjectID()));
                Intent intent=new Intent(context,Surey_start.class);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return  getDatamodel.size();
    }


    public   class myholder extends RecyclerView.ViewHolder {

        private AppCompatTextView tital,Discription,Duration;
        private Button startSurvey;
        public myholder(final View itemView) {
            super(itemView);
            tital=itemView.findViewById(R.id.text_survey_tital);
            Discription=itemView.findViewById(R.id.text_discripction);
            Duration=itemView.findViewById(R.id.text_duration);
            startSurvey=itemView.findViewById(R.id.startSurvey);


        }


    }


}
