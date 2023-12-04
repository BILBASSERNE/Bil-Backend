package com.example.bilbackend.controller;

import com.example.bilbackend.dto.CarRentDTO;
import com.example.bilbackend.dto.GetCarDTO;
import com.example.bilbackend.dto.ImageDTO;
import com.example.bilbackend.dto.PostCarDTO;
import com.example.bilbackend.model.CarAdvertisement;
import com.example.bilbackend.model.CarImage;
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

import java.util.ArrayList;
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


    @GetMapping("/annoncer/{userName}")
    public ResponseEntity<List<CarAdvertisement>> getMyAdvertisedCars(@PathVariable String userName) {
        return ResponseEntity.ok(favoriteCarService.getMyAdvertisedCars(userName));
    }

    @GetMapping("/bilbassen")
    public ResponseEntity<List<GetCarDTO>> SortCarsByIdDecending() {
        List<CarAdvertisement> cars = carAdvertisementRepository.findAllByOrderByIdDesc();
        List<GetCarDTO> carList = new ArrayList<>();

        for (CarAdvertisement car : cars) {
            List<byte[]> imageList = new ArrayList<>();
            GetCarDTO returnedCar = new GetCarDTO();

            returnedCar.setId(car.getId());
            returnedCar.setName(car.getName());
            returnedCar.setDescription(car.getDescription());
            returnedCar.setPrice(car.getPrice());
            returnedCar.setLicenseplate(car.getLicenseplate());
            returnedCar.setCarBrand(car.getCarBrand());
            returnedCar.setModelYear(car.getModelYear());
            returnedCar.setBoughtYear(car.getBoughtYear());
            returnedCar.setFuelType(car.getFuelType());
            returnedCar.setFuelType(car.getFuelType());
            returnedCar.setFuelConsumption(car.getFuelConsumption());
            returnedCar.setCarType(car.getCarType());
            returnedCar.setColor(car.getColor());
            returnedCar.setGearType(car.getGearType());
            returnedCar.setNumberOfGears(car.getNumberOfGears());
            returnedCar.setActive(car.isActive());
            returnedCar.setSeats(car.getSeats());
            returnedCar.setEquipment(car.getEquipment());
            returnedCar.setRules(car.getRules());
            returnedCar.setRenting(car.isRenting());

            for (CarImage image : car.getImages()) {
                imageList.add(image.getSrc());
            }

            returnedCar.setImages(imageList);
            carList.add(returnedCar);
        }

        return new ResponseEntity<>(carList, HttpStatus.OK);
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

            for (ImageDTO imageDTO : car.getImages()) {
                CarImage carImage = new CarImage();
                carImage.setSrc(imageDTO.getSrc());
                carImage.setCarAdvertisement(carAdvertisement);
                carAdvertisement.getImages().add(carImage);
            }

            carAdvertisement.setGearType(car.getGearType());
            carAdvertisement.setNumberOfGears(car.getNumberOfGears());
            carAdvertisement.setKmDriven(car.getKmDriven());
            carAdvertisement.setActive(true);
            carAdvertisement.setUser(user.get());

            //user.get().getCars().add(carAdvertisement);

            carAdvertisementRepository.save(carAdvertisement);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/rentcar")
    public ResponseEntity<CarRentDTO> rentCar(@RequestBody CarRentDTO car) {
        System.out.println(car);
        CarAdvertisement carAdvertisement = new CarAdvertisement();
        carAdvertisement.setName(car.getName());
        carAdvertisement.setDescription(car.getDescription());
        carAdvertisement.setPrice(car.getPrice());
        carAdvertisement.setCarBrand(car.getCarBrand());
        carAdvertisement.setModelYear(car.getModelYear());
        carAdvertisement.setGearType(car.getGearType());
        carAdvertisement.setSeats(car.getSeats());
        carAdvertisement.setEquipment(car.getEquipment());
        carAdvertisement.setRules(car.getRules());
        carAdvertisement.setRenting(true);
        carAdvertisementRepository.save(carAdvertisement);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/cardetails/{carId}")
    public ResponseEntity<GetCarDTO> getCarInformation (@PathVariable int carId) {
        Optional<CarAdvertisement> car = carAdvertisementRepository.findCarAdvertisementById(carId);
        if (car.isPresent()) {

            CarAdvertisement foundCar = car.get();

            List<byte[]> imageList = new ArrayList<>();
            GetCarDTO returnedCar = new GetCarDTO();

            returnedCar.setId(foundCar.getId());
            returnedCar.setName(foundCar.getName());
            returnedCar.setDescription(foundCar.getDescription());
            returnedCar.setPrice(foundCar.getPrice());
            returnedCar.setLicenseplate(foundCar.getLicenseplate());
            returnedCar.setCarBrand(foundCar.getCarBrand());
            returnedCar.setModelYear(foundCar.getModelYear());
            returnedCar.setBoughtYear(foundCar.getBoughtYear());
            returnedCar.setFuelType(foundCar.getFuelType());
            returnedCar.setFuelType(foundCar.getFuelType());
            returnedCar.setFuelConsumption(foundCar.getFuelConsumption());
            returnedCar.setCarType(foundCar.getCarType());
            returnedCar.setColor(foundCar.getColor());
            returnedCar.setGearType(foundCar.getGearType());
            returnedCar.setNumberOfGears(foundCar.getNumberOfGears());
            returnedCar.setActive(foundCar.isActive());
            returnedCar.setSeats(foundCar.getSeats());
            returnedCar.setEquipment(foundCar.getEquipment());
            returnedCar.setRules(foundCar.getRules());
            returnedCar.setRenting(foundCar.isRenting());

            for (CarImage image : foundCar.getImages()) {
                imageList.add(image.getSrc());
            }

            returnedCar.setImages(imageList);

            returnedCar.setFirstName(foundCar.getUser().getFirstName());
            returnedCar.setLastName(foundCar.getUser().getLastName());
            returnedCar.setCity(foundCar.getUser().getCity());
            returnedCar.setPhoneNumber(foundCar.getUser().getPhoneNumber());



            return new ResponseEntity<>(returnedCar, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/list")
    public List<CarAdvertisement> get() {
        return carAdvertisementRepository.findAll();
    }
}



