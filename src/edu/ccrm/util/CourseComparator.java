package edu.ccrm.util;

import edu.ccrm.domain.Course;
import java.util.Comparator;

public class CourseComparator {
    public static Comparator<Course> byCode() {
        return Comparator.comparing(Course::getCode);
    }
    public static Comparator<Course> byTitle() {
        return Comparator.comparing(Course::getTitle);
    }
}
