package com.tourdesign.platform.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "package_spot")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageSpotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer orderIndex;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private TravelPackageEntity travelPackage;

    @ManyToOne
    @JoinColumn(name = "spot_id")
    private TouristSpotEntity touristSpot;
}