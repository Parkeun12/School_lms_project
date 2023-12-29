package com.example.school_lms.controller;

import com.example.school_lms.Role.UserRole;
import com.example.school_lms.Service.UserService;
import com.example.school_lms.dto.HomeworkForm;
import com.example.school_lms.dto.HomeworkSubmitForm;
import com.example.school_lms.entity.*;
import com.example.school_lms.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeworkController {

    private final LectureRepository lectureRepository;
    private final UserService userService;
    private final HomeworkRepository homeworkRepository;
    private final UserRepository userRepository;
    private final HomeworkSubmitRepository homeworkSubmitRepository;

    @GetMapping("/homework/create")
    public String showCreateHomeworkForm(@SessionAttribute(name = "userId", required = false) Long userId, Model model) {
        User user = userService.getLoginUserById(userId);
        model.addAttribute("homeworkForm", new HomeworkForm());
        model.addAttribute("userdataName", user.getUserdataName());
        return "hwCreate";
    }

    @PostMapping("/homework/create")
    public String createHomework(
            HomeworkForm homeworkForm,
            @SessionAttribute(name = "userId", required = false) Long userId) {
        User loginUser = userService.getLoginUserById(userId);

        Long subjectId = 1L;
        Lecture lecture = lectureRepository.findById(subjectId).orElse(null);

        // 강의 정보를 HomeworkForm에 설정
        homeworkForm.setSubjectId(lecture.getSubjectId());

        // Homework 생성 및 저장
        Homework homework = homeworkForm.toEntity();

        // Homework에 사용자와 강의 데이터 설정
        homework.setUser(loginUser);
        homework.setSubjectId(lecture);

        // Homework 저장
        Homework savedHomework = homeworkRepository.save(homework);

        // 과제 등록 후 리다이렉트 또는 다른 작업을 수행할 수 있음
        return "redirect:/homework/list";
    }


    @GetMapping("/homework/list")
    public String homeworkList(@SessionAttribute(name = "userId", required = false) Long userId, Model model){

        User user = userService.getLoginUserById(userId);
        ArrayList<Homework> homeworkData = homeworkRepository.findAll();
//        ArrayList<Homework> homeworkData = homeworkRepository.findHomeworkTypeHomeworkStartHomeworkEndHomeworkTitle;

        if(!user.getRole().equals(UserRole.PROFESSOR)) {
            model.addAttribute("userdataName", user.getUserdataName());
            model.addAttribute("homeworks", homeworkData);

            return "hwListStudent";
        }

        model.addAttribute("userdataName", user.getUserdataName());
        model.addAttribute("homeworks", homeworkData);

        return "hwList";
    }

    @GetMapping("/homework/submit/{homeworkId}")
    public String homeworkSubmit(
            @PathVariable(name = "homeworkId") Long homeworkId,
            @SessionAttribute(name = "userId", required = false) Long userId,
            Model model) {

        // 사용자 정보 추가 (예: userdataName)
        User user = userService.getLoginUserById(userId);
        model.addAttribute("userdataName", user.getUserdataName());

        if(!user.getRole().equals(UserRole.PROFESSOR)) {

            // homeworkId에 해당하는 숙제 데이터만 가져오기
            Homework homework = homeworkRepository.findById(homeworkId).orElse(null);

            // homeworkId에 해당하는 숙제 데이터가 없다면 예외 처리 또는 적절한 로직 추가
            if (homework == null) {
                // 숙제 데이터가 없는 경우
                return "/homework/list";
            }

            // 해당 숙제 데이터를 모델에 추가
            model.addAttribute("homework", homework);

            // 해당 숙제에 대한 제출 정보 가져오기
            Optional<HomeworkSubmit> existingSubmit = homeworkSubmitRepository.findByHomework_HomeworkIdAndUser_Id(homeworkId, userId);

            if (existingSubmit.isPresent()) {
// 기존 제출 정보가 있는 경우
                HomeworkSubmitForm homeworkSubmitForm = new HomeworkSubmitForm();
                HomeworkSubmit submit = existingSubmit.get();
                homeworkSubmitForm.setHomeworkSubContent(submit.getHomeworkSubContent());
                homeworkSubmitForm.setHomeworkSubFile(submit.getHomeworkSubFile());

// 파일 이름을 모델에 추가
                model.addAttribute("submittedFileName", submit.getHomeworkSubFile());

// 모델에 homeworkSubmitForm을 추가
                model.addAttribute("homeworkSubmitForm", homeworkSubmitForm);
            } else {
                // 기존 제출 정보가 없는 경우
                // 모델에 빈 homeworkSubmitForm 추가
                model.addAttribute("homeworkSubmitForm", new HomeworkSubmitForm());
            }

            // 다른 모델 데이터 추가
            ArrayList<Homework> homeworkData = homeworkRepository.findAll();
            model.addAttribute("homeworks", homeworkData);

            return "hwSubmitStudent";
        }

        // homeworkId에 해당하는 숙제 데이터만 가져오기
        Homework homework = homeworkRepository.findById(homeworkId).orElse(null);

        // homeworkId에 해당하는 숙제 데이터가 없다면 예외 처리 또는 적절한 로직 추가
        if (homework == null) {
            // 숙제 데이터가 없는 경우
            return "/homework/list";
        }

        // 해당 숙제 데이터를 모델에 추가
        model.addAttribute("homework", homework);

//        // 해당 숙제에 대한 제출 정보 가져오기
//        Optional<HomeworkSubmit> existingSubmit = homeworkSubmitRepository.findByHomework_HomeworkIdAndUser_Id(homeworkId, userId);
//
//        if (existingSubmit.isPresent()) {
//// 기존 제출 정보가 있는 경우
//            HomeworkSubmitForm homeworkSubmitForm = new HomeworkSubmitForm();
//            HomeworkSubmit submit = existingSubmit.get();
//            homeworkSubmitForm.setHomeworkSubContent(submit.getHomeworkSubContent());
//            homeworkSubmitForm.setHomeworkSubFile(submit.getHomeworkSubFile());
//
//// 파일 이름을 모델에 추가
//            model.addAttribute("submittedFileName", submit.getHomeworkSubFile());
//
//// 모델에 homeworkSubmitForm을 추가
//            model.addAttribute("homeworkSubmitForm", homeworkSubmitForm);
//        } else {
//            // 기존 제출 정보가 없는 경우
//            // 모델에 빈 homeworkSubmitForm 추가
//            model.addAttribute("homeworkSubmitForm", new HomeworkSubmitForm());
//        }

        // 다른 모델 데이터 추가
        ArrayList<Homework> homeworkData = homeworkRepository.findAll();
        model.addAttribute("homeworks", homeworkData);


        return "hwSubmit";
    }

    @PostMapping("/homework/submit/{homeworkId}")
    public String homeworkSubmitCreate(
            HomeworkSubmitForm homeworkSubmitForm,
            @PathVariable(name = "homeworkId") Long homeworkId,
            @SessionAttribute(name = "userId", required = false) Long userId) {

        User loginUser = userService.getLoginUserById(userId);
        Homework homework = homeworkRepository.findById(homeworkId).orElse(null);

        if (homework == null) {
            // 과제가 없는 경우 예외 처리 또는 적절한 로직 추가
            return "redirect:/homework/list";
        }

        Optional<HomeworkSubmit> existingSubmit = homeworkSubmitRepository.findByHomework_HomeworkIdAndUser_Id(homeworkId, userId);

        if (existingSubmit.isPresent()) {
            // 기존 제출 정보가 있는 경우 업데이트
            HomeworkSubmit submitToUpdate = existingSubmit.get();
            submitToUpdate.setHomeworkSubContent(homeworkSubmitForm.getHomeworkSubContent());
            // 파일 업데이트를 위해 필요한 부분을 추가하세요
             submitToUpdate.setHomeworkSubFile(homeworkSubmitForm.getHomeworkSubFile());
            // 다른 필드 업데이트...

            homeworkSubmitRepository.save(submitToUpdate);
        } else {
            // 기존 제출 정보가 없는 경우 새로운 제출 정보 등록
            HomeworkSubmit homeworkSubmit = homeworkSubmitForm.toEntity();
            homeworkSubmit.setUser(loginUser);
            homeworkSubmit.setHomework(homework);
            // 파일 정보를 저장하기 위한 부분을 추가하세요
             homeworkSubmit.setHomeworkSubFile(homeworkSubmitForm.getHomeworkSubFile());

            homeworkSubmitRepository.save(homeworkSubmit);
        }

        return "redirect:/homework/submit/" + homeworkId;
    }



}