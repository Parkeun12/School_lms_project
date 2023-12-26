package com.example.school_lms.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Table(name="lecture_detail")
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

// column에 defualt 값을 설정할 때에 밑의 두개를 같이 써줘야 한다.
@DynamicInsert
@DynamicUpdate
public class LectureDetail {

    @Id
    @Column(name = "lecture_sel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lectureSelId;         // 세부 강의 번호

    @Column(name = "lecture_sel_week")
    private String lectureSelWeek;     // 주차

    @Column(name = "lecture_sel_session")
    private String lectureSelSession;     // 차시

    @Column(name = "lecture_sel_sec_title")
    private String lectureSelSecTitle;     // 상세 강의 제목

    @Column(name = "lecture_sel_type")
    private String lectureSelType;     // 수업 형태

    @Column(name = "lecture_sel_attend")
    private String lectureSelAttend;     // 출석(등록 페이지엔 없음, 기준 확인해서 가져와야하는 지 확인)

    @Column(name = "lecture_sel_project")
    private String lectureSelProject;     // 과제(등록 테이블엔 없음, 과제 테이블에서 끌어와야하는지)

    @Column(name = "lecture_start")
    private String lectureStart;     // 수업 시작일

    @Column(name = "lecture_end")
    private String lectureEnd;     // 수업 종료일

    @Column(name = "lecture_url")
    private String lectureUrl;     // 강의 주소(url)

//    유저 테이블에서 가져와야함
//    @Column(length = 20, nullable = false)
//    private String id;          // 교수님


    //강의 테이블에서 가져와야함
//    @Column(length = 200, nullable = false)
//    private String title;       // 강의 제목

//    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
//    @JoinColumn(name = "cno", referencedColumnName = "no")
//    private Lecture lecture;        // 강의 번호 외래키 지정

}
