package edu.ccrm.domain;

public class InvalidCourseCreditsException extends RuntimeException {
    public InvalidCourseCreditsException(String message) {
        super(message);
    }

    public InvalidCourseCreditsException(String courseCode, int credits, String reason) {
        super(String.format("Invalid credits for course '%s' (%d credits): %s", 
            courseCode, credits, reason));
    }
}
