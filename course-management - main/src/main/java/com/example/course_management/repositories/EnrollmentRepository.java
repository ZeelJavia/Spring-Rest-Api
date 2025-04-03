package com.example.course_management.repositories;

import com.example.course_management.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    @Query("SELECT e FROM Enrollment e " +
            "JOIN FETCH e.course c " +
            "LEFT JOIN FETCH c.instructor i")
    List<Enrollment> findAllWithCourseAndInstructor(); // Retrieve enrollments with course and instructor
}
