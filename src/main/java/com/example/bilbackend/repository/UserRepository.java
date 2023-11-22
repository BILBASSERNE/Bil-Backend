package com.example.bilbackend.repository;

import com.example.bilbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUserName(String userName);


}
