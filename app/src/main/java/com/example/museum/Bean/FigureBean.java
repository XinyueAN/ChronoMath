package com.example.museum.Bean;

import java.io.Serializable;

public class FigureBean implements Serializable {
    private String imgName;
    private String name;
    private String price;
    private String author;
    private String description;
    private String era;

    public FigureBean(String imgName, String name, String price, String author, String description, String era) {
        this.imgName = imgName;
        this.name = name;
        this.price = price;
        this.author = author;
        this.description = description;
        this.era = era;
    }

    public String getImgName() { return imgName; }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getName() {
        return name;
    }

    public void setPrice(String name) {
        this.price = price;
    }
    public String getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String author) {
        this.description = description;
    }

    public String getEra() {
        return era;
    }

    public void setEra(String era) {
        this.era = era;
    }
}
