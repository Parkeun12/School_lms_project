package com.example.school_lms.dto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class SelectedLectureListDto {


    private String subjectId;
    private String subjectClassification;
    private String userName;
    private int lectureSelType;

    public SelectedLectureListDto(String subject_id, String subject_classification, String user_name, int lecture_sel_type) {

        this.subjectId = subject_id;
        this.subjectClassification = subject_classification;
        this.userName = user_name;
        this.lectureSelType = lecture_sel_type;
    }

//    public SelectedLectureListDto(Object[] tuple) {
//        // Assuming tuple[0] is of type int
//        this.subjectClassification = String.valueOf(tuple[0]);
//        this.subjectId = (String) tuple[1];
//        this.userName = (String) tuple[2];
//        // Assuming tuple[3] is of type int
//        this.lectureSelType = (int) tuple[3];
//    }

    public SelectedLectureListDto() {
        // 기본 생성자
    }
}
