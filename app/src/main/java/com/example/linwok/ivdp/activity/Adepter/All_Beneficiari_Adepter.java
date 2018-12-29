package com.example.linwok.ivdp.activity.Adepter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linwok.ivdp.R;
import com.example.linwok.ivdp.activity.activity.Beneficial_profile;
import com.example.linwok.ivdp.activity.model.All_Beneficiari_Model;

import java.util.List;

public class All_Beneficiari_Adepter extends RecyclerView.Adapter<All_Beneficiari_Adepter.myholder> {
    private Context context;
    private List<All_Beneficiari_Model> getDataAdapter;
    String id;



    public All_Beneficiari_Adepter(List<All_Beneficiari_Model> getDataModel, Context context){
        this.getDataAdapter = getDataModel;
        this.context = context;
    }

 @Override
    public All_Beneficiari_Adepter.myholder onCreateViewHolder(final ViewGroup viewGroup, final int i) {
        final View view = LayoutInflater.from(context).inflate(R.layout.all_baneficiari, viewGroup,false);
        return new All_Beneficiari_Adepter.myholder(view);
    }

    @Override
    public void onBindViewHolder(final All_Beneficiari_Adepter.myholder myholder, int i) {

        final All_Beneficiari_Model detaisModel=getDataAdapter.get(i);
        myholder.Fname.setText(detaisModel.getName());


        myholder.Age.setText(detaisModel.getAge());
        myholder.Gender.setText(detaisModel.getGender());


        myholder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Baneficial_code= detaisModel.getCode();
                String Baneficial_id= detaisModel.getId();

                Bundle bundle = new Bundle();
                bundle.putString("BANEFICIAL_CODE",Baneficial_code);
                bundle.putString("BNIFICIAL_ID",Baneficial_id);

                Intent intent=new Intent(context,Beneficial_profile.class);
                intent.putExtras(bundle);

                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return getDataAdapter.size() ;
    }
    public class myholder extends RecyclerView.ViewHolder {

        private TextView Fname,Age,Gender,updated;
        private ConstraintLayout layout;
        public myholder(final View itemView) {
            super(itemView);
            Fname=itemView.findViewById(R.id.Fname);
            Age=itemView.findViewById(R.id.Age);
            Gender=itemView.findViewById(R.id.Gender);
            layout=itemView.findViewById(R.id.ConstrantAllBeneficials);


        }


    }




}
