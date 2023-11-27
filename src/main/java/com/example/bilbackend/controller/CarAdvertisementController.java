package com.example.bilbackend.controller;

import com.example.bilbackend.dto.CarRentDTO;
import com.example.bilbackend.dto.PostCarDTO;
import com.example.bilbackend.model.CarAdvertisement;
import com.example.bilbackend.repository.CarAdvertisementRepository;
import com.example.bilbackend.service.FavoriteCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController

public class CarAdvertisementController {

    @Autowired
    CarAdvertisementRepository carAdvertisementRepository;

    @Autowired
    private FavoriteCarService favoriteCarService;

    @PostMapping("/{carId}/{userName}/favorite")
    public ResponseEntity<String> addToFavorites(@PathVariable int carId, @PathVariable String userName) {
        try {
            favoriteCarService.addToFavorites(carId, userName);
            return ResponseEntity.ok("Car added to favorites successfully!");
        } catch (Exception e) {
            // Handle the exception appropriately, e.g., return a 404 status if the car is not found
            return ResponseEntity.status(404).body("Car not found");
        }
    }

    @GetMapping("/{userName}/favorite")
    public ResponseEntity<List<CarAdvertisement>> getFavoriteCars(@PathVariable String userName) {
        return ResponseEntity.ok(favoriteCarService.getFavoriteCars(userName));
    }


    @GetMapping("/")
    public List<CarAdvertisement> SortCarsByIdDecending() {
        return carAdvertisementRepository.findAllByOrderByIdDesc();
    }

    @GetMapping("/cars")
    public List<CarAdvertisement> getCars(@RequestParam String keyword) {
        return carAdvertisementRepository.findByNameContainingOrDescriptionContainingOrCarBrandContainingOrColorContainingOrGearTypeContaining(
                keyword, keyword, keyword, keyword, keyword);
    }

    @PostMapping("/sellcar")
    public ResponseEntity<PostCarDTO> sellCar(@RequestBody PostCarDTO car) {
        CarAdvertisement carAdvertisement = new CarAdvertisement();
        carAdvertisement.setName(car.getName());
        carAdvertisement.setDescription(car.getDescription());
        carAdvertisement.setPrice(car.getPrice());
        carAdvertisement.setLicenseplate(car.getLicenseplate());
        carAdvertisement.setCarBrand(car.getCarBrand());
        carAdvertisement.setModelYear(car.getModelYear());
        carAdvertisement.setBoughtYear(car.getBoughtYear());
        carAdvertisement.setFuelType(car.getFuelType());
        carAdvertisement.setFuelConsumption(car.getFuelConsumption());
        carAdvertisement.setCarType(car.getCarType());
        carAdvertisement.setColor(car.getColor());
        carAdvertisement.setGearType(car.getGearType());
        carAdvertisement.setNumberOfGears(car.getNumberOfGears());
        carAdvertisement.setKmDriven(car.getKmDriven());
        carAdvertisement.setActive(car.isActive());
        carAdvertisementRepository.save(carAdvertisement);
        return new ResponseEntity<>(HttpStatus.OK);
    }

@PostMapping("/rentcar")
    public ResponseEntity<CarRentDTO> rentCar(@RequestBody CarRentDTO car) {
        CarAdvertisement carAdvertisement = new CarAdvertisement();
        carAdvertisement.setName(car.getName());
        carAdvertisement.setDescription(car.getDescription());
        carAdvertisement.setPrice(car.getPricePerDay());
        carAdvertisement.setCarBrand(car.getCarBrand());
        carAdvertisement.setModelYear(car.getModelYear());
        carAdvertisement.setGearType(car.getGearType());
        carAdvertisement.setSeats(car.getSeats());
        carAdvertisement.setEquipment(car.getEquipment());
        carAdvertisement.setRules(car.getRules());
        carAdvertisement.setLocation(car.getLocation());
        carAdvertisementRepository.save(carAdvertisement);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}