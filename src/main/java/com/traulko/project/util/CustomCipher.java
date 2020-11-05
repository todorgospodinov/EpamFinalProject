package com.traulko.project.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CustomCipher {
    public static String encrypt(String string) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        byte[] data = messageDigest.digest(string.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            stringBuilder.append(String.format("%02x", data[i]));
        }
        return stringBuilder.toString();
    }
}
