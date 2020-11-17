package com.traulko.project.validator;

public class ProductValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String TITLE_REGEX = "^\\p{L}[^<>]{2,25}$";
    private static final String DESCRIPTION_REGEX = "^\\p{L}[^<>]{1,1000}$";
    private static final String PRICE_REGEX = "^[0-9]\\d{0,4}(\\.\\d{0,2})?$";

    public static boolean isIdValid(String id) {
        return isStringCorrect(id, ID_REGEX);
    }

    public static boolean isTitleValid(String title) {
        return isStringCorrect(title, TITLE_REGEX) && !title.isBlank();
    }

    public static boolean isDescriptionValid(String description) {
        return isStringCorrect(description, DESCRIPTION_REGEX) && !description.isBlank();
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
