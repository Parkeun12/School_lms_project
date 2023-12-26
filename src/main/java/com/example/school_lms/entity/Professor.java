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
@Table(name = "professor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name ="user_name")
    private String userName;
    @Column(name = "user_phone")
    private String userPhone;
    @Column(name = "user_major")
    private String userMajor;
    @Column(name = "user_role")
    private int userRole;
    @Column(name = "user_sns")
    private String userSns;
    @Column(name = "user_pr")
    private String userPr;
}
