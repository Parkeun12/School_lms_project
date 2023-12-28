package com.example.school_lms.dto;

import com.example.school_lms.entity.Homework;
import com.example.school_lms.entity.Lecture;
import com.example.school_lms.entity.LectureData;
import com.example.school_lms.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class HomeworkForm {

    private Long homeworkId;
    private Long userId; //User
    private Long subjectId; //LectureData

    private String homeworkType;
    private LocalDate homeworkStart;
    private LocalDate homeworkEnd;
    private String homeworkTitle;
    //    255자 제한
    private String homeworkContent;
    private String homeworkFile;

    public Homework toEntity() {

        User user = new User();
        user.setId(userId);

        Lecture lecture = new Lecture();
        lecture.setSubjectId(subjectId);

        return new Homework(
                homeworkId,
                homeworkType,
                homeworkStart,
                homeworkEnd,
                homeworkTitle,
                homeworkContent,
                homeworkFile,
                user,
                lecture
        );
    }

}
