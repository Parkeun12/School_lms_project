package com.example.school_lms.repository;

import com.example.school_lms.entity.SelectedLecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public interface SelectedLectureRepository extends JpaRepository<SelectedLecture, Long> {

    @Override
    ArrayList<SelectedLecture> findAll();

    SelectedLecture findByIdAndSubjectId(@Param("id") Long id, @Param("subjectId") Long subjectId);

    SelectedLecture findBySubjectId(@Param("subid") Long subid);

}
