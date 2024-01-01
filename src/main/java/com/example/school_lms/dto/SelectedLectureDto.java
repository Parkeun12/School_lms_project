package com.example.school_lms.dto;

import com.example.school_lms.entity.SelectedLecture;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SelectedLectureDto {


    private Long selectedId;


    private Long id;


    private Long subjectId;

    public SelectedLecture toEntity(){

        return new SelectedLecture(null, id, subjectId);
    }


}
