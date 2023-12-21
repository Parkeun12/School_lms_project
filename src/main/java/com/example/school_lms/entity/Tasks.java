package com.example.school_lms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectPfId;

    //회원 아이디 양방향 매핑
    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    //강의 아이디 양방향 매핑
//    @ManyToOne
//    @JoinColumn(name = "lecture_id")
//    private Lecture lecture;

    private String projectType;
    private String projectStart;
    private String projectEnd;
    private String projectTitle;
    private String projectContent;
    private String projectFile;
    private String projectStandard;
}
