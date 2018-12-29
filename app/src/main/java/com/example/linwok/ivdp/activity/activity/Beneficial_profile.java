package com.example.linwok.ivdp.activity.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linwok.ivdp.R;
import com.example.linwok.ivdp.activity.Fregment.fragment_complit_kyc;
import com.example.linwok.ivdp.activity.Fregment.fregment_beneficial_kyc;
import com.example.linwok.ivdp.activity.sql.DBManager;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class Beneficial_profile extends AppCompatActivity {
    private AppCompatActivity activity = Beneficial_profile.this;
    ImageButton takeProfileImage,editProfile,Save_profile_Data;
    private static final int ACTIVITY_START_CAMERA_APP = 0;
    private String mImageFileLocation = "";
    TextInputLayout textInputLayoutName,textInputLayoutcode,textInputLayoutgender,textInputLayoutBOD,
            textInputLayoutcontact_number,textInputLfamily_head_id,textInputLhousehold_id,textInputage,
            textInputwhatsapp_number;
    TextInputEditText textInputEditTextName,textInputEditTextCode,textInputEditTextGender,
                    textInputEditTextBOD,textInputEditTexContectNumber,textInputEditTextFamily_had,
                    textInputEditTextHouse_hold,textInputEditTextAge,Whatsapp_number;
    ImageView ProfileImage;
    AppCompatButton Kyc_profile_Data;
    DBManager manager;
    String Beneficial_code,Beneficial_Id;
    Cursor cursor = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficial_profile);
        initViews();
        manager=new DBManager(activity);
        manager.open();
        try {
            Bundle bundle = new Bundle();
            bundle = getIntent().getExtras();
            Beneficial_code = bundle.getString("BANEFICIAL_CODE");
            Beneficial_Id=bundle.getString("BNIFICIAL_ID");
            Log.d("in profile BId",Beneficial_Id);
        }catch (Exception e){Log.d("@@","Error get B_code in Beneficial_profilr");
        }
    }

    private void initViews() {
        textInputLayoutName=findViewById(R.id.textInputLayoutName);
        textInputLayoutcode=findViewById(R.id.textInputLayoutcode);
        textInputLayoutgender=findViewById(R.id.textInputLayoutgender);
        textInputLayoutBOD=findViewById(R.id.textInputLayoutBOD);
        textInputLayoutcontact_number=findViewById(R.id.textInputLayoutcontact_number);
        textInputLfamily_head_id=findViewById(R.id.textInputLfamily_head_id);
        textInputLhousehold_id=findViewById(R.id.textInputLhousehold_id);
        textInputage=findViewById(R.id.textInputage);
        textInputwhatsapp_number=findViewById(R.id.textInputwhatsapp_number);
        //------------------------------------------------------------------//
        textInputEditTextName=findViewById(R.id.textInputEditTextName);
        textInputEditTextCode=findViewById(R.id.textInputEditTextCode);
        textInputEditTextGender=findViewById(R.id.textInputEditTextGender);
        textInputEditTextBOD=findViewById(R.id.textInputEditTextBOD);
        textInputEditTexContectNumber=findViewById(R.id.textInputEditTexContectNumber);
        textInputEditTextFamily_had=findViewById(R.id.textInputEditTextFamily_had);
        textInputEditTextHouse_hold=findViewById(R.id.textInputEditTextHouse_hold);
        textInputEditTextAge=findViewById(R.id.textInputEditTextAge);
        Whatsapp_number=findViewById(R.id.Whatsapp_number);
        //----------------------------------------------------\\

        takeProfileImage=findViewById(R.id.takeimage);
        ProfileImage=findViewById(R.id.ProfileImage);
        editProfile=findViewById(R.id.EditProfile);
     /*   RelativeLayout Relative_button=findViewById(R.id.Relative_button);*/
        Kyc_profile_Data=findViewById(R.id.Kyc_profile_Data);
        Save_profile_Data=findViewById(R.id.Save_profile_Data);
        Save_profile_Data.setEnabled(false);






    }


    @Override
    protected void onStart() {
        super.onStart();


        takeProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callCameraApplicationIntent = new Intent();
                callCameraApplicationIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                /* File photoFile = null;*/
                Uri photoURI = null;
                try {
                  photoURI= FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".my.package.name.provider", createImageFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                callCameraApplicationIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(callCameraApplicationIntent, ACTIVITY_START_CAMERA_APP);
            }
        });
        Save_profile_Data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile.setEnabled(false);
                HashMap<String, String> queryValues = new HashMap<String,String>();
                manager.open();
                queryValues.put("beneficiary_name",textInputEditTextName.getText().toString().trim());
                queryValues.put("code",textInputEditTextCode.getText().toString().trim());
                queryValues.put("gender",textInputEditTextGender.getText().toString().trim());
                queryValues.put("date_of_birth",textInputEditTextBOD.getText().toString().trim());
                queryValues.put("contact_number",textInputEditTexContectNumber.getText().toString().trim());
                queryValues.put("family_head_id",textInputEditTextFamily_had.getText().toString().trim());
                queryValues.put("household_id",textInputEditTextHouse_hold.getText().toString().trim());
                queryValues.put("age",textInputEditTextAge.getText().toString().trim());
                queryValues.put("whatsapp_number",Whatsapp_number.getText().toString().trim());
                manager.addBeneficialey_New(queryValues);



            }
        });


        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputEditTextBOD.setEnabled(true);
                textInputEditTexContectNumber.setEnabled(true);
                textInputEditTextHouse_hold.setEnabled(true);
                textInputEditTextAge.setEnabled(true);
                Whatsapp_number.setEnabled(true);
                Save_profile_Data.setEnabled(true);
        }
        });

        try {
            cursor = manager.getBanificialsDetails(Beneficial_code);
            if (cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    do {
                        textInputEditTextName.setText(cursor.getString(cursor.getColumnIndex("beneficiary_name")));
                        textInputEditTextCode.setText(cursor.getString(cursor.getColumnIndex("code")));
                        textInputEditTextGender.setText(cursor.getString(cursor.getColumnIndex("gender")));
                        textInputEditTextBOD.setText(cursor.getString(cursor.getColumnIndex("date_of_birth")));
                        textInputEditTexContectNumber.setText(cursor.getString(cursor.getColumnIndex("contact_number")));
                        textInputEditTextFamily_had.setText(cursor.getString(cursor.getColumnIndex("family_head_id")));
                        textInputEditTextHouse_hold.setText(cursor.getString(cursor.getColumnIndex("household_id")));
                        textInputEditTextAge.setText(cursor.getString(cursor.getColumnIndex("age")));
                        Whatsapp_number.setText(cursor.getString(cursor.getColumnIndex("whatsapp_number")));
                    } while (cursor.moveToNext());
                }
            }
        }catch (Exception e){Log.d("@@","Error in cursor in Beneficial_profilr ");}


        Kyc_profile_Data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity,Activity_complete_kyc.class);
                Bundle bundle = new Bundle();
                bundle.putString("BANEFICIAL_CODE",Beneficial_code);
                bundle.putString("BANEFICIAL_ID",Beneficial_Id);
                intent.putExtras(bundle);
                activity.startActivity(intent);

            }
        });


    }


    private File createImageFile() throws IOException {
        String timeStamp = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        }
        String imageFileName = "IMAGE_" + timeStamp + "_";
        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName,".jpg", storageDirectory);
        mImageFileLocation = image.getAbsolutePath();
        return image;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_OK) {
            Toast.makeText(this, "Picture taken successfully", Toast.LENGTH_SHORT).show();

            setReducedImageSize();

        }
    }
    void setReducedImageSize() {
        int targetImageViewWidth = ProfileImage.getWidth();
        int targetImageViewHeight = ProfileImage.getHeight();
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mImageFileLocation, bmOptions);
        int cameraImageWidth = bmOptions.outWidth;
        int cameraImageHeight = bmOptions.outHeight;
        int scaleFactor = Math.min(cameraImageWidth/targetImageViewWidth, cameraImageHeight/targetImageViewHeight);
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inJustDecodeBounds = false;
        Bitmap photoReducedSizeBitmp = BitmapFactory.decodeFile(mImageFileLocation, bmOptions);
        ProfileImage.setImageBitmap(photoReducedSizeBitmp);


    }

}



