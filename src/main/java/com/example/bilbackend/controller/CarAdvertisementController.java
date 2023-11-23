package com.example.bilbackend.controller;

import com.example.bilbackend.model.CarAdvertisement;
import com.example.bilbackend.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class CarAdvertisementController {

   @Autowired
    CarRepository carRepository;

    @GetMapping("/bilbassen")
    public List<CarAdvertisement> SortCarsByIdDecending() {
        return carRepository.findAllByOrderByIdDesc();
    }

}
