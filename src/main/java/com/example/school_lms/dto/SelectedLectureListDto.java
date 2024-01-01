package com.example.school_lms.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class SelectedLectureListDto {


    private String subjectName; // 교과목명

    private String subjectClassification;  //구분

    private String userdataName;  // 담당교수 이름으로


    public SelectedLectureListDto(String subject_name, String subject_classification, String userdata_name) {

        this.subjectName = subject_name;
        this.subjectClassification = subject_classification;
        this.userdataName = userdata_name;

    }


}
