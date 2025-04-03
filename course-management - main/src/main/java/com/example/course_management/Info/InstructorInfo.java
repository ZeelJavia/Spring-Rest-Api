package com.example.course_management.Info;

import com.example.course_management.entity.Instructor;

public class InstructorInfo {
    private Long id;
    private String name;
    private String email;

    // Constructor to extract only needed fields
    public InstructorInfo(Instructor instructor) {
        this.id = instructor.getId();
        this.name = instructor.getName();
        this.email = instructor.getEmail();
    }

    // Getters and setters
    // ...

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
}
