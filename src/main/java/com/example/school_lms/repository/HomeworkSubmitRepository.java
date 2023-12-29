package com.example.school_lms.repository;

import com.example.school_lms.entity.HomeworkSubmit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HomeworkSubmitRepository extends JpaRepository<HomeworkSubmit, Long> {

    Optional<HomeworkSubmit> findByHomework_HomeworkIdAndUser_Id(Long homeworkId, Long Id);

}
