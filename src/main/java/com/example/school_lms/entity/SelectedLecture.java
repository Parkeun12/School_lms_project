package com.example.school_lms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "selected_lecture")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SelectedLecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long selectedId;

    @Column(name = "id")
    private Long id;

    @Column(name = "subject_id")
    private Long subjectId;




}
