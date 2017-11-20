package com.example.wenwei.diycode_sdk.api.notifications.bean;

import com.example.wenwei.diycode_sdk.api.base.bean.Abilities;
import com.example.wenwei.diycode_sdk.api.user.bean.User;

import java.io.Serializable;

public class Reply implements Serializable {

    private int id;
    private String body_html;
    private String created_at;
    private String updated_at;
    private boolean deleted;
    private int topic_id;
    private User user;
    private int likes_count;
    private Abilities abilities;
    private String body;
    private String topic_title;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setBody_html(String body_html) {
        this.body_html = body_html;
    }

    public String getBody_html() {
        return this.body_html;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUpdated_at() {
        return this.updated_at;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean getDeleted() {
        return this.deleted;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public int getTopic_id() {
        return this.topic_id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public int getLikes_count() {
        return this.likes_count;
    }

    public void setAbilities(Abilities abilities) {
        this.abilities = abilities;
    }

    public Abilities getAbilities() {
        return this.abilities;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return this.body;
    }

    public void setTopic_title(String topic_title) {
        this.topic_title = topic_title;
    }

    public String getTopic_title() {
        return this.topic_title;
    }

}
