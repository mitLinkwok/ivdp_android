package com.example.linwok.ivdp.activity.activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.Layout;
import android.view.View;

import com.example.linwok.ivdp.R;

public class add_newBeneficiares extends AppCompatActivity {
    AppCompatEditText Fname,Mname,Lname,age,gender,adress;
    AppCompatButton Saveinformation,AddKycs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_beneficiares);
        Fname=findViewById(R.id.Fname);
        Mname=findViewById(R.id.Mname);
        Lname=findViewById(R.id.Lname);
        age=findViewById(R.id.age);
        gender=findViewById(R.id.gender);
        adress=findViewById(R.id.adress);
        Saveinformation=findViewById(R.id.SveData);
        AddKycs=findViewById(R.id.ADDkyc);

        Saveinformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String firstName =Fname.getText().toString().trim();
               String LastName=Lname.getText().toString().trim();
               String MiddelName=Mname.getText().toString().trim();
               String Age=age.getText().toString().trim();
               String Gender=gender.getText().toString().trim();
               String Address= adress.getText().toString().trim();




            }
        });

        AddKycs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });







    }
}
