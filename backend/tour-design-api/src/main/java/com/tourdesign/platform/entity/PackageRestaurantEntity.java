package com.tourdesign.platform.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "package_restaurant")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageRestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer orderIndex;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private TravelPackageEntity travelPackage;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;
}