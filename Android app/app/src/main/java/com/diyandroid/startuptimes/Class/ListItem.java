package com.diyandroid.startuptimes.Class;

public class ListItem {

    private String title, timestamp;
    private String description, featured_image;

    public Integer getTruthness() {
        return truthness;
    }

    private Integer truthness;

    public ListItem() {
        //required for firebase
    }

    public String getTitle() {
        return title;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    public String getFeatured_image() {
        return featured_image;
    }
}
