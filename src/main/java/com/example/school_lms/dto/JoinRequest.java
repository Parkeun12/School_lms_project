package com.example.school_lms.dto;


import com.example.school_lms.Role.UserRole;
import com.example.school_lms.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequest {

    @NotBlank(message = "학번이 비어있습니다.")
    private String userdataNum;

    @NotBlank(message = "이름 비어있습니다.")
    private String userdataName;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String userPassword;
    private String passwordCheck;

    @NotBlank(message = "전화번호가 비어있습니다.")
    private String userPhone;

    private String userMajor;

    // 비밀번호 암호화 X
    public User toEntityUser() {

        UserRole userRole = (this.userdataNum.length() == 8) ? UserRole.STUDENT : UserRole.PROFESSOR;

        return User.builder()
                .userdataNum(this.userdataNum)
                .userPassword(this.userPassword)
                .userdataName(this.userdataName)
                .userPhone(this.userPhone)
                .userMajor(this.userMajor)
                .role(userRole)
                .build();
    }

//    public User toEntityAdmin() {
//        return User.builder()
//                .userdataNum(this.userdataNum)
//                .userPassword(this.userPassword)
//                .userdataName(this.userdataName)
//                .userPhone(this.userPhone)
//                .userMajor(this.userMajor)
//                .role(UserRole.ADMIN)
//                .build();
//    }

//    // 비밀번호 암호화
//    public User toEntity(String encodedPassword) {
//        return User.builder()
//                .userdataNum(this.userdataNum)
//                .userPassword(encodedPassword)
//                .userdataName(this.userdataName)
//                .userPhone(this.userPhone)
//                .userMajor(this.userMajor)
//                .role(UserRole.USER)
//                .build();
//    }
}