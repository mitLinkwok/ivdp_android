package com.example.linwok.ivdp.activity.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.linwok.ivdp.R;
import com.example.linwok.ivdp.activity.SessionManeger.SessionManager;
import com.example.linwok.ivdp.activity.sql.DBManager;


import java.util.HashMap;



public class activity_beneficiaries extends AppCompatActivity {
    private AppCompatActivity activity = activity_beneficiaries.this;
    private SessionManager session;
    private DBManager manager;
    private TextView idpr,mail;
    private int Users_id;
    private CardView detailes;
    private Button ShowAll,AddNewBenificiri;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiaries);
        manager=new DBManager(activity);
        idpr=findViewById(R.id.numberofbene);
        AddNewBenificiri=findViewById(R.id.AddNewBenificiri);
        manager.open();
        ShowAll=findViewById(R.id.showALL);
        ShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity,Baneficiaries_details.class);
                startActivity(intent);
         }
        });
        AddNewBenificiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity,add_newBeneficiares.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Cursor BNF_count =manager.getBeneficiales();
        if(BNF_count.getCount()>0){
            if(BNF_count.moveToFirst()){
                int count =0;
                do{
                    count=BNF_count.getInt(BNF_count.getColumnIndex("id"));

                    String code=BNF_count.getString(BNF_count.getColumnIndex("code"));


                }while (BNF_count.moveToNext());
               idpr.setText(String.valueOf(count));
            }
        }
    }
}
