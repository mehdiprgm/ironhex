package com.zen.ironhex.domain.entity.main;

import com.zen.ironhex.domain.entity.base.BaseEntity;

public class Account extends BaseEntity {
    private int userId;

    private String name;

    private String username;
    private String password;

    private String extraInformation;
    private String createDate;

    public Account() {
    }

    public Account(int userId, String name, String username, String password, String extraInformation, String createDate) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.extraInformation = extraInformation;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExtraInformation() {
        return extraInformation;
    }

    public void setExtraInformation(String extraInformation) {
        this.extraInformation = extraInformation;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}