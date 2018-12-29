package com.example.linwok.ivdp.activity.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.linwok.ivdp.R;
import com.example.linwok.ivdp.activity.DataSyncActivity.MySqlToLocal;
import com.example.linwok.ivdp.activity.SessionManeger.SessionManager;
import com.example.linwok.ivdp.activity.activity.BrodcastReciver.CheckInternetBrodcast;
import com.example.linwok.ivdp.activity.helpers.InputValidation;
import com.example.linwok.ivdp.activity.model.Token_model;
import com.example.linwok.ivdp.activity.sql.DBManager;
import com.example.linwok.ivdp.activity.sql.SharedPrefrenceConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = MainActivity.this;
    private NestedScrollView nestedScrollView;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private AppCompatButton appCompatButtonLogin;
    private AppCompatTextView resetpassword,ErrorMessage;
    private InputValidation inputValidation;
    private SessionManager session;
    private DBManager dbManager;
    private BroadcastReceiver receiver;
    private  IntentFilter filter;
    String message,token,information;
    int serverID;
    SharedPreferences pref;
    SharedPrefrenceConfig config;
 boolean loginstats = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appCompatButtonLogin=findViewById(R.id.appCompatButtonLogin);
        initViews();
        pref = getApplicationContext().getSharedPreferences("myToKen!@", 0);
        config =new SharedPrefrenceConfig(activity);
        if(config.ReadLoginStatus()){
            Intent i = new Intent(activity, Dashboard.class);
            startActivity(i);
            finish();
        }
        dbManager=new DBManager(activity);
        dbManager.open();
        information=Build.SERIAL;
        }



    private void initViews() {
        nestedScrollView =findViewById(R.id.nestedScrollView);
        textInputLayoutEmail =findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        textInputEditTextEmail =findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = findViewById(R.id.textInputEditTextPassword);
        appCompatButtonLogin =  findViewById(R.id.appCompatButtonLogin);
        resetpassword=findViewById(R.id.reserpwd);
        session = new SessionManager(getApplicationContext());
    }


    @Override
    protected void onStart()  {
        super.onStart();
        CheckInternetActivity();
        initListeners();
        initObjects();
        textInputEditTextPassword.onEditorAction(EditorInfo.IME_ACTION_DONE);
        textInputEditTextEmail.onEditorAction(EditorInfo.IME_ACTION_DONE);
    }




    private void CheckInternetActivity() {

        filter=new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
         receiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int[] type = {ConnectivityManager.TYPE_WIFI, ConnectivityManager.TYPE_MOBILE};
                if(CheckInternetBrodcast.isNetwokAvailable(activity, type)){
                return  ;
                }
                else {

                     AlertDialog.Builder builder=new AlertDialog.Builder(activity);
                     View view=getLayoutInflater().inflate(R.layout.internet_error_message,null);
                     ErrorMessage=view.findViewById(R.id.ErrorInterNet);
                     builder.setView(view);
                     AlertDialog dialog=builder.create();
                     dialog.show();
                }
            }
        };
        registerReceiver(receiver,filter);

    }
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
        resetpassword.setOnClickListener(this);
    }
    private void initObjects() {
        inputValidation = new InputValidation(activity);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;
            case R.id.reserpwd:
                Resetpwd();
                break;
        }
    }





    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null) {
            registerReceiver(receiver,filter);
        }
        unregisterReceiver(receiver);
        receiver = null;
    }


    private void Resetpwd() {
        /*Toast.makeText(activity,"click on forgotpassword",Toast.LENGTH_LONG).show();*/
    }

    private void verifyFromSQLite() {

        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return ;
            }
     if(textInputEditTextEmail.getText().toString().trim() != "" && textInputEditTextPassword.getText().toString().trim() != "" ){
           verifyFromInterNET();
        }
     else {
             Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();

        }
    }

    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
    private void verifyFromInterNET() {


        String URL="http://172.16.20.247:8000/api/v1/auth/login";
        RequestQueue requestQueue= Volley.newRequestQueue(activity);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    serverID=jsonObject.getInt("id");
                    message =jsonObject.getString("message");
                    token = jsonObject.getString("token");

                    if(message.equals("Login Successfull.")){
                        loginstats = true;
                        SharedPrefrenceConfig config =new SharedPrefrenceConfig(activity);
                        config.WriteSharePrefrence(serverID,textInputEditTextEmail.getText().toString().trim(),textInputEditTextPassword.getText().toString().trim(),token,loginstats);
                        session.createLoginSession(serverID,textInputEditTextEmail.getText().toString().trim(), textInputEditTextPassword.getText().toString().trim(),information);
                        Toast.makeText(activity,message,Toast.LENGTH_LONG).show();
                        Intent i = new Intent(activity, Dashboard.class);
                        startActivity(i);
                        emptyInputEditText();
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
             Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams()  {
                Map<String,String> map = new HashMap<String, String>();
                map.put("email",textInputEditTextEmail.getText().toString().trim());
                map.put("password",textInputEditTextPassword.getText().toString().trim());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }



}
