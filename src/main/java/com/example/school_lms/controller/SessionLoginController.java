package com.example.school_lms.controller;

import com.example.school_lms.Role.UserRole;
import com.example.school_lms.Service.BoardService;
import com.example.school_lms.Service.UserService;
import com.example.school_lms.dto.JoinRequest;
import com.example.school_lms.dto.LoginRequest;
import com.example.school_lms.entity.Board;
import com.example.school_lms.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequiredArgsConstructor
@RequestMapping("/visang_university")
public class SessionLoginController {

    private final UserService userService;
<<<<<<< HEAD
=======

//    메인페이지
>>>>>>> main
    @GetMapping(value = {"", "/"})
    public String mainUniversity(Model model, @SessionAttribute(name = "userId", required = false) Long userId) {
        model.addAttribute("loginType", "visang_university");

        User loginUser = userService.getLoginUserById(userId);

        //로그인이 되어있으면 userdataName 뿌려주기
        if(loginUser != null) {
            model.addAttribute("userdataName", loginUser.getUserdataName());
        }

        return "main";
    }

//    회원가입 페이지
    @GetMapping("/join")
    public String joinPage(Model model) {
        model.addAttribute("loginType", "visang_university");

        model.addAttribute("joinRequest", new JoinRequest());
        return "join";
    }

    @PostMapping("/join")
    public String join(@Valid @ModelAttribute JoinRequest joinRequest, BindingResult bindingResult, Model model) {
        model.addAttribute("loginType", "visang_university");

        // 학번과 이름 중복 체크
        if(!userService.checkUserdataDuplicate(joinRequest.getUserdataNum(), joinRequest.getUserdataName())) {
            bindingResult.addError(new FieldError("joinRequest", "userIdNum", "등록된 학번과 이름이 없습니다."));
        }

        // password와 passwordCheck가 같은지 체크
        if(!joinRequest.getUserPassword().equals(joinRequest.getPasswordCheck())) {
            bindingResult.addError(new FieldError("joinRequest", "passwordCheck", "바밀번호가 일치하지 않습니다."));
        }

        if(bindingResult.hasErrors()) {
            return "join";
        }

        userService.join(joinRequest);
        return "redirect:/visang_university";
    }

//    로그인 페이지
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginType", "visang_university");

        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest loginRequest, BindingResult bindingResult,
                        HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("loginType", "visang_university");

        User user = userService.login(loginRequest);

        // 로그인 아이디나 비밀번호가 틀린 경우 global error return
        if(user == null) {
            bindingResult.reject("loginFail", "로그인 아이디 또는 비밀번호가 틀렸습니다.");
        }

        if(bindingResult.hasErrors()) {
            return "login";
        }

        // 로그인 성공 => 세션 생성

        // 세션을 생성하기 전에 기존의 세션 파기
        httpServletRequest.getSession().invalidate();
        HttpSession session = httpServletRequest.getSession(true);  // Session이 없으면 생성
        // 세션에 userId를 넣어줌
        session.setAttribute("userId", user.getId());
        session.setMaxInactiveInterval(1800); // Session이 30분동안 유지

        return "redirect:/visang_university";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        model.addAttribute("loginType", "visang_university");

        HttpSession session = request.getSession(false);  // Session이 없으면 null return
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/visang_university";
    }
<<<<<<< HEAD

    @GetMapping("/info")
    public String userInfo(@SessionAttribute(name = "userId", required = false) Long userId, Model model) {
        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageName", "세션 로그인");

        User loginUser = userService.getLoginUserById(userId);

        if(loginUser == null) {
            return "redirect:/visang_university/login";
        }

        model.addAttribute("user", loginUser);
        return "info";
    }

    //교수 admin으로 잡음
    @GetMapping("/admin")
    public String adminPage(@SessionAttribute(name = "userId", required = false) Long userId, Model model) {
        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageName", "세션 로그인");

        User loginUser = userService.getLoginUserById(userId);

        if(loginUser == null) {
            return "redirect:/visang_university/login";
        }

        if(!loginUser.getRole().equals(UserRole.ADMIN)) {
            return "redirect:/visang_university";
        }

        return "admin";
    }
//
//    public static Hashtable sessionList = new Hashtable();
//
//    @GetMapping("/session-list")
//    @ResponseBody
//    public Map<String, String> sessionList() {
//        Enumeration elements = sessionList.elements();
//        Map<String, String> lists = new HashMap<>();
//        while(elements.hasMoreElements()) {
//            HttpSession session = (HttpSession)elements.nextElement();
//            lists.put(session.getId(), String.valueOf(session.getAttribute("userId")));
//        }
//        return lists;
//    }

    

=======
>>>>>>> main
}