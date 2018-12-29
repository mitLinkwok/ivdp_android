package com.example.linwok.ivdp.activity.sql;

public class tables  {

    protected static final String TABLE_USER = "users";
    protected static final String TABLE_BENEFICIARIES = "beneficiaries";
    protected static final String TABLE_BENEFICIARIES_NEW = "beneficiaries_NEW";
    protected static final String TABLE_KYCS = "kycs";
    protected static final String TABLE_KYCS_NEW = "kycs_NEW";


    protected static final String TABLE_SURVEYS = "surveys";
    protected static final String TABLE_SECTION = "sections";
    protected static final String TABLE_PROJECT= "projects";
    protected static final String TABLE_QUESTIONS= "questions";

    // User Table Columns names
    protected static final String COLUMN_USER_ID = "id";
    protected static final String COLUMN_USER_NAME = "first_name";
    protected static final String COLUMN_USER_LNAME = "last_name";
    protected static final String COLUMN_USER_EMAIL = "email";
    protected static final String COLUMN_USER_PASSWORD = "password";

     // beneficiaries Table Columns names
    protected static final String COLUMN_BENEFICIAL_ID = "id";
    protected static final String COLUMN_BENEFICIAL_CODE="code";
    protected static final String COLUMN_BENEFICIAL_NAME="beneficiary_name";
    protected static final String COLUMN_BENEFICIAL_GENDER  = "gender";
    protected static final String COLUMN_BENEFICIAL_BOD="date_of_birth";
    protected static final String COLUMN_BENEFICIAL_CONTECTnUMBER="contact_number";
    protected static final String COLUMN_BENEFICIAL_CREATED_AT  = "created_at";
    protected static final String COLUMN_BENEFICIAL_UPDATED_AT  = "updated_at";
    protected static final String COLUMN_BENEFICIAL_FAMILYHAD_ID="family_head_id";
    protected static final String COLUMN_BENEFICIAL_FIRSTNAME = "firstname";
    protected static final String COLUMN_BENEFICIAL_LASTNAME  = "lastname";
    protected static final String COLUMN_BENEFICIAL_MIDDLENAME  = "middlename";
    protected static final String COLUMN_BENEFICIAL_HOUSEHOLD_ID="household_id";
    protected static final String COLUMN_BENEFICIAL_VILLAGE_ID="village_id";
    protected static final String COLUMN_BENEFICIAL_USER_ID="user_id";
    protected static final String COLUMN_BENEFICIAL_AGE="age";
    protected static final String COLUMN_BENEFICIAL_FAMILYHAD_RELATION="family_head_relation";
    protected static final String COLUMN_BENEFICIAL_WHATSAPPNUMBER="whatsapp_number";

    //new Update
    // beneficiaries Table Columns names
    protected static final String COLUMN_BENEFICIAL_ID_NEW = "id";
    protected static final String COLUMN_BENEFICIAL_CODE_NEW ="code";
    protected static final String COLUMN_BENEFICIAL_NAME_NEW ="beneficiary_name";
    protected static final String COLUMN_BENEFICIAL_GENDER_NEW   = "gender";
    protected static final String COLUMN_BENEFICIAL_BOD_NEW ="date_of_birth";
    protected static final String COLUMN_BENEFICIAL_CONTECTnUMBER_NEW ="contact_number";
    protected static final String COLUMN_BENEFICIAL_FAMILYHAD_ID_NEW ="family_head_id";
    protected static final String COLUMN_BENEFICIAL_FIRSTNAME_NEW  = "firstname";
    protected static final String COLUMN_BENEFICIAL_LASTNAME_NEW   = "lastname";
    protected static final String COLUMN_BENEFICIAL_MIDDLENAME_NEW   = "middlename";
    protected static final String COLUMN_BENEFICIAL_HOUSEHOLD_ID_NEW ="household_id";
    protected static final String COLUMN_BENEFICIAL_VILLAGE_ID_NEW ="village_id";
    protected static final String COLUMN_BENEFICIAL_USER_ID_NEW ="user_id";
    protected static final String COLUMN_BENEFICIAL_AGE_NEW ="age";
    protected static final String COLUMN_BENEFICIAL_FAMILYHAD_RELATION_NEW ="family_head_relation";
    protected static final String COLUMN_BENEFICIAL_WHATSAPPNUMBER_NEW ="whatsapp_number";






    // kycs Table Columns names
    protected static final String COLUMN_KYC_ID = "id";
    protected static final String USERS_ID="user_id";
    protected static final String COLUMN_KYC_PERSIONID = "kyc_person_id";
    protected static final String COLUMN_KYC_PERSONTYPE = "kyc_person_type";
    protected static final String COLUMN_KYC_NAME  = "kyc_name";
    protected static final String COLUMN_KYC_DETAIL  = "kyc_detail";
    protected static final String COLUMN_KYC_NUMBER = "kyc_number";
    protected static final String COLUMN_KYC_FILE = "kyc_file";
    protected static final String COLUMN_KYC_CREATED_AT  = "created_at";
    protected static final String COLUMN_KYC_UPDATED_AT  = "updated_at";

    //KYC_UPDATE_NEW_DOUCUMENT
    protected static final String COLUMN_KYC_ID_NEW = "id";
    protected static final String COLUMN_KYC_PERSIONID_NEW = "kyc_person_id";
    protected static final String COLUMN_KYC_PERSONTYPE_NEW = "kyc_person_type";
    protected static final String COLUMN_KYC_NAME_NEW  = "kyc_name";
    protected static final String COLUMN_KYC_DETAIL_NEW  = "kyc_detail";
    protected static final String COLUMN_KYC_NUMBER_NEW = "kyc_number";
    protected static final String COLUMN_KYC_FILE_NEW = "kyc_file";






    //surveys Tables Columns Name
    protected static final String COLUMN_SURVEYS_ID = "id";
    protected static final String PROJECT_ID="project_id";
    protected static final String COLUMN_SURVEYS_TITAL = "title";
    protected static final String COLUMN_SURVEYS_DESC = "description";
    protected static final String COLUMN_SURVEYS_DURATION  = "duration_months";
    protected static final String COLUMN_SURVEYS_STARTdATE  = "start_date";
    protected static final String COLUMN_SURVEYS_LANG_JSON = "language_json";
    protected static final String COLUMN_SURVEYS_SETTING_JASON  = "setting_json";
    protected static final String COLUMN_SURVEYS_CREATED  = "created_at";
    protected static final String COLUMN_SURVEYS_UPDATED  = "updated_at";

    //SECTION Tables Columns Name
    protected static final String COLUMN_SECTION_ID = "id";
    protected static final String SURVEYS_ID="survey_id";
    protected static final String COLUMN_SECTION_ORDER = "order";
    protected static final String COLUMN_SECTION_NAME = "name";
    protected static final String COLUMN_SECTION_LANG_JSON  = "language_json";
    protected static final String COLUMN_SECTION_CREATED  = "created_at";
    protected static final String COLUMN_SECTION_UPDATED  = "updated_at";

    //PROJECT Tables Columns Name
    protected static final String COLUMN_PROJECT_ID = "id";
    protected static final String DEPARTMENT_ID="department_id";
    protected static final String COLUMN_PROJECT_NAME = "name";
    protected static final String COLUMN_PROJECT_CODE = "code";
    protected static final String COLUMN_PROJECT_CREATED  = "created_at";
    protected static final String COLUMN_PROJECT_UPDATED  = "updated_at";




    //QUESTION Tables Columns Name
    protected static final String COLUMN_QUESTION_ID = "id";
    protected static final String SURVEY_ID="survey_id";
    protected static final String SECTION_ID = "section_id";
    protected static final String COLUMN_QUESTION_ORDER = "order";
    protected static final String COLUMN_QUESTION_TEXT  = "text";
    protected static final String COLUMN_QUESTION_TYPE  = "type";
    protected static final String COLUMN_QUESTION_LANG_JSON = "language_json";
    protected static final String COLUMN_QUESTION_RULE_JASON  = "rule_json";
    protected static final String COLUMN_QUESTION_CREATED  = "created_at";
    protected static final String COLUMN_QUESTION_UPDATED  = "updated_at";
}
