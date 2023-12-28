package com.example.school_lms.controller;

//import com.example.school_lms.Service.LectureService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(value = "/lecture")
public class LectureController {

//    @Autowired
//    LectureService lectureService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping(value = "/regist")
    public String lecForm(@SessionAttribute(name = "userId", required = false) Long userId, Model model){

        model.addAttribute ("loginType", "visang_university");
        model.addAttribute ("lecDto",new LectureDto());

        if (userId != null) {
            User user = userService.getLoginUserById(userId);
            if (user != null) {
                model.addAttribute("user", user);
                model.addAttribute("userdataName", user.getUserdataName());

                return "lecture/regist";
            }
        }
        // userId가 없거나 사용자를 찾을 수 없는 경우 로그인 페이지로 리다이렉트
        return "redirect:/visang_university/login";
    }

//    @GetMapping(value = "/regist")
//    public String lecForm(Model model){
//
//
//        User user = userRepository.findByUserdataNum(userdataNum).orElse(null);
//         model.addAttribute("userInfo", );
//
//        return "/lecture/regist";
//    }

    //수강신청 맵핑
//    @PostMapping(value = "/regist")
//    public String lecRegist(LectureDto lectureDto){
//
//        Lecture lecture = LectureService.createLecture(lectureDto);
//        lectureService.saveLecture(lecture);
//
//        return "lecture/regist";
//    }



}
