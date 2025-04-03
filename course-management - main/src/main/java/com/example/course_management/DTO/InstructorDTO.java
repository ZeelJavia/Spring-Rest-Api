package com.example.course_management.DTO;

import com.example.course_management.entity.Instructor;
import java.util.List;

public class InstructorDTO {
    private Long id;
    private String name;
    private String email;
    private List<CourseDTO> coursesTaught;

    // Improved constructor to properly map Course to CourseDTO
    public InstructorDTO(Instructor instructor) {
        this.id = instructor.getId();
        this.name = instructor.getName();
        this.email = instructor.getEmail();
        this.coursesTaught = instructor.getCoursesTaught().stream()
                .map(course -> new CourseDTO(
                        course.getId(),
                        course.getTitle(),
                        course.getDescription(),
                        null // Avoid circular reference
                ))
                .toList();
    }

    // Other constructors and methods remain the same
    public InstructorDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    public InstructorDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CourseDTO> getCoursesTaught() {
        return coursesTaught;
    }

    public void setCoursesTaught(List<CourseDTO> coursesTaught) {
        this.coursesTaught = coursesTaught;
    }

    @Override
    public String toString() {
        return "InstructorDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
