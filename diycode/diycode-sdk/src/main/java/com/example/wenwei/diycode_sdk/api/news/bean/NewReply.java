package com.example.wenwei.diycode_sdk.api.news.bean;


import com.example.wenwei.diycode_sdk.api.base.bean.Abilities;
import com.example.wenwei.diycode_sdk.api.user.bean.User;

import java.io.Serializable;

public class NewReply implements Serializable {

    /**
     * id : 395
     * body_html : <p>最近在做一个新的产品，然后想起很早之前 coding 的这个文章，整个流程和思路都很有借鉴意义。</p>
     * created_at : 2017-02-26T23:42:34.758+08:00
     * updated_at : 2017-02-26T23:42:34.758+08:00
     * deleted : false
     * news_id : 2037
     * user : {"id":1,"login":"jixiaohua","name":"寂小桦","avatar_url":"https://diycode.b0.upaiyun.com/user/large_avatar/2.jpg"}
     * likes_count : 0
     * abilities : {"update":false,"destroy":false}
     */

    private int id;                 // 回复 的 id
    private String body_html;       // 回复内容详情(HTML)
    private String created_at;      // 创建时间
    private String updated_at;      // 更新时间
    private boolean deleted;        // 是否已经删除
    private int news_id;            // new 的 id
    private User user;              // 创建该回复的用户信息
    private int likes_count;        // 喜欢的人数
    private Abilities abilities;    // 当前用户所拥有的权限


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody_html() {
        return body_html;
    }

    public void setBody_html(String body_html) {
        this.body_html = body_html;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public Abilities getAbilities() {
        return abilities;
    }

    public void setAbilities(Abilities abilities) {
        this.abilities = abilities;
    }

}
