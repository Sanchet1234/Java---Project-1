# Project Requirements and Setup Guide

## System Requirements
- Java Development Kit (JDK) 17 or higher
- Command line interface (Terminal/PowerShell/CMD)
- Minimum 1GB free disk space
- Text editor or IDE (optional)

## Environment Setup

### 1. Java Installation
```bash
# Verify Java installation
java -version   # Should show version 17 or higher
javac -version  # Should match java version
```

### 2. Project Structure
The project must maintain this exact structure for automated evaluation:
```
Java project/
├── src/
│   └── edu/
│       └── ccrm/
│           ├── cli/
│           ├── config/
│           ├── domain/
│           ├── io/
│           ├── service/
│           └── util/
├── bin/
├── test-data/
├── USAGE.md
├── README.md
└── requirements.md
```

### 3. Compilation Instructions
```bash
# Create bin directory if it doesn't exist
mkdir bin

# Compile all source files
javac -d bin src/edu/ccrm/**/*.java
```

### 4. Execution Instructions
```bash
# Normal execution
java -cp bin edu.ccrm.cli.Main

# Run with assertions enabled (recommended for testing)
java -ea -cp bin edu.ccrm.cli.Main
```

## Critical Requirements for Automated Evaluation

### 1. File Naming
- All file names must exactly match the class names
- Maintain case sensitivity (e.g., `Student.java` not `student.java`)
- Keep the package structure intact

### 2. Class Structure
- Do not modify the provided interface signatures
- Keep all public methods as specified
- Maintain the inheritance hierarchy

### 3. Test Data
- Use provided test data files without modification
- Keep test file names as specified
- Maintain CSV format as documented

### 4. Console Output
- Follow the exact format specified in USAGE.md
- Include all required error messages
- Maintain menu structure as specified

### 5. Dependencies
- No external libraries are allowed
- Use only Java SE standard libraries
- No build tools (Maven/Gradle) required

### 6. Compilation Flags
```bash
# Required compilation flags
javac -Xlint:all -Xdiags:verbose -d bin src/edu/ccrm/**/*.java
```

## Common Issues and Solutions

### 1. Compilation Errors
If you encounter "package edu.ccrm.* does not exist":
- Ensure correct package declarations
- Verify directory structure matches package names
- Check for case sensitivity in paths

### 2. Runtime Errors
If you see "ClassNotFoundException":
- Verify bin directory contains compiled classes
- Check classpath in java command
- Ensure proper package structure in bin

### 3. Test Data Issues
If test data isn't recognized:
- Verify test-data directory is at project root
- Check file permissions
- Ensure UTF-8 encoding for CSV files

## Automated Testing Requirements

### 1. Input Validation
- Program must handle all error cases in test data
- Invalid input should not crash the program
- Error messages must match specified formats

### 2. Output Format
- Console output must be exactly as specified
- No additional print statements
- Use provided error message formats

### 3. File Operations
- Program must create/read files as specified
- Handle file not found scenarios gracefully
- Maintain data consistency

## Final Checklist Before Submission
- [ ] Code compiles without warnings
- [ ] All tests pass with provided data
- [ ] File structure matches specification
- [ ] No external dependencies added
- [ ] Console output matches requirements
- [ ] Error handling implemented
- [ ] Documentation complete
- [ ] DISCLOSURE.md included
- [ ] requirements.md updated if needed
