package com.example.museum.Bean;

public class MathematiclanBean {
    private int img;
    private String name;
    //南北朝时限
    private String period;
    private String tags;
    private String des;

    public MathematiclanBean(int img, String name, String period, String tags, String des) {
        this.img = img;
        this.name = name;
        this.period = period;
        this.tags = tags;
        this.des = des;
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
