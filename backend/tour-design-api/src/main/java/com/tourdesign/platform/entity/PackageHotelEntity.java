package com.tourdesign.platform.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "package_hotel")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageHotelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer orderIndex;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private TravelPackageEntity travelPackage;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;
}