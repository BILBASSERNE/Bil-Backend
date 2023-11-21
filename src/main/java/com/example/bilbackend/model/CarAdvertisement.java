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
@Entity
public class CarAdvertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    private double price;

    private String licenseplate;

    private String carBrand;

    private int modelYear;

    private int boughtYear;

    private String fuelType;

    private double fuelConsumption;

    private String carType;

    private String color;

    private String gearType;

    private int numberOfGears;

    private double kmDriven;

    private boolean isActive;

    @OneToMany(mappedBy="car_advertisment",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<CarImage> images=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "company", referencedColumnName = "id")
    private Company company;

}

