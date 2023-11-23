package com.example.bilbackend.repositories;

import com.example.bilbackend.model.CarAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarAdvertisementRepository extends JpaRepository<CarAdvertisement, Integer> {
    List<CarAdvertisement> findByNameContainingOrDescriptionContainingOrCarBrandContainingOrColorContainingOrGearTypeContaining(String name, String description, String carBrand, String color, String gearType);
}