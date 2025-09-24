package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.ArrayList;
import java.util.List;
// ...existing code...

public class StudentServiceImpl implements StudentService {
    private final List<Student> students = new ArrayList<>();

    @Override
    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public List<Student> listStudents() {
        return new ArrayList<>(students);
    }

    @Override
    public Student getStudentByRegNo(String regNo) {
        return students.stream()
                .filter(s -> s.getRegNo().equalsIgnoreCase(regNo))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateStudent(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getRegNo().equalsIgnoreCase(student.getRegNo())) {
                students.set(i, student);
                return;
            }
        }
    }

    @Override
    public void deactivateStudent(String regNo) {
        students.stream()
                .filter(s -> s.getRegNo().equalsIgnoreCase(regNo))
                .forEach(Student::deactivate);
    }
}
