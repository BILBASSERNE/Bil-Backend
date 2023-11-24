package com.example.bilbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PostCarDTO {
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
}
