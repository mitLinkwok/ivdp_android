package com.example.linwok.ivdp.activity.activity;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.linwok.ivdp.R;
import com.example.linwok.ivdp.activity.DataSyncActivity.LocalToMysql;
import com.example.linwok.ivdp.activity.DataSyncActivity.MySqlToLocal;
import com.example.linwok.ivdp.activity.SessionManeger.SessionManager;
import com.example.linwok.ivdp.activity.activity.BrodcastReciver.CheckInternetBrodcast;
import com.example.linwok.ivdp.activity.activity.SurvyActivity.Survy_List;
import com.example.linwok.ivdp.activity.model.Token_model;
import com.example.linwok.ivdp.activity.sql.DBManager;
import com.example.linwok.ivdp.activity.sql.SharedPrefrenceConfig;
import org.json.JSONException;

import java.util.Arrays;
import java.util.HashMap;
public class Dashboard extends AppCompatActivity {
    private AppCompatActivity activity = Dashboard.this;
    private AppCompatTextView textViewName;
    private SessionManager session;
    private String emailFromIntent;
    private DBManager manager;
    private EditText first_name, last_name, inEmail;
    private BroadcastReceiver receiver;
    private IntentFilter filter;
    private ImageButton sync;
    LinearLayout profile;
    AppCompatButton changPassword,benificials,activity1,logout;
    private static final int permission_all=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initViews();
        initObjects();
        String[] permission={ Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if(!haspermission(activity,permission)){
            ActivityCompat.requestPermissions(this,permission,permission_all);
        }
    }
    public static  boolean haspermission(Context context, String... permission){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && context!=null && permission!=null){
        for (String permissions:permission){
        if (ActivityCompat.checkSelfPermission(context,permissions)!= PackageManager.PERMISSION_GRANTED){
            return false;
          }
        }
        }
        return  true;
    }

 private void initObjects() {

        textViewName.setText(emailFromIntent);
    }


    private void initViews() {
        textViewName = findViewById(R.id.textViewName);
        changPassword = findViewById(R.id.changpwd);
        benificials = findViewById(R.id.Btn_benificials);
         logout = findViewById(R.id.Logout);
         profile = findViewById(R.id.profile);
         activity1 = findViewById(R.id.Btn_Activity);
        sync = findViewById(R.id.syncData);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        manager = new DBManager(activity);
        HashMap<String, String> user = session.getUserDetails();
        String passwordFromIntent = user.get(SessionManager.KEY_PASSWORD);
        emailFromIntent = user.get(SessionManager.KEY_EMAIL);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
                ClearSharePrefrence();
                unregisterReceiver(receiver);
                Intent intent=new Intent(activity,MainActivity.class);
                startActivity(intent);
                finish();
             }
        });
     }

    private void ClearSharePrefrence() {
        SharedPrefrenceConfig config = new SharedPrefrenceConfig(activity);
        config.ClearSharePrefrence();
    }

    private void LoadFregMent() {
        Intent intent = new Intent(activity, ChangPassword.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            CheckInternetActivity();
            manager.open();
            Cursor cursor = manager.getIDOfServeyar(emailFromIntent);
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        String serveyarID = cursor.getString(cursor.getColumnIndex("id"));

                    } while (cursor.moveToNext());
                }

            }
        } catch (Exception e) {
            Log.d("Error :-", String.valueOf(e));
        }
        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSynck();
            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenProfile();
            }
        });
        changPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadFregMent();
            }
        });
        benificials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, activity_beneficiaries.class);
                startActivity(intent);
            }
        });


        activity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Survy_List.class);
                startActivity(intent);
            }
        });


    }

    private void OpenProfile() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final View view = getLayoutInflater().inflate(R.layout.fragment_dialog, null);
        first_name = view.findViewById(R.id.first_name);
        last_name = view.findViewById(R.id.last_name);
        inEmail = view.findViewById(R.id.inEmail);
        Button save = view.findViewById(R.id.btnDone);
        manager.open();
        session.getUserDetails();
        Cursor res = manager.getProfileData(emailFromIntent);
        int id = res.getInt(0);
        String Fname = res.getString(1);
        String Lname = res.getString(2);
        String email = res.getString(3);
        inEmail.setText(email);
        first_name.setText(Fname);
        last_name.setText(Lname);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Fname = first_name.getText().toString().trim();
                String Lname = last_name.getText().toString().trim();
                String emails = inEmail.getText().toString().trim();
                if (manager.updateUserProfile(Fname, Lname, emails)) {
                    Toast.makeText(activity, "Sucessfully Update Profile", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(activity, "Error in Update Profile", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void DataSynck() {
        final MySqlToLocal mySqlToLocal = new MySqlToLocal(activity);
        final LocalToMysql localToMysql=new LocalToMysql(activity);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final View view = getLayoutInflater().inflate(R.layout.alertdilog_sync, null);
        ImageButton kyc_upload,kyc_Download,Benificial_upload,Benificial_Download;
        kyc_upload=view.findViewById(R.id.kyc_dilog_Upload);
        kyc_Download=view.findViewById(R.id.kyc_dilog_Downlod);
        Benificial_upload=view.findViewById(R.id.beneficiery_dilog_Upload);
        Benificial_Download=view.findViewById(R.id.beneficiery_dilog_Download);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();

        kyc_Download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySqlToLocal.getUserData();
               mySqlToLocal.getKycsData();
            }
        });
        kyc_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                  /*haredPreferences pref = getApplicationContext().getSharedPreferences("ImagePrefrance", 0);
                    String image;

                    image=null;
                   image= pref.getString("image",null);

                    System.out.print("@@: " + image);
                    Log.d("@@",image);*/


                  localToMysql.getUserData();
                  localToMysql.UplaodkycsData();
                } catch (Exception e) {
                   Log.d("@@","Exception");
                }
            }
        });
        Benificial_Download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySqlToLocal.getUserData();
                mySqlToLocal.getBeneficialy();
            }
        });
        Benificial_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.open();
               Cursor cursor=manager.getBeneficiales();

                if(cursor.getCount()>0){
                    if (cursor.moveToFirst()){
                        do {
                            String name=cursor.getString(cursor.getColumnIndex("beneficiary_name"));

                        }while (cursor.moveToNext());
                    }
                }


            }
        });



    }

    private void CheckInternetActivity() {
        filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int[] type = {ConnectivityManager.TYPE_WIFI, ConnectivityManager.TYPE_MOBILE};
                if (CheckInternetBrodcast.isNetwokAvailable(activity, type)) {
                    Token_model model = new Token_model();
                    String token = model.getToken();
                    Toast.makeText(activity, token, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Toast.makeText(activity, "NO internet Acess", Toast.LENGTH_LONG).show();
                }
            }
        };
        registerReceiver(receiver, filter);
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null) {
            registerReceiver(receiver, filter);

        }
        unregisterReceiver(receiver);
        receiver = null;
    }


}
