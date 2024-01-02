package com.example.school_lms.controller;

import com.example.school_lms.Service.LectureService;
import com.example.school_lms.Service.UserService;
import com.example.school_lms.dto.LectureDto;
import com.example.school_lms.entity.Lecture;
import com.example.school_lms.entity.User;
import com.example.school_lms.repository.UserRepository;
import com.example.school_lms.repository.UserdataRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/visang_university" )
public class LectureController {

    @Autowired
    LectureService lectureService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/lecture/regist")
    public String lecForm(@SessionAttribute(name = "userId", required = false) Long userId, Model model){

        model.addAttribute ("loginType", "visang_university");


        if (userId != null) {
            User user = userService.getLoginUserById(userId);
            if (user != null) {
                model.addAttribute("user", user);
                model.addAttribute("userdataName", user.getUserdataName());

                LectureDto lectureDto = new LectureDto();
                model.addAttribute("lectureDto", lectureDto);

                return "/lecture_regist";
            }
        }
        // userId가 없거나 사용자를 찾을 수 없는 경우 로그인 페이지로 리다이렉트
        return "redirect:/visang_university/login";
    }


    //강의등록 맵핑
    @PostMapping(value = "/lecture/regist")
    public String lecRegist(LectureDto lectureDto,
                            @SessionAttribute (name="userId") Long userId, Model model){

        //User 정보 가져오기
        User user = userService.getLoginUserById(userId);

        //Lecture 객체 생성 및 정보 설정
        Lecture lecture = LectureService.createLecture(lectureDto);

        int sumPercent = lectureDto.getTestMidterm()
                + lectureDto.getTestFinal()
                + lectureDto.getTestProject()
                + lectureDto.getTestTeamPj()
                + lectureDto.getTestAttend();

        // 100넘겨서 경고말이 하단에 빨간색으로 뜨더라도 드롭다운 박스 빼고 나머지는 입력하던 값 그대로 나옴.
        if(sumPercent > 100) {
            model.addAttribute("showWarning", true);
            model.addAttribute("warningMessage", "평가 비율의 총 합은 100이 되어야 합니다.");
            model.addAttribute("lectureDto", lectureDto);
            model.addAttribute("user", user);  // Add user for rendering on the same page
            model.addAttribute("userdataName", user.getUserdataName());
            model.addAttribute("loginType", "visang_university");

            return "lecture_regist";
        }

        // 100넘겨서 경고말이 하단에 빨간색으로 뜨더라도 드롭다운 박스 빼고 나머지는 입력하던 값 그대로 나옴.
        if(sumPercent < 100) {
            model.addAttribute("showWarning", true);
            model.addAttribute("warningMessage", "평가 비율의 총 합은 100이 되어야 합니다.");
            model.addAttribute("lectureDto", lectureDto);
            model.addAttribute("user", user);  // Add user for rendering on the same page
            model.addAttribute("userdataName", user.getUserdataName());
            model.addAttribute("loginType", "visang_university");

            return "lecture_regist";
        }


        // User 정보 설정 // Join 해놔서 User세팅을 할 수 있음
        lecture.setUserId(user);
        lecture.setUserdataName(user.getUserdataName());
        lecture.setUserPhone(user.getUserPhone());
        lecture.setUserMajor(user.getUserMajor());


        // Lecture 저장
        lectureService.saveLecture(lecture);

        return "redirect:/visang_university/classHome";
    }



}