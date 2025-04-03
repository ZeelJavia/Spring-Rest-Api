package com.example.course_management.controller;

import com.example.course_management.entity.Admin;
import com.example.course_management.entity.Instructor;
import com.example.course_management.entity.Student;
import com.example.course_management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/students")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        return ResponseEntity.ok(userService.createStudent(student));
    }
    
    @PostMapping("/instructors")
    public ResponseEntity<Instructor> registerInstructor(@RequestBody Instructor instructor) {
        return ResponseEntity.ok(userService.createInstructor(instructor));
    }
    
    @PostMapping("/admins")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
        return ResponseEntity.ok(userService.createAdmin(admin));
    }
}
