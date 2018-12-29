package com.example.linwok.ivdp.activity.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.linwok.ivdp.R;
import com.example.linwok.ivdp.activity.Adepter.All_Beneficiari_Adepter;
import com.example.linwok.ivdp.activity.Adepter.Beneficial_Kyc_Adepter;
import com.example.linwok.ivdp.activity.model.Baneficial_kayc_model;
import com.example.linwok.ivdp.activity.sql.DBManager;
import com.example.linwok.ivdp.activity.sql.SharedPrefrenceConfig;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Activity_complete_kyc extends AppCompatActivity {
    private AppCompatActivity activity = Activity_complete_kyc.this;

    AppCompatImageButton Take_Document_image;
    AppCompatButton submit;
    TextInputEditText  textInputEditTextPersonType,textInputEditTextKyc_name,textInputEditTextkyc_details,textInputEditTextKyc_number,textInputEditTexDocumentFileName;
    TextInputLayout textInputLayoutPersontype,textInputLayoutkyc_name,textInputLayoutkyc_details,textInputLayoutkyc_number,textInputLayoukycfileName;
    private ImageView imageView;
    FloatingActionButton AddDoc;
    private RecyclerView recyclerView;
    DBManager manager;
    SharedPreferences pref;
    SharedPrefrenceConfig config;
    String Beneficial_code,Beneficial_id;
    public static final int REQUEST_IMAGE = 100;
    public static final int REQUEST_PERMISSION = 200;
    private static final int ACTIVITY_START_CAMERA_APP = 0;
    private String mImageFileLocation = "";
    Uri photoURI = null;
    String ff;
    Beneficial_Kyc_Adepter adepter;
    List<Baneficial_kayc_model> modelList;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    Bitmap photoReducedSizeBitmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_kyc);
        config =new SharedPrefrenceConfig(activity);
        modelList=new ArrayList<>();
        AddDoc =  findViewById(R.id.AddDoc);
        pref = getApplicationContext().getSharedPreferences("myToKen!@", 0);
        manager=new DBManager(activity);
        try {
            Bundle bundle = new Bundle();
            bundle = getIntent().getExtras();
            Beneficial_code = bundle.getString("BANEFICIAL_CODE");
            Beneficial_id=bundle.getString("BANEFICIAL_ID");
            }catch (Exception e){Log.d("@@","Error get B_code in Beneficial_profilr");
        }
        recyclerView = findViewById(R.id.kyc_list);
        recyclerViewlayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(recyclerViewlayoutManager);
        recyclerView.setHasFixedSize(true);
        LoadKycData();
    }

    private void LoadKycData() {
        manager.open();
        Cursor cursor=manager.ProfileKYCDetails(Beneficial_id);
        if (cursor.getCount()>0){
           if (cursor.moveToFirst()){
            do{
                Baneficial_kayc_model model= new Baneficial_kayc_model();
                model.setKyc_name(cursor.getString(cursor.getColumnIndex("kyc_name")));
                model.setKyc_detail (cursor.getString(cursor.getColumnIndex("kyc_detail")));
                String fileName= (cursor.getString(cursor.getColumnIndex("kyc_file")));
                model.setKyc_file(fileName);


                model.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                model.setKyc_number(cursor.getString(cursor.getColumnIndex("kyc_number")));

                modelList.add(model);
            }while (cursor.moveToNext());
               adepter = new Beneficial_Kyc_Adepter(modelList, activity);
               recyclerView.setAdapter(adepter);
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        AddDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                final View view = getLayoutInflater().inflate(R.layout.add_kyc_alertdilog, null);
                 //-------textlayout//////
                textInputLayoutPersontype=view.findViewById(R.id.textInputLayoutPersontype);
                textInputLayoutkyc_name=view.findViewById(R.id.textInputLayoutkyc_name);
                textInputLayoutkyc_details=view.findViewById(R.id.textInputLayoutkyc_details);
                textInputLayoutkyc_number=view.findViewById(R.id.textInputLayoutkyc_number);
                textInputLayoukycfileName=view.findViewById(R.id.textInputLayoukycfileName);

                //------textEdit------///

                textInputEditTextPersonType=view.findViewById(R.id.textInputEditTextPersonType);
                textInputEditTextKyc_name=view.findViewById(R.id.textInputEditTextKyc_name);
                textInputEditTextkyc_details=view.findViewById(R.id.textInputEditTextkyc_details);
                textInputEditTextKyc_number=view.findViewById(R.id.textInputEditTextKyc_number);
                textInputEditTexDocumentFileName=view.findViewById(R.id.textInputEditTexDocumentFileName);
                //----------------------/////
                submit=view.findViewById(R.id.submit);
                Take_Document_image=view.findViewById(R.id.Capture_Image);
                Take_Document_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getDocumentKycImage();
                    }
                });

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* SharedPrefrenceConfig config =new SharedPrefrenceConfig(activity);
                        config.WriteImageSharePrefrence(textInputEditTextKyc_name.getText().toString().trim(),textInputEditTextPersonType.getText().toString(),ff);*/
                        HashMap<String, String>  queryValues = new HashMap<String,String>();
                        manager.open();

                        Log.d("@@",textInputEditTextPersonType.getText().toString().trim()+"----"+textInputEditTextKyc_name.getText().toString().trim()
                        +"---"+ textInputEditTextKyc_number.getText().toString().trim());
                        queryValues.put("kyc_person_id", Beneficial_id);
                        queryValues.put("kyc_person_type",textInputEditTextPersonType.getText().toString().trim());
                        queryValues.put("kyc_name",textInputEditTextKyc_name.getText().toString().trim() );
                        queryValues.put("kyc_detail",textInputEditTextkyc_details.getText().toString());
                        queryValues.put("kyc_file", mImageFileLocation);
                        queryValues.put("kyc_number",textInputEditTextKyc_number.getText().toString().trim());
                        manager.addNewkyc(queryValues);
                        //////---------------------------------////////////
                }
                });

                builder.setView(view);
                final AlertDialog dialog = builder.create();
                dialog.show();


            }
        });








    }

    private void getDocumentKycImage() {

        Intent callCameraApplicationIntent = new Intent();
        callCameraApplicationIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        /* File photoFile = null;*/

        try {
            photoURI= FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".my.package.name.provider", createImageFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("@@", String.valueOf(photoURI));
        callCameraApplicationIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        startActivityForResult(callCameraApplicationIntent, ACTIVITY_START_CAMERA_APP);
    }

    private File createImageFile() throws IOException{

       try {
           String imageFileName= textInputEditTextKyc_name.getText().toString().trim() + "_"+Beneficial_id+"_";

           File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
           File image = File.createTempFile(imageFileName,".jpg", storageDirectory);
           mImageFileLocation = image.getAbsolutePath();
           return image;
       }catch (Exception e){
           Log.d("@@" ,"error create document image");
       }


        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_OK) {
            Toast.makeText(this, "Picture taken successfully", Toast.LENGTH_SHORT).show();

           inbitmap();

        }
    }

    private void inbitmap() {

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        BitmapFactory.decodeFile(mImageFileLocation, bmOptions);
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        Bitmap photoReducedSizeBitmp = BitmapFactory.decodeFile(mImageFileLocation, bmOptions);
        photoReducedSizeBitmp.compress(Bitmap.CompressFormat.JPEG,50,outputStream);
        byte[] image=outputStream.toByteArray();

         ff=Base64.encodeToString(image,Base64.DEFAULT);


    }


}

