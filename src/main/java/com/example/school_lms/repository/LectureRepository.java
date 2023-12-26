package com.example.school_lms.repository;


import com.example.school_lms.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface LectureRepository extends JpaRepository<Lecture, String> {

    @Override
    ArrayList<Lecture> findAll();

    Lecture findBySubjectId(String subject_id);


//    Lecture findById();

//    Lecture findById(String subject_id);


    // 검색기능
//    List<Posts> findBySubjectNameContaining(String keyword);

//    ArrayList<Lecture> findAllSubjectId(Long subject_id);
}
