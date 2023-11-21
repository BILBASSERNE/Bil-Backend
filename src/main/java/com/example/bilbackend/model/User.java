package com.example.bilbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity //Føres til databasen
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;

    private String firstName;

    private String lastName;

    private String city;

    private int phoneNumber;

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn (name = "company", referencedColumnName = "id")
    private Company company;

    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<CarAdvertisement> carAdversitementList = new ArrayList<>();

}
