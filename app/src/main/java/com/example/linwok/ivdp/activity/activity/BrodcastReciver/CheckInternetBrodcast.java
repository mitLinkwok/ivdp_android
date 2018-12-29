package com.example.linwok.ivdp.activity.activity.BrodcastReciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.widget.Toast;

public class CheckInternetBrodcast extends BroadcastReceiver  {
    @Override
    public void onReceive(Context context, Intent intent) {
        int[] type = { ConnectivityManager.TYPE_MOBILE,ConnectivityManager.TYPE_WIFI};
        if( isNetwokAvailable(context,type) == false ){
            return;
        }else {
            Toast.makeText(context, "NO INTERNET CONNECTION", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isNetwokAvailable(Context context, int[] typenetwoks) {
        try {

                ConnectivityManager manager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

                for( int typeNetwok : typenetwoks){
                    NetworkInfo info = manager.getNetworkInfo(typeNetwok);
                    if(info != null && info.getState() == NetworkInfo.State.CONNECTED){
                        return true;
                    }
                }

        }catch (Exception e){
            return false;
        }

        return false;
    }
}
