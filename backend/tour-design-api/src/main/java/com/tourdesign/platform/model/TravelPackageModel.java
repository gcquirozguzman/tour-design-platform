package com.tourdesign.platform.model;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelPackageModel {
    private Long id;

    private String name;
    private String description;
    private Integer durationDays;
    private Double estimatedPrice;
    private String focusType;
    private Integer difficultyLevel;
    private String targetAudience;
    private String serviceLanguage;

    private List<PackageHotelModel> hotels;
    private List<PackageRestaurantModel> restaurants;
    private List<PackageSpotModel> spots;
}
