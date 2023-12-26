package com.example.school_lms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @Column(name = "id")
    private int id;

    @Column
    private String userdataNum;
    @Column
    private String userPassword;
    @Column
    private String userdataName;
    @Column
    private String userPhone;
    @Column
    private String userMajor;
    @Column
    private int role;
    @Column
    private String userSns;
    @Column
    private String userPr;


}
