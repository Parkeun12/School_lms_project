package com.example.school_lms.controller;

import com.example.school_lms.Role.UserRole;
import com.example.school_lms.Service.UserService;
import com.example.school_lms.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/visang_university")
public class ProfessorController {

    private final UserService userService;

    //교수화면 만들 때 참고
    @GetMapping("/professor")
    public String adminPage(@SessionAttribute(name = "userId", required = false) Long userId, Model model) {
//        model.addAttribute("loginType", "session-login");
//        model.addAttribute("pageName", "세션 로그인");

        User loginUser = userService.getLoginUserById(userId);

        if(loginUser == null) {
            return "redirect:/visang_university/login";
        }

        if(!loginUser.getRole().equals(UserRole.PROFESSOR)) {
            return "redirect:/visang_university";
        }

        return "";
    }
}