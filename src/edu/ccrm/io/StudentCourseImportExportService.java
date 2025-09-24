package edu.ccrm.io;

import edu.ccrm.domain.Student;
import edu.ccrm.domain.Course;
import java.nio.file.*;
import java.io.IOException;
import java.util.*;
// ...existing code...

public class StudentCourseImportExportService implements ImportExportService<Object> {
    @Override
    public List<Object> importFromFile(String filePath) {
        List<Object> imported = new ArrayList<>();
        Path path = Paths.get(filePath);
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 4) { // Student: id, regNo, fullName, email
                    imported.add(new Student(parts[0], parts[1], parts[2], parts[3]));
                } else if (parts.length == 6) { // Course: code, title, credits, instructor, semester, department
                    imported.add(new Course(parts[0], parts[1], Integer.parseInt(parts[2]), null, null, parts[5]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return imported;
    }

    @Override
    public void exportToFile(List<Object> data, String filePath) {
        Path path = Paths.get(filePath);
        List<String> lines = new ArrayList<>();
        for (Object obj : data) {
            if (obj instanceof Student) {
                Student s = (Student) obj;
                lines.add(String.join(",",
                    s.getId(), s.getRegNo(), s.getFullName(), s.getEmail()
                ));
            } else if (obj instanceof Course) {
                Course c = (Course) obj;
                lines.add(String.join(",",
                    c.getCode(), c.getTitle(), String.valueOf(c.getCredits()),
                    c.getInstructor() != null ? c.getInstructor().getFullName() : "", 
                    c.getSemester() != null ? c.getSemester().toString() : "", c.getDepartment()
                ));
            }
        }
        try {
            Files.write(path, lines);
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}
