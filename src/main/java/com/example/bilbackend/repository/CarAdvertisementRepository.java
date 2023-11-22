package com.example.bilbackend.repository;

import com.example.bilbackend.model.CarAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarAdvertisementRepository extends JpaRepository<CarAdvertisement, Integer> {
}
