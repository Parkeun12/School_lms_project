package com.example.testproject.repository;

import com.example.testproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserdataNum(String userdataNum);
    @Query(value = "select char_length(userdata_num)" +
            "from userdata;" +
            "where userdata_num = :userdataNum", nativeQuery = true)
    Optional<User> findByUserdataNum2(@Param("userdataNum") String userdataNum);
}