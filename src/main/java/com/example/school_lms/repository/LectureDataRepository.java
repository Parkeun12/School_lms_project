package com.example.school_lms.repository;

import com.example.school_lms.entity.LectureData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureDataRepository extends JpaRepository<LectureData, Long> {

}