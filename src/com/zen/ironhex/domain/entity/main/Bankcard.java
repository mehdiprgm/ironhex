package com.zen.ironhex.domain.entity.main;

import com.zen.ironhex.domain.entity.base.BaseEntity;


public class Bankcard extends BaseEntity {
    private int userId;

    private String name;

    private String cardNumber;
    private String accountNumber;

    private String cvv2;
    private String expireDate;

    private String password;
    private String createDate;

    public Bankcard() {

    }

    public Bankcard(int userId, String name, String cardNumber, String accountNumber, String cvv2, String expireDate, String password, String createDate) {
        this.userId = userId;
        this.name = name;
        this.cardNumber = cardNumber;
        this.accountNumber = accountNumber;
        this.cvv2 = cvv2;
        this.expireDate = expireDate;
        this.password = password;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}