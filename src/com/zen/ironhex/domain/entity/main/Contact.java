package com.zen.ironhex.domain.entity.main;

import com.zen.ironhex.domain.entity.base.BaseEntity;

public class Contact extends BaseEntity {
    private int userId;

    private String name;

    private String phoneNumber;
    private String extraInformation;

    private String createDate;

    public Contact() {

    }

    public Contact(int userId, String name, String phoneNumber, String extraInformation, String createDate) {
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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