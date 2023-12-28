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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class HomeworkController {

    private final LectureRepository lectureRepository;
    private final LectureDataRepository lectureDataRepository;
    private final UserService userService;
    private final HomeworkRepository homeworkRepository;
    private final UserRepository userRepository;
    private final HomeworkSubmitRepository homeworkSubmitRepository;

    @GetMapping("/homework/create")
    public String showCreateHomeworkForm(@SessionAttribute(name = "userId", required = false) Long userId, Model model) {
        User user = userService.getLoginUserById(userId);

        model.addAttribute("homeworkForm", new HomeworkForm());
//        위에 로그인했을 시 이름표기부분
        model.addAttribute("userdataName", user.getUserdataName());
        return "hwCreate";
    }

    @PostMapping("/homework/create")
    public String createHomework(
            HomeworkForm homeworkForm,
            @SessionAttribute(name = "userId", required = false) Long userId,
            @PathVariable(name = "subjectId") Long subjectId) {
        User loginUser = userService.getLoginUserById(userId);

        // 강의 정보 가져오기 (homeworkForm에서 lectureDataId를 가져와서 사용)
        // 더미데이터 임시사용
        Long lectureDataId = 1L;
        LectureData lectureData = lectureDataRepository.findById(lectureDataId).orElse(null);

        if (!loginUser.getRole().equals(UserRole.PROFESSOR)) {
            // 교수가 아닌 경우 또는 로그인 사용자가 없는 경우
            return "redirect:/visang_university"; // 또는 다른 처리를 수행하도록 수정
        }

        // 강의 정보를 HomeworkForm에 설정
        homeworkForm.setSubjectId(lectureData.getSubjectDataId());

        // Homework 생성 및 저장
        Homework homework = homeworkForm.toEntity();
//
//        // 사용자와 강의 데이터 저장
//        User user = loginUser; // 사용자 정보는 이미 로그인 정보에서 가져옴
//        // 변경사항이 있다면 반영하여 저장
//        user = userRepository.save(user); // 사용자 정보 저장


//        여기아래랑 homework.setSubjectId부분 활성화해야함 오류로 활성화 안해둠
//        Lecture savedLecture = lectureRepository.save(lecture); // 강의 데이터 저장

        // Homework에 사용자와 강의 데이터 설정
        homework.setUser(loginUser);
//        homework.setSubjectId(savedLecture);

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

        // homeworkId에 해당하는 숙제 데이터만 가져오기
        Homework homework = homeworkRepository.findById(homeworkId).orElse(null);

        // homeworkId에 해당하는 숙제 데이터가 없다면 예외 처리 또는 적절한 로직 추가
        if (homework == null) {
            // 숙제 데이터가 없는 경우
            return "/homework/list";
        }

        // 해당 숙제 데이터를 모델에 추가
        model.addAttribute("homework", homework);

        // 모델에 homeworkSubmitForm을 빈 객체로 추가
        HomeworkSubmitForm homeworkSubmitForm = new HomeworkSubmitForm();
        homeworkSubmitForm.setHomeworkId(homeworkId); // 해당 숙제 ID 설정
        model.addAttribute("homeworkSubmitForm", homeworkSubmitForm);

        ArrayList<Homework> homeworkData = homeworkRepository.findAll();
        model.addAttribute("homeworks", homeworkData);

        return "hwSubmit";
    }


    @PostMapping("/homework/submit/{homeworkId}")
    public String homeworkSubmitCreate(
            HomeworkSubmitForm homeworkSubmitForm,
            @PathVariable(name = "homeworkId") Long homeworkId,
            @SessionAttribute(name = "userId", required = false) Long userId){

        User loginUser = userService.getLoginUserById(userId);

        Homework homework = homeworkRepository.findById(homeworkId).orElse(null);

        //과제 정보를 homeworkForm에 설정
        homeworkSubmitForm.setHomeworkId(homework.getHomeworkId());

        HomeworkSubmit homeworkSubmit = homeworkSubmitForm.toEntity();

        //과제 데이터 저장
        Homework savedHomework = homeworkRepository.save(homework);

        homeworkSubmit.setUser(loginUser);
        homeworkSubmit.setHomework(savedHomework);

        HomeworkSubmit savedHomeworkSubmit = homeworkSubmitRepository.save(homeworkSubmit);

        return "redirect:/homework/submit/" + homeworkId;
    }

}
