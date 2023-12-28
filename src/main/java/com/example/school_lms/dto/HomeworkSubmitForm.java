package com.example.school_lms.dto;

import com.example.school_lms.entity.Homework;
import com.example.school_lms.entity.HomeworkSubmit;
import com.example.school_lms.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HomeworkSubmitForm {

    private Long homeworkSubId;
    private Long homeworkId;
    private Long userId;

    private String homeworkSubContent;
    private String homeworkSubFile;

    public HomeworkSubmit toEntity(){

        User user = new User();
        user.setId(userId);

        Homework homework = new Homework();
        homework.setHomeworkId(homeworkId);

        return new HomeworkSubmit(
                homeworkSubId,
                homeworkSubContent,
                homeworkSubFile,
                homework,
                user
        );
    }
}
