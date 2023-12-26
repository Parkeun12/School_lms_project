package com.example.school_lms.repository;

import com.example.school_lms.entity.SelectedLecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedLectureRepository extends JpaRepository<SelectedLecture, Long> {
}
