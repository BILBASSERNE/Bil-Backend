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
        Optional<User> findUserByUserName = userRepository.findByUserName(userName);
        User user = new User();
        if (findUserByUserName.isPresent()) {
            user = findUserByUserName.get();
        }
        user.getCars().clear();
        user.getCars().add(car);
        userRepository.save(user);
    }

    public List<CarAdvertisement> getFavoriteCars(String userName) {
        List<CarAdvertisement> favoriteCars = carAdvertisementRepository.findAllByUser_UserName(userName);
        favoriteCars.sort(Comparator.comparing(CarAdvertisement::isFavorited).reversed());
        return favoriteCars;
    }

}
