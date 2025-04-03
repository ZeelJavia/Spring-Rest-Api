package com.example.course_management.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("INSTRUCTOR")
public class Instructor extends User {
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Course> coursesTaught = new ArrayList<>();

    public Instructor() {
        setRole(User.Role.INSTRUCTOR);
    }

    public Instructor(List<Course> coursesTaught) {
        this.coursesTaught = coursesTaught;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "coursesTaught=" + coursesTaught +
                '}';
    }

    public List<Course> getCoursesTaught() {
        return coursesTaught;
    }

    public void setCoursesTaught(List<Course> coursesTaught) {
        this.coursesTaught = coursesTaught;
    }
}