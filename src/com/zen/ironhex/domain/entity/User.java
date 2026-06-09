package com.zen.ironhex.domain.entity;

import com.zen.ironhex.domain.entity.base.BaseEntity;

public class User extends BaseEntity {
    private String username;
    private String password;

    private String salt;
    private boolean isAdmin;

    private String lastLoginDate;
    private String createDate;

    public User() {
    }

    public User(String username, String password, String salt, boolean isAdmin, String lastLoginDate, String createDate) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.isAdmin = isAdmin;
        this.lastLoginDate = lastLoginDate;
        this.createDate = createDate;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}