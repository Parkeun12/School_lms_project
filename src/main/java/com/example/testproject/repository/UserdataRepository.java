package com.example.testproject.repository;

import com.example.testproject.entity.Userdata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserdataRepository extends JpaRepository<Userdata, String> {

    Optional<Userdata> findByUserdataNumAndUserdataName(String userdataNum, String userdataName);

    Optional<Userdata> findByUserdataNum(String userdataNum);
}
