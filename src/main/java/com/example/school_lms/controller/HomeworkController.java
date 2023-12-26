package com.example.school_lms.controller;

import com.example.school_lms.dto.HomeworkForm;
import com.example.school_lms.entity.Homework;
import com.example.school_lms.entity.User;
import com.example.school_lms.repository.HomeworkRepository;
import com.example.school_lms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/visang_university")
public class HomeworkController {

    private final HomeworkRepository homeworkRepository;
    private final UserRepository userRepository;

    @GetMapping("/homework/new")
    public String homeworkNew(){
        return "hwCreate";
    }

    @PostMapping("/homework/create")
    public String createHomework(HomeworkForm form, Model model){

        Homework homework = form.toEntity();

        Homework saved = homeworkRepository.save(homework);

        //강의아이디를 바탕으로 과제목록을 가져와야함
        return "redirect:/homework/list/" + saved.getSubjectDataId();
    }
}
