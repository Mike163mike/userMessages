package com.mike.usermessages.service.dto;

import java.time.Instant;


public class MessageResponseDto {

    private Integer id;
    private String message;
    private Integer user_id;
    private Instant createTime;
    private Instant editTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getEditTime() {
        return editTime;
    }

    public void setEditTime(Instant editTime) {
        this.editTime = editTime;
    }
}
