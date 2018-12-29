package com.example.linwok.ivdp.activity.activity;

import android.app.FragmentManager;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.linwok.ivdp.R;
import com.example.linwok.ivdp.activity.Adepter.All_Beneficiari_Adepter;
import com.example.linwok.ivdp.activity.SessionManeger.SessionManager;
import com.example.linwok.ivdp.activity.model.All_Beneficiari_Model;
import com.example.linwok.ivdp.activity.sql.DBManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Baneficiaries_details extends AppCompatActivity {
    private AppCompatActivity activity = Baneficiaries_details.this;
    private SessionManager session;
    private DBManager manager;
    private RecyclerView recyclerView;


    List<All_Beneficiari_Model> adepters;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    All_Beneficiari_Adepter detailsRecycleAdepter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baneficiariesdetails);
        manager=new DBManager(activity);
        recyclerView=findViewById(R.id.recycleAllBeneficial);
        manager.open();
        adepters=new ArrayList<>();
        recyclerViewlayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(recyclerViewlayoutManager);
        recyclerView.setHasFixedSize(true);

        Cursor BNF_count =manager.getBeneficiales();
        if(BNF_count.getCount()>0){
            if(BNF_count.moveToFirst()){
                String name,Age,gender,code,id;

                do{
                    All_Beneficiari_Model model= new All_Beneficiari_Model();
                    code=BNF_count.getString(BNF_count.getColumnIndex("code"));
                    model.setCode(code);
                    id = BNF_count.getString(BNF_count.getColumnIndex("id"));
                    model.setId(id);
                    name = BNF_count.getString(BNF_count.getColumnIndex("beneficiary_name"));
                    model.setName(name);
                    Age = BNF_count.getString(BNF_count.getColumnIndex("age"));
                    model.setAge(Age);
                    gender = BNF_count.getString(BNF_count.getColumnIndex("gender"));
                    model.setGender(gender);
                    adepters.add(model);
                }
                while (BNF_count.moveToNext());
                detailsRecycleAdepter = new All_Beneficiari_Adepter(adepters, activity);
                recyclerView.setAdapter(detailsRecycleAdepter);
            }
        }
    }
}
