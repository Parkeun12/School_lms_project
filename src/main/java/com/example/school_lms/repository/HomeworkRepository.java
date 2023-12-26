package com.example.school_lms.repository;

import com.example.school_lms.entity.Homework;
import com.example.school_lms.entity.Userdata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
}
