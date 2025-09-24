package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends Person {
    private static final long serialVersionUID = 1L;
    private String regNo;
    private List<String> enrolledCourses;
    private LocalDate enrollmentDate;
    private double gpa;
    private Map<String, Grade> grades;

    public Student(String id, String regNo, String fullName, String email) {
        super(id, fullName, email);
        this.regNo = regNo;
        this.enrolledCourses = new ArrayList<>();
        this.enrollmentDate = LocalDate.now();
        this.grades = new HashMap<>();
        this.gpa = 0.0;
    }

    public String getRegNo() { return regNo; }
    public List<String> getEnrolledCourses() { return enrolledCourses; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public double getGpa() { return gpa; }
    public Map<String, Grade> getGrades() { return Collections.unmodifiableMap(grades); }

    public void enrollCourse(String courseCode) {
        if (!enrolledCourses.contains(courseCode)) {
            enrolledCourses.add(courseCode);
        }
    }

    public void unenrollCourse(String courseCode) {
        enrolledCourses.remove(courseCode);
        grades.remove(courseCode);
        recalculateGPA();
    }

    public void setGrade(String courseCode, Grade grade) {
        if (enrolledCourses.contains(courseCode)) {
            grades.put(courseCode, grade);
            recalculateGPA();
        }
    }

    private void recalculateGPA() {
        if (grades.isEmpty()) {
            this.gpa = 0.0;
            return;
        }
        double totalPoints = 0.0;
        for (Grade grade : grades.values()) {
            totalPoints += grade.getGradePoint();
        }
        this.gpa = totalPoints / grades.size();
    }

    @Override
    public String getProfile() {
        return String.format("Student Profile:\nRegNo: %s\nName: %s\nEmail: %s\nStatus: %s\nEnrolled Courses: %s\nEnrollment Date: %s", regNo, getFullName(), getEmail(), isActive() ? "Active" : "Inactive", enrolledCourses, enrollmentDate);
    }

    @Override
    public String toString() {
        return String.format("Student: %s (%s)", getFullName(), regNo);
    }
}
