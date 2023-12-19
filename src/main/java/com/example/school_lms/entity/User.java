package com.example.school_lms.entity;

import com.example.school_lms.Role.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.attoparser.dom.Text;

@Entity
@Builder
@Getter
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
    private Text userPr;


    private UserRole role;
}