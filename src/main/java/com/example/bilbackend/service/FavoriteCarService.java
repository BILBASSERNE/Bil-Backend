package com.example.bilbackend.service;

import com.example.bilbackend.model.CarAdvertisement;
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
        System.out.println("Car");
        System.out.println(car.getName());
        Optional<User> findUserByUserName = userRepository.findByUserName(userName);
        System.out.println("findByUsername");
        User user = new User();
        if (findUserByUserName.isPresent()) {
            System.out.println("User");
            user = findUserByUserName.get();
            System.out.println(user.getId());
        }
        user.getCars().clear();
        user.getCars().add(car);
        System.out.println("getCars");
        userRepository.save(user);
        System.out.println("saveee");
    }

    public List<CarAdvertisement> getFavoriteCars(String userName) {
        List<CarAdvertisement> favoriteCars = carAdvertisementRepository.findAllByUser_UserName(userName);
        favoriteCars.sort(Comparator.comparing(CarAdvertisement::isFavorited).reversed());
        return favoriteCars;
    }

}
