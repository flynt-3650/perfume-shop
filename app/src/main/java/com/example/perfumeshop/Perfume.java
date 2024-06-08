package com.example.perfumeshop;

public class Perfume {

    // Declare instance variables
    private String name;
    private String brand;
    private String gender;
    private int size;
    private double price;
    private String description;
    private String imageUrl;

    private boolean added;

    // Declare constructors
    public Perfume() {

    }

    public Perfume(String name, String brand, String gender, int size, double price, String description, String imageUrl, boolean added) {
        this.name = name;
        this.brand = brand;
        this.gender = gender;
        this.size = size;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.added = added;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }
}
