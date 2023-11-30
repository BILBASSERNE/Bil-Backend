package com.example.bilbackend.repository;

import com.example.bilbackend.dto.PostCarDTO;
import com.example.bilbackend.model.CarAdvertisement;
import com.example.bilbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarAdvertisementRepository extends JpaRepository<CarAdvertisement, Integer> {

    List<CarAdvertisement> findAllByUser_UserName(String userName);
    List<CarAdvertisement> findAllByOrderByIdDesc();
    List<CarAdvertisement> findByNameContainingOrDescriptionContainingOrCarBrandContainingOrColorContainingOrGearTypeContaining(String keyword, String keyword1, String keyword2, String keyword3, String keyword4);

}
