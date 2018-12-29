package com.example.linwok.ivdp.activity.activity;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.linwok.ivdp.R;
import com.example.linwok.ivdp.activity.SessionManeger.SessionManager;
import com.example.linwok.ivdp.activity.helpers.InputValidation;
import com.example.linwok.ivdp.activity.helpers.InputValidationforChangPassword;
import com.example.linwok.ivdp.activity.sql.DBManager;
import com.example.linwok.ivdp.activity.sql.DatabaseHelper;

import java.util.HashMap;

public class ChangPassword extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = ChangPassword.this;
    private TextInputLayout textInputLayoutCurrentPassword,textInputLayoutnewPassword,textInputLayoutConformPassword;

    private TextInputEditText textInptcurrentPassword,textInptNewPassword,textInptConformPassword;
    private AppCompatButton appCompatButtonSavePwd;
    private DatabaseHelper databaseHelper;
    private InputValidationforChangPassword inputValidation;
    private LinearLayout linearLayout;
    private SessionManager session;
    private  String emailFromIntent,passwordFromIntent;
    private AppCompatTextView profile;
    private DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changpassword);
        appCompatButtonSavePwd=findViewById(R.id.appCompatButtonSavePwd);

        initViews();
        initListeners();
        initObjects();


      /*  databaseHelper.addUser();*/



    }

    private void initObjects() {

        inputValidation = new InputValidationforChangPassword(activity);
    }

    private void initListeners() {

        appCompatButtonSavePwd.setOnClickListener(this);
    }

    private void initViews() {
        textInputLayoutCurrentPassword=findViewById(R.id.textInputLayoutCurrentPassword);
        textInputLayoutnewPassword=findViewById(R.id.textInputLayoutnewPassword);
        textInputLayoutConformPassword=findViewById(R.id.textInputLayoutConformPassword);
        textInptNewPassword=findViewById(R.id.textInptNewPassword);
        textInptConformPassword=findViewById(R.id.textInptConformPassword);
        textInptcurrentPassword=findViewById(R.id.textInptcurrentPassword);
        linearLayout=findViewById(R.id.changpwdActivity);
        profile=findViewById(R.id.texName);
         emailFromIntent = getIntent().getStringExtra("Email");

        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        profile.setText(emailFromIntent);
        dbManager=new DBManager(activity);
        dbManager.open();



        HashMap<String, String> user = session.getUserDetails();
        passwordFromIntent = user.get(SessionManager.KEY_PASSWORD);
        emailFromIntent = user.get(SessionManager.KEY_EMAIL);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonSavePwd:
                verifyFromSQLite();
                break;

    }
}

    private void verifyFromSQLite() {

        if (!inputValidation.isInputEditTextMatches(textInptNewPassword, textInptConformPassword,textInputLayoutConformPassword,"Password is not match")) {
            return;
        }
        if(!inputValidation.isInputEditTextMatchescurrentpassword(passwordFromIntent,textInptcurrentPassword,textInputLayoutCurrentPassword,"Conform current password")){
            return;
        }


        if(dbManager.updateUser(emailFromIntent,textInptNewPassword.getText().toString().trim())){

            Intent intent = new Intent(this, Dashboard.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            try {
                // Perform the operation associated with our pendingIntent
                Toast.makeText(activity,"Chang successfully",Toast.LENGTH_LONG).show();
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }



        }

        else {
            Snackbar.make(linearLayout, getString(R.string.error_update_password), Snackbar.LENGTH_LONG).show();

        }


    }


}
