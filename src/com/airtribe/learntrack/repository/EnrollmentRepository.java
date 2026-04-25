package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentRepository {
    private List<Enrollment> enrollments;

    public EnrollmentRepository() {
        this.enrollments = new ArrayList<>();
    }

    public void save(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public void update(Enrollment enrollment) {
        for (int i = 0; i < enrollments.size(); i++) {
            if (enrollments.get(i).getId() == enrollment.getId()) {
                enrollments.set(i, enrollment);
                return;
            }
        }
    }

    public Enrollment findById(int id) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == id) {
                return enrollment;
            }
        }
        return null;
    }

    public List<Enrollment> findAll() {
        return enrollments;
    }

    public List<Enrollment> findByStudentId(int studentId) {
        List<Enrollment> studentEnrollments = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId) {
                studentEnrollments.add(enrollment);
            }
        }
        return studentEnrollments;
    }

    public List<Enrollment> findByCourseId(int courseId) {
        List<Enrollment> courseEnrollments = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourseId() == courseId) {
                courseEnrollments.add(enrollment);
            }
        }
        return courseEnrollments;
    }

    public boolean delete(int id) {
        for (int i = 0; i < enrollments.size(); i++) {
            if (enrollments.get(i).getId() == id) {
                enrollments.remove(i);
                return true;
            }
        }
        return false;
    }

    public int getTotalEnrollments() {
        return enrollments.size();
    }

    public void clear() {
        enrollments.clear();
    }
}

