package com.example.linwok.ivdp.activity.Adepter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linwok.ivdp.R;
import com.example.linwok.ivdp.activity.model.Baneficial_kayc_model;
import java.util.List;

public class Beneficial_Kyc_Adepter extends RecyclerView.Adapter<Beneficial_Kyc_Adepter.myholder> {
    private Context context;
    private List<Baneficial_kayc_model> getmodel;

    public Beneficial_Kyc_Adepter( List<Baneficial_kayc_model> getmodel,Context context) {
        this.getmodel = getmodel;
        this.context = context;

    }

    @Override
    public Beneficial_Kyc_Adepter.myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.beneficial_kyc_detail, parent,false);
        return new Beneficial_Kyc_Adepter.myholder(view);
    }

    @Override
    public void onBindViewHolder(Beneficial_Kyc_Adepter.myholder holder, int position) {

        Baneficial_kayc_model model=getmodel.get(position);
        holder.Name.setText(model.getKyc_name());
        if(model.getKyc_file() == null){

        }else {
            holder.status.setImageResource(R.drawable.yes);
        }



    }

    @Override
    public int getItemCount() {
        return getmodel.size();
    }

    class myholder extends RecyclerView.ViewHolder{
    TextView Name;
    ImageView status;

        public myholder(View itemView) {
            super(itemView);

           Name=itemView.findViewById(R.id.Kyc_Name);
           status=itemView.findViewById(R.id.Kyc_status);
        }
    }
}
