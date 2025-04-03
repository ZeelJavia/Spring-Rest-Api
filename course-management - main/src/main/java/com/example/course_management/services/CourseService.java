package com.example.course_management.services;

import com.example.course_management.entity.Course;
import com.example.course_management.entity.Instructor;
import com.example.course_management.repositories.CourseRepository;
import com.example.course_management.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {
    @Autowired private CourseRepository courseRepo;
    @Autowired private InstructorRepository instructorRepo;

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepo.findByIdWithInstructor(id); // Use the custom query
    }

    public Course createCourse(Course course) {
        if (course.getInstructorId() != null) {
            Instructor instructor = instructorRepo.findById(course.getInstructorId())
                    .orElseThrow(() -> new RuntimeException("Instructor not found"));
            course.setInstructor(instructor);
        }
        return courseRepo.save(course);
    }

    public Course updateCourse(Long id, Course updatedCourse) {
        // First, ensure the ID is set correctly
        updatedCourse.setId(id); // 1
        
        // Handle instructorId similar to how it's handled in createCourse
        if (updatedCourse.getInstructorId() != null) {
            Instructor instructor = instructorRepo.findById(updatedCourse.getInstructorId())
                    .orElseThrow(() -> new RuntimeException("Instructor not found"));
            updatedCourse.setInstructor(instructor);
        } else if (updatedCourse.getInstructorId() == null && updatedCourse.getInstructor() == null) {
            // If no instructor info is provided, preserve the existing instructor
            Course existingCourse = getCourseById(id);
            if (existingCourse != null) {
                updatedCourse.setInstructor(existingCourse.getInstructor());
            }
        }
        
        return courseRepo.save(updatedCourse); // 2
    }

    public void deleteCourse(Long id) {
        courseRepo.deleteById(id);
    }
}