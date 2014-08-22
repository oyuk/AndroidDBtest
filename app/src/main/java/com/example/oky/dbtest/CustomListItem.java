package com.example.oky.dbtest;

/**
 * Created by oky on 2014/08/22.
 */
public class CustomListItem {

    private String title;
    private String description;

    public CustomListItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
