package edu.ccrm.domain;

public class InvalidRegistrationNumberException extends RuntimeException {
    public InvalidRegistrationNumberException(String message) {
        super(message);
    }

    public InvalidRegistrationNumberException(String regNo, String reason) {
        super(String.format("Invalid registration number '%s': %s", regNo, reason));
    }
}
