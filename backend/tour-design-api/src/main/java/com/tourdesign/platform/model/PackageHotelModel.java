package com.tourdesign.platform.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageHotelModel {
    private Long id;
    private Integer orderIndex;

    private TravelPackageModel travelPackage;
    private HotelModel hotel;
}
