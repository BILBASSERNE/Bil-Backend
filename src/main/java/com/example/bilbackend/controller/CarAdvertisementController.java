package com.example.bilbackend.controller;

import com.example.bilbackend.model.CarAdvertisement;
import com.example.bilbackend.repositories.CarAdvertisementRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarAdvertisementController {

    @Autowired
    private CarAdvertisementRepository carAdvertisementRepository;

    @GetMapping("/cars")
    public List<CarAdvertisement> getCars(@RequestParam String keyword) {
        return carAdvertisementRepository.findByNameContainingOrDescriptionContainingOrCarBrandContainingOrColorContainingOrGearTypeContaining(
                keyword, keyword, keyword, keyword, keyword);
    }
}

