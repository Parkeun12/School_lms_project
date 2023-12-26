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

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LectureService {

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    UserRepository userRepository;

    public static Lecture createLecture(LectureDto lectureDto) {

        Lecture lecture = new Lecture();

        lecture.setSubjectName(lectureDto.getSubjectName());
        lecture.setSubjectScore(lectureDto.getSubjectScore());
        lecture.setSubjectTime(lectureDto.getSubjectTime());
        lecture.setSubjectClassification(lectureDto.getSubjectClassification());
        lecture.setSubjectGrade(lectureDto.getSubjectGrade());
        lecture.setSubjectYear(lectureDto.getSubjectYear());
//        lecture.setSubjectSemester(lectureDto.getSubjectSemester());
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



    // 엔티티를 디티오로 바꿔서 클라이언트에 뿌리기 위한 메서드
    private LectureDto joinLectureAndUser(Lecture lecture){

        LectureDto dto = new LectureDto();
            dto.setSubjectName(lecture.getSubjectName());
            dto.setSubjectScore(lecture.getSubjectScore());
            dto.setSubjectTime(lecture.getSubjectTime());
            dto.setSubjectClassification(lecture.getSubjectClassification());
            dto.setSubjectGrade(lecture.getSubjectGrade());
            dto.setSubjectYear(lecture.getSubjectYear());
    //        lecture.setSubjectSemester(lectureDto.getSubjectSemester());
            dto.setSubjectOutline(lecture.getSubjectOutline());
            dto.setSubjectGoal(lecture.getSubjectGoal());
            dto.setSubjectClass(lecture.getSubjectClass());
            dto.setSubjectDay(lecture.getSubjectDay());
            dto.setSubjectPeriod(lecture.getSubjectPeriod());
            dto.setSubjectBook(lecture.getSubjectBook());
            dto.setSubjectReference(lecture.getSubjectReference());
            dto.setSubjectReqStart(lecture.getSubjectReqStart());
            dto.setSubjectReqEnd(lecture.getSubjectReqEnd());
            dto.setSubjectOpStart(lecture.getSubjectOpStart());
            dto.setSubjectOpEnd(lecture.getSubjectOpEnd());
            dto.setTestMidterm(lecture.getTestMidterm());
            dto.setTestFinal(lecture.getTestFinal());
            dto.setTestProject(lecture.getTestProject());
            dto.setTestTeamPj(lecture.getTestTeamPj());
            dto.setTestAttend(lecture.getTestAttend());
            dto.setUserdataNum(lecture.getUser().getUserdataNum());
            dto.setUserMajor(lecture.getUser().getUserMajor());
            dto.setUserdataName(lecture.getUser().getUserdataName());
            dto.setUserPhone(lecture.getUser().getUserPhone());



            System.out.println(dto.toString());
            return dto;

    }

    // DB에 저장 시 DropDown 박스 셀렉트 된 부분이 자꾸 콤마가 들어가서 수정하기 위한 메서드
    public Lecture saveLecture(Lecture lecture) {

        // 강의 등록 시 셀렉트 박스 value 값에 콤마가 들어가서 콤마 삭제하기 위해 추가함.
        lecture.setSubjectScore(lecture.getSubjectScore() != null ? lecture.getSubjectScore().replace(",","") : null);
        lecture.setSubjectTime(lecture.getSubjectTime() != null ? lecture.getSubjectTime().replace(",","") : null);
        lecture.setSubjectClassification(lecture.getSubjectClassification() != null ? lecture.getSubjectClassification().replace(",","") : null);
        lecture.setSubjectGrade(lecture.getSubjectGrade() != null ? lecture.getSubjectGrade().replace(",","") : null);
        lecture.setSubjectDay(lecture.getSubjectDay() != null ? lecture.getSubjectDay().replace(",","") : null);

        System.out.println(lecture.toString());
        return lectureRepository.save(lecture);

    }

}
