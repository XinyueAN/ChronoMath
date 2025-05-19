package com.example.museum.Bean;

public class OrderBean {

    private String userMail;
    private String orderId;
    private String orderTime;
    private String visitDate;
    private String visitTime;
    private String ticketType1;
    private String ticketCount1;
    private String totalPrice1;
    private String ticketType2;
    private String ticketCount2;
    private String totalPrice2;
    private String ticketType3;
    private String ticketCount3;
    private String totalPrice3;
    private String ticketType4;
    private String ticketCount4;
    private String totalPrice4;
    private String paymentStatus;

    public OrderBean(String userMail, String orderId, String orderTime, String visitDate, String visitTime, String ticketType1, String ticketCount1, String totalPrice1, String ticketType2, String ticketCount2, String totalPrice2, String ticketType3, String ticketCount3, String totalPrice3, String ticketType4, String ticketCount4, String totalPrice4, String paymentStatus) {
        this.userMail = userMail;
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.visitDate = visitDate;
        this.visitTime = visitTime;
        this.ticketType1 = ticketType1;
        this.ticketCount1 = ticketCount1;
        this.totalPrice1 = totalPrice1;
        this.ticketType2 = ticketType2;
        this.ticketCount2 = ticketCount2;
        this.totalPrice2 = totalPrice2;
        this.ticketType3 = ticketType3;
        this.ticketCount3 = ticketCount3;
        this.totalPrice3 = totalPrice3;
        this.ticketType4 = ticketType4;
        this.ticketCount4 = ticketCount4;
        this.totalPrice4 = totalPrice4;
        this.paymentStatus = paymentStatus;
    }

    public OrderBean() {
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    public String getTicketType1() {
        return ticketType1;
    }

    public void setTicketType1(String ticketType1) {
        this.ticketType1 = ticketType1;
    }

    public String getTicketCount1() {
        return ticketCount1;
    }

    public void setTicketCount1(String ticketCount1) {
        this.ticketCount1 = ticketCount1;
    }

    public String getTotalPrice1() {
        return totalPrice1;
    }

    public void setTotalPrice1(String totalPrice1) {
        this.totalPrice1 = totalPrice1;
    }

    public String getTicketType2() {
        return ticketType2;
    }

    public void setTicketType2(String ticketType2) {
        this.ticketType2 = ticketType2;
    }

    public String getTicketCount2() {
        return ticketCount2;
    }

    public void setTicketCount2(String ticketCount2) {
        this.ticketCount2 = ticketCount2;
    }

    public String getTotalPrice2() {
        return totalPrice2;
    }

    public void setTotalPrice2(String totalPrice2) {
        this.totalPrice2 = totalPrice2;
    }

    public String getTicketType3() {
        return ticketType3;
    }

    public void setTicketType3(String ticketType3) {
        this.ticketType3 = ticketType3;
    }

    public String getTicketCount3() {
        return ticketCount3;
    }

    public void setTicketCount3(String ticketCount3) {
        this.ticketCount3 = ticketCount3;
    }

    public String getTotalPrice3() {
        return totalPrice3;
    }

    public void setTotalPrice3(String totalPrice3) {
        this.totalPrice3 = totalPrice3;
    }

    public String getTicketType4() {
        return ticketType4;
    }

    public void setTicketType4(String ticketType4) {
        this.ticketType4 = ticketType4;
    }

    public String getTicketCount4() {
        return ticketCount4;
    }

    public void setTicketCount4(String ticketCount4) {
        this.ticketCount4 = ticketCount4;
    }

    public String getTotalPrice4() {
        return totalPrice4;
    }

    public void setTotalPrice4(String totalPrice4) {
        this.totalPrice4 = totalPrice4;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
