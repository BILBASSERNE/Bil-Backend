package com.example.bilbackend.service;

import com.example.bilbackend.dto.GetCarDTO;
import com.example.bilbackend.model.CarAdvertisement;
import com.example.bilbackend.model.CarImage;
import com.example.bilbackend.model.User;
import com.example.bilbackend.repository.CarAdvertisementRepository;
import com.example.bilbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FavoriteCarService {

    private final CarAdvertisementRepository carAdvertisementRepository;

    private final UserRepository userRepository;

    public void addToFavorites(int carId, String userName) throws Exception {
        CarAdvertisement car = carAdvertisementRepository.findById(carId).orElseThrow(() -> new Exception("Car not found"));
        Optional<User> findUserByUserName = userRepository.findByUserName(userName);
        User user = new User();
        if (findUserByUserName.isPresent()) {
            user = findUserByUserName.get();
        }
        user.getCars().clear();
        user.getCars().add(car);
        userRepository.save(user);
    }


    public List<GetCarDTO> getMyAdvertisedCars(String userName) {
        List<CarAdvertisement> myAdvertisedCars = carAdvertisementRepository.findAllByUser_UserName(userName);
        List<GetCarDTO> listOfCars = new ArrayList<>();
        System.out.println(myAdvertisedCars);

        for (CarAdvertisement car : myAdvertisedCars) {
            List<byte[]> imageList = new ArrayList<>();

            for (CarImage image : car.getImages()) {
                imageList.add(image.getSrc());
            }

            GetCarDTO carDTO = new GetCarDTO();
            carDTO.setImages(imageList);
            carDTO.setName(car.getName());
            carDTO.setPrice(car.getPrice());

            listOfCars.add(carDTO);

        }


        return listOfCars;
    }

}
