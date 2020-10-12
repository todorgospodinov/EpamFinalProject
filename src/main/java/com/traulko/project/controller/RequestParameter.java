package com.traulko.project.controller;

public class RequestParameter {
    public static final String COMMAND_NAME = "commandName";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String ERROR_LOGIN_PASSWORD_MESSAGE = "errorLoginPasswordMessage";
    public static final String REGISTER_PATRONYMIC = "register_patronymic";
    public static final String REGISTER_PASSWORD = "register_password";
    public static final String REGISTER_PASSWORD_REPEAT = "register_password_repeat";
    public static final String ERROR_REGISTER_MESSAGE = "errorRegisterMessage";
    public static final String REGISTER_EMAIL = "register_email";
    public static final String REGISTER_NAME = "register_name";
    public static final String REGISTER_SURNAME = "register_surname";
    public static final String MAIL_MESSAGE = "Register in online store\nTo make your account enable, follow this link";
    public static final String MAIL_MESSAGE_LINK = "http://localhost:8080/EpamFinalProject_1_0_SNAPSHOT_war/" +
            "controller?commandName=activate_account&email=";

    private RequestParameter() {
    }
}
