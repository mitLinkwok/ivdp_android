package com.example.linwok.ivdp.activity.DataSyncActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.linwok.ivdp.activity.model.Token_model;
import com.example.linwok.ivdp.activity.sql.DBManager;
import com.example.linwok.ivdp.activity.sql.SharedPrefrenceConfig;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LocalToMysql  {
  String i="";


  private Context context;
  private Token_model token_model;
  private SharedPreferences sharedpreferences;
  private int serverid;
  DBManager manager;
  String Email, Token;
  SharedPrefrenceConfig config;
  HashMap<String, String> queryValues;
  ProgressDialog progressDialog;
  String url = "http://172.16.20.247:8000/api/v1/beneficiaries/displaydata";

  public LocalToMysql(Context context) {
    this.context = context;
    sharedpreferences = context.getSharedPreferences("MySharPrefrence", 0);
    config = new SharedPrefrenceConfig(context);
    manager=new DBManager(context);
  }
  public void getUserData() {
    try {
    if (config.ReadLoginStatus()) {
      serverid = sharedpreferences.getInt("SerVerID", 0);
      Email = sharedpreferences.getString("EmailPRofile", "");
      Token = sharedpreferences.getString("TokenOFProcess", "");
    }
    }
    catch (Exception e){
      Log.d("@@","Error in getUserData from sharePrefrence");

    }
  }

  public void UplaodkycsData() throws JSONException {
    JSONObject jo;
    final String requestBody=null;
    JSONObject object = null;
    JSONArray jsonArray = new JSONArray();


    Map<String, String> postParam= new HashMap<String, String>();

    progressDialog= ProgressDialog.show(context,"ProgressDialog","Wait for Process!!!");
    progressDialog.setCancelable(true);
    manager.open();
   try {
      final Cursor cursor=manager.getKYCDetails();
      if(cursor.getCount()>0){
        if (cursor.moveToFirst()){
          do {

            postParam.put("kyc_person_id",cursor.getString(cursor.getColumnIndex("kyc_person_id")));
            postParam.put("kyc_person_type",cursor.getString(cursor.getColumnIndex("kyc_person_type")));
            postParam.put("kyc_name",cursor.getString(cursor.getColumnIndex("kyc_name")));
            postParam.put("kyc_detail",cursor.getString(cursor.getColumnIndex("kyc_detail")));
            postParam.put("kyc_number",cursor.getString(cursor.getColumnIndex("kyc_number")));
            String imagepath=(cursor.getString(cursor.getColumnIndex("kyc_file")));
            postParam.put("kyc_file",  getFile(imagepath));
            object=new JSONObject(postParam);

          /* jsonArray.put(object);*/
            sendData(object);
          }while (cursor.moveToNext());
        }
      }

      jo = new JSONObject();
      jo.put("Kyc",jsonArray);



    } catch (JSONException e) {
      e.printStackTrace();
    }


}

    private String getFile(String imagepath) {
      try {
          if (imagepath !=null){
              String uploadId = UUID.randomUUID().toString();
              String fff=null;
              new MultipartUploadRequest(context,uploadId,imagepath)
                  .addFileToUpload(imagepath,"jpeg")
                  .addParameter("kyc_file",fff)
                      .setNotificationConfig(new UploadNotificationConfig())
                      .setMaxRetries(2)
                      .startUpload(); //Starting the upload
              return String.valueOf(true);

          }



      }catch (Exception e){
          Log.d("@@exepction in file", String.valueOf(e));
      }
    return String.valueOf(false);
    }

    private void sendData(final JSONObject ddd )
  {

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, url,null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
        Log.d("@@respons", String.valueOf(response));
        }
        }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
          Log.d("@@error", String.valueOf(error));

    }
      }) {

      @Override
      public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        String FinalToken = "Bearer " + Token;
        headers.put("Authorization", FinalToken);
        return headers;
  }
        @Override
          public byte[] getBody() {
            return ddd.toString().getBytes();
          }
        };
    queue.add(request);

  }


}




