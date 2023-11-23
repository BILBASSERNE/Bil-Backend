package com.example.bilbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String userName;
    private String firstName;
    private String lastName;
    private String city;
    private int phoneNumber;
    private String email;
    private String password;

}
