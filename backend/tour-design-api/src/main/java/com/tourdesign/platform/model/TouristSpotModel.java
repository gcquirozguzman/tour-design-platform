package com.tourdesign.platform.model;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TouristSpotModel {
    private Long id;

    private String name;
    private String spotType;
    private String location;
    private Double entranceFee;
    private Integer difficultyLevel;
    private String restrictions;

    private List<PackageSpotModel> packages;
}
