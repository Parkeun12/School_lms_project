package com.example.school_lms.Service;

import com.example.school_lms.dto.LectureDto;
import com.example.school_lms.entity.Lecture;
import com.example.school_lms.entity.User;
import com.example.school_lms.entity.Userdata;
import com.example.school_lms.repository.LectureRepository;
import com.example.school_lms.repository.UserRepository;
import com.example.school_lms.repository.UserdataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LectureService {

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    static UserService userService;

    // 강의 등록 메서드. 등록하다 오류 발생 시 롤백하기 위하여 Transactional 어노테이션 사용.
    @Transactional
    public static Lecture createLecture(LectureDto lectureDto) {

        Lecture lecture = new Lecture();

        lecture.setSubjectName(lectureDto.getSubjectName());
        lecture.setSubjectScore(lectureDto.getSubjectScore());
        lecture.setSubjectTime(lectureDto.getSubjectTime());
        lecture.setSubjectClassification(lectureDto.getSubjectClassification());
        lecture.setSubjectGrade(lectureDto.getSubjectGrade());
        lecture.setSubjectYear(lectureDto.getSubjectYear());
        lecture.setSubjectSemester(lectureDto.getSubjectSemester());
        lecture.setSubjectOutline(lectureDto.getSubjectOutline());
        lecture.setSubjectGoal(lectureDto.getSubjectGoal());
        lecture.setSubjectClass(lectureDto.getSubjectClass());
        lecture.setSubjectDay(lectureDto.getSubjectDay());
        lecture.setSubjectPeriod(lectureDto.getSubjectPeriod());
        lecture.setSubjectBook(lectureDto.getSubjectBook());
        lecture.setSubjectReference(lectureDto.getSubjectReference());
        lecture.setSubjectReqStart(lectureDto.getSubjectReqStart());
        lecture.setSubjectReqEnd(lectureDto.getSubjectReqEnd());
        lecture.setSubjectOpStart(lectureDto.getSubjectOpStart());
        lecture.setSubjectOpEnd(lectureDto.getSubjectOpEnd());
        lecture.setTestMidterm(lectureDto.getTestMidterm());
        lecture.setTestFinal(lectureDto.getTestFinal());
        lecture.setTestProject(lectureDto.getTestProject());
        lecture.setTestTeamPj(lectureDto.getTestTeamPj());
        lecture.setTestAttend(lectureDto.getTestAttend());


        System.out.println(lecture.toString());
        return lecture;
    }




    // DB에 저장 시 DropDown 박스 셀렉트 된 부분이 자꾸 콤마가 들어가서 수정하기 위한 메서드
    public Lecture saveLecture(Lecture lecture) {

        // 강의 등록 시 셀렉트 박스 value 값에 콤마가 들어가서 콤마 삭제하기 위해 추가함.
        lecture.setSubjectScore(lecture.getSubjectScore() != null ? lecture.getSubjectScore().replace(",","") : null);
        lecture.setSubjectTime(lecture.getSubjectTime() != null ? lecture.getSubjectTime().replace(",","") : null);
        lecture.setSubjectClassification(lecture.getSubjectClassification() != null ? lecture.getSubjectClassification().replace(",","") : null);
        lecture.setSubjectGrade(lecture.getSubjectGrade() != null ? lecture.getSubjectGrade().replace(",","") : null);
        lecture.setSubjectDay(lecture.getSubjectDay() != null ? lecture.getSubjectDay().replace(",","") : null);
        lecture.setSubjectSemester(lecture.getSubjectSemester() != null ? lecture.getSubjectSemester().replace(",","") : null);
        System.out.println(lecture.toString());
        return lectureRepository.save(lecture);

    }

}
