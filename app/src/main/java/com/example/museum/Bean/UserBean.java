package com.example.museum.Bean;

public class UserBean {
    private String userMail;
    private String username;
    private String userpassword;

    public UserBean(String userMail, String username, String userpassword) {
        this.userMail = userMail;
        this.username = username;
        this.userpassword = userpassword;
    }

    // Getters and Setters
    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

}