package edu.ccrm.service;

import edu.ccrm.domain.Course;
import java.util.List;

public interface CourseService {
    void addCourse(Course course);
    List<Course> listCourses();
    Course getCourseByCode(String code);
    void updateCourse(Course course);
    void deactivateCourse(String code);
    
    // Search and filter methods using Stream API
    List<Course> searchByInstructor(String instructorId);
    List<Course> searchByDepartment(String department);
    List<Course> searchBySemester(String semester);
    
    // Advanced search with multiple criteria
    List<Course> searchCourses(String department, String semester, Integer minCredits, Integer maxCredits);
    
    // Statistical queries using streams
    double getAverageCredits();
    int getTotalCoursesByDepartment(String department);
    List<Course> getCoursesOrderedByCredits();
}
