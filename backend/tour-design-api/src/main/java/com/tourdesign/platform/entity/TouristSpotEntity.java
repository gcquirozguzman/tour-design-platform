package com.tourdesign.platform.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "tourist_spot")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TouristSpotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String spotType;

    private String location;

    private Double entranceFee;

    private Integer difficultyLevel;

    private String restrictions;

}