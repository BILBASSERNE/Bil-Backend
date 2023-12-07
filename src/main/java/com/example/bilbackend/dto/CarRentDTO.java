package com.example.bilbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CarRentDTO {
    private int id;
    private String name;
    private String description;
    private double price;
    private String carBrand;
    private int modelYear;
    private String fuelType;
    private String gearType;
    private int seats;
    private String equipment;
    private String rules;
    private boolean isRenting;
    private List<ImageDTO> images;
}