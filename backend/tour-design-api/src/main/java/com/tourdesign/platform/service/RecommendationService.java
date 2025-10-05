package com.tourdesign.platform.service;

import com.tourdesign.platform.dto.request.RecommendationRequest;
import com.tourdesign.platform.dto.response.RecommendationResponse;

public interface RecommendationService {

    RecommendationResponse createRecommendation(RecommendationRequest request);

}
