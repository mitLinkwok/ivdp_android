package com.example.linwok.ivdp.activity.model;

public class All_survey_Model {
    private String SurveyTital,SurveyDescripction,SurveyDuration;
private int SurveyID,ProjectID;
    public All_survey_Model(){}

    public int getSurveyID() {
        return SurveyID;
    }

    public void setSurveyID(int surveyID) {
        this.SurveyID = surveyID;
    }

    public int getProjectID() {
        return ProjectID;
    }

    public void setProjectID(int projectID) {
        this.ProjectID = projectID;
    }

    public String getSurveyTital() {
        return SurveyTital;
    }

    public void setSurveyTital(String surveyTital) {
       this.SurveyTital = surveyTital;
    }

    public String getSurveyDescripction() {
        return SurveyDescripction;
    }

    public void setSurveyDescripction(String surveyDescripction) {
       this.SurveyDescripction = surveyDescripction;
    }

    public String getSurveyDuration() {
        return SurveyDuration;
    }

    public void setSurveyDuration(String surveyDuration) {
        this.SurveyDuration = surveyDuration;
    }
}
