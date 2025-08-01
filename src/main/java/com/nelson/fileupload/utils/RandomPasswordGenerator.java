package com.nelson.fileupload.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class RandomPasswordGenerator {

    private final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String LOWER = UPPER.toLowerCase();
    private final String DIGITS = "0123456789";
    private final String SPECIAL_CHARS = "!@#$%^&*()_+{}[]";

    private final String ALL_CHARS = UPPER + LOWER + DIGITS + SPECIAL_CHARS;

    private SecureRandom random = new SecureRandom();

    public String generateRandomPassword(int length) {
        StringBuilder password = new StringBuilder(length);

        // At least one uppercase letter
        password.append(UPPER.charAt(random.nextInt(UPPER.length())));
        // At least one lowercase letter
        password.append(LOWER.charAt(random.nextInt(LOWER.length())));
        // At least one digit
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        // At least one special character
        password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

        // Remaining characters randomly selected from all characters
        for (int i = 4; i < length; i++) {
            password.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }

        return password.toString();
    }
}