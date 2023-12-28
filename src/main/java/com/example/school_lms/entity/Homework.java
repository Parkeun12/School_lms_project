package com.example.school_lms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    private String homeworkType;
    private LocalDate homeworkStart;
    private LocalDate homeworkEnd;
    private String homeworkTitle;
//    255자 제한
    private String homeworkContent;
    private String homeworkFile;

    //아이디 양방향 매핑
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //강의 아이디 양방향 매핑
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Lecture subjectId;
}
