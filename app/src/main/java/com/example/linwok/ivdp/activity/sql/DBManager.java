package com.example.linwok.ivdp.activity.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.linwok.ivdp.activity.activity.Dashboard;

import java.util.Arrays;
import java.util.HashMap;

public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;
    public DBManager(Context c) {
        context = c;
    }
    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        db=dbHelper.getReadableDatabase();
        return this;
    }

    public void addkyc(HashMap<String, String> queryValues){
        String message="Successfully Insert";
        ContentValues values=new ContentValues();
        values.put(tables.COLUMN_KYC_ID,queryValues.get("id"));
        values.put(tables.USERS_ID,queryValues.get("user_id"));
        values.put(tables.COLUMN_KYC_PERSIONID,queryValues.get("kyc_person_id"));
        values.put(tables.COLUMN_KYC_PERSONTYPE,queryValues.get("kyc_person_type"));
        values.put(tables.COLUMN_KYC_NAME,queryValues.get("kyc_name"));
        values.put(tables.COLUMN_KYC_DETAIL,queryValues.get("kyc_detail"));
        values.put(tables.COLUMN_KYC_NUMBER,queryValues.get("kyc_number"));
        values.put(tables.COLUMN_KYC_FILE,queryValues.get("kyc_file"));
        values.put(tables.COLUMN_KYC_CREATED_AT,queryValues.get("created_at"));
        values.put(tables.COLUMN_KYC_UPDATED_AT,queryValues.get("updated_at"));
        db.insertWithOnConflict(tables.TABLE_KYCS ,null,values,SQLiteDatabase.CONFLICT_REPLACE);
        Log.d("@@","insert");
    }
    public void addBeneficialey(HashMap<String, String> queryValues){
        String message="Successfully Insert";
        ContentValues values=new ContentValues();
        values.put(tables.COLUMN_BENEFICIAL_ID,queryValues.get("id"));
        values.put(tables.COLUMN_BENEFICIAL_CODE,queryValues.get("code"));
        values.put(tables.COLUMN_BENEFICIAL_NAME,queryValues.get("beneficiary_name"));
        values.put(tables.COLUMN_BENEFICIAL_GENDER,queryValues.get("gender"));
        values.put(tables.COLUMN_BENEFICIAL_BOD,queryValues.get("date_of_birth"));
        values.put(tables.COLUMN_BENEFICIAL_CONTECTnUMBER,queryValues.get("contact_number"));
        values.put(tables.COLUMN_BENEFICIAL_CREATED_AT,queryValues.get("created_at"));
        values.put(tables.COLUMN_BENEFICIAL_UPDATED_AT,queryValues.get("updated_at"));
        values.put(tables.COLUMN_BENEFICIAL_FAMILYHAD_ID,queryValues.get("family_head_id"));
        values.put(tables.COLUMN_BENEFICIAL_FIRSTNAME,queryValues.get("firstname"));
        values.put(tables.COLUMN_BENEFICIAL_MIDDLENAME,queryValues.get("middlename"));
        values.put(tables.COLUMN_BENEFICIAL_LASTNAME,queryValues.get("lastname"));
        values.put(tables.COLUMN_BENEFICIAL_HOUSEHOLD_ID,queryValues.get("household_id"));
        values.put(tables.COLUMN_BENEFICIAL_VILLAGE_ID,queryValues.get("village_id"));
        values.put(tables.COLUMN_BENEFICIAL_USER_ID,queryValues.get("user_id"));
        values.put(tables.COLUMN_BENEFICIAL_AGE,queryValues.get("age"));
        values.put(tables.COLUMN_BENEFICIAL_FAMILYHAD_RELATION,queryValues.get("family_head_relation"));
        values.put(tables.COLUMN_BENEFICIAL_WHATSAPPNUMBER,queryValues.get("whatsapp_number"));
        db.insertWithOnConflict(tables.TABLE_BENEFICIARIES ,null,values,SQLiteDatabase.CONFLICT_REPLACE);
        Log.d("@@","insertBENEFICIALY");
    }
    public void addBeneficialey_New(HashMap<String, String> queryValues){
        String message="Successfully Insert";
        ContentValues values=new ContentValues();
        values.put(tables.COLUMN_BENEFICIAL_ID,queryValues.get("id"));
        values.put(tables.COLUMN_BENEFICIAL_CODE,queryValues.get("code"));
        values.put(tables.COLUMN_BENEFICIAL_NAME,queryValues.get("beneficiary_name"));
        values.put(tables.COLUMN_BENEFICIAL_GENDER,queryValues.get("gender"));
        values.put(tables.COLUMN_BENEFICIAL_BOD,queryValues.get("date_of_birth"));
        values.put(tables.COLUMN_BENEFICIAL_CONTECTnUMBER,queryValues.get("contact_number"));
        values.put(tables.COLUMN_BENEFICIAL_CREATED_AT,queryValues.get("created_at"));
        values.put(tables.COLUMN_BENEFICIAL_UPDATED_AT,queryValues.get("updated_at"));
        values.put(tables.COLUMN_BENEFICIAL_FAMILYHAD_ID,queryValues.get("family_head_id"));
        values.put(tables.COLUMN_BENEFICIAL_FIRSTNAME,queryValues.get("firstname"));
        values.put(tables.COLUMN_BENEFICIAL_MIDDLENAME,queryValues.get("middlename"));
        values.put(tables.COLUMN_BENEFICIAL_LASTNAME,queryValues.get("lastname"));
        values.put(tables.COLUMN_BENEFICIAL_HOUSEHOLD_ID,queryValues.get("household_id"));
        values.put(tables.COLUMN_BENEFICIAL_VILLAGE_ID,queryValues.get("village_id"));
        values.put(tables.COLUMN_BENEFICIAL_USER_ID,queryValues.get("user_id"));
        values.put(tables.COLUMN_BENEFICIAL_AGE,queryValues.get("age"));
        values.put(tables.COLUMN_BENEFICIAL_FAMILYHAD_RELATION,queryValues.get("family_head_relation"));
        values.put(tables.COLUMN_BENEFICIAL_WHATSAPPNUMBER,queryValues.get("whatsapp_number"));
        db.insertWithOnConflict(tables.TABLE_BENEFICIARIES ,null,values,SQLiteDatabase.CONFLICT_REPLACE);
        Log.d("@@","insertBENEFICIALY");
    }

    public void addNewkyc(HashMap<String, String> queryValues){
        try {
            ContentValues values = new ContentValues();
            values.put(tables.COLUMN_KYC_PERSIONID_NEW, queryValues.get("kyc_person_id"));
            values.put(tables.COLUMN_KYC_PERSONTYPE_NEW, queryValues.get("kyc_person_type"));
            values.put(tables.COLUMN_KYC_NAME_NEW, queryValues.get("kyc_name"));
            values.put(tables.COLUMN_KYC_DETAIL_NEW, queryValues.get("kyc_detail"));
            values.put(tables.COLUMN_KYC_NUMBER_NEW, queryValues.get("kyc_number"));
            values.put(tables.COLUMN_KYC_FILE_NEW, queryValues.get("kyc_file"));
            db.insertWithOnConflict(tables.TABLE_KYCS_NEW, null, values, SQLiteDatabase.CONFLICT_REPLACE);
            Log.d("@@", "insert++++");
        }catch (Exception e){
            Log.d("@@add", String.valueOf(e));
        }
    }




    public boolean updateUser(String email,String Password) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tables.COLUMN_USER_PASSWORD, Password);
        int i= db.update(tables.TABLE_USER, values, tables.COLUMN_USER_EMAIL + " = ?", new String[]{email});
        db.close();
        if(i>0){
            return true;
        }

        return false;
    }


    public Cursor getProfileData(String Demail){
    String query = "SELECT * FROM users "+ " Where "+ tables.COLUMN_USER_EMAIL + " = " + "'" +Demail + "'" ;
      Cursor cursor = db.rawQuery(query, null);
     if (cursor != null) {
          cursor.moveToFirst();
     }
      return cursor;
  }

    public Cursor getBanificialsDetails(String bani_id){
        String query = "SELECT * FROM "+  tables.TABLE_BENEFICIARIES  + "  Where  "+   tables.COLUMN_BENEFICIAL_CODE
                + " = " + "'" +bani_id + "'" ;
        Cursor cursor = db.rawQuery(query, null);

         if (cursor != null) {
            cursor.moveToFirst();
         }
        return cursor;
    }
    public Cursor getBanificialsKYCDetails(String kyc_id){
        String query = "SELECT * FROM "+  tables.TABLE_KYCS  + "  Where  "+   tables.COLUMN_KYC_PERSIONID
                + " = " + "'" +kyc_id + "'" +"  LIMIT 1" ;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();

        }
        Log.e("@@Error2",String.valueOf(cursor));
        return cursor;
    }
    public boolean updateUserProfile(String Fname,String Lname,String email) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tables.COLUMN_USER_NAME, Fname);
        values.put(tables.COLUMN_USER_LNAME, Lname);
        values.put(tables.COLUMN_USER_EMAIL, email);
        int i= db.update(tables.TABLE_USER, values, tables.COLUMN_USER_EMAIL + " = ?", new String[]{email});

        if(i>0){
            return true;
        }
        return false;
    }
    public Cursor getSurveyes(){

     String query="SELECT * FROM   surveys ";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();

        }
        return cursor;
    }
    public  Cursor getIDOfServeyar(String UserEmail){
        String query = "SELECT * FROM "+  tables.TABLE_USER  + "  Where  "+   tables.COLUMN_USER_EMAIL
                + " = " + "'" +UserEmail + "'" ;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor != null){
            cursor.moveToFirst();

            return cursor;
        }
        Log.d("@@Error2",String.valueOf(cursor));
        return null;

    }

    public Cursor getKYCDetails(){
        String query="select * from "+ tables.TABLE_KYCS_NEW;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && !cursor.isClosed()) {
            cursor.moveToFirst();
        }
        Log.e("@@",String.valueOf(cursor));
        return cursor;
    }

    public Cursor ProfileKYCDetails(String Beneficial_id ){
        String query = "SELECT * FROM  "+  tables.TABLE_KYCS  + "  Where  "+   tables.COLUMN_KYC_PERSIONID
                + "  = "+ "'" + Beneficial_id + "'";

       Log.d("@@",query);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
       return cursor;
    }




    public Cursor getBeneficiales(){
     String query="select * from " +  tables.TABLE_BENEFICIARIES;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
       return cursor;
    }


    }
