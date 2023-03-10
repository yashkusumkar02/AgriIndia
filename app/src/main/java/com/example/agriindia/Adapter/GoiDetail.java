package com.example.agriindia.Adapter;

public class GoiDetail {

    private String GroceryName, GroceryPlace, GroceryPrice;
    private Long GroceryTime;

    public GoiDetail() {
    }

    public GoiDetail(String groceryName, String groceryPlace, String groceryPrice, Long groceryTime) {
        GroceryName = groceryName;
        GroceryPlace = groceryPlace;
        GroceryPrice = groceryPrice;
        GroceryTime = groceryTime;
    }

    public String getGroceryName() {
        return GroceryName;
    }

    public void setGroceryName(String groceryName) {
        GroceryName = groceryName;
    }

    public String getGroceryPlace() {
        return GroceryPlace;
    }

    public void setGroceryPlace(String groceryPlace) {
        GroceryPlace = groceryPlace;
    }

    public String getGroceryPrice() {
        return GroceryPrice;
    }

    public void setGroceryPrice(String groceryPrice) {
        GroceryPrice = groceryPrice;
    }

    public Long getGroceryTime() {
        return GroceryTime;
    }

    public void setGroceryTime(Long groceryTime) {
        GroceryTime = groceryTime;
    }
}
