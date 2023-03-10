package com.example.agriindia.model;

public class postModel {
    String Title,Description,purl,Name;


    postModel(){

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public postModel(String title, String description, String purl, String name) {
        Title = title;
        Description = description;
        this.purl = purl;
        Name = name;
    }
}
