package com.example.museum.Bean;

import java.io.Serializable;

public class FigureBean implements Serializable {
    private String imagePath;
    private String name;
    private String num;
    private String category;
    private String era;

    public FigureBean(String imagePath, String name, String num, String category, String era) {
        this.imagePath = imagePath;
        this.name = name;
        this.num = num;
        this.category = category;
        this.era = era;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEra() {
        return era;
    }

    public void setEra(String era) {
        this.era = era;
    }
}
