package com.example.course_management.services;

import com.example.course_management.entity.Enrollment;
import com.example.course_management.repositories.EnrollmentRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EnrollmentService {
    @Autowired private EnrollmentRepository enrollmentRepo;

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepo.findAllWithCourseAndInstructor();
    }

    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepo.findById(id).orElse(null);
    }

    public Enrollment createEnrollment(Enrollment enrollment) {
        return enrollmentRepo.save(enrollment);
    }

    @Transactional
    public Enrollment createAndGetEnrollment(Enrollment enrollment) {
        Enrollment saved = enrollmentRepo.save(enrollment);
        return enrollmentRepo.findById(saved.getId()).orElse(null);
    }

    public void deleteEnrollment(Long id) {
        enrollmentRepo.deleteById(id);
    }

    @Transactional
    public Enrollment updateEnrollment(Long id, Enrollment enrollment) {
        // Check if enrollment exists
        enrollmentRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Enrollment not found with id: " + id));
        
        // Ensure the ID is set correctly
        enrollment.setId(id);
        
        // Save the updated enrollment
        Enrollment saved = enrollmentRepo.save(enrollment);
        
        // Return the updated enrollment, fetched fresh to ensure all relations are loaded
        return enrollmentRepo.findById(saved.getId()).orElse(null);
    }
}