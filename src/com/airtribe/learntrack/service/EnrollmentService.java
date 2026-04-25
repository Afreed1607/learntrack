package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import java.time.LocalDate;
import java.util.List;

public class EnrollmentService {
    private EnrollmentRepository enrollmentRepository;
    private StudentService studentService;
    private CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.enrollmentRepository = new EnrollmentRepository();
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public void enrollStudent(int studentId, int courseId)
            throws EntityNotFoundException, InvalidInputException {
        // Verify student exists
        Student student = studentService.getStudentById(studentId);

        // Verify course exists
        Course course = courseService.getCourseById(courseId);

        if (!student.isActive()) {
            throw new InvalidInputException("Cannot enroll an inactive student.");
        }

        if (!course.isActive()) {
            throw new InvalidInputException("Cannot enroll in an inactive course.");
        }

        // Check if already enrolled
        List<Enrollment> existingEnrollments = enrollmentRepository.findByStudentId(studentId);
        for (Enrollment enrollment : existingEnrollments) {
            if (enrollment.getCourseId() == courseId &&
                enrollment.getStatus().equals(Enrollment.STATUS_ACTIVE)) {
                throw new InvalidInputException("Student is already enrolled in this course.");
            }
        }

        int enrollmentId = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = new Enrollment(studentId, courseId, LocalDate.now());
        enrollment.setId(enrollmentId);
        enrollmentRepository.save(enrollment);
    }

    public Enrollment getEnrollmentById(int enrollmentId) throws EntityNotFoundException {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId);
        if (enrollment == null) {
            throw new EntityNotFoundException("Enrollment with ID " + enrollmentId + " not found.");
        }
        return enrollment;
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public List<Enrollment> getEnrollmentsByStudentId(int studentId) throws EntityNotFoundException {
        // Verify student exists
        studentService.getStudentById(studentId);
        return enrollmentRepository.findByStudentId(studentId);
    }

    public List<Enrollment> getEnrollmentsByCourseId(int courseId) throws EntityNotFoundException {
        // Verify course exists
        courseService.getCourseById(courseId);
        return enrollmentRepository.findByCourseId(courseId);
    }

    public void updateEnrollmentStatus(int enrollmentId, String newStatus)
            throws EntityNotFoundException, InvalidInputException {
        Enrollment enrollment = getEnrollmentById(enrollmentId);

        if (!isValidStatus(newStatus)) {
            throw new InvalidInputException("Invalid status. Use: ACTIVE, COMPLETED, or CANCELLED.");
        }

        enrollment.setStatus(newStatus);
        enrollmentRepository.update(enrollment);
    }

    public void completeEnrollment(int enrollmentId) throws EntityNotFoundException {
        Enrollment enrollment = getEnrollmentById(enrollmentId);
        enrollment.setStatus(Enrollment.STATUS_COMPLETED);
        enrollmentRepository.update(enrollment);
    }

    public void cancelEnrollment(int enrollmentId) throws EntityNotFoundException {
        Enrollment enrollment = getEnrollmentById(enrollmentId);
        enrollment.setStatus(Enrollment.STATUS_CANCELLED);
        enrollmentRepository.update(enrollment);
    }

    public int getTotalEnrollments() {
        return enrollmentRepository.getTotalEnrollments();
    }

    public void displayAllEnrollments() {
        List<Enrollment> enrollments = getAllEnrollments();
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found.");
            return;
        }
        System.out.println("\n--- All Enrollments ---");
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }

    public void displayEnrollmentsByStudentId(int studentId) throws EntityNotFoundException {
        List<Enrollment> enrollments = getEnrollmentsByStudentId(studentId);
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found for student ID: " + studentId);
            return;
        }
        System.out.println("\n--- Enrollments for Student ID: " + studentId + " ---");
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }

    public void displayEnrollmentsByCourseId(int courseId) throws EntityNotFoundException {
        List<Enrollment> enrollments = getEnrollmentsByCourseId(courseId);
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found for course ID: " + courseId);
            return;
        }
        System.out.println("\n--- Enrollments for Course ID: " + courseId + " ---");
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }

    private boolean isValidStatus(String status) {
        return status.equals(Enrollment.STATUS_ACTIVE) ||
               status.equals(Enrollment.STATUS_COMPLETED) ||
               status.equals(Enrollment.STATUS_CANCELLED);
    }
}


