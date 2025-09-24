package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
// ...existing code...
import java.util.List;

public class TranscriptServiceImpl implements TranscriptService {
    private final EnrollmentService enrollmentService;

    public TranscriptServiceImpl(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @Override
    public String generateTranscript(Student student) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(student);
        StringBuilder sb = new StringBuilder();
        sb.append("Transcript for ").append(student.getFullName()).append(":\n");
        for (Enrollment e : enrollments) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public double computeGPA(Student student) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(student);
        int totalCredits = 0;
        int totalPoints = 0;
        for (Enrollment e : enrollments) {
            if (e.getGrade() != null) {
                totalCredits += e.getCourse().getCredits();
                totalPoints += e.getCourse().getCredits() * e.getGrade().getGradePoint();
            }
        }
        return totalCredits == 0 ? 0.0 : (double) totalPoints / totalCredits;
    }
}
