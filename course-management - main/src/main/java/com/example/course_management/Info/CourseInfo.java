package com.example.course_management.Info;

import com.example.course_management.entity.Course;

public class CourseInfo {
    private Long courseId;
    private String title;
    private String description; // Added description field

    // Constructor to extract only needed fields
    public CourseInfo(Course course) {
        this.courseId = course.getId();
        this.title = course.getTitle();
        this.description = course.getDescription();
    }

    // Constructor with specific values
    public CourseInfo(Long courseId, String title, String description) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
    }

    // Default constructor
    public CourseInfo() {
    }

    // Getters and setters
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
}
