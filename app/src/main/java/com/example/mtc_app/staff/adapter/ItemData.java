package com.example.mtc_app.staff.adapter;

public class ItemData {
    private String orderId;
    private String customerName;
    private String status;
    private String sampleName;

    // Empty constructor for Firestore deserialization
    public ItemData(String orderId, String title, String subtitle, int ic_placeholder) {}

    public ItemData(String orderId, String customerName, String status, String sampleName) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.status = status;
        this.sampleName = sampleName;
    }

    // Getter for orderId
    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getStatus() {
        return status;
    }

    public String getSampleName() {
        return sampleName;
    }
}
