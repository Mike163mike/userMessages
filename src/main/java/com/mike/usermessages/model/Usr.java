package com.mike.usermessages.model;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.Instant;
import java.util.Objects;

@Entity
public class Usr {

    @Id
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private Integer message;
    private Instant createTime;
    private Instant editTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
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
        Usr usr = (Usr) o;
        return Objects.equals(id, usr.id) && Objects.equals(firstName, usr.firstName) && Objects.equals(middleName, usr.middleName) && Objects.equals(lastName, usr.lastName) && Objects.equals(email, usr.email) && Objects.equals(login, usr.login) && Objects.equals(password, usr.password) && Objects.equals(message, usr.message) && Objects.equals(createTime, usr.createTime) && Objects.equals(editTime, usr.editTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName, email, login, password, message, createTime, editTime);
    }
}
