package com.example.school_lms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HomeworkSubmit {

//    과제 제출 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long homeworkSubId;

//    제출 내용
    private String homeworkSubContent;
//    제출 첨부파일
    private String homeworkSubFile;

//    과제랑 양방향 매핑
    @ManyToOne
    @JoinColumn(name = "homework_id")
    private Homework homework;

//    유저랑 양방향 매핑
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
