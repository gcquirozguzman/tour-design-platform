package com.tourdesign.platform.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "client_preference")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientPreferenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    private String category; // Enum possible: ADVENTURE, GASTRONOMY, CULTURE, etc.
    private Integer interestLevel;
    private String details;
}