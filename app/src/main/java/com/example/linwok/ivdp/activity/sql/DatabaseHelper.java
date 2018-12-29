package com.example.linwok.ivdp.activity.sql;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "IVDP.db";
     // create table sql query


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("@@","create DB");


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {



            String CREATE_KYCS_TABLE = "CREATE TABLE " + tables.TABLE_KYCS + "("
                    + tables.COLUMN_KYC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + tables.USERS_ID + "  INTEGER,"
                    + tables.COLUMN_KYC_PERSIONID + "  INTEGER,"
                    + tables.COLUMN_KYC_PERSONTYPE + " TEXT,"
                    + tables.COLUMN_KYC_NAME + " TEXT,"
                    + tables.COLUMN_KYC_DETAIL + " TEXT,"
                    + tables.COLUMN_KYC_NUMBER + " TEXT,"
                    + tables.COLUMN_KYC_FILE + "  TEXT,"
                    + tables.COLUMN_KYC_CREATED_AT + " TIMESTAMP,"
                    + tables.COLUMN_KYC_UPDATED_AT + " TIMESTAMP"
                    + ")";

            String CREATE_KYCS_NEW_TABLE = "CREATE TABLE " + tables.TABLE_KYCS_NEW + "("
                    + tables.COLUMN_KYC_ID_NEW + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + tables.COLUMN_KYC_PERSIONID_NEW + "  INTEGER,"
                    + tables.COLUMN_KYC_PERSONTYPE_NEW + " TEXT,"
                    + tables.COLUMN_KYC_NAME_NEW + " TEXT,"
                    + tables.COLUMN_KYC_DETAIL_NEW + " TEXT,"
                    + tables.COLUMN_KYC_NUMBER_NEW + " TEXT,"
                    + tables.COLUMN_KYC_FILE_NEW + "  BLOB NOT NULL"
                    + ")";


            // create table sql query FOR TABLE_SURVEYS
          /*  String CREATE_SURVEYS_TABLE = "CREATE TABLE " + tables.TABLE_SURVEYS + "("
                    + tables.COLUMN_SURVEYS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + tables.PROJECT_ID + "  INTEGER,"
                    + tables.COLUMN_SURVEYS_TITAL + "  TEXT,"
                    + tables.COLUMN_SURVEYS_DESC + " TEXT,"
                    + tables.COLUMN_SURVEYS_DURATION + " INTEGER,"
                    + tables.COLUMN_SURVEYS_STARTdATE + " TIMESTAMP,"
                    + tables.COLUMN_SURVEYS_LANG_JSON + " TEXT,"
                    + tables.COLUMN_SURVEYS_SETTING_JASON + " TEXT,"
                    + tables.COLUMN_SURVEYS_CREATED + " TIMESTAMP,"
                    + tables.COLUMN_SURVEYS_UPDATED + " TIMESTAMP"
                    + ")";
*/
            // create table sql query FOR TABLE_SURVEYS
           /* String CREATE_PROJECT_TABLE = "CREATE TABLE " + tables.TABLE_PROJECT + "("
                    + tables.COLUMN_PROJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + tables.DEPARTMENT_ID + "  INTEGER,"
                    + tables.COLUMN_PROJECT_NAME + "  TEXT,"
                    + tables.COLUMN_PROJECT_CODE + " TEXT,"
                    + tables.COLUMN_PROJECT_CREATED + " TIMESTAMP,"
                    + tables.COLUMN_PROJECT_UPDATED + " TIMESTAMP"
                    + ")";*/

            // create table sql query FOR TABLE_SECTION
           /* String CREATE_SECTION_TABLE = "CREATE TABLE " + tables.TABLE_SECTION + "("
                    + tables.COLUMN_SECTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + tables.SURVEYS_ID + "  INTEGER,"
                    + tables.COLUMN_SECTION_ORDER + " INTEGER,"
                    + tables.COLUMN_SECTION_NAME + " TEXT,"
                    + tables.COLUMN_SECTION_LANG_JSON + " TEXT,"
                    + tables.COLUMN_SURVEYS_SETTING_JASON + " TEXT,"
                    + tables.COLUMN_SECTION_CREATED + " TIMESTAMP,"
                    + tables.COLUMN_SECTION_UPDATED + " TIMESTAMP"
                    + ")";
*/
            // create table sql query FOR TABLE_SURVEYS
           /* String CREATE_QUESTION_TABLE = "CREATE TABLE " + tables.TABLE_QUESTIONS + "("
                    + tables.COLUMN_QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + tables.SURVEY_ID + "   INTEGER,"
                    + tables.COLUMN_QUESTION_ORDER + " INTEGER,"
                    + tables.COLUMN_QUESTION_TEXT + " TEXT,"
                    + tables.COLUMN_QUESTION_TYPE + "  TEXT,"
                    + tables.COLUMN_QUESTION_LANG_JSON + " TEXT,"
                    + tables.COLUMN_QUESTION_RULE_JASON + "  TEXT,"
                    + tables.COLUMN_QUESTION_CREATED + " TIMESTAMP,"
                    + tables.COLUMN_QUESTION_UPDATED + " TIMESTAMP"
                    + ")";*/

            // create table sql query FOR BENEFICIARIES
            String CREATE_BENEFICIARIES_TABLE = "CREATE TABLE " + tables.TABLE_BENEFICIARIES + "("
                    + tables.COLUMN_BENEFICIAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + tables.COLUMN_BENEFICIAL_CODE + "  TEXT,"
                    + tables.COLUMN_BENEFICIAL_NAME + "  TEXT,"
                    + tables.COLUMN_BENEFICIAL_GENDER + " NUMBER,"
                    + tables.COLUMN_BENEFICIAL_BOD + " TEXT,"
                    + tables.COLUMN_BENEFICIAL_CONTECTnUMBER + " NUMBER,"
                    + tables.COLUMN_BENEFICIAL_CREATED_AT + " TIMESTAMP,"
                    + tables.COLUMN_BENEFICIAL_UPDATED_AT + " TIMESTAMP,"
                    + tables.COLUMN_BENEFICIAL_FAMILYHAD_ID + "  TEXT,"
                    + tables.COLUMN_BENEFICIAL_FIRSTNAME + " TEXT,"
                    + tables.COLUMN_BENEFICIAL_MIDDLENAME + " TEXT,"
                    + tables.COLUMN_BENEFICIAL_LASTNAME + " TEXT,"
                    + tables.COLUMN_BENEFICIAL_HOUSEHOLD_ID + " NUMBER,"
                    + tables.COLUMN_BENEFICIAL_VILLAGE_ID + " NUMBER,"
                    + tables.COLUMN_BENEFICIAL_USER_ID + " NUMBER,"
                    + tables.COLUMN_BENEFICIAL_AGE + " NUMBER,"
                    + tables.COLUMN_BENEFICIAL_FAMILYHAD_RELATION + " TEXT,"
                    + tables.COLUMN_BENEFICIAL_WHATSAPPNUMBER + " NUMBER"
                    + ")";
            String CREATE_BENEFICIARIES_NEW_TABLE = "CREATE TABLE " + tables.TABLE_BENEFICIARIES_NEW + "("
                    + tables.COLUMN_BENEFICIAL_ID_NEW + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + tables.COLUMN_BENEFICIAL_CODE_NEW + "  TEXT,"
                    + tables.COLUMN_BENEFICIAL_NAME_NEW + "  TEXT,"
                    + tables.COLUMN_BENEFICIAL_GENDER_NEW + " NUMBER,"
                    + tables.COLUMN_BENEFICIAL_BOD_NEW + " TEXT,"
                    + tables.COLUMN_BENEFICIAL_CONTECTnUMBER_NEW + " NUMBER,"
                    + tables.COLUMN_BENEFICIAL_CREATED_AT + " TIMESTAMP,"
                    + tables.COLUMN_BENEFICIAL_UPDATED_AT + " TIMESTAMP,"
                    + tables.COLUMN_BENEFICIAL_FAMILYHAD_ID_NEW + "  TEXT,"
                    + tables.COLUMN_BENEFICIAL_FIRSTNAME_NEW + " TEXT,"
                    + tables.COLUMN_BENEFICIAL_MIDDLENAME_NEW + " TEXT,"
                    + tables.COLUMN_BENEFICIAL_LASTNAME_NEW + " TEXT,"
                    + tables.COLUMN_BENEFICIAL_HOUSEHOLD_ID_NEW + " NUMBER,"
                    + tables.COLUMN_BENEFICIAL_VILLAGE_ID_NEW + " NUMBER,"
                    + tables.COLUMN_BENEFICIAL_USER_ID_NEW + " NUMBER,"
                    + tables.COLUMN_BENEFICIAL_AGE_NEW + " NUMBER,"
                    + tables.COLUMN_BENEFICIAL_FAMILYHAD_RELATION_NEW + " TEXT,"
                    + tables.COLUMN_BENEFICIAL_WHATSAPPNUMBER_NEW + " NUMBER"
                    + ")";



            db.execSQL(CREATE_KYCS_TABLE);
            db.execSQL(CREATE_BENEFICIARIES_TABLE);
            db.execSQL(CREATE_KYCS_NEW_TABLE);
            db.execSQL(CREATE_BENEFICIARIES_NEW_TABLE);
            Log.d("@@","create table");

        }catch (Exception E){

            Log.d("@@", String.valueOf(E));
        }


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       try {

           String DROP_BENEFICIARIES_TABLE = "DROP TABLE IF EXISTS " + tables.TABLE_BENEFICIARIES;
           db.execSQL(DROP_BENEFICIARIES_TABLE);
           String DROP_KYCS_TABLE = "DROP TABLE IF EXISTS " + tables.TABLE_KYCS;
           db.execSQL(DROP_KYCS_TABLE);
           String DROP_SURVEYS_TABLE = "DROP TABLE IF EXISTS " + tables.TABLE_SURVEYS;
           db.execSQL(DROP_SURVEYS_TABLE);
           String DROP_SECTION_TABLE = "DROP TABLE IF EXISTS " + tables.TABLE_SECTION;
           db.execSQL(DROP_SECTION_TABLE);
           String DROP_PROJECT_TABLE = "DROP TABLE IF EXISTS " + tables.TABLE_PROJECT;
           db.execSQL(DROP_PROJECT_TABLE);
          /* String DROP_QUESTION_TABLE = "DROP TABLE IF EXISTS " + tables.TABLE_QUESTIONS;
           db.execSQL(DROP_QUESTION_TABLE);*/
           onCreate(db);
       }catch (Exception e){
           Log.d("@@","Error in Deop table",e);
       }


    }
}