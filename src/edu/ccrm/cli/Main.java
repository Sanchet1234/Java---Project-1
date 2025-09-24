package edu.ccrm.cli;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import edu.ccrm.service.*;
import edu.ccrm.domain.*;
import edu.ccrm.util.Validator;
import edu.ccrm.util.StudentComparator;
import edu.ccrm.util.RecursionUtils;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.io.StudentCourseImportExportService;
import edu.ccrm.io.BackupService;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            StudentService studentService = new StudentServiceImpl();
            CourseService courseService = new CourseServiceImpl();
            EnrollmentService enrollmentService = new EnrollmentServiceImpl();
            TranscriptService transcriptService = new TranscriptServiceImpl(enrollmentService);
            ImportExportService<Object> importExportService = new StudentCourseImportExportService();
            BackupService backupService = new BackupService();
            boolean running = true;
        while (running) {
            System.out.println("\n=== Campus Course & Records Manager (CCRM) ===");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Courses");
            System.out.println("3. Enrollment & Grades");
            System.out.println("4. Import/Export Data");
            System.out.println("5. Backup & Reports");
            System.out.println("6. Platform Note");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Student management selected.");
                    System.out.println("a) Add Student\nb) List Students\nc) Deactivate Student");
                    String smOpt = scanner.nextLine();
                    if (smOpt.equalsIgnoreCase("a")) {
                        System.out.print("Enter id, regNo, fullName, email (comma separated): ");
                            String[] rawParts = scanner.nextLine().split(",");
                            if (rawParts.length == 4) {
                                String id = rawParts[0].trim();
                                String regNo = rawParts[1].trim();
                                String fullName = rawParts[2].trim();
                                String email = rawParts[3].trim();
                                if (!Validator.isValidRegNo(regNo)) {
                                    System.out.println("Invalid regNo format.");
                                    break;
                                }
                                if (!Validator.isValidEmail(email)) {
                                    System.out.println("Invalid email format.");
                                    break;
                                }
                                studentService.addStudent(new Student(id, regNo, fullName, email));
                                System.out.println("Student added.");
                            }
                    } else if (smOpt.equalsIgnoreCase("b")) {
                        studentService.listStudents().stream()
                            .sorted(StudentComparator.byRegNo())
                            .forEach(System.out::println);
                    } else if (smOpt.equalsIgnoreCase("c")) {
                        System.out.print("Enter regNo to deactivate: ");
                        String regNo = scanner.nextLine();
                        studentService.deactivateStudent(regNo);
                        System.out.println("Student deactivated.");
                    }
                    break;
                case "2":
                    System.out.println("Course management selected.");
                    System.out.println("a) Add Course\nb) List Courses\nc) Deactivate Course");
                    System.out.println("d) Search by Department\ne) Search by Instructor");
                    System.out.println("f) Search by Semester\ng) Advanced Search\nh) Course Statistics");
                    String cmOpt = scanner.nextLine();
                    if (cmOpt.equalsIgnoreCase("a")) {
                        System.out.print("Enter code, title, credits, department (comma separated): ");
                        String[] parts = scanner.nextLine().split(",");
                        if (parts.length == 4) {
                            String code = parts[0].trim();
                            String title = parts[1].trim();
                            int credits = Integer.parseInt(parts[2].trim());
                            String department = parts[3].trim();
                            courseService.addCourse(new Course(code, title, credits, null, null, department));
                            System.out.println("Course added.");
                        }
                    } else if (cmOpt.equalsIgnoreCase("b")) {
                        courseService.listCourses().forEach(System.out::println);
                    } else if (cmOpt.equalsIgnoreCase("c")) {
                        System.out.print("Enter code to deactivate: ");
                        String code = scanner.nextLine();
                        courseService.deactivateCourse(code);
                        System.out.println("Course deactivated.");
                    } else if (cmOpt.equalsIgnoreCase("d")) {
                        System.out.print("Enter department name: ");
                        String dept = scanner.nextLine();
                        List<Course> courses = courseService.searchByDepartment(dept);
                        System.out.println("\nCourses in " + dept + " department:");
                        courses.forEach(System.out::println);
                    } else if (cmOpt.equalsIgnoreCase("e")) {
                        System.out.print("Enter instructor ID: ");
                        String instructorId = scanner.nextLine();
                        List<Course> courses = courseService.searchByInstructor(instructorId);
                        System.out.println("\nCourses taught by instructor " + instructorId + ":");
                        courses.forEach(System.out::println);
                    } else if (cmOpt.equalsIgnoreCase("f")) {
                        System.out.print("Enter semester (SPRING/SUMMER/FALL): ");
                        String semester = scanner.nextLine();
                        List<Course> courses = courseService.searchBySemester(semester);
                        System.out.println("\nCourses in " + semester + " semester:");
                        courses.forEach(System.out::println);
                    } else if (cmOpt.equalsIgnoreCase("g")) {
                        System.out.print("Enter department (or press Enter to skip): ");
                        String dept = scanner.nextLine();
                        dept = dept.isEmpty() ? null : dept;
                        
                        System.out.print("Enter semester (or press Enter to skip): ");
                        String sem = scanner.nextLine();
                        sem = sem.isEmpty() ? null : sem;
                        
                        System.out.print("Enter minimum credits (or press Enter to skip): ");
                        String minCred = scanner.nextLine();
                        Integer minCredits = minCred.isEmpty() ? null : Integer.parseInt(minCred);
                        
                        System.out.print("Enter maximum credits (or press Enter to skip): ");
                        String maxCred = scanner.nextLine();
                        Integer maxCredits = maxCred.isEmpty() ? null : Integer.parseInt(maxCred);
                        
                        List<Course> courses = courseService.searchCourses(dept, sem, minCredits, maxCredits);
                        System.out.println("\nSearch Results:");
                        courses.forEach(System.out::println);
                    } else if (cmOpt.equalsIgnoreCase("h")) {
                        System.out.println("\nCourse Statistics:");
                        System.out.printf("Average Credits per Course: %.2f\n", 
                            courseService.getAverageCredits());
                        
                        System.out.println("\nCourses by Credits (Descending):");
                        courseService.getCoursesOrderedByCredits()
                            .forEach(c -> System.out.printf("%s: %d credits\n", 
                                c.getCode(), c.getCredits()));
                        
                        System.out.print("\nEnter department for course count: ");
                        String dept = scanner.nextLine();
                        int count = courseService.getTotalCoursesByDepartment(dept);
                        System.out.printf("Total courses in %s: %d\n", dept, count);
                    }
                    break;
                case "3":
                    System.out.println("Enrollment & Grades selected.");
                    System.out.println("a) Enroll Student\nb) Record Marks\nc) Print Transcript");
                    String egOpt = scanner.nextLine();
                    if (egOpt.equalsIgnoreCase("a")) {
                        System.out.print("Enter regNo and courseCode (comma separated): ");
                        String[] parts = scanner.nextLine().split(",");
                        Student s = studentService.getStudentByRegNo(parts[0].trim());
                        Course c = courseService.getCourseByCode(parts[1].trim());
                        if (s != null && c != null) {
                            enrollmentService.enrollStudent(s, c);
                            System.out.println("Enrolled.");
                        }
                    } else if (egOpt.equalsIgnoreCase("b")) {
                        System.out.print("Enter regNo, courseCode, marks (comma separated): ");
                        String[] parts = scanner.nextLine().split(",");
                        Student s = studentService.getStudentByRegNo(parts[0].trim());
                        Course c = courseService.getCourseByCode(parts[1].trim());
                        if (s != null && c != null) {
                            enrollmentService.recordMarks(s, c, Integer.parseInt(parts[2].trim()));
                            System.out.println("Marks recorded.");
                        }
                    } else if (egOpt.equalsIgnoreCase("c")) {
                        System.out.print("Enter regNo: ");
                        Student s = studentService.getStudentByRegNo(scanner.nextLine());
                        if (s != null) {
                            System.out.println(transcriptService.generateTranscript(s));
                            System.out.println("GPA: " + transcriptService.computeGPA(s));
                        }
                    }
                    break;
                case "4":
                    System.out.println("Import/Export Data selected.");
                    System.out.println("a) Import\nb) Export");
                    String ioOpt = scanner.nextLine();
                    if (ioOpt.equalsIgnoreCase("a")) {
                        System.out.print("Enter file path to import: ");
                        String path = scanner.nextLine();
                        List<Object> imported = importExportService.importFromFile(path);
                        imported.forEach(obj -> {
                            if (obj instanceof Student) studentService.addStudent((Student)obj);
                            else if (obj instanceof Course) courseService.addCourse((Course)obj);
                        });
                        System.out.println("Import complete.");
                    } else if (ioOpt.equalsIgnoreCase("b")) {
                        System.out.print("Enter file path to export: ");
                        String path = scanner.nextLine();
                        List<Object> data = new ArrayList<>();
                        data.addAll(studentService.listStudents());
                        data.addAll(courseService.listCourses());
                        importExportService.exportToFile(data, path);
                        System.out.println("Export complete.");
                    }
                    break;
                case "5":
                    System.out.println("Backup & Reports selected.");
                    System.out.println("a) Backup\nb) Show Backup Size\nc) List Backup Files");
                    String brOpt = scanner.nextLine();
                    if (brOpt.equalsIgnoreCase("a")) {
                        System.out.print("Enter source dir and backup root dir (comma separated): ");
                        String[] parts = scanner.nextLine().split(",");
                        backupService.backupData(parts[0].trim(), parts[1].trim());
                    } else if (brOpt.equalsIgnoreCase("b")) {
                        System.out.print("Enter backup dir path: ");
                        Path dir = Paths.get(scanner.nextLine());
                        System.out.println("Total size: " + backupService.getDirectorySize(dir) + " bytes");
                    } else if (brOpt.equalsIgnoreCase("c")) {
                        System.out.print("Enter backup dir path: ");
                        Path dir = Paths.get(scanner.nextLine());
                        RecursionUtils.printFilesByDepth(dir, 0);
                    }
                    break;
                case "6":
                    System.out.println("Java SE vs ME vs EE: Java SE is for desktop/server apps, ME for mobile/embedded, EE for enterprise/web.");
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting CCRM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            }
        } catch (Exception e) {
            System.err.println("[Error] " + e.getMessage());
            e.printStackTrace();
        }
    }
}
