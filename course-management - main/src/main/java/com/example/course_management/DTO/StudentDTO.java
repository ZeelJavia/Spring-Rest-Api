package com.example.course_management.DTO;

import java.util.List;

public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private List<EnrollmentDTO> enrollments; // Add enrollments to the DTO

    public StudentDTO(Long id, String name, String email, List<EnrollmentDTO> enrollments) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.enrollments = enrollments;
    }

    // Getters and Setters
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

    public List<EnrollmentDTO> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<EnrollmentDTO> enrollments) {
        this.enrollments = enrollments;
    }
}