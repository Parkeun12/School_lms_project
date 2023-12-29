package com.example.school_lms.controller;

import com.example.school_lms.Role.UserRole;
import com.example.school_lms.Service.UserService;
import com.example.school_lms.dto.LectureDetailDto;
import com.example.school_lms.entity.LectureDetail;
import com.example.school_lms.entity.User;
import com.example.school_lms.repository.LectureDetailRepository;
import com.example.school_lms.repository.LectureRepository;
import com.example.school_lms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LectureDetailController {
    @Autowired
    private LectureDetailRepository lectureDetailRepository;

    private final UserService userService;

    // 상세 강의 등록 페이지

    @GetMapping("/lectureDetail/new")
    public String lectureDetailSave(Model model, @SessionAttribute(name = "userId", required = false) Long userId){

        if (userId != null) {
            User user = userService.getLoginUserById(userId);
            if (user != null) {
                model.addAttribute("userdataName", user.getUserdataName());
                model.addAttribute("lectureDetailDto", new LectureDetailDto());

                return "lecture_detailNew";
            }
        }
        // userId가 없거나 사용자를 찾을 수 없는 경우 로그인 페이지로 리다이렉트
        return "redirect:/visang_university/login";
    }
    //     강의 등록 (post)
    @PostMapping("/lectureDetail/create")
    public String createLectureDetail(@ModelAttribute LectureDetailDto lectureDetailDto){

        log.info(lectureDetailDto.toString());
        //1. DTO > Entity로 변환
        LectureDetail lectureDetail = lectureDetailDto.toEntity();
        log.info(lectureDetail.toString());
        //2. 변환된 Entity를 Repository를 통해서 DB에 저장
        LectureDetail saved = lectureDetailRepository.save(lectureDetail);
        log.info(lectureDetailDto.toString());

        return "redirect:/lecture_detail";
    }
    @GetMapping("/lecture_detail")
    public String index(Model model, @SessionAttribute(name = "userId", required = false) Long userId)
    {
        if (userId != null) {
            User user = userService.getLoginUserById(userId);
            if (user != null) {
                model.addAttribute("userdataName", user.getUserdataName());
                //1. 디비에서 Article 테이블에 있는 모든 데이터 가져오기
                ArrayList<LectureDetail> lectureDetailEntityList = lectureDetailRepository.findAll();

                //2. Article 묶음을 모델에 등록( Entity > Model )
                model.addAttribute("lectureDetailList", lectureDetailEntityList);

                if(!user.getRole().equals(UserRole.PROFESSOR)) {
                    return "lecture_detailList2";
                }
                //3. 뷰에 모델 뿌
                return "lecture_detailList";
            }
        }
        // userId가 없거나 사용자를 찾을 수 없는 경우 로그인 페이지로 리다이렉트
        return "redirect:/visang_university/login";
    }
}
