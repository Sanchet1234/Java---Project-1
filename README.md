# 🎓 Campus Course & Records Manager (CCRM)

## Overview
The **Campus Course & Records Manager (CCRM)** is a comprehensive, console-based academic management system developed in pure Java SE. It provides a robust solution for educational institutions to manage student records, course catalogs, enrollment, and grading efficiently.

---

## 🚀 Key Features

* **Student Management**: Handles student registration, profile updates, academic history, GPA calculation, and enrollment status.
* **Course Administration**: Manages course creation, cataloging, credit systems, and scheduling by department and semester.
* **Enrollment System**: Features automated enrollment processing with validation for credit limits and course prerequisites.
* **Grade Management**: Implements a flexible grading system (S to F), computes weighted GPAs, and generates student transcripts.
* **Data Persistence**: Includes CSV import/export functionality, automated backups, and robust data validation using file I/O utilities.

---

## 🛠️ Technical Details

* **Language**: Built with **Java SE 17+** without any external dependencies.
* **Architecture**: Follows an **Object-Oriented design** with an interface-based service architecture.
* **Core Java Features**: Leverages modern Java features including the **Stream API** for data processing, **NIO.2** for high-performance file I/O, and the **Date/Time API**.
* **Error Handling**: Implements robust error handling with custom exceptions and comprehensive input validation.
* **Design Patterns**: Utilizes patterns like the **Builder** pattern (`CourseBuilder`) for object creation.

---

## ⚙️ Getting Started

### Prerequisites
* **JDK 17** or a higher version must be installed.

### Installation & Setup
1.  **Download the JDK**: We recommend [Adoptium Temurin](https://adoptium.net) (LTS) or [Oracle JDK](https://www.oracle.com/java/technologies/downloads/). Run the installer.
2.  **Configure Environment Variables**:
    * Create a `JAVA_HOME` variable pointing to your JDK installation directory (e.g., `C:\Program Files\Java\jdk-17`).
    * Add `%JAVA_HOME%\bin` to your `PATH` variable.
3.  **Verify the Installation**: Open a terminal and run `java -version` and `javac -version` to confirm the setup.

### Running the Application
1.  **Compile the code**:
    ```bash
    javac -d bin src/edu/ccrm/cli/Main.java
    ```
2.  **Run the application**:
    ```bash
    java -cp bin edu.ccrm.cli.Main
    ```
3.  **Enable Assertions (Optional)**: To run with assertions enabled for debugging, use the `-ea` flag.
    ```bash
    java -ea -cp bin edu.ccrm.cli.Main
    ```

---

## 📝 Syllabus & Concept Mapping
This project demonstrates a wide range of core Java programming concepts.

| Topic / Concept        | Implementation Location                     | Description                                    |
| ---------------------- | ------------------------------------------- | ---------------------------------------------- |
| **Encapsulation** | `domain/Person.java`                        | Private fields with public getters/setters.    |
| **Inheritance** | `domain/Student.java`                       | `Student` class extends the `Person` class.    |
| **Abstraction** | `domain/Person.java`                        | Abstract base class with abstract methods.     |
| **Polymorphism** | `service/*Service`, `toString()` overrides  | Multiple implementations of service interfaces.|
| **Interfaces** | `service/CourseService.java`                | Defines contracts for service layers.          |
| **Collections & Generics** | `domain/Student.java`, `service/*`      | `ArrayList<Course>` for enrollments.           |
| **Exception Handling** | `domain/MaxCreditLimitExceededException.java` | Custom exceptions for business rule violations.|
| **Stream API** | `service/CourseServiceImpl.java`            | Used for efficient searching and filtering.    |
| **Date/Time API** | `domain/Student.java`                       | Manages enrollment dates and birth dates.      |
| **File I/O (NIO.2)** | `io/BackupService.java`                     | Modern file operations for data management.    |

---

## Appendix: Java Platform Overview

### Java Architecture: JDK vs. JRE vs. JVM
* **JDK (Java Development Kit)**: The complete toolkit for developers. It includes the JRE, compiler (`javac`), debugger, and other tools needed to create Java applications.
* **JRE (Java Runtime Environment)**: The environment needed to *run* Java applications. It contains the JVM and core Java libraries.
* **JVM (Java Virtual Machine)**: An abstract machine that executes Java bytecode. It provides platform independence, memory management, and security.

**Flow**: `.java` (source code) → `javac` (compiler in **JDK**) → `.class` (bytecode) → **JVM** (executes using **JRE** libraries)

### Java Editions Comparison

| Feature          | Java ME (Micro)    | Java SE (Standard) | Java EE (Enterprise) -> Jakarta EE |
| ---------------- | ------------------ | ------------------ | -------------------------------- |
| **Target** | Embedded/IoT devices | Desktop/Servers    | Large-scale enterprise applications |
| **APIs** | Limited subset     | Core platform APIs | Extended with enterprise features (JPA, Servlets) |
| **Primary Use** | IoT, smart cards   | General apps       | Web services, cloud applications |
