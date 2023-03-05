package com.example.agriindia.model;

public class articleModel {

    String title,purl;
    articleModel(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public articleModel(String title, String purl) {
        this.title = title;
        this.purl = purl;
    }
}
