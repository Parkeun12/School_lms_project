package com.example.school_lms.repository;

import com.example.school_lms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {


    Student findById(Student id);
}
