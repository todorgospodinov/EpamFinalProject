package com.traulko.project.entity;

public class User {
    private String login;
    private String password;
    private boolean isEnable;

    public User(String login, String password, boolean isEnable) {
        this.login = login;
        this.password = password;
        this.isEnable = isEnable;
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

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        if (login != null ? !login.equals(user.login) : user.login != null) {
            return false;
        }
        if (password != null ? !password.equals(user.password) : user.password != null) {
            return false;
        }
        return isEnable == user.isEnable;
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result += prime * (login != null ? login.hashCode() : 0);
        result += prime * (password != null ? password.hashCode() : 0);
        result += prime * Boolean.hashCode(isEnable);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", isEnable=").append(isEnable);
        sb.append('}');
        return sb.toString();
    }
}
