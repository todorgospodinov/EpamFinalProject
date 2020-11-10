package com.traulko.project.validator;

public class ProjectValidator {
    public static boolean isCorrectIntValue(String value) {
        boolean result = true;
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    public static boolean isCorrectDoubleValue(String value) {
        boolean result = true;
        try {
            Double.parseDouble(value);
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }
}
