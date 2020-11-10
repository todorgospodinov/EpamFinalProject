package com.traulko.project.validator;

public class ProjectValidator {
    public static boolean isCorrectIntValue(String id) {
        boolean result = true;
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }
}
