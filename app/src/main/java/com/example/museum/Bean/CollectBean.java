package com.example.museum.Bean;


public class CollectBean {
    private String userMail;
    private String type;
    private String collect;
    private String imgPath;
    private String name;
    private String num;
    private String era;
    private String category;
    private String region;
    private String description;

    public CollectBean(String userMail, String type, String collect, String imgPath, String name, String num, String era, String category, String region, String description) {
        this.userMail = userMail;
        this.type = type;
        this.collect = collect;
        this.imgPath = imgPath;
        this.name = name;
        this.num = num;
        this.era = era;
        this.category = category;
        this.region = region;
        this.description = description;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCollect() {
        return collect;
    }

    public void setCollect(String collect) {
        this.collect = collect;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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

    public String getEra() {
        return era;
    }

    public void setEra(String era) {
        this.era = era;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}