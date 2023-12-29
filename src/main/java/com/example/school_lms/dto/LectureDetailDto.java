package com.example.school_lms.dto;

import com.example.school_lms.entity.LectureDetail;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LectureDetailDto {

    private Integer  lectureSelId;         // 세부 강의 번호
    private String lectureSelWeek;     // 주차
    private String lectureSelSession;     // 차시
    private String lectureSelSecTitle;     // 상세 강의 제목
    private String lectureSelType;     // 수업 형태
    private String lectureSelAttend;     // 출석
    private String lectureSelProject;     // 과제
    private String lectureStart;     // 수업 시작일
    private String lectureEnd;     // 수업 종료일
    private String lectureUrl;    // 강의 주소(url)]

    private Long Id;
    private Long subjectId;
    public LectureDetail toEntity(){
        return new LectureDetail(lectureSelId,lectureSelWeek,lectureSelSession,lectureSelSecTitle,lectureSelType,lectureSelAttend,lectureSelProject,lectureStart,lectureEnd,lectureUrl,null,null);
    }
}
