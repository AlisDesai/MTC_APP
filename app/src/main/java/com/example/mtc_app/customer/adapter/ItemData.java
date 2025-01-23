package com.example.mtc_app.customer.adapter;

public class ItemData {

    private String title;
    private String subtitle;
    private int iconResId;

    public ItemData(String title, String subtitle, int iconResId) {
        this.title = title;
        this.subtitle = subtitle;
        this.iconResId = iconResId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }
}
