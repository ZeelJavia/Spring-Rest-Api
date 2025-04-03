package com.example.course_management.controller;

import com.example.course_management.entity.Admin;
import com.example.course_management.entity.Instructor;
import com.example.course_management.entity.Student;
import com.example.course_management.DTO.EnrollmentDTO;
import com.example.course_management.DTO.InstructorDTO;
import com.example.course_management.Info.InstructorInfo;
import com.example.course_management.DTO.StudentDTO;
import com.example.course_management.Info.StudentInfo;
import com.example.course_management.Info.CourseInfo;
import com.example.course_management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired private UserService userService;

    // ===== Students =====
    @GetMapping("/students")
    public List<StudentDTO> getAllStudents() {
        return userService.getAllStudents().stream()
                .map(student -> new StudentDTO(
                        student.getId(),
                        student.getName(),
                        student.getEmail(),
                        student.getEnrollments().stream()
                                .map(enrollment -> {
                                    // Find all students enrolled in the same course
                                    List<StudentInfo> enrolledStudents = enrollment.getCourse().getEnrollments().stream()
                                            .map(e -> new StudentInfo(e.getStudent()))
                                            .collect(Collectors.toList());
                                            
                                    return new EnrollmentDTO(
                                            enrollment.getId(),
                                            enrollment.getCourse() != null ? new CourseInfo(enrollment.getCourse()) : null,
                                            enrollment.getCourse() != null && enrollment.getCourse().getInstructor() != null ? 
                                                new InstructorInfo(enrollment.getCourse().getInstructor()) : null,
                                            enrolledStudents
                                    );
                                })
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/students/{id}")
    public StudentDTO getStudent(@PathVariable Long id) {
        Student student = userService.getStudentById(id);
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getEnrollments().stream()
                        .map(enrollment -> {
                            // Find all students enrolled in the same course
                            List<StudentInfo> enrolledStudents = enrollment.getCourse().getEnrollments().stream()
                                    .map(e -> new StudentInfo(e.getStudent()))
                                    .collect(Collectors.toList());
                                    
                            return new EnrollmentDTO(
                                    enrollment.getId(),
                                    enrollment.getCourse() != null ? new CourseInfo(enrollment.getCourse()) : null,
                                    enrollment.getCourse() != null && enrollment.getCourse().getInstructor() != null ? 
                                        new InstructorInfo(enrollment.getCourse().getInstructor()) : null,
                                    enrolledStudents
                            );
                        })
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return userService.createStudent(student);
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return userService.updateStudent(id, student);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable Long id) {
        userService.deleteStudent(id);
    }

    // ===== Instructors =====
    @GetMapping("/instructors")
    public List<InstructorDTO> getAllInstructors() {
        return userService.getAllInstructors();
    }

    @GetMapping("/instructors/{id}")
    public InstructorDTO getInstructor(@PathVariable Long id) {
        Instructor instructor = userService.getInstructorById(id);
        return new InstructorDTO(instructor);
    }

    @PostMapping("/instructors")
    public Instructor createInstructor(@RequestBody Instructor instructor) {
        return userService.createInstructor(instructor);
    }

    @PutMapping("/instructors/{id}")
    public Instructor updateInstructor(@PathVariable Long id, @RequestBody Instructor instructor) {
        return userService.updateInstructor(id, instructor);
    }

    @DeleteMapping("/instructors/{id}")
    public void deleteInstructor(@PathVariable Long id) {
        userService.deleteInstructor(id);
    }

    // ===== Admins =====
    @GetMapping("/admins")
    public List<Admin> getAllAdmins() {
        return userService.getAllAdmins();
    }

    @GetMapping("/admins/{id}")
    public Admin getAdmin(@PathVariable Long id) {
        return userService.getAdminById(id);
    }

    @PostMapping("/admins")
    public Admin createAdmin(@RequestBody Admin admin) {
        return userService.createAdmin(admin);
    }

    @PutMapping("/admins/{id}")
    public Admin updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        return userService.updateAdmin(id, admin);
    }

    @DeleteMapping("/admins/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        userService.deleteAdmin(id);
    }
}