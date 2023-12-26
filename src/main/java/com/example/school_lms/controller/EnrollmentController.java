package com.example.school_lms.controller;

import com.example.school_lms.entity.Lecture;
import com.example.school_lms.entity.LectureDetail;
import com.example.school_lms.entity.SelectedLectureList;
import com.example.school_lms.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
@Slf4j
public class EnrollmentController {
    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    SelectedLectureRepository selectedLectureRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    DetailedLectureRepository detailedLectureRepository;



    @GetMapping("/enroll") // 수강신청 페이지가 열리면 등록된 강의 리스트를 불러온다.
    public String enrollment(Model model){
        // 1. url요청을 받으면 페이지에 강의에 대한 데이터 전달받음
        ArrayList<Lecture> lectureList = lectureRepository.findAll();

        model.addAttribute("lectureList", lectureList);

        return "enrollment";
    }


    @GetMapping("/enroll/selectedList")
    public String selectedList(Model model){ // 수강신청한 목록을 보여주는 메소드(기획서 7페이지)
        ArrayList<SelectedLectureList> selectedLectureList = enrollmentRepository.findBySelectedLectures();

        model.addAttribute("selectedLectureList", selectedLectureList);

        return "selectedlecturelist";
    }

    @GetMapping("/enroll/selectedList/{subjectId}")
    public String SelectedDetail(@PathVariable String subjectId, Model model){ // 선택된 강의 목록에서 강의를 클릭하면 세부강의가 나온다(LectureDetails테이블에 해당하는 내용이다. 기획서 14페이지)
        log.info(subjectId.toString());
        ArrayList<LectureDetail> lectureDetails = detailedLectureRepository.findBySubjectId(subjectId);
        log.info(lectureDetails.toString());
        model.addAttribute("lectureDetails", lectureDetails);
        log.info("info log={}",lectureDetails.toString());

        return "detailedLecture";
    }

    @GetMapping("/enroll/lecPlan/{Id}") // 수강신청화면(기획서 11페이지) 강의 제목을 누르면 해당 강의의 상세 정보(기획서 12페이지)를 볼 수 있다.
    public String enrollLecturePlan(@PathVariable String Id, Model model){ // Long을 String으로 바꿨음. lectureRepository수정하면서.
        String subjectId = Id;
        Lecture lecturePlan = lectureRepository.findBySubjectId(subjectId);
        if (lecturePlan == null) {
            String lecturePlanExcep= "SubjectIdIsNull";
        }
        model.addAttribute("lecturePlan", lecturePlan);

        return "lecturePlan";
    }

    @PostMapping("/enroll/apply") // 수강신청 화면에서 신청 버튼을 누르면 신청한 유저의 아이디와 강의의 아이디가 DB에 저장.
    public String applyLecture(){ //id자료형을 int에서 Student타입으로 바꾸고 레포지토리도 자료형을 Student로 바꿈



        return "redirect:/enroll/selectedList";
    }

}

