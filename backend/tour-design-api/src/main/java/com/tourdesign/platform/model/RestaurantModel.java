package com.tourdesign.platform.model;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantModel {
    private Long id;

    private String name;
    private String cuisineType;
    private String location;
    private Double averagePrice;
    private Integer capacity;
    private String services;

    private List<PackageRestaurantModel> packages;
}
