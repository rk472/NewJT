package com.jt.javatechnocrat;

/**
 * Created by daduc on 20-03-2018.
 */

public class Team {
    String name;
    String image_url;
    String desc;

    public Team(String name, String image_url, String desc, String subject) {
        this.name = name;
        this.image_url = image_url;
        this.desc = desc;
        this.subject = subject;
    }

    String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Team() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
