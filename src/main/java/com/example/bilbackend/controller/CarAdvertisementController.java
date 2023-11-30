package com.example.bilbackend.controller;

import com.example.bilbackend.dto.CarRentDTO;
import com.example.bilbackend.dto.PostCarDTO;
import com.example.bilbackend.model.CarAdvertisement;
import com.example.bilbackend.model.User;
import com.example.bilbackend.repository.CarAdvertisementRepository;
import com.example.bilbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.bilbackend.service.FavoriteCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController

public class CarAdvertisementController {

    @Autowired
    CarAdvertisementRepository carAdvertisementRepository;

    @Autowired
    private FavoriteCarService favoriteCarService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/favorite/{carId}/{userName}")
    public ResponseEntity<String> addToFavorites(@PathVariable int carId, @PathVariable String userName) {
        try {
            favoriteCarService.addToFavorites(carId, userName);
            return ResponseEntity.ok("Car added to favorites successfully!");
        } catch (Exception e) {
            // Handle the exception appropriately, e.g., return a 404 status if the car is not found
            return ResponseEntity.status(404).body("Car not found");
        }
    }

    @GetMapping("/favorite/{userName}")
    public ResponseEntity<List<CarAdvertisement>> getFavoriteCars(@PathVariable String userName) {
        return ResponseEntity.ok(favoriteCarService.getFavoriteCars(userName));
    }

    @GetMapping("/bilbassen")
    public List<CarAdvertisement> SortCarsByIdDecending() {
        return carAdvertisementRepository.findAllByOrderByIdDesc();
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarAdvertisement>> getCars(@RequestParam String keyword) {
        List<CarAdvertisement> cars = carAdvertisementRepository.findByNameContainingOrDescriptionContainingOrCarBrandContainingOrColorContainingOrGearTypeContaining(
                keyword, keyword, keyword, keyword, keyword);

        if (cars.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
    }

    @PostMapping("/sellcar/{userName}")
    public ResponseEntity<PostCarDTO> sellCar(@RequestBody PostCarDTO car, @PathVariable String userName) {
        Optional<User> user = userRepository.findByUserName(userName);

        if (user.isPresent()) {
          user.get().getUsername();
            System.out.println("jeg er inde i if-statement");
            System.out.println(user);
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
            // implement carAdvertisement.setImages(car.getImages());
            carAdvertisement.setGearType(car.getGearType());
            carAdvertisement.setNumberOfGears(car.getNumberOfGears());
            carAdvertisement.setKmDriven(car.getKmDriven());
            carAdvertisement.setActive(true);
            carAdvertisement.setUser(user.get());
            carAdvertisementRepository.save(carAdvertisement);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
