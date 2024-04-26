package com.example.courselast;


import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;


public class Item {
    private String name;
    private int quantity;
    private double price;
    private String description;
    private String category;
    private Image image;


    public Item(String name, int quantity, double price, String description, String category) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.category = category;
    }


    public Image getImage() { return image; }


    public void setImage(byte[] imageData) {
        this.image = new Image(new ByteArrayInputStream(imageData));
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
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


    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}