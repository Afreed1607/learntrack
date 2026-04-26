package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private List<Student> students;

    public StudentRepository() {
        this.students = new ArrayList<>();
    }

    public void save(Student student) {
        students.add(student);
    }

    public void update(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == student.getId()) {
                students.set(i, student);
                return;
            }
        }
    }

    public Student findById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public List<Student> findAll() {
        return students;
    }

    public boolean delete(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    public int getTotalStudents() {
        return students.size();
    }

    public int getActiveStudentsCount() {
        int activeCount = 0;
        for (Student student : students) {
            if (student.isActive()) {
                activeCount++;
            }
        }
        return activeCount;
    }

    public void clear() {
        students.clear();
    }
}

