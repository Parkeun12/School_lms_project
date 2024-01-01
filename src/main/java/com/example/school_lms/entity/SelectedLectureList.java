package com.example.school_lms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SelectedLectureList {


    @Id
    @Column

    private int subjectId;


    private String subjectName; // 교과목명

    private String subjectClassification;  //구분

    private String userdataName;  // 담당교수 이름으로


}
// DB에서 값을 가지고 오기 위한 엔티티.