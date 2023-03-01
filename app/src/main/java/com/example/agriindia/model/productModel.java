package com.example.agriindia.model;

public class productModel {
String purl,price,title,desc,quantity;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public productModel(String purl, String price, String title, String desc, String quantity) {
        this.purl = purl;
        this.price = price;
        this.title = title;
        this.desc = desc;
        this.quantity = quantity;
    }
}
