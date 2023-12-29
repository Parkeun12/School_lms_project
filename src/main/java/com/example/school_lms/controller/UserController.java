package com.example.school_lms.controller;

import com.example.school_lms.Service.UserService;
import com.example.school_lms.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/visang_university")
public class UserController {

    private final UserService userService;

    @GetMapping("/mypage")
    public String myPage(@SessionAttribute(name = "userId", required = false) Long userId, Model model) {
        model.addAttribute("loginType", "visang_university");

        if (userId != null) {
            User user = userService.getLoginUserById(userId);
            if (user != null) {
                model.addAttribute("user", user);
                model.addAttribute("userdataName", user.getUserdataName());
                return "mypage";
            }
        }
        // userId가 없거나 사용자를 찾을 수 없는 경우 로그인 페이지로 리다이렉트
        return "redirect:/visang_university/login";

//        if (userId != null) {
//            User user = userService.getLoginUserById(userId);
//            if (user != null) {
//
//                return "";
//            }
//        }
//        // userId가 없거나 사용자를 찾을 수 없는 경우 로그인 페이지로 리다이렉트
//        return "redirect:/visang_university/login";
    }


    @PostMapping("/save")
    public String saveUserInfo(@ModelAttribute User user, Model model) {
        model.addAttribute("loginType", "visang_university");

        userService.updateUserInfo(user.getId(), user.getUserSns(), user.getUserPr());

        return "redirect:/visang_university/mypage";
    }
}