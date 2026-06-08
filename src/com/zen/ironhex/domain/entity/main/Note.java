package com.zen.ironhex.domain.entity.main;

import com.zen.ironhex.domain.entity.base.BaseEntity;


public class Note extends BaseEntity {
    private int userId;

    private String name;

    private String content;
    private String createDate;

    public Note() {

    }

    public Note(int userId, String name, String content, String createDate) {
        this.userId = userId;
        this.name = name;
        this.content = content;
        this.createDate = createDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}