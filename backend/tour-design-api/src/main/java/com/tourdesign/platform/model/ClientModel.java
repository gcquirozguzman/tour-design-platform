package com.tourdesign.platform.model;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientModel {
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String nationality;
    private String email;
    private String phone;
    private Integer numberOfChildren;
    private String occupation;
    private Double annualIncome;
    private String languages;
    private LocalDateTime registrationDate;
    private Boolean active;

    private List<ClientPreferenceModel> preferences;
    private List<RecommendationHistoryModel> recommendationHistory;
}
