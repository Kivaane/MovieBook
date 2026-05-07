package com.cinema.util;

import java.util.Base64;

public class PasswordHasher {
    /**
     * Simple password hashing using Base64 for academic demonstration.
     * In a production environment, use BCrypt or Argon2.
     */
    public static String hash(String password) {
        if (password == null) return null;
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public static boolean check(String password, String hashed) {
        if (password == null || hashed == null) return false;
        return hash(password).equals(hashed);
    }
}
