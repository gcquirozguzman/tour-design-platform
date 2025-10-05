package com.tourdesign.platform.model;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendationHistoryModel {
    private Long id;
    private LocalDateTime recommendationDate;
    private Boolean accepted;

    private ClientModel client;
    private TravelPackageModel travelPackage;
}
