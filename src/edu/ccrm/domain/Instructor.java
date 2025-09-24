package edu.ccrm.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Instructor extends Person {
    private String department;
    private Set<String> assignedCourses;
    private String specialization;
    private boolean isAvailable;

    public Instructor(String id, String fullName, String email, String department) {
        super(id, fullName, email);
        this.department = department;
        this.assignedCourses = new HashSet<>();
        this.isAvailable = true;
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    
    public Set<String> getAssignedCourses() { return Collections.unmodifiableSet(assignedCourses); }
    
    public void assignCourse(String courseCode) {
        if (isAvailable) {
            assignedCourses.add(courseCode);
        } else {
            throw new IllegalStateException("Instructor is not available for course assignment");
        }
    }
    
    public void unassignCourse(String courseCode) {
        assignedCourses.remove(courseCode);
    }
    
    public int getTeachingLoad() {
        return assignedCourses.size();
    }

    @Override
    public String getProfile() {
        return String.format(
            "Instructor Profile:\nName: %s\nEmail: %s\nDepartment: %s\nSpecialization: %s\n" +
            "Status: %s\nAvailability: %s\nTeaching Load: %d courses\nAssigned Courses: %s",
            getFullName(), getEmail(), department, 
            specialization != null ? specialization : "Not specified",
            isActive() ? "Active" : "Inactive",
            isAvailable ? "Available" : "Not Available",
            getTeachingLoad(),
            assignedCourses
        );
    }

    @Override
    public String toString() {
        return String.format("Instructor: %s (%s, %s)", 
            getFullName(), 
            department,
            specialization != null ? specialization : "No specialization"
        );
    }
}
