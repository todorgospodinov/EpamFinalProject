package com.traulko.project.util;

public class XssSecurity {
    private static final String SCRIPT_REGEX = "</?script>";
    private static final String EMPTY_LINE = "";

    private XssSecurity() {
    }

    public static String secure(String line) {
        String securedLine = line;
        if (line != null) {
            securedLine = line.replaceAll(SCRIPT_REGEX, EMPTY_LINE);
        }
        return securedLine;
    }
}
