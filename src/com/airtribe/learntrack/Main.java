package com.airtribe.learntrack;

import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.util.InputValidator;
import java.util.Scanner;

public class Main {
    private static StudentService studentService;
    private static CourseService courseService;
    private static EnrollmentService enrollmentService;
    private static Scanner scanner;

    public static void main(String[] args) {
        initializeServices();
        displayWelcomeMessage();
        mainMenu();
    }

    private static void initializeServices() {
        studentService = new StudentService();
        courseService = new CourseService();
        enrollmentService = new EnrollmentService(studentService, courseService);
        scanner = new Scanner(System.in);
    }

    private static void displayWelcomeMessage() {
        System.out.println("\n========================================");
        System.out.println("  Welcome to LearnTrack");
        System.out.println("  Student & Course Management System");
        System.out.println("========================================\n");
    }

    private static void mainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Student Management");
            System.out.println("2. Course Management");
            System.out.println("3. Enrollment Management");
            System.out.println("4. View Statistics");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = InputValidator.parseInteger(scanner.nextLine());
                switch (choice) {
                    case 1:
                        studentManagementMenu();
                        break;
                    case 2:
                        courseManagementMenu();
                        break;
                    case 3:
                        enrollmentManagementMenu();
                        break;
                    case 4:
                        viewStatistics();
                        break;
                    case 5:
                        running = false;
                        System.out.println("Thank you for using LearnTrack. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void studentManagementMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add new student");
            System.out.println("2. View all students");
            System.out.println("3. Search student by ID");
            System.out.println("4. Update student");
            System.out.println("5. Deactivate student");
            System.out.println("6. Activate student");
            System.out.println("7. Delete student");
            System.out.println("8. Back to main menu");
            System.out.print("Choose an option: ");

            try {
                int choice = InputValidator.parseInteger(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addStudentOption();
                        break;
                    case 2:
                        studentService.displayAllStudents();
                        break;
                    case 3:
                        searchStudentByIdOption();
                        break;
                    case 4:
                        updateStudentOption();
                        break;
                    case 5:
                        deactivateStudentOption();
                        break;
                    case 6:
                        activateStudentOption();
                        break;
                    case 7:
                        deleteStudentOption();
                        break;
                    case 8:
                        inMenu = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addStudentOption() {
        try {
            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine().trim();

            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine().trim();

            System.out.print("Enter email: ");
            String email = scanner.nextLine().trim();

            System.out.print("Enter batch: ");
            String batch = scanner.nextLine().trim();

            studentService.addStudent(firstName, lastName, email, batch);
            System.out.println("Student added successfully!");
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchStudentByIdOption() {
        try {
            System.out.print("Enter student ID: ");
            int studentId = InputValidator.parseInteger(scanner.nextLine());
            studentService.displayStudentById(studentId);
        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateStudentOption() {
        try {
            System.out.print("Enter student ID: ");
            int studentId = InputValidator.parseInteger(scanner.nextLine());

            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine().trim();

            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine().trim();

            System.out.print("Enter email: ");
            String email = scanner.nextLine().trim();

            System.out.print("Enter batch: ");
            String batch = scanner.nextLine().trim();

            studentService.updateStudent(studentId, firstName, lastName, email, batch);
            System.out.println("Student updated successfully!");
        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deactivateStudentOption() {
        try {
            System.out.print("Enter student ID: ");
            int studentId = InputValidator.parseInteger(scanner.nextLine());
            studentService.deactivateStudent(studentId);
            System.out.println("Student deactivated successfully!");
        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void activateStudentOption() {
        try {
            System.out.print("Enter student ID: ");
            int studentId = InputValidator.parseInteger(scanner.nextLine());
            studentService.activateStudent(studentId);
            System.out.println("Student activated successfully!");
        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteStudentOption() {
        try {
            System.out.print("Enter student ID: ");
            int studentId = InputValidator.parseInteger(scanner.nextLine());
            if (studentService.deleteStudent(studentId)) {
                System.out.println("Student deleted successfully!");
            }
        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void courseManagementMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n--- Course Management ---");
            System.out.println("1. Add new course");
            System.out.println("2. View all courses");
            System.out.println("3. View active courses");
            System.out.println("4. Search course by ID");
            System.out.println("5. Update course");
            System.out.println("6. Activate course");
            System.out.println("7. Deactivate course");
            System.out.println("8. Delete course");
            System.out.println("9. Back to main menu");
            System.out.print("Choose an option: ");

            try {
                int choice = InputValidator.parseInteger(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addCourseOption();
                        break;
                    case 2:
                        courseService.displayAllCourses();
                        break;
                    case 3:
                        displayActiveCourses();
                        break;
                    case 4:
                        searchCourseByIdOption();
                        break;
                    case 5:
                        updateCourseOption();
                        break;
                    case 6:
                        activateCourseOption();
                        break;
                    case 7:
                        deactivateCourseOption();
                        break;
                    case 8:
                        deleteCourseOption();
                        break;
                    case 9:
                        inMenu = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addCourseOption() {
        try {
            System.out.print("Enter course name: ");
            String courseName = scanner.nextLine().trim();

            System.out.print("Enter description: ");
            String description = scanner.nextLine().trim();

            System.out.print("Enter duration (in weeks): ");
            int duration = InputValidator.parseInteger(scanner.nextLine());

            courseService.addCourse(courseName, description, duration);
            System.out.println("Course added successfully!");
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchCourseByIdOption() {
        try {
            System.out.print("Enter course ID: ");
            int courseId = InputValidator.parseInteger(scanner.nextLine());
            courseService.displayCourseById(courseId);
        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateCourseOption() {
        try {
            System.out.print("Enter course ID: ");
            int courseId = InputValidator.parseInteger(scanner.nextLine());

            System.out.print("Enter course name: ");
            String courseName = scanner.nextLine().trim();

            System.out.print("Enter description: ");
            String description = scanner.nextLine().trim();

            System.out.print("Enter duration (in weeks): ");
            int duration = InputValidator.parseInteger(scanner.nextLine());

            courseService.updateCourse(courseId, courseName, description, duration);
            System.out.println("Course updated successfully!");
        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void activateCourseOption() {
        try {
            System.out.print("Enter course ID: ");
            int courseId = InputValidator.parseInteger(scanner.nextLine());
            courseService.activateCourse(courseId);
            System.out.println("Course activated successfully!");
        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deactivateCourseOption() {
        try {
            System.out.print("Enter course ID: ");
            int courseId = InputValidator.parseInteger(scanner.nextLine());
            courseService.deactivateCourse(courseId);
            System.out.println("Course deactivated successfully!");
        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void displayActiveCourses() {
        var activeCourses = courseService.getActiveCourses();
        if (activeCourses.isEmpty()) {
            System.out.println("No active courses found.");
            return;
        }
        System.out.println("\n--- Active Courses ---");
        for (var course : activeCourses) {
            System.out.println(course);
        }
    }

    private static void deleteCourseOption() {
        try {
            System.out.print("Enter course ID: ");
            int courseId = InputValidator.parseInteger(scanner.nextLine());
            if (courseService.deleteCourse(courseId)) {
                System.out.println("Course deleted successfully!");
            }
        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void enrollmentManagementMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n--- Enrollment Management ---");
            System.out.println("1. Enroll student in course");
            System.out.println("2. View all enrollments");
            System.out.println("3. View enrollments by student ID");
            System.out.println("4. View enrollments by course ID");
            System.out.println("5. Complete enrollment");
            System.out.println("6. Cancel enrollment");
            System.out.println("7. Back to main menu");
            System.out.print("Choose an option: ");

            try {
                int choice = InputValidator.parseInteger(scanner.nextLine());
                switch (choice) {
                    case 1:
                        enrollStudentOption();
                        break;
                    case 2:
                        enrollmentService.displayAllEnrollments();
                        break;
                    case 3:
                        viewEnrollmentsByStudentOption();
                        break;
                    case 4:
                        viewEnrollmentsByCourseOption();
                        break;
                    case 5:
                        completeEnrollmentOption();
                        break;
                    case 6:
                        cancelEnrollmentOption();
                        break;
                    case 7:
                        inMenu = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void enrollStudentOption() {
        try {
            System.out.print("Enter student ID: ");
            int studentId = InputValidator.parseInteger(scanner.nextLine());

            System.out.print("Enter course ID: ");
            int courseId = InputValidator.parseInteger(scanner.nextLine());

            enrollmentService.enrollStudent(studentId, courseId);
            System.out.println("Student enrolled successfully!");
        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewEnrollmentsByStudentOption() {
        try {
            System.out.print("Enter student ID: ");
            int studentId = InputValidator.parseInteger(scanner.nextLine());
            enrollmentService.displayEnrollmentsByStudentId(studentId);
        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewEnrollmentsByCourseOption() {
        try {
            System.out.print("Enter course ID: ");
            int courseId = InputValidator.parseInteger(scanner.nextLine());
            enrollmentService.displayEnrollmentsByCourseId(courseId);
        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void completeEnrollmentOption() {
        try {
            System.out.print("Enter enrollment ID: ");
            int enrollmentId = InputValidator.parseInteger(scanner.nextLine());
            enrollmentService.completeEnrollment(enrollmentId);
            System.out.println("Enrollment marked as completed!");
        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void cancelEnrollmentOption() {
        try {
            System.out.print("Enter enrollment ID: ");
            int enrollmentId = InputValidator.parseInteger(scanner.nextLine());
            enrollmentService.cancelEnrollment(enrollmentId);
            System.out.println("Enrollment cancelled successfully!");
        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewStatistics() {
        System.out.println("\n--- System Statistics ---");
        System.out.println("Total Students: " + studentService.getTotalStudents());
        System.out.println("Active Students: " + studentService.getActiveStudentsCount());
        System.out.println("Total Courses: " + courseService.getTotalCourses());
        System.out.println("Total Enrollments: " + enrollmentService.getTotalEnrollments());
    }
}

