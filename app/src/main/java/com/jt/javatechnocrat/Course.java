package com.jt.javatechnocrat;

/**
 * Created by daduc on 20-03-2018.
 */

public class Course {
    String name,price,image_url,duration,syllabus;

    public Course() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public Course(String name, String price, String image_url, String duration, String syllabus) {

        this.name = name;
        this.price = price;
        this.image_url = image_url;
        this.duration = duration;
        this.syllabus = syllabus;
    }
}
