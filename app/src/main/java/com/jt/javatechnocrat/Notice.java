package com.jt.javatechnocrat;

/**
 * Created by daduc on 20-03-2018.
 */

public class Notice {
    String title;
    String date;
    String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Notice(String title, String date, String description) {

        this.title = title;
        this.date = date;
        this.description = description;
    }

    public Notice() {
    }
}
