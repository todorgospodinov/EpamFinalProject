package com.traulko.project.validator;

public interface UserValidator {
    boolean isPasswordValid(String password);

    boolean isNameValid(String firstName);

    boolean isEmailValid(String email);
}
