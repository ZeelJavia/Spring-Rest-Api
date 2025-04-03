package com.example.course_management.controller;

import com.example.course_management.entity.Enrollment;
import com.example.course_management.Info.CourseInfo;
import com.example.course_management.DTO.EnrollmentDTO;
import com.example.course_management.Info.InstructorInfo;
import com.example.course_management.Info.StudentInfo;
import com.example.course_management.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public List<EnrollmentDTO> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();

        // Group enrollments by course
        Map<Long, List<Enrollment>> enrollmentsByCourseid = enrollments.stream()
                .collect(Collectors.groupingBy(e -> e.getCourse().getId()));
        
        return enrollments.stream()
                // Avoid duplicates - take one enrollment per course
                .filter(e -> e.equals(enrollmentsByCourseid.get(e.getCourse().getId()).get(0)))
                .map(enrollment -> {
                    // Get all enrollments for this course
                    List<Enrollment> courseEnrollments = enrollmentsByCourseid.get(enrollment.getCourse().getId());
                    
                    // Map students to StudentInfo objects
                    List<StudentInfo> enrolledStudents = courseEnrollments.stream()
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
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EnrollmentDTO getEnrollment(@PathVariable Long id) {
        Enrollment enrollment = enrollmentService.getEnrollmentById(id);
        
        // Find all enrollments for the same course
        List<Enrollment> courseEnrollments = enrollmentService.getAllEnrollments().stream()
                .filter(e -> e.getCourse().getId().equals(enrollment.getCourse().getId()))
                .collect(Collectors.toList());
        
        // Map students to StudentInfo objects
        List<StudentInfo> enrolledStudents = courseEnrollments.stream()
                .map(e -> new StudentInfo(e.getStudent()))
                .collect(Collectors.toList());
        
        return new EnrollmentDTO(
                enrollment.getId(),
                enrollment.getCourse() != null ? new CourseInfo(enrollment.getCourse()) : null,
                enrollment.getCourse() != null && enrollment.getCourse().getInstructor() != null ? 
                    new InstructorInfo(enrollment.getCourse().getInstructor()) : null,
                enrolledStudents
        );
    }

    @PostMapping
    public EnrollmentDTO createEnrollment(@RequestBody Enrollment enrollment) {
        // Step 1: Save the enrollment
        Enrollment savedEnrollment = enrollmentService.createAndGetEnrollment(enrollment);
        
        // Step 2: Explicitly load the course with instructor
        Long courseId = savedEnrollment.getCourse().getId();
        
        // Step 3: Reload all enrollments for this course to ensure they're fully loaded
        List<Enrollment> courseEnrollments = enrollmentService.getAllEnrollments().stream()
                .filter(e -> e.getCourse() != null && e.getCourse().getId().equals(courseId))
                .collect(Collectors.toList());
        
        // Step 4: Find the saved enrollment in the fully loaded list
        Enrollment completeEnrollment = courseEnrollments.stream()
                .filter(e -> e.getId().equals(savedEnrollment.getId()))
                .findFirst()
                .orElse(savedEnrollment); // Fallback to original if not found
        
        // Step 5: Create the course info explicitly
        CourseInfo courseInfo = null;
        InstructorInfo instructorInfo = null;
        
        if (completeEnrollment.getCourse() != null) {
            courseInfo = new CourseInfo(
                completeEnrollment.getCourse().getId(),
                completeEnrollment.getCourse().getTitle(),
                completeEnrollment.getCourse().getDescription()
            );
            
            // Step 6: Create the instructor info explicitly
            if (completeEnrollment.getCourse().getInstructor() != null) {
                instructorInfo = new InstructorInfo(completeEnrollment.getCourse().getInstructor());
            }
        }
        
        // Step 7: Map students to StudentInfo objects
        List<StudentInfo> enrolledStudents = courseEnrollments.stream()
                .map(e -> new StudentInfo(e.getStudent()))
                .collect(Collectors.toList());
        
        // Step 8: Create and return the DTO with explicit values
        return new EnrollmentDTO(
                completeEnrollment.getId(),
                courseInfo,
                instructorInfo,
                enrolledStudents
        );
    }

    @PutMapping("/{id}")
    public EnrollmentDTO updateEnrollment(@PathVariable Long id, @RequestBody Enrollment enrollment) {
        // Set the ID to ensure we're updating the correct record
        enrollment.setId(id);
        
        // Step 1: Save the updated enrollment
        Enrollment updatedEnrollment = enrollmentService.updateEnrollment(id, enrollment);
        
        // Step 2: Explicitly load the course with instructor
        Long courseId = updatedEnrollment.getCourse().getId();
        
        // Step 3: Reload all enrollments for this course to ensure they're fully loaded
        List<Enrollment> courseEnrollments = enrollmentService.getAllEnrollments().stream()
                .filter(e -> e.getCourse() != null && e.getCourse().getId().equals(courseId))
                .collect(Collectors.toList());
        
        // Step 4: Find the updated enrollment in the fully loaded list
        Enrollment completeEnrollment = courseEnrollments.stream()
                .filter(e -> e.getId().equals(updatedEnrollment.getId()))
                .findFirst()
                .orElse(updatedEnrollment); // Fallback to original if not found
        
        // Step 5: Create the course info explicitly
        CourseInfo courseInfo = null;
        InstructorInfo instructorInfo = null;
        
        if (completeEnrollment.getCourse() != null) {
            courseInfo = new CourseInfo(
                completeEnrollment.getCourse().getId(),
                completeEnrollment.getCourse().getTitle(),
                completeEnrollment.getCourse().getDescription()
            );
            
            // Step 6: Create the instructor info explicitly
            if (completeEnrollment.getCourse().getInstructor() != null) {
                instructorInfo = new InstructorInfo(completeEnrollment.getCourse().getInstructor());
            }
        }
        
        // Step 7: Map students to StudentInfo objects
        List<StudentInfo> enrolledStudents = courseEnrollments.stream()
                .map(e -> new StudentInfo(e.getStudent()))
                .collect(Collectors.toList());
        
        // Step 8: Create and return the DTO with explicit values
        return new EnrollmentDTO(
                completeEnrollment.getId(),
                courseInfo,
                instructorInfo,
                enrolledStudents
        );
    }

    @DeleteMapping("/{id}")
    public void deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
    }
}