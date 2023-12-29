package com.example.school_lms.repository;

import com.example.school_lms.Role.UserRole;
import com.example.school_lms.entity.LectureDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface LectureDetailRepository extends JpaRepository<LectureDetail, Integer> {
    @Override
    ArrayList<LectureDetail> findAll();
    // 강의 테이블 pk로 강의 리스트 추출해야함
    @Query(value = "select lecture_sel_id, lecture_end, lecture_sel_attend, lecture_sel_project, lecture_sel_sec_title, lecture_sel_session, lecture_sel_type, lecture_sel_week, lecture_start, lecture_url, subject_id, id " +
            "from lecture_detail " +
            "where subject_id=?", nativeQuery = true)
    ArrayList<LectureDetail> findBySubjectId(Long subject_id);
    @Query(value = "select lecture_sel_id, lecture_end, lecture_sel_attend, lecture_sel_project, lecture_sel_sec_title, lecture_sel_session, lecture_sel_type, lecture_sel_week, lecture_start, lecture_url, subject_id, id " +
            "from lecture_detail " +
            "where userdataName=?", nativeQuery = true)
    ArrayList<LectureDetail> findById(Long id);
}
