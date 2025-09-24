# Campus Course & Records Manager (CCRM)

## Project Description
The Campus Course & Records Manager (CCRM) is a comprehensive academic management system developed in Java SE. This system provides a robust solution for educational institutions to manage their academic records, course offerings, and student enrollment processes.

### Key Features
1. **Student Management**
   - Student registration with validation
   - Profile management with academic history
   - GPA calculation and tracking
   - Enrollment status monitoring

2. **Course Administration**
   - Course creation and catalog management
   - Credit system implementation
   - Department-wise course organization
   - Semester-based course scheduling

3. **Enrollment System**
   - Automated enrollment processing
   - Credit limit enforcement
   - Cross-department enrollment support
   - Prerequisites validation

4. **Grade Management**
   - Flexible grading system (S to F scale)
   - GPA computation with weighted credits
   - Transcript generation
   - Academic performance tracking

5. **Data Management**
   - CSV import/export functionality
   - Automated backup systems
   - Data validation and error handling
   - File operation utilities

### Technical Implementation
- Built using pure Java SE (no external dependencies)
- Object-Oriented design with inheritance hierarchy
- Interface-based service architecture
- Robust error handling and input validation
- File I/O with NIO.2 for better performance
- Stream API for efficient data processing

### Business Rules
- Maximum 30 credits per semester (33 for high performers)
- Minimum 12 credits for first enrollment
- Grade point calculation: S(10) to F(0)
- Department-specific course restrictions
- Instructor workload limitations

## Project Overview
A console-based Java SE application for managing students, courses, enrollments, grades, transcripts, and file operations for an academic institute.

## How to Run
- Requires JDK 17+ (or as per your syllabus)
- Compile: `javac -d bin src/edu/ccrm/cli/Main.java`
- Run: `java -cp bin edu.ccrm.cli.Main`

## Evolution of Java (Timeline)
- 1995: Java 1.0 released (write once, run anywhere)
- 1998: Java 2 split into J2SE, J2EE, J2ME
- 2004: Java 5 (Generics, Enums, Annotations, enhanced for-loop)
- 2011: Java 7 (try-with-resources, NIO.2)
- 2014: Java 8 (Lambdas, Streams, Date/Time API)
- 2017: Java 9 (Modules, JShell) and 6-month release cadence begins
- 2021: Java 17 LTS (long-term support)
- 2023+: Java 21 LTS, ongoing LTS every ~3 years

## Java Editions Comparison

| Feature           | Java ME (Micro)     | Java SE (Standard)    | Java EE (Enterprise)   |
|------------------|--------------------|--------------------|---------------------|
| Purpose          | Mobile/Embedded    | Desktop/Server     | Large Enterprise    |
| Memory Footprint | Minimal (<512KB)   | Medium (100MB+)    | Large (500MB+)      |
| APIs             | Limited subset     | Core platform      | Enterprise features |
| Use Cases        | IoT, Smart cards   | Applications       | Web/Cloud apps      |
| Deployment       | Embedded devices   | Standalone         | App servers         |
| Key Features     | CLDC, MIDP        | Collections, NIO   | JPA, EJB, JSP      |
| Development      | Resource-conscious | General purpose    | Distributed systems |

## Java Architecture (JDK/JRE/JVM)

### Components Overview
1. JDK (Java Development Kit)
   - Development tools (javac, jar)
   - Debugger (jdb)
   - Documentation generator (javadoc)
   - Contains JRE

2. JRE (Java Runtime Environment)
   - Java class libraries
   - Runtime components
   - Contains JVM

3. JVM (Java Virtual Machine)
   - Bytecode execution engine
   - Memory management/GC
   - Platform independence
   - Security sandbox

### Component Interaction Flow
```plaintext
Source Code (.java)
      ↓
    JDK (javac)
      ↓
Bytecode (.class)
      ↓
    JRE
      ↓
    JVM
      ↓
Native Machine Code
```

## Installation Guide (Windows)

### JDK Installation Steps
1. Download JDK:
   - Visit [Oracle's download page](https://www.oracle.com/java/technologies/downloads/)
   - Select Windows x64 Installer
   - Run the installer

2. Verify Installation:
   ```powershell
   java -version
   javac -version
   ```

3. Configure Environment Variables:
   - Open System Properties → Advanced → Environment Variables
   - Add JAVA_HOME: `C:\Program Files\Java\jdk-17`
   - Add to PATH: `%JAVA_HOME%\bin`

### Eclipse Setup
1. Download Eclipse:
   - Visit [Eclipse Downloads](https://www.eclipse.org/downloads/)
   - Get "Eclipse IDE for Java Developers"

2. Install and Configure:
   - Run Eclipse installer
   - Choose installation directory
   - Select Java development tools

3. Create CCRM Project:
   - File → New → Java Project
   - Project name: CCRM
   - JRE: Use project specific JRE
   - Create module-info.java: No

## Feature Implementation Map

| Topic/Concept | Implementation Location | Description |
|--------------|------------------------|-------------|
| Encapsulation | `domain/Person.java` | Private fields with getters/setters |
| Inheritance | `domain/Student.java` | Extends Person class |
| Abstraction | `domain/Person.java` | Abstract base class with abstract methods |
| Polymorphism | `service/*Service` | Interface implementations |
| Interfaces | `service/CourseService.java` | Service contracts |
| Collections | `domain/Student.java` | ArrayList for enrolledCourses |
| Generics | `service/ImportExportService.java` | Generic type parameters |
| Exception Handling | `domain/MaxCreditLimitExceededException.java` | Custom exceptions |
| Stream API | `service/CourseServiceImpl.java` | Course filtering and searching |
| Date/Time API | `domain/Student.java` | Enrollment dates |
| File I/O | `io/BackupService.java` | NIO.2 file operations |
| Design Patterns | `domain/CourseBuilder.java` | Builder pattern |
| Recursion | `util/RecursionUtils.java` | Directory traversal |

## Using Assertions
Enable assertions when running:
```bash
java -ea -cp bin edu.ccrm.cli.Main
```

Key assertion points in code:
```java
// Student.java
assert regNo.matches("^[A-Z]{2}\\d{6}$") : "Invalid registration number format";

// Course.java
assert credits > 0 && credits <= 6 : "Credits must be between 1 and 6";

// EnrollmentService.java
assert !isMaxCreditLimitExceeded() : "Maximum credit limit exceeded";
```

## Java ME vs SE vs EE
| Category       | Java ME (Micro Edition)              | Java SE (Standard Edition)                 | Java EE (Enterprise Edition)                          |
|----------------|--------------------------------------|--------------------------------------------|------------------------------------------------------|
| Target         | Mobile/embedded/IoT devices          | Desktop apps, command-line tools, servers  | Large-scale enterprise/web apps, microservices       |
| APIs           | Subset of SE, device-constrained APIs| Full core libraries (collections, streams) | SE + enterprise APIs (Servlets, JPA, EJB, JAX-RS)    |
| Runtime        | CLDC/CDC profiles                     | JRE (JVM + core libs)                      | Application server (e.g., GlassFish, WildFly, Payara)|
| Packaging      | MIDlets                                | JARs                                        | WAR/EAR (web/enterprise archives)                    |
| Examples       | Feature phones, sensors, set-top boxes| CLI tools, Swing/JavaFX apps, services     | Web apps, REST APIs, message-driven apps             |
| Status         | Legacy/limited adoption today         | Mainstream                                  | Evolved into Jakarta EE (under Eclipse Foundation)   |

## Java Architecture
- JDK (Java Development Kit)
  - What: Developer toolkit that includes the JRE, compiler (javac), debugger, and build tools.
  - Use: You install the JDK to compile and build Java programs.
- JRE (Java Runtime Environment)
  - What: Runtime that includes the JVM plus the standard libraries needed to run Java apps.
  - Use: End-users need the JRE to run Java apps (JRE is bundled inside the JDK).
- JVM (Java Virtual Machine)
  - What: The virtual machine that executes Java bytecode (.class files) on your OS/CPU.
  - Use: Provides portability, memory management (GC), JIT compilation.

How they interact:
- You write .java source files -> JDK compiles to .class bytecode -> JVM executes bytecode using JRE libraries.
- Diagram (conceptual): JDK = JRE + tools; JRE = JVM + core libraries.

## Install & Configure Java on Windows (Step-by-step)
1) Download the JDK
- Recommended: [Adoptium Temurin](https://adoptium.net) (LTS) or [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)
- Choose the Windows x64 MSI installer.

2) Install
- Run the installer and follow defaults. If offered, select "Set JAVA_HOME" and "Add to PATH".
- Otherwise, set them manually after installation (see below).

3) Set environment variables (if not set by installer)
- JAVA_HOME: points to your JDK install directory (e.g., C:\Program Files\Java\jdk-17)
- PATH: should include %JAVA_HOME%\bin

Using UI (System Properties):
- Start > "Environment Variables" > Edit system variables
- New System Variable: JAVA_HOME = C:\Program Files\Java\jdk-17
- Edit PATH > Add: %JAVA_HOME%\bin

Using PowerShell (Admin):
```powershell path=null start=null
[System.Environment]::SetEnvironmentVariable('JAVA_HOME','C:\\Program Files\\Java\\jdk-17','Machine')
$envPath = [System.Environment]::GetEnvironmentVariable('Path','Machine')
[System.Environment]::SetEnvironmentVariable('Path',"$envPath;%JAVA_HOME%\\bin",'Machine')
```

4) Verify installation
```powershell path=null start=null
java -version
javac -version
```
Expected output shows the installed versions.

Screenshots (replace with your own):
- screenshots/java-install-download.png
- screenshots/java-install-env-vars.png
- screenshots/java-install-verify.png

## Using Eclipse IDE (Project & Run Config)
1) Install Eclipse IDE for Java Developers
- Download from https://www.eclipse.org/downloads/
- Run installer and choose "Eclipse IDE for Java Developers".

2) Create a new Java Project
- File > New > Java Project
- Project name: CCRM (or your choice)
- JRE: Use installed JDK (JavaSE-17 or higher)
- Finish

3) Add source folders and packages
- Right-click project > New > Source Folder: src
- Create packages: edu.ccrm.cli, edu.ccrm.domain, edu.ccrm.service, etc.
- Add existing files under src/ accordingly.

4) Create/run Main class
- File > New > Class > Name: Main, Package: edu.ccrm.cli
- Ensure it has public static void main(String[] args)
- Right-click Main.java > Run As > Java Application

5) Configure Run Configuration (optional)
- Run > Run Configurations...
- Java Application > Main (or your class)
- Classpath: ensure project bin/ (or Eclipse auto builds) is present
- Arguments: set Program Arguments if needed
- Apply > Run

Screenshots (replace with your own):
- screenshots/eclipse-new-project.png
- screenshots/eclipse-package-structure.png
- screenshots/eclipse-run-config.png

## Syllabus Mapping Table
| Topic                        | File/Class/Method                |
|------------------------------|----------------------------------|
| Encapsulation                | domain/Person, Student           |
| Inheritance                  | domain/Person, Student, Instructor|
| Abstraction                  | domain/Person (abstract)         |
| Polymorphism                 | TranscriptService, toString()    |
| Access Levels                | All domain classes               |
| Immutability                 | domain/CourseCode, Name          |
| Nested Classes               | CourseBuilder, RecursionUtils    |
| Interfaces                   | service/*Service, util/Searchable|
| Lambdas/Streams              | CourseServiceImpl, CLI           |
| Enums                        | domain/Semester, Grade           |
| Upcast/Downcast              | Comments in EnrollmentService    |
| Overriding/Overloading       | domain/*, TranscriptService      |
| Singleton                    | config/AppConfig                 |
| Builder                      | domain/CourseBuilder             |
| Exceptions                   | domain/*Exception, try/catch     |
| NIO.2/File I/O               | io/*, BackupService              |
| Date/Time API                | domain/Person, Enrollment        |
| Recursion                    | util/RecursionUtils              |

## Enabling Assertions
- Run with: `java -ea -cp bin edu.ccrm.cli.Main`

## Sample Commands & Data Files
- See `test-data/` for sample CSVs
- Import: `import students.csv`
- Export: `export all.csv`

Academic Integrity: All work is original and for educational purposes only.
# Programming-in-Java---Project-1 
