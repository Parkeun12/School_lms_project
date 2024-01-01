package com.example.school_lms.controller;

import com.example.school_lms.Service.UserService;
import com.example.school_lms.dto.SelectedLectureDto;
import com.example.school_lms.entity.*;
import com.example.school_lms.repository.*;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;

@Controller
@Slf4j
@RequestMapping("/visang_university")
public class EnrollmentController{

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    SelectedLectureRepository selectedLectureRepository;

    @Autowired
    DetailedLectureRepository detailedLectureRepository;
    @Autowired
    SelectedLectureListRepository selectedLectureListRepository;

    @Autowired
    UserService userService;

    @GetMapping(value = "/classHome")
    public String lecForm(@SessionAttribute(name = "userId", required = false) Long userId, Model model) {

        model.addAttribute("loginType", "visang_university");

        if (userId != null) {
            User user = userService.getLoginUserById(userId);
            if (user != null) {
                model.addAttribute("user", user);
                model.addAttribute("userdataName", user.getUserdataName());

                ArrayList<SelectedLectureList> selectedLectureLists = selectedLectureListRepository.findByUserId(userId);
                log.info(selectedLectureLists.toString());
                model.addAttribute("selectedLectureLists", selectedLectureLists);

//                //공지사항 데이터 전달
//                ArrayList<Notice> notices = noticeRepository.findAll();
//                log.info(notices.toString());
//                model.addAttribute("notices", notices);
//
//                //Q&A 데이터 전달
//                ArrayList<QnA> qnas = qnARepository.findAll();
//                log.info(qnas.toString());
//                model.addAttribute("qnas", qnas);

                return "classHome";
            }
        }
        // userId가 없거나 사용자를 찾을 수 없는 경우 로그인 페이지로 리다이렉트
        return "redirect:/visang_university/login";
    }


    @GetMapping("/enroll") //classHome 페이지에서 수강신청 누르면 수강신청 페이지로 이동
    public String enrollment(Model model, @SessionAttribute(name = "userId", required = false) Long userId){
        User loginUser = userService.getLoginUserById(userId);
        if (loginUser != null){
            model.addAttribute("loginUser", loginUser);
        }
        //lecture 테이블의 데이터를 화면에 뿌려줌.
        ArrayList<Lecture> lectureList = lectureRepository.findAll();
        model.addAttribute("lectureList", lectureList);
        return "enrollment"; // 수강신청 페이지로 이동
    }

    @PostMapping("/enroll/apply/{id}/{subid}") // 수강신청 컨트롤러 수강신청 페이지에서 수강신청 버튼을 누르면 신청한 유저의 아이디와 강의의 아이디가 DB에 저장.
    public String applyLecture(@PathVariable("id") Long id, @PathVariable("subid") Long subid, SelectedLectureDto form){ //id자료형을 int에서 Student타입으로 바꾸고 레포지토리도 자료형을 Student로 바꿈
        //enrollment(수강신청 페이지)에서 수강신청 버튼 누르면 학생의 id값과 선택한 과목의 subject_id값을 받아서 Dto로 보내줌.
        form.setId(id);
        form.setSubjectId(subid);


        SelectedLecture selectedLecture =form.toEntity();
        selectedLectureRepository.save(selectedLecture);
        //DB selected_lecture테이블에 id와 subject_id값 저장 후 리다이렉트

        return "redirect:/visang_university/enroll/selectedLectureList" + '/' + id + '/' + subid;
    } // Home 화면(classHome)으로 리다이렉트


    @GetMapping("/enroll/selectedLectureList/{id}/{subid}")//enrollment 화면에서 수강신청 버튼을 누르면 강의 id와 유저 id를 받아서 해당 화면을 보여줌. 등록은 별도.
    public String selectedList(Model model, @SessionAttribute(name = "userId", required = false) Long userId, @PathVariable("subid") int subid){ // 수강신청한 목록을 보여주는 메소드(기획서 7페이지)

        ArrayList<SelectedLectureList> selectedLectureLists = selectedLectureListRepository.findByUserId(userId);
        model.addAttribute("selectedLectureLists", selectedLectureLists);

//        ArrayList<Notice> notices = noticeRepository.findAll();
//        model.addAttribute("notices", notices);
//
//        ArrayList<QnA> qnas = qnARepository.findAll();
//        model.addAttribute("qnas", qnas);

        return "redirect:/visang_university/classHome";
    }

    @Transactional
    @PostMapping(value = "/enroll/delete/{subid}")
    public String selLecdelete(@SessionAttribute(name = "userId", required = false) Long id, @PathVariable("subid") Long subjectId, Model model){
        log.info(id.toString());
        log.info(subjectId.toString());
        SelectedLecture target = selectedLectureRepository.findByIdAndSubjectId(id, subjectId);
        log.info(target.toString());
        if (target != null) {
            log.info(target.toString());
            selectedLectureRepository.delete(target);
            return "redirect:/visang_university/classHome";
        } else {
            log.warn("삭제 대상이 존재하지 않습니다. id={} subjectId={}", id, subjectId);
            return "redirect:/classHome";
        }
    }




    @GetMapping("/enroll/lecPlan/{Id}") // 수강신청화면(기획서 11페이지) 강의 제목을 누르면 해당 강의의 상세 정보(기획서 12페이지)를 볼 수 있다.
    public String enrollLecturePlan(@PathVariable Long Id, Model model){ // Long을 String으로 바꿨음. lectureRepository수정하면서.
        Long subjectId = Id;
        Lecture lecturePlan = lectureRepository.findBySubjectId(subjectId);
        if (lecturePlan == null) {
            String lecturePlanExcep= "SubjectIdIsNull";
        }
        model.addAttribute("lecturePlan", lecturePlan);

        return "lecturePlan";
    }

}

