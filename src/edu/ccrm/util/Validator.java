package edu.ccrm.util;

public class Validator {
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
    public static boolean isValidRegNo(String regNo) {
        return regNo != null && regNo.matches("^[A-Z]{2,3}\\d{6}$");
    }
    public static boolean isNonEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
