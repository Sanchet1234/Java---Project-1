package edu.ccrm.util;

import edu.ccrm.domain.Student;
import java.util.Comparator;

public class StudentComparator {
    public static Comparator<Student> byRegNo() {
        return Comparator.comparing(Student::getRegNo);
    }
    public static Comparator<Student> byName() {
        return Comparator.comparing(Student::getFullName);
    }
}
