package edu.ccrm.domain;

import java.time.LocalDate;

public class Enrollment {
    private Student student;
    private Course course;
    private LocalDate enrollmentDate;
    private Grade grade;
    private int marks;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = LocalDate.now();
        this.grade = null;
        this.marks = -1;
    }

    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public Grade getGrade() { return grade; }
    public int getMarks() { return marks; }

    public void recordMarks(int marks) {
        this.marks = marks;
        this.grade = computeGrade(marks);
    }

    private Grade computeGrade(int marks) {
        if (marks >= 90) return Grade.S;
        else if (marks >= 80) return Grade.A;
        else if (marks >= 70) return Grade.B;
        else if (marks >= 60) return Grade.C;
        else if (marks >= 50) return Grade.D;
        else if (marks >= 40) return Grade.E;
        else return Grade.F;
    }

    @Override
    public String toString() {
        return String.format("Enrollment: %s in %s on %s, Marks: %d, Grade: %s", student.getFullName(), course.getCode(), enrollmentDate, marks, grade);
    }
}
