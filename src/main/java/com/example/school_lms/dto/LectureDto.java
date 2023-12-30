package com.example.school_lms.dto;

import com.example.school_lms.entity.Lecture;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LectureDto {

    private Long subjectId; // 강의 아이디

    private String userdataNum; // 교수님 아이디(학번)

    private String subjectName; // 교과목명

    private String userMajor; // 교수님 학과

    private String subjectScore; // 학점

    private String subjectTime; // 시간

    private String subjectClassification; // 이수구분

    private String subjectGrade; //대상 학년

    private int subjectYear; //개설년도

    private String subjectSemester; //학기

    private String userdataName; // 교수님 성함

    private String userPhone; // 교수님 연락처

    private String subjectOutline; // 교과목 개요

    private String subjectGoal; // 교과 교육 목표

    private String subjectClass; // 강의실

    private String subjectDay; // 강의 요일

    private String subjectPeriod; // 강의 해당 교시

    private String subjectBook; // 강의 교재

    private String subjectReference; // 참고문헌 및 관련 인터넷 사이트

    private LocalDate subjectReqStart; // 신청 시작일

    private LocalDate subjectReqEnd; // 신청 종료일

    private LocalDate subjectOpStart; // 학기 시작일

    private LocalDate subjectOpEnd; // 학기 종료일

    // 여기서부터는 학점 평가 비율
    private int testMidterm; // 중간고사 비율

    private int testFinal; // 기말고사 비율

    private int testProject; // 과제 비율

    private int testTeamPj; // 팀Pj 비율

    private int testAttend; // 출석 비율


    public Lecture createLecture() {
        return new Lecture();
    }
}
