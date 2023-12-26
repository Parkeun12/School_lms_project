package com.example.school_lms.Service;

import com.example.school_lms.repository.LectureDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LectureDetailService {
    @Autowired
    private LectureDetailRepository lectureDetailRepository;
    //상세 강의 등록

}

