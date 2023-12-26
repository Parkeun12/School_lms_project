package com.example.school_lms.repository;

import com.example.school_lms.entity.LectureDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface DetailedLectureRepository extends JpaRepository<LectureDetail, String> {

    @Query(value = "select lecture_sel_id, lecture_end, lecture_sel_attend, lecture_sel_project, lecture_sel_sec_title, lecture_sel_session, lecture_sel_type, lecture_sel_week, lecture_start, lecture_url, subject_id, user_id " +
            "from lecturedetails " +
            "where subject_id=?", nativeQuery = true)

    ArrayList<LectureDetail> findBySubjectId(String subject_id);

}
