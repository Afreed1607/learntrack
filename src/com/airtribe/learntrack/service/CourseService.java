package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;
import java.util.List;

public class CourseService {
    private CourseRepository courseRepository;

    public CourseService() {
        this.courseRepository = new CourseRepository();
    }

    public void addCourse(String courseName, String description, int durationInWeeks)
            throws InvalidInputException {
        validateCourseInput(courseName, description, durationInWeeks);
        int courseId = IdGenerator.getNextCourseId();
        Course course = new Course(courseId, courseName, description, durationInWeeks);
        courseRepository.save(course);
    }

    public Course getCourseById(int courseId) throws EntityNotFoundException {
        Course course = courseRepository.findById(courseId);
        if (course == null) {
            throw new EntityNotFoundException("Course with ID " + courseId + " not found.");
        }
        return course;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getActiveCourses() {
        return courseRepository.findActiveCourses();
    }

    public void updateCourse(int courseId, String courseName, String description, int durationInWeeks)
            throws EntityNotFoundException, InvalidInputException {
        validateCourseInput(courseName, description, durationInWeeks);
        Course course = getCourseById(courseId);
        course.setCourseName(courseName);
        course.setDescription(description);
        course.setDurationInWeeks(durationInWeeks);
        courseRepository.update(course);
    }

    public void activateCourse(int courseId) throws EntityNotFoundException {
        Course course = getCourseById(courseId);
        course.setActive(true);
        courseRepository.update(course);
    }

    public void deactivateCourse(int courseId) throws EntityNotFoundException {
        Course course = getCourseById(courseId);
        course.setActive(false);
        courseRepository.update(course);
    }

    public boolean deleteCourse(int courseId) throws EntityNotFoundException {
        getCourseById(courseId);
        return courseRepository.delete(courseId);
    }

    public int getTotalCourses() {
        return courseRepository.getTotalCourses();
    }

    public void displayAllCourses() {
        List<Course> courses = getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        System.out.println("\n--- All Courses ---");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public void displayCourseById(int courseId) throws EntityNotFoundException {
        Course course = getCourseById(courseId);
        System.out.println("\n--- Course Details ---");
        System.out.println(course);
    }

    private void validateCourseInput(String courseName, String description, int durationInWeeks)
            throws InvalidInputException {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new InvalidInputException("Course name cannot be empty.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new InvalidInputException("Description cannot be empty.");
        }
        if (durationInWeeks <= 0) {
            throw new InvalidInputException("Duration must be a positive number.");
        }
    }
}

