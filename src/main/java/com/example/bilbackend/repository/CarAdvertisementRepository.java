package com.example.bilbackend.repository;

import com.example.bilbackend.model.CarAdvertisement;
import com.example.bilbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CarAdvertisementRepository extends JpaRepository<CarAdvertisement, Integer> {

    List<CarAdvertisement> findAllByUser_UserName(String userName);
    List<CarAdvertisement> findAllByOrderByIdDesc();
    Optional<CarAdvertisement> findCarAdvertisementById(int id);
    List<CarAdvertisement> findByNameContainingOrDescriptionContainingOrCarBrandContainingOrColorContainingOrGearTypeContaining(String keyword, String keyword1, String keyword2, String keyword3, String keyword4);

    @Query("SELECT u.favoriteCars FROM User u WHERE u.userName = :userName")
    Set<CarAdvertisement> findFavoriteCarsByUserName(@Param("userName") String userName);

}
