package com.traulko.project.validator;

public class UserValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,16}$";
    //Password must contain at least one letter, at least one number, and be longer
    //than six characters and less than sixteen.
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+$";
    private static final String NAME_REGEX = "^\\p{L}{2,25}$";
    private static final String PRICE_REGEX = "^[1-9]\\d{0,4}(\\.\\d{0,2})?$";

    // TODO: 12.11.2020

    public static boolean isIdValid(String id) {
        return isStringCorrect(id, ID_REGEX);
    }

    public static boolean isPasswordValid(String password) {
        return isStringCorrect(password, PASSWORD_REGEX) && !password.isBlank();
    }

    public static boolean isEmailValid(String email) {
        return isStringCorrect(email, EMAIL_REGEX) && !email.isBlank();
    }

    public static boolean isNameValid(String name) {
        return isStringCorrect(name, NAME_REGEX) && !name.isBlank();
    }

    public static boolean isPriceValid(String price) {
        return isStringCorrect(price, PRICE_REGEX);
    }

    private static boolean isStringCorrect(String line, String regex) {
        boolean result = false;
        if (line != null) {
            result = line.matches(regex);
        }
        return result;
    }
}
