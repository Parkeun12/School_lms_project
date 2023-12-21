package com.example.school_lms.controller;

import com.example.school_lms.Service.TasksService;
import com.example.school_lms.Service.UserService;
import com.example.school_lms.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
@RequestMapping("/visang_university")
public class TasksController {

    private final TasksService tasksService;
    private final UserService userService;

    @GetMapping("tasks")
        public String tasksList(Model model, @SessionAttribute(name = "userId", required = false) Long userId) {
            model.addAttribute("loginType", "visang_university");

            User loginUser = userService.getLoginUserById(userId);

            //로그인이 되어있으면 userdataName 뿌려주기
            if(loginUser != null) {
                model.addAttribute("userdataName", loginUser.getUserdataName());
            }

            return "task";
    }
}
