package edu.ccrm.domain;

import java.util.Objects;

public final class CourseCode {
    private final String value;

    private static final String COURSE_CODE_PATTERN = "^[A-Z]{2,4}\\d{3,4}$";

    public CourseCode(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be null or empty");
        }
        String normalized = value.trim().toUpperCase();
        if (!normalized.matches(COURSE_CODE_PATTERN)) {
            throw new IllegalArgumentException(
                "Invalid course code format. Must be 2-4 letters followed by 3-4 digits (e.g., CS101, MATH1001)"
            );
        }
        this.value = normalized;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseCode that = (CourseCode) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
