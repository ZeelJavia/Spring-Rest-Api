package com.example.course_management.controller;

import com.example.course_management.entity.Course;
import com.example.course_management.DTO.CourseDTO;
import com.example.course_management.Info.InstructorInfo;
import com.example.course_management.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired private CourseService courseService;

    // Get all courses (with instructor details)
    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses().stream()
                .map(course -> new CourseDTO(
                        course.getId(),
                        course.getTitle(),
                        course.getDescription(),
                        course.getInstructor() != null ? new InstructorInfo(course.getInstructor()) : null
                ))
                .collect(Collectors.toList());
    }

    // Get a single course (with instructor details)
    @GetMapping("/{id}")
    public CourseDTO getCourse(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);

        return new CourseDTO(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getInstructor() != null ? new InstructorInfo(course.getInstructor()) : null
        );
    }

    @PostMapping
    public CourseDTO createCourse(@RequestBody Course course) {
        Course savedCourse = courseService.createCourse(course);
        return new CourseDTO(
                savedCourse.getId(),
                savedCourse.getTitle(),
                savedCourse.getDescription(),
                savedCourse.getInstructor() != null ? new InstructorInfo(savedCourse.getInstructor()) : null
        );
    }

    @PutMapping("/{id}")
    public CourseDTO updateCourse(@PathVariable Long id, @RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(id, course);
        return new CourseDTO(
                updatedCourse.getId(),
                updatedCourse.getTitle(),
                updatedCourse.getDescription(),
                updatedCourse.getInstructor() != null ? new InstructorInfo(updatedCourse.getInstructor()) : null
        );
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
}