package com.tourdesign.platform.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "travel_package")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelPackageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Integer durationDays;
    private Double estimatedPrice;
    private String focusType;
    private Integer difficultyLevel;
    private String targetAudience;
    private String serviceLanguage;

    @OneToMany(mappedBy = "travelPackage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PackageHotelEntity> hotels;

    @OneToMany(mappedBy = "travelPackage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PackageRestaurantEntity> restaurants;

    @OneToMany(mappedBy = "travelPackage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PackageSpotEntity> spots;
}