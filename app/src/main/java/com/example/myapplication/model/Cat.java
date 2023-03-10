package com.example.myapplication.model;

public class Cat {
    private int img;
    private String name;
    private Double price;
    private Boolean b1,b2,b3;
    private int rating;

    public Cat() {
    }

    public Cat(int img, String name, Double price, Boolean b1, Boolean b2, Boolean b3, int rating) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getB1() {
        return b1;
    }

    public void setB1(Boolean b1) {
        this.b1 = b1;
    }

    public Boolean getB2() {
        return b2;
    }

    public void setB2(Boolean b2) {
        this.b2 = b2;
    }

    public Boolean getB3() {
        return b3;
    }

    public void setB3(Boolean b3) {
        this.b3 = b3;
    }
}
