package com.tourdesign.platform.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class RecommendationRequest {
    private Long clientId;
    private List<Long> preferenceIds;
}