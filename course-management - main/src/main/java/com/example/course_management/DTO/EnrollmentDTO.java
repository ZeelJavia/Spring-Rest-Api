package com.example.course_management.DTO;

import com.example.course_management.Info.CourseInfo;
import com.example.course_management.Info.InstructorInfo;
import com.example.course_management.Info.StudentInfo;

import java.util.List;

public class EnrollmentDTO {
    private Long enrollmentId; // Represents enrollment's ID
    private CourseInfo course; // Changed from String courseName to CourseInfo
    private InstructorInfo instructor; // Added instructor info
    private List<StudentInfo> enrolledStudents; // List of students enrolled in this course

    public EnrollmentDTO(Long enrollmentId, CourseInfo course, InstructorInfo instructor, List<StudentInfo> enrolledStudents) {
        this.enrollmentId = enrollmentId;
        this.course = course;
        this.instructor = instructor;
        this.enrolledStudents = enrolledStudents;
    }

    public EnrollmentDTO() {
        // Default constructor
    }

    // Getters and Setters
    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public CourseInfo getCourse() {
        return course;
    }

    public void setCourse(CourseInfo course) {
        this.course = course;
    }

    public InstructorInfo getInstructor() {
        return instructor;
    }

    public void setInstructor(InstructorInfo instructor) {
        this.instructor = instructor;
    }

    public List<StudentInfo> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<StudentInfo> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }
}