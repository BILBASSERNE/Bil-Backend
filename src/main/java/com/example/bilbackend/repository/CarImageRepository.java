package com.example.bilbackend.repository;

import com.example.bilbackend.model.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarImageRepository extends JpaRepository<CarImage, Integer> {
}
