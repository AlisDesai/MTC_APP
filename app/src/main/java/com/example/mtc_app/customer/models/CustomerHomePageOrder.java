package com.example.mtc_app.customer.models;

public class CustomerHomePageOrder {
    private String status, dispatchMode, date, segment;
    private int price;

    public CustomerHomePageOrder() { } // Required for Firestore

    public CustomerHomePageOrder(String status, String dispatchMode, String date, String segment , int price) {
        this.status = status;
        this.dispatchMode = dispatchMode;
        this.date = date;
        this.segment = segment;
        this.price = price;
    }


    public String getStatus() { return status; }
    public String getDispatchMode() { return dispatchMode; }
    public String getDate() { return date; }
    public String getSegment() { return segment; }

    public int getPrice() { return price; }
}