package com.example.school_lms.repository;


import com.example.school_lms.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    @Override
    ArrayList<Lecture> findAll();

    Lecture findBySubjectId(Long subjectId);

}