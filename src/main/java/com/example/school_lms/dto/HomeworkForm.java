package com.example.school_lms.dto;

import com.example.school_lms.entity.Homework;
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
        return new Homework(
                homeworkId,
                userId,  // User 엔티티의 id
                subjectDateId,  // LectureData 엔티티의 subjectDataId
                homeworkType,
                homeworkStart,
                homeworkEnd,
                homeworkTitle,
                homeworkContent,
                homeworkFile
        );
    }

}
