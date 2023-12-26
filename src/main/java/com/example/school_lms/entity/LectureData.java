package com.example.school_lms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LectureData {

    @Id
    @Column(name = "subject_data_id")
    private Long subjectDataId; //강의 아이디

    @Column
    private String subjectDataName; //교과목명
}
