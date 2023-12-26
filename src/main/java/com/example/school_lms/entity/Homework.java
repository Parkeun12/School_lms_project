package com.example.school_lms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long homeworkId;

    //아이디 양방향 매핑
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //강의 아이디 양방향 매핑
    @ManyToOne
    @JoinColumn(name = "subject_data_id")
    private LectureData subjectDataId;

    private String homeworkType;
    private Date homeworkStart;
    private Date homeworkEnd;
    private String homeworkTitle;
//    255자 제한
    private String homeworkContent;
    private String homeworkFile;
}
