package com.example.school_lms.repository;


import com.example.school_lms.entity.SelectedLectureList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface EnrollmentRepository extends CrudRepository<SelectedLectureList, Long> {


    @Override
    ArrayList<SelectedLectureList> findAll();



}