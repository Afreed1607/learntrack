# LearnTrack - Student & Course Management System

A console-based Student & Course Management System built using Core Java, designed to practice fundamental Java concepts and OOP principles.

## Project Description

LearnTrack is an in-memory application that allows administrators to manage:
- **Students**: Add, update, view, and deactivate student records
- **Courses**: Create, manage, and activate/deactivate courses
- **Enrollments**: Enroll students in courses, track enrollment status, and manage course completions

## Features

### Student Management
- Add new students with name, email, and batch information
- View all students or search by ID
- Update student information
- Activate/deactivate students
- Delete student records

### Course Management
- Create new courses with name, description, and duration
- View all courses or filter by active courses
- Update course information
- Activate/deactivate courses
- Delete course records

### Enrollment Management
- Enroll active students in active courses
- Track enrollment dates and status
- View enrollments by student or course
- Update enrollment status (ACTIVE, COMPLETED, CANCELLED)
- System statistics showing total counts

## Learning Objectives Covered

- **Java Fundamentals**: Variables, data types, control flow, loop structures
- **OOP Principles**: Classes, objects, inheritance, encapsulation, polymorphism
- **Collections**: ArrayList for dynamic data storage
- **Exception Handling**: Custom exceptions for error handling
- **Code Design**: Separation of concerns (entity, service, repository layers)

## System Architecture

### Package Structure

```
com.airtribe.learntrack
├── Main.java                      # Console UI and menu logic
├── entity/
│   ├── Person.java               # Base class for entities
│   ├── Student.java              # Student entity (extends Person)
│   ├── Course.java               # Course entity
│   └── Enrollment.java           # Enrollment entity
├── service/
│   ├── StudentService.java       # Student business logic
│   ├── CourseService.java        # Course business logic
│   └── EnrollmentService.java    # Enrollment business logic
├── repository/
│   ├── StudentRepository.java    # Student data storage (in-memory)
│   ├── CourseRepository.java     # Course data storage (in-memory)
│   └── EnrollmentRepository.java # Enrollment data storage (in-memory)
├── exception/
│   ├── EntityNotFoundException.java
│   └── InvalidInputException.java
└── util/
    ├── IdGenerator.java          # Static ID counters for entities
    └── InputValidator.java       # Input validation utilities
```

### Design Patterns Used

1. **Repository Pattern**: Manages data storage and retrieval at the repository layer
2. **Service Layer Pattern**: Contains business logic separate from UI
3. **Separation of Concerns**: Clear distinction between UI (Main), services (business logic), repositories (data access)
4. **Singleton-like Utility Classes**: IdGenerator uses static methods for ID generation

## How to Compile and Run

### Prerequisites
- Java Development Kit (JDK) 11 or higher installed
- Command line terminal or IDE (IntelliJ IDEA recommended)

### Compilation

```bash
# Navigate to the project root directory
cd com.airtribe.learningtrack

# Compile all Java files
javac -d bin src/com/airtribe/learntrack/**/*.java src/com/airtribe/learntrack/Main.java
```

### Execution

```bash
# Run the application
java -cp bin com.airtribe.learntrack.Main
```

### Running from IDE

1. Open the project in IntelliJ IDEA or Eclipse
2. Right-click on `Main.java`
3. Select "Run Main.main()"
4. Follow the menu prompts in the console

## Usage Example

Once the application is running, you'll see a main menu with options to:

1. **Student Management** - Add/view/update students
2. **Course Management** - Add/view courses
3. **Enrollment Management** - Enroll students, manage enrollments
4. **View Statistics** - See total counts
5. **Exit** - Close the application

Navigate using numeric inputs and follow the prompts to manage your data.

## Data Model

### Student
- `id`: Unique identifier (auto-generated)
- `firstName`: Student's first name
- `lastName`: Student's last name
- `email`: Student's email address
- `batch`: Batch/cohort information
- `active`: Whether the student is active (boolean)

### Course
- `id`: Unique identifier (auto-generated)
- `courseName`: Name of the course
- `description`: Course description
- `durationInWeeks`: Course duration in weeks
- `active`: Whether the course is active (boolean)

### Enrollment
- `id`: Unique identifier (auto-generated)
- `studentId`: Reference to student
- `courseId`: Reference to course
- `enrollmentDate`: Date of enrollment
- `status`: Enrollment status (ACTIVE, COMPLETED, CANCELLED)

## Key Concepts Demonstrated

1. **Inheritance**: Student extends Person, demonstrating class hierarchy
2. **Encapsulation**: Private fields with public getters/setters
3. **Constructor Overloading**: Multiple constructors in entity classes
4. **Static Members**: IdGenerator class uses static counters and methods
5. **Collections**: ArrayList for storing students, courses, and enrollments
6. **Exception Handling**: Custom exceptions with try-catch blocks
7. **Method Overriding**: Student overrides getDisplayName() from Person

## Notes

- All data is stored in-memory (RAM). It will be lost when the application exits.
- The system prevents invalid operations (e.g., enrolling inactive students, duplicate enrollments)
- Student and course status can be toggled (active/inactive) without deletion
- The application uses a simple numeric menu-driven interface

## Future Enhancements

- Persistent data storage (File I/O or Database)
- User authentication and role-based access
- Advanced filtering and search capabilities
- Reporting and analytics features
- Batch operations
- Email notifications for enrollments

---

**Created for Learning Java Fundamentals**

