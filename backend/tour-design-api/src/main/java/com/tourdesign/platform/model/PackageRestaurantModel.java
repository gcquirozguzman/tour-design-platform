package com.tourdesign.platform.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageRestaurantModel {
    private Long id;
    private Integer orderIndex;

    private TravelPackageModel travelPackage;
    private RestaurantModel restaurant;
}
