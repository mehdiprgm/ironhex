package com.zen.ironhex.domain.entity;

import com.zen.ironhex.domain.entity.base.BaseEntity;

public class Log extends BaseEntity {
    private int userId;

    private String message;

    private int type;
    private int module;

    private String date;
    private String time;

    public Log() {
    }

    public Log(int userId, String message, int type, int module, String date, String time) {
        this.userId = userId;
        this.message = message;
        this.type = type;
        this.module = module;
        this.date = date;
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getModule() {
        return module;
    }

    public void setModule(int module) {
        this.module = module;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}