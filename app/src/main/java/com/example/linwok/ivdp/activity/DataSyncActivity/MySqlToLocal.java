package com.example.linwok.ivdp.activity.DataSyncActivity;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.linwok.ivdp.activity.model.Token_model;
import com.example.linwok.ivdp.activity.sql.DBManager;
import com.example.linwok.ivdp.activity.sql.SharedPrefrenceConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class MySqlToLocal {
    private Context context;
    private Token_model token_model;
    private SharedPreferences sharedpreferences;
    private int serverid;
    private DBManager manager;
    private String Email, Token;
    private SharedPrefrenceConfig config;
    private HashMap<String, String> queryValues;
    private  ProgressDialog progressDialog;
    private static String  url = "http://172.16.20.247:8000/api/v1/beneficiaries/getusersbeneficiary";



    public MySqlToLocal(Context context) {
        this.context = context;
        sharedpreferences = context.getSharedPreferences("MySharPrefrence", 0);
        config = new SharedPrefrenceConfig(context);
        manager=new DBManager(context);
    }

    public void getUserData() {
        if (config.ReadLoginStatus()) {
            serverid = sharedpreferences.getInt("SerVerID", 0);
            Email = sharedpreferences.getString("EmailPRofile", "");
            Token = sharedpreferences.getString("TokenOFProcess", "");
        }
    }

    public void getKycsData() {
        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute();

    }


    @SuppressLint("StaticFieldLeak")
    private class AsyncTaskRunner extends AsyncTask {


        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progressDialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(context,
                    "ProgressDialog",
                    "Wait for collecting data ");
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            getKycDetails();

            return objects;
        }
    }

    private void getKycDetails() {

        manager = new DBManager(context);
        manager.open();
        final StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {

                    String message = "Sucess Fully";
                    queryValues = new HashMap<>();
                    jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("kyc");
                        for (int i = 0; i <= jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        queryValues.put("id", object.getString("id"));
                        queryValues.put("kyc_person_id", object.getString("kyc_person_id"));
                        queryValues.put("user_id", object.getString("user_id"));
                        queryValues.put("kyc_person_type", object.getString("kyc_person_type"));
                        queryValues.put("kyc_name", object.getString("kyc_name"));
                        queryValues.put("kyc_detail", object.getString("kyc_detail"));
                        queryValues.put("kyc_number", object.getString("kyc_number"));
                        queryValues.put("created_at", object.getString("created_at"));
                        queryValues.put("updated_at", object.getString("updated_at"));
                        queryValues.put("kyc_file", object.getString("kyc_file"));
                        manager.addkyc(queryValues);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("@@ ", "" + error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                String FinalToken = "Bearer " + Token;
                params.put("Authorization", FinalToken);
                return params;
            }
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", String.valueOf(serverid));
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }


    public void getBeneficialy(){

       manager = new DBManager(context);
        manager.open();
        queryValues = new HashMap<>();
        progressDialog = ProgressDialog.show(context,
                "ProgressDialog",
                "Wait for collecting data ");
        final StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("beneficiary");

                    for (int i=0;i<=array.length();i++){

                        JSONObject object=array.getJSONObject(i);
                        queryValues.put("id",object.getString("id"));
                        queryValues.put("code",object.getString("code"));
                        queryValues.put("beneficiary_name",object.getString("beneficiary_name"));
                        queryValues.put("gender",object.getString("gender"));
                        queryValues.put("date_of_birth",object.getString("date_of_birth"));
                        queryValues.put("contact_number",object.getString("contact_number"));
                        queryValues.put("created_at",object.getString("created_at"));
                        queryValues.put("updated_at",object.getString("updated_at"));
                        queryValues.put("family_head_id",object.getString("family_head_id"));

                        queryValues.put("firstname",object.getString("firstname"));
                        queryValues.put("lastname",object.getString("lastname"));
                        queryValues.put("household_id",object.getString("household_id"));
                        queryValues.put("village_id",object.getString("village_id"));
                        queryValues.put("user_id",object.getString("user_id"));
                        queryValues.put("age",object.getString("age"));
                        queryValues.put("family_head_relation",object.getString("family_head_relation"));
                        queryValues.put("whatsapp_number",object.getString("whatsapp_number"));
                        manager.addBeneficialey(queryValues);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                progressDialog.dismiss();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("@@ ", "" + error);
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                String FinalToken = "Bearer " + Token;
                params.put("Authorization", FinalToken);
                return params;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", String.valueOf(serverid));
                return params;
            }
        };

            RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(request);


    }




}




