package com.example.wenwei.diycode_sdk.api.notifications.bean;

import com.example.wenwei.diycode_sdk.api.topic.bean.Topic;
import com.example.wenwei.diycode_sdk.api.user.bean.User;

import java.io.Serializable;

public class Notification implements Serializable {

    private int id;                 // notification id
    private String type;            // 类型
    private Boolean read;           // 是否已读
    private User actor;             // 相关人员
    private String mention_type;    // 提及类型
    private Reply mention;          // 提及详情
    private Topic topic;            // topic
    private Reply reply;            // 回复
    private Node node;              // 节点变更
    private String created_at;      // 创建时间
    private String updated_at;      // 更新时间

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Boolean getRead() {
        return this.read;
    }

    public void setActor(User actor) {
        this.actor = actor;
    }

    public User getActor() {
        return this.actor;
    }

    public void setMention_type(String mention_type) {
        this.mention_type = mention_type;
    }

    public String getMention_type() {
        return this.mention_type;
    }

    public void setMention(Reply mention) {
        this.mention = mention;
    }

    public Reply getMention() {
        return this.mention;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Topic getTopic() {
        return this.topic;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

    public Reply getReply() {
        return this.reply;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Node getNode() {
        return this.node;
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
}
