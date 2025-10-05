package com.tourdesign.platform.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientPreferenceModel {
    private Long id;
    private ClientModel client;

    private String category;
    private Integer interestLevel;
    private String details;
}
