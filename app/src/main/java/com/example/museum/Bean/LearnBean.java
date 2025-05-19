package com.example.museum.Bean;

public class LearnBean {
    private int learn_img;
    private String title;
    private String content;
    public LearnBean(int learn_img, String title, String content) {
        this.learn_img = learn_img;
        this.title = title;
        this.content = content;
    }

    public int getLearn_img() {
        return learn_img;
    }

    public void setLearn_img(int learn_img) {
        this.learn_img = learn_img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
