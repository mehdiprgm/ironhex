package com.zen.ironhex.domain.entity.main;

import com.zen.ironhex.domain.entity.base.BaseEntity;


public class Password extends BaseEntity {
    private int userId;

    private String name;
    private String password;

    private String length;
    private int strength;

    private String createDate;

    public Password() {
    }

    public Password(int userId, String name, String password, String length, int strength, String createDate) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.length = length;
        this.strength = strength;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}