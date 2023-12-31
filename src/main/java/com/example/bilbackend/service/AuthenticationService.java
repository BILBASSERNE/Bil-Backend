package com.example.bilbackend.service;

import com.example.bilbackend.config.JwtService;
import com.example.bilbackend.dto.AuthenticationRequest;
import com.example.bilbackend.dto.AuthenticationResponse;
import com.example.bilbackend.dto.RegisterRequest;
import com.example.bilbackend.model.User;
import com.example.bilbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .userName(registerRequest.getUserName())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .city(registerRequest.getCity())
                .phoneNumber(registerRequest.getPhoneNumber())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUserName(), authenticationRequest.getPassword()
        ));
        var user = userRepository.findByUserName(authenticationRequest.getUserName());

        // DUNNO IF THIS WORKS
        var jwtToken = jwtService.generateToken(user.get());

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse updateUser(RegisterRequest updatedUser, String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        User foundUser = null;
        if (user.isPresent()) {
            foundUser = user.get();

            foundUser.setUserName(updatedUser.getUserName());
            if (!updatedUser.getPassword().isEmpty()) {
                foundUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
            foundUser.setFirstName(updatedUser.getFirstName());
            foundUser.setLastName(updatedUser.getLastName());
            foundUser.setCity(updatedUser.getCity());
            foundUser.setEmail(updatedUser.getEmail());

            userRepository.save(foundUser);
        }

        var jwtToken = jwtService.generateToken(foundUser);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
