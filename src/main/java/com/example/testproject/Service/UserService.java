package com.example.testproject.Service;

import com.example.testproject.dto.JoinRequest;
import com.example.testproject.dto.LoginRequest;
import com.example.testproject.entity.User;
import com.example.testproject.entity.Userdata;
import com.example.testproject.repository.UserRepository;
import com.example.testproject.repository.UserdataRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserdataRepository userdataRepository;

    /**
     * 학번과 이름 중복 체크
     * 회원가입 기능 구현 시 사용
     * 중복되면 true return
     */
    public boolean checkUserdataDuplicate(String userdataNum, String userdataName) {
        Optional<Userdata> existingUserdata = userdataRepository.findByUserdataNumAndUserdataName(userdataNum, userdataName);
        return existingUserdata.isPresent();
    }

    /**
     * 회원가입 기능
     * 화면에서 JoinRequest(userdataNum, userPassword, userdataName)을 입력받아 User로 변환 후 저장
     * 학번과 이름 중복 체크는 Controller에서 진행 => 에러 메세지 출력을 위해
     */

//    교수학생 나누기전에 썼던거
    public void join(JoinRequest req) {
        // 학번과 이름 중복 체크
        if (checkUserdataDuplicate(req.getUserdataNum(), req.getUserdataName())) {  // 수정: getUserdataNum() -> getUserIdNum()
            userRepository.save(req.toEntityUser());
        }else {
            throw new RuntimeException("이미 존재하는 학번과 이름입니다.");
        }
    }

    /**
     *  로그인 기능
     *  화면에서 LoginRequest(loginId, password)을 입력받아 loginId와 password가 일치하면 User return
     *  loginId가 존재하지 않거나 password가 일치하지 않으면 null return
     */
    public User login(LoginRequest req) {
        Optional<User> optionalUser = userRepository.findByUserdataNum(req.getUserdataNum());

        // loginId와 일치하는 User가 없으면 null return
        if(optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        // 찾아온 User의 password와 입력된 password가 다르면 null return
        if(!user.getUserPassword().equals(req.getUserPassword())) {
            return null;
        }

        return user;
    }

    /**
     * userId(Long)를 입력받아 User을 return 해주는 기능
     * 인증, 인가 시 사용
     * userId가 null이거나(로그인 X) userId로 찾아온 User가 없으면 null return
     * userId로 찾아온 User가 존재하면 User return
     */
    public User getLoginUserById(Long userId) {
        if(userId == null) return null;

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }

    /**
     * loginId(String)를 입력받아 User을 return 해주는 기능
     * 인증, 인가 시 사용
     * loginId가 null이거나(로그인 X) userId로 찾아온 User가 없으면 null return
     * loginId로 찾아온 User가 존재하면 User return
     */
    public User getLoginUserByUserId(String userIdNum) {
        if(userIdNum == null) return null;

        Optional<User> optionalUser = userRepository.findByUserdataNum(userIdNum);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }

    public void updateUserInfo(Long userId, String userSns, String userPr) {
        if (userId == null) {
            // 로그인이 되어 있지 않으면 무시
            return;
        }

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (userSns != null) {
                user.setUserSns(userSns);
            }
            if (userPr != null) {
                user.setUserPr(userPr);
            }
            userRepository.save(user);
        }
    }
}