package com.example.school_lms.repository;

import com.example.school_lms.entity.Userdata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserdataRepository extends JpaRepository<Userdata, String> {

    Optional<Userdata> findByUserdataNumAndUserdataName(String userdataNum, String userdataName);
}
