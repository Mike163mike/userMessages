package com.mike.usermessages.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;
import java.util.Objects;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String message;
    private Integer user_id;
    private Instant createTime = Instant.now();
    private Instant editTime = Instant.now();

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

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getUser_id() {
        return user_id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(id, message1.id) && Objects.equals(message, message1.message) && Objects.equals(user_id,
                message1.user_id) && Objects.equals(createTime, message1.createTime) && Objects.equals(editTime,
                message1.editTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, user_id, createTime, editTime);
    }
}
