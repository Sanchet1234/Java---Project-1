package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.DuplicateEnrollmentException;
import edu.ccrm.domain.MaxCreditLimitExceededException;
import java.util.List;

public interface EnrollmentService {
    void enrollStudent(Student student, Course course) 
        throws DuplicateEnrollmentException, MaxCreditLimitExceededException;
    void unenrollStudent(Student student, Course course);
    List<Enrollment> getEnrollmentsByStudent(Student student);
    List<Enrollment> getEnrollmentsByCourse(Course course);
    void recordMarks(Student student, Course course, int marks);
}
