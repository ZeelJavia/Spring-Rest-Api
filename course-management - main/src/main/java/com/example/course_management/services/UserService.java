package com.example.course_management.services;

import com.example.course_management.entity.Admin;
import com.example.course_management.entity.Instructor;
import com.example.course_management.entity.Student;
import com.example.course_management.repositories.AdminRepository;
import com.example.course_management.DTO.InstructorDTO;
import com.example.course_management.repositories.InstructorRepository;
import com.example.course_management.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired private StudentRepository studentRepo;
    @Autowired private InstructorRepository instructorRepo;
    @Autowired private AdminRepository adminRepo;
    @Autowired private PasswordEncoder passwordEncoder; // Inject BCrypt encoder

    // ----- Students -----
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepo.findById(id).orElse(null);
    }

    public Student createStudent(Student student) {
        // Encode password before saving
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepo.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        updatedStudent.setId(id);
        // Encode password if it's being updated
        if (updatedStudent.getPassword() != null) {
            updatedStudent.setPassword(passwordEncoder.encode(updatedStudent.getPassword()));
        }
        return studentRepo.save(updatedStudent);
    }

    public void deleteStudent(Long id) {
        studentRepo.deleteById(id);
    }

    // ----- Instructors -----
    public List<InstructorDTO> getAllInstructors() {
        List<Instructor> instructors = instructorRepo.findAll();
        return instructors.stream()
                .map(InstructorDTO::new)
                .collect(Collectors.toList());
    }

    public Instructor getInstructorById(Long id) {
        return instructorRepo.findById(id).orElse(null);
    }

    public Instructor createInstructor(Instructor instructor) {
        // Encode password before saving
        instructor.setPassword(passwordEncoder.encode(instructor.getPassword()));
        return instructorRepo.save(instructor);
    }

    public Instructor updateInstructor(Long id, Instructor updatedInstructor) {
        updatedInstructor.setId(id);
        // Encode password if it's being updated
        if (updatedInstructor.getPassword() != null) {
            updatedInstructor.setPassword(passwordEncoder.encode(updatedInstructor.getPassword()));
        }
        return instructorRepo.save(updatedInstructor);
    }

    public void deleteInstructor(Long id) {
        instructorRepo.deleteById(id);
    }

    // ----- Admins -----
    public List<Admin> getAllAdmins() {
        return adminRepo.findAll();
    }

    public Admin getAdminById(Long id) {
        return adminRepo.findById(id).orElse(null);
    }

    public Admin createAdmin(Admin admin) {
        // Encode password before saving
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepo.save(admin);
    }

    public Admin updateAdmin(Long id, Admin updatedAdmin) {
        updatedAdmin.setId(id);
        // Encode password if it's being updated
        if (updatedAdmin.getPassword() != null) {
            updatedAdmin.setPassword(passwordEncoder.encode(updatedAdmin.getPassword()));
        }
        return adminRepo.save(updatedAdmin);
    }

    public void deleteAdmin(Long id) {
        adminRepo.deleteById(id);
    }
}