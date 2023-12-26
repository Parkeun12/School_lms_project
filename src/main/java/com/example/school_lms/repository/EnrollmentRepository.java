package com.example.school_lms.repository;


import com.example.school_lms.entity.SelectedLectureList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface EnrollmentRepository extends CrudRepository<SelectedLectureList, Long> {


    @Override
    ArrayList<SelectedLectureList> findAll();

//    @Query(value = "SELECT l.subject_classification, l.subject_name, l.user_name, ld.lecture_sel_type" +
//            "FROM lecture AS l" +
//            "JOIN lecturedetails AS ld" +
//            "ON l.subject_id = ld.subject_id" , nativeQuery = true)
//    ArrayList<SelectedLecture> findAlls();
//
//
//    ArrayList<SelectedLecture>findBysubject_idAndlecture_sel_id(Long subject_id, String lecture_sel_id);

    //    @Query(value = "SELECT a.*, b.* " +
//            "FROM entity_a a " +
//            "JOIN entity_b b " +
//            "ON a.b_id = b.id " +
//            "WHERE b.some_column = :someValue", nativeQuery = true) 예시쿼리
//    @Query(value = "SELECT NEW com.example.school_lms.dto.ArrayList<SelectedLectureListDto>(l.subject_classification, l.subject_name, l.user_name, ld.lecture_sel_type) " +
//            "FROM lecture l " +
//            "JOIN selectedlecturelist sll " +
//            "ON l.subject_id = sll.subject_id " +
//            "JOIN lecturedetails ld " +
//            "ON sll.subject_id = ld.subject_id ")
//            ArrayList<SelectedLectureListDto> findBySelectedLectureSelectedLecture (Long subject_id);
    @Query(value = "SELECT DISTINCT l.subject_classification, l.subject_name, l.user_name, ld.subject_id, ld.lecture_sel_type " + // subject_id추가
            "FROM lecture l " +
            "JOIN lecturedetails ld " +
            "ON l.subject_id = ld.subject_id", nativeQuery = true)
    ArrayList<SelectedLectureList> findBySelectedLectures();


//@Param("subject_id") Long subject_id

}