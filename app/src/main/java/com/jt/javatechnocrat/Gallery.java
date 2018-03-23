package com.jt.javatechnocrat;

/**
 * Created by ramak on 21-Mar-18.
 */

public class Gallery {
    String url;
    String name;
    public Gallery() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gallery(String url, String name) {

        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
