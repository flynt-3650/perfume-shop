package com.example.perfumeshop;

public class Perfume {

    // Declare instance variables
    private String id; // New field for unique identifier
    private String name;
    private String brand;
    private String gender;
    private int size;
    private double price;
    private String description;
    private String imageUrl;
    private boolean added;
    private boolean clickable; // New field to represent button clickability

    // Declare constructors
    public Perfume() {}

    public Perfume(String id, String name, String brand, String gender, int size, double price, String description, String imageUrl, boolean added) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.gender = gender;
        this.size = size;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.added = added;
    }


    // Getter and setter for 'clickable' field
    public boolean isClickable() {
        return clickable;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }
    // Getter and setter methods for 'id' field
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter and setter methods for other fields (name, brand, etc.)
    // These methods remain unchanged

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
