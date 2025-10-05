package com.tourdesign.platform.model;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelModel {
    private Long id;

    private String name;
    private String address;
    private Integer stars;
    private Double pricePerNight;
    private String roomType;
    private Integer capacity;
    private String services;
    private String location;

    private List<PackageHotelModel> packages;
}
