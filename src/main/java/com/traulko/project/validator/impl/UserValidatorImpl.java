package com.traulko.project.validator.impl;

import com.traulko.project.validator.UserValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidatorImpl implements UserValidator {
    private static final String LOGIN_REGEX = "^[a-z0-9_-]{6,16}$";
    //letters hyphens underscores 6-16
    private static final String PASSWORD_REGEX = "^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,16}$";
    //Password must contain at least one letter, at least one number, and be longer
    //than six characters and less than sixteen.

    @Override
    public boolean isLoginValid(String login) {
        boolean isCorrect = false;
        if (login != null) {
            isCorrect = login.matches(LOGIN_REGEX);
        }
        return isCorrect;
    }

    @Override
    public boolean isPasswordValid(String password) {
        boolean isCorrect = false;
        if (password != null) {
            isCorrect = password.matches(PASSWORD_REGEX);
        }
        return isCorrect;
    }
}
