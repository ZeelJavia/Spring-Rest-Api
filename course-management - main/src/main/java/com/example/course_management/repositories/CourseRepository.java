package com.example.course_management.repositories;

import com.example.course_management.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.instructor WHERE c.id = :id")
    Course findByIdWithInstructor(@Param("id") Long id);
}
