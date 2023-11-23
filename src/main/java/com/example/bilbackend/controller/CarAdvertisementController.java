package com.example.bilbackend.controller;

import com.example.bilbackend.model.CarAdvertisement;
import com.example.bilbackend.repository.CarAdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class CarAdvertisementController {

   @Autowired
   CarAdvertisementRepository carAdvertisementRepository;

    @GetMapping("/bilbassen")
    public List<CarAdvertisement> SortCarsByIdDecending() {
        return carAdvertisementRepository.findAllByOrderByIdDesc();
    }

}
