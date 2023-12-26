package com.example.school_lms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SelectedLecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long selectedId;

    @ManyToOne
    private Lecture subjectId;

    @ManyToOne
    @JoinColumn(name = "id")
    private Student id;


}
