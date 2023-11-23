package com.example.bilbackend.controller;

import com.example.bilbackend.dto.AuthenticationRequest;
import com.example.bilbackend.dto.AuthenticationResponse;
import com.example.bilbackend.service.AuthenticationService;
import com.example.bilbackend.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {

        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest authenticationRequest) {

        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }


}
