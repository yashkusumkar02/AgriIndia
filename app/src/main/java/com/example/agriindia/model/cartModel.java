package com.example.agriindia.model;

public class cartModel {
    String purl,price,title,desc,quantity,total,date,orderID,payment,address;
    cartModel(){}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPurl() {
        return purl;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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

    public cartModel(String purl, String price, String title, String desc, String quantity, String total, String date,String orderID,String payment,String address) {
        this.purl = purl;
        this.price = price;
        this.title = title;
        this.desc = desc;
        this.quantity = quantity;
        this.total = total;
        this.date = date;
        this.orderID = orderID;
        this.payment = payment;
        this.address = address;
    }

}
