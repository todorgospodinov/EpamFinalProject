package com.traulko.project.validator.impl;

import com.traulko.project.validator.UserValidator;

public class UserValidatorImpl implements UserValidator {
    private static final String PASSWORD_REGEX = "^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,16}$";
    //Password must contain at least one letter, at least one number, and be longer
    //than six characters and less than sixteen.
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+$";

    @Override
    public boolean isPasswordValid(String password) {
        boolean isCorrect = false;
        if (password != null) {
            isCorrect = password.matches(PASSWORD_REGEX);
        }
        return isCorrect;
    }

    @Override
    public boolean isEmailValid(String email) {
        boolean isCorrect = false;
        if (email != null) {
            isCorrect = email.matches(EMAIL_REGEX);
        }
        return isCorrect;
    }
}
