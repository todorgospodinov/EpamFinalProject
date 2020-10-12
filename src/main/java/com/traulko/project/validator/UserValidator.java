package com.traulko.project.validator;

public interface UserValidator {
    boolean isPasswordValid(String password);
    boolean isEmailValid(String email);
}
