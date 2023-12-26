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

    private String subjectName;
    
    private String subjectId; // repository에도 추가

    private String subjectClassification;

    private String userName;

    private int lectureSelType;

}
// DB에서 값을 가지고 오기 위한 엔티티.