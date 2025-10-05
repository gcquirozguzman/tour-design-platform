package com.tourdesign.platform.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "hotel")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private Integer stars;

    private Double pricePerNight;

    private String roomType;

    private Integer capacity;

    @Column(columnDefinition = "jsonb")
    private String services;

    private String location;

}