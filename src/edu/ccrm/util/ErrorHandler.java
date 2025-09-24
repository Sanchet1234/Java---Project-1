package edu.ccrm.util;

import edu.ccrm.domain.*;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Centralized error handling for CCRM application
 */
public class ErrorHandler {
    private static final Logger logger = Logger.getLogger(ErrorHandler.class.getName());

    public static void handleException(Exception e) {
        String errorMessage;
        
        if (e instanceof InvalidRegistrationNumberException) {
            errorMessage = "⚠️ Registration Number Error: " + e.getMessage();
            logger.log(Level.WARNING, errorMessage);
        }
        else if (e instanceof InvalidCourseCreditsException) {
            errorMessage = "⚠️ Course Credits Error: " + e.getMessage();
            logger.log(Level.WARNING, errorMessage);
        }
        else if (e instanceof InvalidSemesterException) {
            errorMessage = "⚠️ Invalid Semester: " + e.getMessage();
            logger.log(Level.WARNING, errorMessage);
        }
        else if (e instanceof DuplicateEnrollmentException) {
            errorMessage = "⚠️ Enrollment Error: " + e.getMessage();
            logger.log(Level.WARNING, errorMessage);
        }
        else if (e instanceof MaxCreditLimitExceededException) {
            errorMessage = "⚠️ Credit Limit Exceeded: " + e.getMessage();
            logger.log(Level.WARNING, errorMessage);
        }
        else {
            errorMessage = "❌ System Error: " + e.getMessage();
            logger.log(Level.SEVERE, "Unexpected error", e);
        }
        
        System.err.println(errorMessage);
        
        // Additional context for debugging
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("Error context: " + e.getClass().getName());
            logger.fine("Stack trace: ");
            for (StackTraceElement element : e.getStackTrace()) {
                logger.fine("  " + element.toString());
            }
        }
    }

    public static void validateStudentData(String id, String regNo, String fullName, String email) {
        StringBuilder errors = new StringBuilder();

        if (id == null || id.trim().isEmpty()) {
            errors.append("Student ID is required\n");
        }
        if (!Validator.isValidRegNo(regNo)) {
            errors.append("Invalid registration number format (e.g., CS123456)\n");
        }
        if (fullName == null || fullName.trim().isEmpty()) {
            errors.append("Full name is required\n");
        }
        if (!Validator.isValidEmail(email)) {
            errors.append("Invalid email format\n");
        }

        if (errors.length() > 0) {
            throw new IllegalArgumentException("Validation errors:\n" + errors.toString());
        }
    }

    public static void validateCourseData(String code, String title, int credits, String department) {
        StringBuilder errors = new StringBuilder();

        if (code == null || !code.matches("^[A-Z]{2,3}\\d{3}$")) {
            errors.append("Invalid course code format (e.g., CS101)\n");
        }
        if (title == null || title.trim().isEmpty()) {
            errors.append("Course title is required\n");
        }
        if (credits < 1 || credits > 6) {
            errors.append("Credits must be between 1 and 6\n");
        }
        if (department == null || department.trim().isEmpty()) {
            errors.append("Department is required\n");
        }

        if (errors.length() > 0) {
            throw new IllegalArgumentException("Validation errors:\n" + errors.toString());
        }
    }
}
