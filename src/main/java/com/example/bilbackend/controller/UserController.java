package com.example.bilbackend.controller;

import com.example.bilbackend.dto.PostUserDTO;
import com.example.bilbackend.model.User;
import com.example.bilbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@CrossOrigin
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/createAccount")
    public ResponseEntity<User> createAccount (@RequestBody PostUserDTO user) {
        System.out.println("kommer jeg herind??");
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setCity(user.getCity());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setEmail(user.getEmail());

        Optional<User> existinguserEmail = userRepository.findByEmail(user.getEmail());
        Optional<User> existinguserUserName = userRepository.findByUserName(user.getUserName());
        if (existinguserEmail.isPresent() || existinguserUserName.isPresent()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            userRepository.save(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
    }


}
