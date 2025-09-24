# CCRM Usage Guide

## Getting Started

### Prerequisites
- Java 17 or higher
- Command line terminal
- Test data files (provided in test-data folder)

### Compilation
```bash
# Create bin directory if it doesn't exist
mkdir bin

# Compile all source files
javac -d bin src/edu/ccrm/**/*.java
```

### Running the Application
```bash
# Normal run
java -cp bin edu.ccrm.cli.Main

# Run with assertions enabled (recommended)
java -ea -cp bin edu.ccrm.cli.Main
```

## Menu Navigation

### 1. Student Management
```
Option: 1
a) Add Student
   Format: id,regNo,fullName,email
   Example: STU001,CS123456,John Doe,john@university.edu

b) List Students
   - Shows all active students
   - Sorted by registration number

c) Deactivate Student
   - Enter registration number
   - Example: CS123456
```

### 2. Course Management
```
Option: 2
a) Add Course
   Format: code,title,credits,department
   Example: CS101,Introduction to Programming,3,Computer Science

b) List Courses
   - Shows all active courses
   - Grouped by department

c) Deactivate Course
   - Enter course code
   - Example: CS101

d) Search by Department
   - Enter department name
   - Example: Computer Science

e) Search by Instructor
   - Enter instructor ID
   - Example: INS001

f) Search by Semester
   - Enter semester (SPRING/SUMMER/FALL)

g) Advanced Search
   - Multiple criteria support
   - Credits range filter

h) Course Statistics
   - Average credits
   - Department-wise counts
```

### 3. Enrollment & Grades
```
Option: 3
a) Enroll Student
   Format: regNo,courseCode
   Example: CS123456,CS101

b) Record Marks
   Format: regNo,courseCode,marks
   Example: CS123456,CS101,85

c) Print Transcript
   - Enter student regNo
   - Shows all courses and GPA
```

### 4. Import/Export Data
```
Option: 4
a) Import
   - Select file path
   - Supports students.csv and courses.csv
   Example: test-data/students.csv

b) Export
   - Enter export path
   - Creates backup with timestamp
```

### 5. Backup & Reports
```
Option: 5
a) Backup
   - Enter source and destination
   - Creates timestamped backup

b) Show Backup Size
   - Enter backup directory path
   - Shows total size in bytes

c) List Backup Files
   - Shows file tree structure
```

## Sample Data Files

### students.csv format:
```csv
id,regNo,fullName,email
STU001,CS123456,John Doe,john.doe@university.edu
STU002,CS123457,Jane Smith,jane.smith@university.edu
```

### courses.csv format:
```csv
code,title,credits,instructor,semester,department
CS101,Introduction to Programming,3,,FALL,Computer Science
CS201,Data Structures,4,,SPRING,Computer Science
```

## Business Rules

### Credit Limits
- Maximum 30 credits per semester
- High performers (GPA ≥ 8.5) allowed 33 credits
- Minimum 12 credits for first enrollment

### Grade Points
- S: 10.0
- A: 9.0
- B: 8.0
- C: 7.0
- D: 6.0
- E: 5.0
- F: 0.0

### Registration Number Format
- 2 uppercase letters (department code)
- 6 digits
- Example: CS123456

## Error Handling
- The application provides detailed error messages
- All operations are validated
- Data consistency is maintained
- Use assertions for additional validation

## Tips
1. Always back up data before importing
2. Use export feature regularly
3. Enable assertions in production
4. Check credit limits before enrollment
5. Verify registration numbers carefully
