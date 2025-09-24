package edu.ccrm.domain;

public class InvalidSemesterException extends RuntimeException {
    public InvalidSemesterException(String invalidSemester, boolean isDetailedMessage) {
        super(isDetailedMessage ? 
            String.format("Invalid semester '%s'. Must be one of: SPRING, SUMMER, FALL", invalidSemester) :
            invalidSemester);
    }
}
