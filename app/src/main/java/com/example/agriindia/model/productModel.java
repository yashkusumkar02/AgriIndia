package com.example.agriindia.model;

public class productModel {
String purl,price,title;

    productModel(){}
    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public productModel(String purl, String price, String title) {
        this.purl = purl;
        this.price = price;
        this.title = title;
    }
}
