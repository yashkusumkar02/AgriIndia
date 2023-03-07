package com.example.agriindia.Adapter;

public class GoiDetail {

    private String GroceryName, GroceryPlace, GrocerPrice;
    private Long GroceryTime;

    public GoiDetail() {
    }

    public GoiDetail(String groceryName, String groceryPlace, String grocerPrice, Long groceryTime) {
        GroceryName = groceryName;
        GroceryPlace = groceryPlace;
        GrocerPrice = grocerPrice;
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

    public String getGrocerPrice() {
        return GrocerPrice;
    }

    public void setGrocerPrice(String grocerPrice) {
        GrocerPrice = grocerPrice;
    }

    public Long getGroceryTime() {
        return GroceryTime;
    }

    public void setGroceryTime(Long groceryTime) {
        GroceryTime = groceryTime;
    }
}
