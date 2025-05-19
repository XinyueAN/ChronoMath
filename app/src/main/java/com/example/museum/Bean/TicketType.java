package com.example.museum.Bean;
public class TicketType {
    private String type;
    private String price;

    // Constructor, getters, and setters...
    public TicketType(String type, String price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getPrice() {
        return price;
    }
}