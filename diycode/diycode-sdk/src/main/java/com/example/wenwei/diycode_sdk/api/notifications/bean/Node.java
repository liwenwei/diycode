package com.example.wenwei.diycode_sdk.api.notifications.bean;

import java.io.Serializable;


public class Node implements Serializable {

    private int id;

    private String name;

    private int topics_count;

    private String summary;

    private int section_id;

    private int sort;

    private String section_name;

    private String updated_at;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setTopics_count(int topics_count) {
        this.topics_count = topics_count;
    }

    public int getTopics_count() {
        return this.topics_count;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }

    public int getSection_id() {
        return this.section_id;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getSort() {
        return this.sort;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public String getSection_name() {
        return this.section_name;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUpdated_at() {
        return this.updated_at;
    }

}
