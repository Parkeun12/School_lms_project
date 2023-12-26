package com.example.school_lms.dto;

import com.example.school_lms.entity.Homework;
import com.example.school_lms.entity.LectureData;
import com.example.school_lms.entity.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class HomeworkForm {

    private Long homeworkId;
    private Long userId; //User
    private Long subjectDateId; //LectureData

    private String homeworkType;
    private Date homeworkStart;
    private Date homeworkEnd;
    private String homeworkTitle;
    //    255자 제한
    private String homeworkContent;
    private String homeworkFile;

    public Homework toEntity() {

        User user = new User();
        user.setId(userId);

        LectureData lectureData = new LectureData();
        lectureData.setSubjectDataId(subjectDateId);

        return new Homework(
                homeworkId,
                user,
                lectureData,
                homeworkType,
                homeworkStart,
                homeworkEnd,
                homeworkTitle,
                homeworkContent,
                homeworkFile
        );
    }

}
