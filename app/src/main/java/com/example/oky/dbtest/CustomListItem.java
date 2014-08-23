package com.example.oky.dbtest;

import java.io.Serializable;

/**
 * Created by oky on 2014/08/22.
 */
public class CustomListItem implements Serializable{

    private long id;
    private String title;
    private String content;

    public CustomListItem(int id,String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public CustomListItem(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
