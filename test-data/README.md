# Test Data Guide

This directory contains test data files for the CCRM application, designed to test various scenarios including both valid and invalid cases.

## Files Overview

### 1. students.csv & students_with_errors.csv
- Basic student information
- Tests registration number format
- Tests email validation
- Tests required fields

### 2. courses.csv & courses_with_errors.csv
- Course details and structure
- Tests credit limits
- Tests course code format
- Tests semester validation

### 3. enrollments.csv
- Student course enrollments
- Tests credit limit rules
- Tests cross-department enrollment
- Tests duplicate enrollment prevention
- Tests prerequisite validation

### 4. grades.csv
- Grade recording scenarios
- Tests grade calculation
- Tests GPA computation
- Tests grade boundaries
- Tests invalid grade scenarios

### 5. instructors.csv
- Instructor assignments
- Tests teaching load limits
- Tests department restrictions
- Tests scheduling conflicts

## How to Use

1. Start with Clean Data:
   ```bash
   # Import valid data first
   java -cp bin edu.ccrm.cli.Main < test-data/students.csv
   java -cp bin edu.ccrm.cli.Main < test-data/courses.csv
   ```

2. Test Error Handling:
   ```bash
   # Import error cases
   java -cp bin edu.ccrm.cli.Main < test-data/students_with_errors.csv
   java -cp bin edu.ccrm.cli.Main < test-data/courses_with_errors.csv
   ```

3. Test Business Rules:
   ```bash
   # Test enrollments and credit limits
   java -cp bin edu.ccrm.cli.Main < test-data/enrollments.csv
   ```

4. Test Grade Processing:
   ```bash
   # Test grade recording and GPA calculation
   java -cp bin edu.ccrm.cli.Main < test-data/grades.csv
   ```

## Expected Results

### Credit Limit Testing
- Students cannot exceed 30 credits per semester
- High GPA students (≥8.5) can take 33 credits
- Minimum 12 credits for first enrollment

### Grade Boundaries
- 90-100: S (10.0)
- 80-89: A (9.0)
- 70-79: B (8.0)
- 60-69: C (7.0)
- 50-59: D (6.0)
- 40-49: E (5.0)
- 0-39: F (0.0)

### Error Cases
Each error file contains examples of:
- Missing required fields
- Invalid format data
- Boundary conditions
- Business rule violations

## Tips
1. Run with assertions enabled:
   ```bash
   java -ea -cp bin edu.ccrm.cli.Main
   ```

2. Check error logs after each test:
   ```bash
   cat logs/ccrm.log
   ```

3. Verify data integrity:
   ```bash
   # Export data after tests
   java -cp bin edu.ccrm.cli.Main export-all test-data/verification/
   ```
