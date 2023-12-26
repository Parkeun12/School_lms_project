package com.example.school_lms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "lecture")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lecture { // 강의 테이블

    @Id
    @Column(name = "subject_id")
    private String subjectId; //강의 아이디


    @ManyToOne
    @JoinColumn(name="user_id")
    private  Professor userId; // 교수아이디

    @Column
    private String subjectName; //교과목명
    @Column
    private String userMajor; // 교수 학과
    @Column
    private String subjectScore; // 학점
    @Column
    private String subjectTime; // 시간
    @Column
    private String subjectClassification; // 이수구분(일반1, 전공2, 교양3)
    @Column
    private String subjectGrade; // 대상학년
    @Column
    private int subjectYear; // 개설년도
    @Column
    private String subjectSemester; // 학기
    @Column
    private String userName; // 담당교수
    @Column
    private String userPhone; // 담당교수 연락처
    @Column
    private String subjectOutline; // 교과목 개요
    @Column
    private String subjectGoal; // 교과 교육 목표
    @Column
    private String subjectClass; // 강의실
    @Column
    private int subjectDay; // 강의요일
    @Column
    private String subjectPeriod; // ex) 1-4교시
    @Column
    private String subjectBook; // 주교재
    @Column
    private String subjectReference; // 참고 문헌 및 관련 인터넷 사이트
    @Column
    private Date subjectReqStart; // 신청 시작일
    @Column
    private Date subjectReqEnd; // 신청 종료일
    @Column
    private Date subjectOpStart; // 학기 시작일
    @Column
    private Date subjectOpEnd; // 학기 종료일
    @Column
    private int testMidterm; // 중간고사
    @Column
    private int testFinal; // 기말고사
    @Column
    private int testProject; // 과제
    @Column
    private int testTeamPj; // 팀플
    @Column
    private int testAttend; // 출석




}
