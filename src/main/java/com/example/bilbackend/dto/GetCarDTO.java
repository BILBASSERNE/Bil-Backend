package com.example.bilbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarDTO {

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
    private boolean isFavorited;
    private int seats;
    private String equipment;
    private String rules;
    private boolean isRenting;

    private List<byte[]> images;

    private String firstName;
    private String lastName;
    private String city;
    private int phoneNumber;
}
