package com.traulko.project.builder;

import com.traulko.project.entity.User;

public class UserBuilder {
    private Integer userId;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private User.Role role;
    private User.Status status;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }

    public void setStatus(User.Status status) {
        this.status = status;
    }

    public User getUser() {
        return new User(userId, email, name, surname, patronymic, role, status);
    }
}
