package com.jt.javatechnocrat;

/**
 * Created by daduc on 23-03-2018.
 */

public class Project {
    String name,technology;

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project() {

    }

    public Project(String name, String technology) {

        this.name = name;
        this.technology = technology;
    }
}
