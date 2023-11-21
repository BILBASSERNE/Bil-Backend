package com.example.bilbackend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    private String address;

    private int phoneNumber;

    private String website;

    private String logo;

    private int cvr;

    @OneToMany(mappedBy = "company")
    @JsonBackReference
    private Set<CarAdvertisement> carAdvertisements = new HashSet<>();

    @OneToMany(mappedBy = "company")
    @JsonBackReference
    private Set<User> users = new HashSet<>();
}
