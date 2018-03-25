package com.example.wenwei.diycode.fragment.bean;


import java.io.Serializable;

public class SitesItem implements Serializable {

    private String name;

    public SitesItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
