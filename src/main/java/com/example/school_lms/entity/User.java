package com.example.school_lms.entity;

import com.example.school_lms.Role.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.attoparser.dom.Text;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userdataNum;
    private String userdataName;
    private String userPassword;
    private String userPhone;
    private String userMajor;
    private String userSns;
//    Text의 경우 처리하기 힘듦 255자 제한으로 String문 변경
    private String userPr;


    private UserRole role;
}