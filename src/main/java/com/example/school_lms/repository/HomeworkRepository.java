package com.example.school_lms.repository;

import com.example.school_lms.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {

    @Override
    ArrayList<Homework> findAll();

//    @Query(value = "", nativeQuery = true)
//    ArrayList<Homework> findHomeworkTypeHomeworkStartHomeworkEndHomeworkTitle();

}
