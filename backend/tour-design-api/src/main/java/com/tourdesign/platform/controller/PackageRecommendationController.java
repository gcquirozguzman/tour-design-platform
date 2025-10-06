package com.tourdesign.platform.controller;

import com.tourdesign.platform.dto.request.RecommendationRequest;
import com.tourdesign.platform.dto.response.RecommendationResponse;
import com.tourdesign.platform.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/packages-recommendation")
public class PackageRecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/recommend")
    public ResponseEntity<RecommendationResponse> recommend(@RequestBody RecommendationRequest request) {
        RecommendationResponse response = recommendationService.createRecommendation(request);
        return ResponseEntity.ok(response);
    }

}
