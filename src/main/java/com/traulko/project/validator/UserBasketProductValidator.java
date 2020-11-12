package com.traulko.project.validator;

public class UserBasketProductValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";

    public static boolean isIdValid(String id) {
        return isStringCorrect(id, ID_REGEX);
    }

    private static boolean isStringCorrect(String line, String regex) {
        boolean result = false;
        if (line != null) {
            result = line.matches(regex);
        }
        return result;
    }
}
