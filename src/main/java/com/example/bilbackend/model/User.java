package com.example.bilbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String city;
    @Column(unique = true)
    private int phoneNumber;
    @Column(unique = true)
    private String email;

    @ManyToMany
    @JoinTable(name = "favorite_cars",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    private Set<CarAdvertisement> cars = new HashSet<>();

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<CarAdvertisement> carAdvertisementList = new ArrayList<>();

    // no roles for authorizing user (enum - role)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Set<CarAdvertisement> getCars() {
        return cars;
    }

    public void setCars(Set<CarAdvertisement> cars) {
        this.cars = cars;
    }

    public List<CarAdvertisement> getCarAdvertisementList() {
        return carAdvertisementList;
    }

    public void setCarAdvertisementList(List<CarAdvertisement> carAdversitementList) {
        this.carAdvertisementList = carAdversitementList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && phoneNumber == user.phoneNumber && Objects.equals(userName, user.userName) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(city, user.city) && Objects.equals(email, user.email) && Objects.equals(cars, user.cars) && Objects.equals(carAdvertisementList, user.carAdvertisementList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, firstName, lastName, city, phoneNumber, email, cars, carAdvertisementList);
    }
}
