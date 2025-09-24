package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Instructor;
import edu.ccrm.domain.Semester;
import edu.ccrm.domain.CourseBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseServiceImpl implements CourseService {
    private final List<Course> courses = new ArrayList<>();

    @Override
    public void addCourse(Course course) {
        // Example usage of Builder pattern
        Course builtCourse = new CourseBuilder()
            .setCode(course.getCode())
            .setTitle(course.getTitle())
            .setCredits(course.getCredits())
            .setInstructor(course.getInstructor())
            .setSemester(course.getSemester())
            .setDepartment(course.getDepartment())
            .build();
        courses.add(builtCourse);
    }

    @Override
    public List<Course> listCourses() {
        return new ArrayList<>(courses);
    }

    @Override
    public Course getCourseByCode(String code) {
        return courses.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateCourse(Course course) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCode().equalsIgnoreCase(course.getCode())) {
                courses.set(i, course);
                return;
            }
        }
    }

    @Override
    public List<Course> searchByInstructor(String instructorId) {
        return courses.stream()
            .filter(course -> course.getInstructor() != null &&
                    course.getInstructor().getId().equals(instructorId))
            .collect(Collectors.toList());
    }

    @Override
    public List<Course> searchByDepartment(String department) {
        return courses.stream()
            .filter(course -> course.getDepartment().equalsIgnoreCase(department))
            .collect(Collectors.toList());
    }

    @Override
    public List<Course> searchBySemester(String semester) {
        return courses.stream()
            .filter(course -> course.getSemester() != null &&
                    course.getSemester().toString().equalsIgnoreCase(semester))
            .collect(Collectors.toList());
    }

    @Override
    public List<Course> searchCourses(String department, String semester, 
            Integer minCredits, Integer maxCredits) {
        return courses.stream()
            .filter(course -> department == null || 
                    course.getDepartment().equalsIgnoreCase(department))
            .filter(course -> semester == null || 
                    (course.getSemester() != null && 
                    course.getSemester().toString().equalsIgnoreCase(semester)))
            .filter(course -> minCredits == null || course.getCredits() >= minCredits)
            .filter(course -> maxCredits == null || course.getCredits() <= maxCredits)
            .collect(Collectors.toList());
    }

    @Override
    public double getAverageCredits() {
        return courses.stream()
            .mapToInt(Course::getCredits)
            .average()
            .orElse(0.0);
    }

    @Override
    public int getTotalCoursesByDepartment(String department) {
        return (int) courses.stream()
            .filter(course -> course.getDepartment().equalsIgnoreCase(department))
            .count();
    }

    @Override
    public List<Course> getCoursesOrderedByCredits() {
        return courses.stream()
            .sorted((c1, c2) -> Integer.compare(c2.getCredits(), c1.getCredits()))
            .collect(Collectors.toList());
    }

    @Override
    public void deactivateCourse(String code) {
        courses.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .forEach(Course::deactivate);
    }

    // Stream API search/filter examples
    public List<Course> filterByInstructor(Instructor instructor) {
        return courses.stream()
                .filter(c -> c.getInstructor().equals(instructor))
                .collect(Collectors.toList());
    }

    public List<Course> filterByDepartment(String department) {
        return courses.stream()
                .filter(c -> c.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }

    public List<Course> filterBySemester(Semester semester) {
        return courses.stream()
                .filter(c -> c.getSemester() == semester)
                .collect(Collectors.toList());
    }
}
