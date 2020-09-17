package com.traulko.project.validator;

public interface UserValidator {
    boolean isLoginValid(String login);
    boolean isPasswordValid(String password);
}
