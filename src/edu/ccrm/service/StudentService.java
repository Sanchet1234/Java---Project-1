package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.List;

public interface StudentService {
    void addStudent(Student student);
    List<Student> listStudents();
    Student getStudentByRegNo(String regNo);
    void updateStudent(Student student);
    void deactivateStudent(String regNo);
}
