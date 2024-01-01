package com.example.school_lms.repository;

import com.example.school_lms.entity.SelectedLectureList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.ArrayList;

public interface SelectedLectureListRepository extends JpaRepository<SelectedLectureList, Long> {

    SelectedLectureList findBySubjectName(String subject_name);

    @Query(value = "SELECT DISTINCT l.subject_classification, l.subject_name, l.userdata_name, ld.lecture_sel_type, l.subject_id " +
            "FROM lecture l " +
            "JOIN lecture_detail ld " +
            "ON l.subject_id = ld.subject_id", nativeQuery = true)
    ArrayList<SelectedLectureList> findAll();

    @Query(value = "SELECT DISTINCT l.subject_classification, l.subject_name, u.userdata_name, l.subject_id " +
            "FROM lecture l " +
            "JOIN selected_lecture sl " +
            "ON l.subject_id = sl.subject_id " +
            "JOIN user u " +
            "ON l.id = u.id " +
            "WHERE sl.id = :userId", nativeQuery = true)
    ArrayList<SelectedLectureList> findByUserId(@Param("userId") Long userId);

}