package com.example.bilbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    @Column(name = "src", columnDefinition = "LONGBLOB")
    private byte[] src;

    @ManyToOne
    @JoinColumn(name = "carAdvertisement",referencedColumnName = "id")
    private CarAdvertisement carAdvertisement;
}
