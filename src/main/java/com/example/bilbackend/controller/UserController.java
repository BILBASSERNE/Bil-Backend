package com.example.bilbackend.controller;

import com.example.bilbackend.dto.AuthenticationRequest;
import com.example.bilbackend.dto.AuthenticationResponse;
import com.example.bilbackend.dto.RegisterRequest;
import com.example.bilbackend.model.User;
import com.example.bilbackend.repository.UserRepository;
import com.example.bilbackend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {

        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest authenticationRequest) {

        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<User> getUser(@PathVariable String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        User foundUser = null;

        if (user.isPresent()) {
            foundUser = user.get();
        }

        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @DeleteMapping("/user/{userName}")
    public ResponseEntity<User> deleteUser(@PathVariable String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        User foundUser = null;

        if (user.isPresent()) {
            foundUser = user.get();
            userRepository.delete(foundUser);

            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/user/{userName}")
    public ResponseEntity<AuthenticationResponse> updateUser(@PathVariable String userName, @RequestBody RegisterRequest updatedUser) {
        authenticationService.updateUser(updatedUser, userName);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
