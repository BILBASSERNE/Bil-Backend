package com.example.bilbackend.service;

import com.example.bilbackend.dto.GetCarDTO;
import com.example.bilbackend.dto.PostCarDTO;
import com.example.bilbackend.model.CarAdvertisement;
import com.example.bilbackend.model.CarImage;
import com.example.bilbackend.model.User;
import com.example.bilbackend.repository.CarAdvertisementRepository;
import com.example.bilbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FavoriteCarService {

    private final CarAdvertisementRepository carAdvertisementRepository;

    private final UserRepository userRepository;

    public void addToFavorites(int carId, String userName) throws Exception {

        try {
            CarAdvertisement car = carAdvertisementRepository.findById(carId).orElseThrow(() -> new Exception("Car not found"));
            Optional<User> findUserByUserName = userRepository.findByUserName(userName);
            User user = new User();
            if (findUserByUserName.isPresent()) {
                user = findUserByUserName.get();
            }
            user.getCars().clear();
            user.getCars().add(car);
            userRepository.save(user);
        } catch (StackOverflowError stackOverflowError) {
            System.out.println("Car has already been favoritted");
        }
    }

    public List<GetCarDTO> getFavoriteCarsByUserName(String userName) {
        Set<CarAdvertisement> favoriteCars = carAdvertisementRepository.findFavoriteCarsByUserName(userName);
        List<GetCarDTO> listOfCars = new ArrayList<>();

        for (CarAdvertisement car : favoriteCars) {
            List<byte[]> imageList = new ArrayList<>();

            for (CarImage image : car.getImages()) {
                imageList.add(image.getSrc());
            }

            GetCarDTO carDTO = new GetCarDTO();
            carDTO.setId(car.getId());
            carDTO.setImages(imageList);
            carDTO.setName(car.getName());
            carDTO.setPrice(car.getPrice());

            listOfCars.add(carDTO);

        }
        return listOfCars;
    }


    public List<GetCarDTO> getMyAdvertisedCars(String userName) {
        List<CarAdvertisement> myAdvertisedCars = carAdvertisementRepository.findAllByUser_UserName(userName);
        List<GetCarDTO> listOfCars = new ArrayList<>();

        for (CarAdvertisement car : myAdvertisedCars) {
            List<byte[]> imageList = new ArrayList<>();

            for (CarImage image : car.getImages()) {
                imageList.add(image.getSrc());
            }

            GetCarDTO carDTO = new GetCarDTO();
            carDTO.setId(car.getId());
            carDTO.setImages(imageList);
            carDTO.setName(car.getName());
            carDTO.setPrice(car.getPrice());

            listOfCars.add(carDTO);

        }


        return listOfCars;
    }


    public CarAdvertisement editCar(int carId, PostCarDTO editedCarAdvertisement) {
        Optional<CarAdvertisement> foundCar = carAdvertisementRepository.findCarAdvertisementById(carId);
        CarAdvertisement newCarAdvertisement = null;

        if (foundCar.isPresent()) {

            newCarAdvertisement = foundCar.get();
            newCarAdvertisement.setName(editedCarAdvertisement.getName());
            newCarAdvertisement.setDescription(editedCarAdvertisement.getDescription());
            newCarAdvertisement.setPrice(editedCarAdvertisement.getPrice());
            newCarAdvertisement.setLicenseplate(editedCarAdvertisement.getLicenseplate());
            newCarAdvertisement.setCarBrand(editedCarAdvertisement.getCarBrand());
            newCarAdvertisement.setModelYear(editedCarAdvertisement.getModelYear());
            newCarAdvertisement.setBoughtYear(editedCarAdvertisement.getBoughtYear());
            newCarAdvertisement.setFuelType(editedCarAdvertisement.getFuelType());
            newCarAdvertisement.setFuelConsumption(editedCarAdvertisement.getFuelConsumption());
            newCarAdvertisement.setCarType(editedCarAdvertisement.getCarType());
            newCarAdvertisement.setColor(editedCarAdvertisement.getColor());
           // newCarAdvertisement.setImages(editedCarAdvertisement.getImages());
            newCarAdvertisement.setGearType(editedCarAdvertisement.getGearType());
            newCarAdvertisement.setNumberOfGears(editedCarAdvertisement.getNumberOfGears());
            newCarAdvertisement.setKmDriven(editedCarAdvertisement.getKmDriven());
            carAdvertisementRepository.save(newCarAdvertisement);
        }
        return newCarAdvertisement;
    }

}
