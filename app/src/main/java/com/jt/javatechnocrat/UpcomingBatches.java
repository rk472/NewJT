package com.jt.javatechnocrat;

/**
 * Created by daduc on 20-03-2018.
 */

public class UpcomingBatches {
    String name,date,timing;

    public UpcomingBatches(String name, String date, String timing) {
        this.name = name;
        this.date = date;
        this.timing = timing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public UpcomingBatches() {
    }
}
