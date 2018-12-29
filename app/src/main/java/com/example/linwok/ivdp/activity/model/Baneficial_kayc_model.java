package com.example.linwok.ivdp.activity.model;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Baneficial_kayc_model {
    private String kyc_person_id,user_id,kyc_person_type,kyc_name,kyc_detail,kyc_number,kyc_file,created_at;
    private Integer status;

    public Baneficial_kayc_model(){}



    public Baneficial_kayc_model(String kyc_person_id, String user_id, String kyc_person_type,
                                 String kyc_name, String kyc_detail, String kyc_number, String kyc_file, String created_at ){
        this.kyc_person_id=kyc_person_id;

        this.user_id=user_id;
        this.kyc_person_type=kyc_person_type;
        this.kyc_name=kyc_name;
        this.kyc_detail=kyc_detail;
        this.kyc_number=kyc_number;
        this.kyc_file=kyc_file;
        this.created_at=created_at;

    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getKyc_person_id() {
        return kyc_person_id;
    }

    public void setKyc_person_id(String kyc_person_id) {
        this.kyc_person_id = kyc_person_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getKyc_person_type() {
        return kyc_person_type;
    }

    public void setKyc_person_type(String kyc_person_type) {
        this.kyc_person_type = kyc_person_type;
    }

    public String getKyc_name() {
        return kyc_name;
    }

    public void setKyc_name(String kyc_name) {

        this.kyc_name = kyc_name;
    }

    public String getKyc_detail() {
        return kyc_detail;
    }

    public void setKyc_detail(String kyc_detail) {
        this.kyc_detail = kyc_detail;
    }

    public String getKyc_number() {
        return kyc_number;
    }

    public void setKyc_number(String kyc_number) {
        this.kyc_number = kyc_number;
    }

    public String getKyc_file() {
        return kyc_file;
    }

    public void setKyc_file(String kyc_file) {
        this.kyc_file = kyc_file;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
