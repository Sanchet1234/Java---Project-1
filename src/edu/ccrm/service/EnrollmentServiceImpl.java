package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.DuplicateEnrollmentException;
import edu.ccrm.domain.MaxCreditLimitExceededException;
import edu.ccrm.config.EnrollmentConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EnrollmentServiceImpl implements EnrollmentService {
    private final List<Enrollment> enrollments = new ArrayList<>();

    @Override
    public void enrollStudent(Student student, Course course) 
        throws DuplicateEnrollmentException, MaxCreditLimitExceededException {
        boolean exists = enrollments.stream()
            .anyMatch(e -> e.getStudent().equals(student) && e.getCourse().equals(course));
        if (exists) {
            throw new DuplicateEnrollmentException("Student is already enrolled in this course.");
        }

        // Calculate current semester credits
        int currentCredits = enrollments.stream()
            .filter(e -> e.getStudent().equals(student))
            .filter(e -> e.getCourse().getSemester() == course.getSemester())
            .mapToInt(e -> e.getCourse().getCredits())
            .sum();

        // Validate credit limits based on student's GPA
        String validationError = EnrollmentConfig.validateCreditLoad(
            currentCredits, 
            course.getCredits(),
            student.getGpa()
        );

        if (validationError != null) {
            throw new MaxCreditLimitExceededException(validationError);
        }

        // Ensure minimum credits per semester
        if (currentCredits == 0 && course.getCredits() < EnrollmentConfig.getMinCreditsPerSemester()) {
            throw new IllegalArgumentException(
                String.format("Must enroll in at least %d credits for first course in semester",
                EnrollmentConfig.getMinCreditsPerSemester())
            );
        }

        enrollments.add(new Enrollment(student, course));
        student.enrollCourse(course.getCode());
    }

    @Override
    public void unenrollStudent(Student student, Course course) {
        enrollments.removeIf(e -> e.getStudent().equals(student) && e.getCourse().equals(course));
        student.unenrollCourse(course.getCode());
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudent(Student student) {
        return enrollments.stream()
            .filter(e -> e.getStudent().equals(student))
            .collect(Collectors.toList());
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourse(Course course) {
        return enrollments.stream()
            .filter(e -> e.getCourse().equals(course))
            .collect(Collectors.toList());
    }

    @Override
    public void recordMarks(Student student, Course course, int marks) {
        enrollments.stream()
            .filter(e -> e.getStudent().equals(student) && e.getCourse().equals(course))
            .findFirst()
            .ifPresent(e -> e.recordMarks(marks));
    }
}
