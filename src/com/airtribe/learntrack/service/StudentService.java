package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import java.util.List;

public class StudentService {
    private StudentRepository studentRepository;

    public StudentService() {
        this.studentRepository = new StudentRepository();
    }

    public void addStudent(String firstName, String lastName, String email, String batch)
            throws InvalidInputException {
        validateStudentInput(firstName, lastName, email, batch);
        int studentId = IdGenerator.getNextStudentId();
        Student student = new Student(studentId, firstName, lastName, email, batch);
        studentRepository.save(student);
    }

    public Student getStudentById(int studentId) throws EntityNotFoundException {
        Student student = studentRepository.findById(studentId);
        if (student == null) {
            throw new EntityNotFoundException("Student with ID " + studentId + " not found.");
        }
        return student;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void updateStudent(int studentId, String firstName, String lastName, String email, String batch)
            throws EntityNotFoundException, InvalidInputException {
        validateStudentInput(firstName, lastName, email, batch);
        Student student = getStudentById(studentId);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setBatch(batch);
        studentRepository.update(student);
    }

    public void deactivateStudent(int studentId) throws EntityNotFoundException {
        Student student = getStudentById(studentId);
        student.setActive(false);
        studentRepository.update(student);
    }

    public void activateStudent(int studentId) throws EntityNotFoundException {
        Student student = getStudentById(studentId);
        student.setActive(true);
        studentRepository.update(student);
    }

    public boolean deleteStudent(int studentId) throws EntityNotFoundException {
        getStudentById(studentId);
        return studentRepository.delete(studentId);
    }

    public int getTotalStudents() {
        return studentRepository.getTotalStudents();
    }

    public void displayAllStudents() {
        List<Student> students = getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\n--- All Students ---");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void displayStudentById(int studentId) throws EntityNotFoundException {
        Student student = getStudentById(studentId);
        System.out.println("\n--- Student Details ---");
        System.out.println(student);
    }

    private void validateStudentInput(String firstName, String lastName, String email, String batch)
            throws InvalidInputException {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new InvalidInputException("First name cannot be empty.");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new InvalidInputException("Last name cannot be empty.");
        }
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new InvalidInputException("Invalid email address.");
        }
        if (batch == null || batch.trim().isEmpty()) {
            throw new InvalidInputException("Batch cannot be empty.");
        }
    }
}

