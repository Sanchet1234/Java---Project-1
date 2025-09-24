package edu.ccrm.config;

public class EnrollmentConfig {
    private static final int DEFAULT_MAX_CREDITS = 30;
    private static final int MIN_CREDITS_PER_SEMESTER = 12;
    private static final double HIGH_GPA_THRESHOLD = 8.5;
    private static final int BONUS_CREDITS = 3;

    public static int getMaxCreditsForStudent(double gpa) {
        // High performing students can take extra credits
        if (gpa >= HIGH_GPA_THRESHOLD) {
            return DEFAULT_MAX_CREDITS + BONUS_CREDITS;
        }
        return DEFAULT_MAX_CREDITS;
    }

    public static int getMinCreditsPerSemester() {
        return MIN_CREDITS_PER_SEMESTER;
    }

    public static String validateCreditLoad(int currentCredits, int newCredits, double gpa) {
        int maxCredits = getMaxCreditsForStudent(gpa);
        if (currentCredits + newCredits > maxCredits) {
            return String.format(
                "Credit limit exceeded. Maximum allowed: %d, Attempted: %d",
                maxCredits, currentCredits + newCredits
            );
        }
        return null; // null means validation passed
    }
}
