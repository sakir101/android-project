package com.example.firebaseconnection;

public class foodCollection {
    String name, category, price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public foodCollection(String name, String category, String price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
}
