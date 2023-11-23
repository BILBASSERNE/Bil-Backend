package com.example.bilbackend.repository;

import com.example.bilbackend.model.CarAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarAdvertisementRepository extends JpaRepository<CarAdvertisement, Integer> {
    List<CarAdvertisement> findAllByOrderByIdDesc();
}
