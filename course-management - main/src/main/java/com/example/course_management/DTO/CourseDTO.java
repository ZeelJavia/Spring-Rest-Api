package com.example.course_management.DTO;

import com.example.course_management.entity.Course;
import com.example.course_management.Info.InstructorInfo;

public class CourseDTO {
    private Long courseId;
    private String title;
    private String description;
    private InstructorInfo instructor; // Changed from InstructorDTO to InstructorInfo

    public CourseDTO(Long courseId, String title, String description, InstructorInfo instructor) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.instructor = instructor;
    }

    // Constructor for Course and Instructor mapping
    public CourseDTO(Course course) {
        this.courseId = course.getId();
        this.title = course.getTitle();
        this.description = course.getDescription();
        if (course.getInstructor() != null) {
            this.instructor = new InstructorInfo(course.getInstructor());
        }
    }

    // Fix this constructor to actually set the fields
    public CourseDTO(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Getters and Setters
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public InstructorInfo getInstructor() {
        return instructor;
    }
    
    public void setInstructor(InstructorInfo instructor) {
        this.instructor = instructor;
    }
}