package com.example.course_management.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {
    private String accessLevel;

    public Admin() {
        setRole(User.Role.ADMIN);
    }

    public Admin(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "accessLevel='" + accessLevel + '\'' +
                '}';
    }
}
