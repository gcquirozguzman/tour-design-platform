package com.tourdesign.platform.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageSpotModel {
    private Long id;
    private Integer orderIndex;

    private TravelPackageModel travelPackage;
    private TouristSpotModel touristSpot;
}
