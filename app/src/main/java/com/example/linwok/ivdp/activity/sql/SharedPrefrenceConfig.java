package com.example.linwok.ivdp.activity.sql;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPrefrenceConfig {

  private  SharedPreferences preferences,imageprefrance;
  private  Context context;
    public SharedPrefrenceConfig(Context context) {
        this.context=context;
        preferences= context.getSharedPreferences("MySharPrefrence",0);
        imageprefrance=context.getSharedPreferences("ImagePrefrance",0);
    }

    public void WriteSharePrefrence(int ServerID,String email,String password, String Token,Boolean loginstats){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("SerVerID",ServerID);
        editor.putString("EmailPRofile",email);
        editor.putString("passPRoFiLe",password);
        editor.putString("TokenOFProcess",Token);
        editor.putBoolean("StatusOFLogin",loginstats);
        editor.commit();
        Log.d("@@Write", String.valueOf(loginstats));

    }

    public void WriteImageSharePrefrence(String kycname, String kycperceontype,String BitImage){
        SharedPreferences.Editor editor = imageprefrance.edit();
        editor.putString("Kyc_Name",kycname);
        editor.putString("Kyc_type",kycperceontype);
        editor.putString("image",BitImage);
        editor.commit();

Log.d("@@","insertPreftences");
    }



  public void ClearSharePrefrence(){
      SharedPreferences.Editor editor = preferences.edit();
      editor.clear();
      editor.commit();

      Log.d("@@Clear", "dataClear");

  }

  public boolean ReadLoginStatus(){
        boolean loginstats = false;

        loginstats= preferences.getBoolean("StatusOFLogin",false);
        return loginstats;

  }





}
